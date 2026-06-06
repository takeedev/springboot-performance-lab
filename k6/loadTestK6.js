import http from 'k6/http';
import { sleep } from 'k6';
import { check } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 4 },
    { duration: '1m', target: 3 },
    { duration: '1m', target: 5 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<2000'], // 95% ของ request ต้องเร็วกว่า 2 วินาที
    http_req_failed: ['rate<0.05'],    // อัตราการพังต้องน้อยกว่า 5%
  },
};

const BASE_URL = 'http://host.docker.internal:8080';

export default function () {
  let req;

  if (Math.random() < 0.2) {
    req = http.get(`${BASE_URL}/api/error`, {
      tags: { type: 'error_path' },
    });
  } else {
    req = http.get(`${BASE_URL}/api/slow`, {
      tags: { type: 'slow_path' },
    });
  }

  check(req, {
    'status is 200': (r) => r.status === 200,
    'transaction time < 3s': (r) => r.timings.duration < 3000,
  });

  sleep(Math.random() * 1 + 0.5);
}
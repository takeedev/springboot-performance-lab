import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '5m', target: 100 },
    { duration: '3m', target: 30 },
    { duration: '2m', target: 50 },
  ],
};

export default function () {
  if (Math.random() < 0.2) {
    http.get('http://host.docker.internal:8080/api/error');
  } else {
    http.get('http://host.docker.internal:8080/api/slow');
  }
  sleep(1);
}
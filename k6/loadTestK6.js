import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 100 },
    { duration: '10m', target: 100 },
    { duration: '2m', target: 0 },
  ],
};

export default function () {
  http.get('http://host.docker.internal:8080/api/slow');
  sleep(1);
}
import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 10,
  duration: '1m',
};

export default function () {
  http.get('http://host.docker.internal:8080/api/slow');
  sleep(1);
}
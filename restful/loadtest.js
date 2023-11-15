//https://k6.io
//Run with command: k6 run --out csv=report.csv loadtest.js

//Bob credential
//Basic Ym9iOmJvYnB3ZA==

//Alice credential
//Basic YWxpY2U6YWxpY2Vwd2Q=


import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '7s', target: 20 },
    { duration: '1m02s', target: 10 },
    { duration: '10s', target: 0 },
  ],
};

const params = {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Basic Ym9iOmJvYnB3ZA=='
    },
};

export default function () {
  const res = http.get('http://localhost:5050/categories', params);
  check(res, { 'status : ': (r) => r.status == 200 });
  sleep(1);
}


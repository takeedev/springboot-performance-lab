# springboot-performance-lab
performance lab


```shell
docker-compose -p pref -f loadtest-compose.yml -f monitoring-compose.yml up -d
```

```shell
docker-compose -p pref -f loadtest-compose.yml -f monitoring-compose.yml down
```

```shell
docker exec -it pref-k6-1 k6 run --out influxdb=http://pref-influxdb-1:8086/k6 /scripts/loadTestK6.js
```

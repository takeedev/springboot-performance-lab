# springboot-performance-lab
performance lab


## command 

#### create container 
```shell
docker-compose -p pref -f loadtest-compose.yml -f monitoring-compose.yml up -d
```

#### down container 
```shell
docker-compose -p pref -f loadtest-compose.yml -f monitoring-compose.yml down
```

#### run scrip load test 
```shell
docker exec -it pref-k6-1 k6 run --out influxdb=http://pref-influxdb-1:8086/k6 /scripts/loadTestK6.js
```
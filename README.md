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
docker exec -it pref-k6-1 k6 run --out experimental-prometheus-rw=http://pref-prometheus-1:9090/api/v1/write /scripts/loadTestK6.js
```
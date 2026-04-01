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


## Dashboard
[Dashboard spring boot](https://grafana.com/grafana/dashboards/17053-spring-boot-statistics-endpoint-metrics/)

[Dashboard K6 Grafana](https://grafana.com/grafana/dashboards/19665-k6-prometheus/)

[Document K6 Prometheus](https://grafana.com/docs/k6/latest/results-output/real-time/prometheus-remote-write/)

## JFR (Java Flight Recorder)
```shell
jfr summary recording.jfr
```
command JCMD
command jconsole


## JMC (Java Mission Control)
#### open jfr file
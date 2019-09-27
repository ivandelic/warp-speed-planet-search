
# Warp Speed Microservices

The component implements a simple habitable-planet search across universe using Java Streams to demonstrate speed of Graal compiler in comparison with C2 from the HotSpot platform.

## Prerequisites

1. Maven 3.5 or newer
2. Java SE 8 or newer
3. Docker 17 or newer (if you want to build and run docker images)
4. Kubernetes 1.7.4 or newer cluster
5. Kubectl 1.7.4 or newer for deploying to Kubernetes

## Build

```
mvn package
```

## Start the application

```
java -jar target/warp-speed-planet-search.jar
```

## Exercise the application

```
curl -X GET http://localhost:8080/universe/traverse
{"habitablePlanets":21,"time":2261}
```

## Try health and metrics

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .

```

## Build the Docker Image

```
docker build -t warp-speed-planet-search .
```

## Start the application with Docker

```
docker run --rm -p 8080:8080 warp-speed-planet-search:latest
```

## Deploy the application to Kubernetes

```
kubectl cluster-info                         # Verify which cluster
kubectl get pods                             # Verify connectivity to cluster
kubectl create -f app.yaml               # Deploy application
kubectl get service warp-speed-planet-search  # Verify deployed service
```

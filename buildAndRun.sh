#!/bin/sh
mvn clean package && docker build -t de.ingoschindler/wild .
docker rm -f wild || true && docker run -d -p 8080:8080 -p 4848:4848 --name wild de.ingoschindler/wild 

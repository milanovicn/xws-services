#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar vozilo-service-0.0.1-SNAPSHOT.jar

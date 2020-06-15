#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar zahtev-sevices-0.0.1-SNAPSHOT.jar

#!/bin/bash

mvn clean install -DskipTests
pkill -f ".*main-service.*.jar"
java -jar target/main-service-*.jar

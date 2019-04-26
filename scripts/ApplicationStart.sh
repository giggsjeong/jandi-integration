#!/bin/bash

JAR_PATH=$(ls /srv/*/build/libs/*.jar | tail -n 1)

echo "> JAR Path: $JAR_PATH"

pkill -9 -ef java

nohup java ${JAVA_OPTS} -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &

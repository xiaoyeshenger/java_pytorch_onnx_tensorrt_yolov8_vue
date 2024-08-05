#!/bin/bash 
java -Duser.timezone=GMT+08 -Dfile.encoding=utf-8 -Dloader.path="lib/" -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/app/dump/headdump.hprof -jar /data/app/algorithmCenter-0.0.1-SNAPSHOT.jar


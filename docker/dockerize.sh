#!/usr/bin/env bash
set -ex

NAME=vite-server
TAG=${1:-test}

#./gradlew build
#mv build/libs/$NAME-*.jar docker/$NAME.jar
docker build docker/ -t "${NAME}:${TAG}"
#rm docker/$NAME.jar

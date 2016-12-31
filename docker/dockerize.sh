#!/usr/bin/env bash
set -ex

NAME=vite-server
TAG=${1:-test}

docker build docker/ -t "${NAME}:${TAG}"

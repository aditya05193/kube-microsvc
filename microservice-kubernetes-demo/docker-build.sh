#!/bin/sh
if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=aditya05193
fi;
docker build --tag=$DOCKER_ACCOUNT/microservice-catalog:v1 microservice-catalog
docker push $DOCKER_ACCOUNT/microservice-catalog

docker build --tag=$DOCKER_ACCOUNT/microservice-customer:v1 microservice-customer
docker push $DOCKER_ACCOUNT/microservice-customer

docker build --tag=$DOCKER_ACCOUNT/microservice-order:v1 microservice-order
docker push $DOCKER_ACCOUNT/microservice-order

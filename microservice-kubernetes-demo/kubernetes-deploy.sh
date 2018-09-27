#!/bin/sh
if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=aditya05193
fi;
kubectl run catalog --image=docker.io/$DOCKER_ACCOUNT/microservice-catalog:v1 --port=8080
kubectl expose deployment/catalog --type="LoadBalancer" --port 8080
kubectl run customer --image=docker.io/$DOCKER_ACCOUNT/microservice-customer:v1 --port=8080
kubectl expose deployment/customer --type="LoadBalancer" --port 8080
kubectl run order --image=docker.io/$DOCKER_ACCOUNT/microservice-order:v1 --port=8080
kubectl expose deployment/order --type="LoadBalancer" --port 8080

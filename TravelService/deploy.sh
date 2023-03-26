#!/bin/bash

echo "打包镜像"
docker build -t hash-yang/travel-service:v1.2 .

echo "给镜像打标签"
docker tag hash-yang/travel-service:v1.2 172.16.17.37:5000/hash-yang/travel-service:v1.2

echo "将镜像上传至registry"
docker push 172.16.17.37:5000/hash-yang/travel-service:v1.2


echo "部署镜像到容器"
kubectl replace -f travel-service.yaml

#!/usr/bin/env bash

echo "打包镜像"
docker build -t hash-yang/route-service:v1.0 .

echo "给镜像打标签"
docker tag hash-yang/route-service:v1.0 172.16.17.37:5000/hash-yang/route-service:v1.0

echo "将镜像上传至registry"
docker push 172.16.17.37:5000/hash-yang/route-service:v1.0

echo "部署镜像到容器"
kubectl replace -f route-service.yaml
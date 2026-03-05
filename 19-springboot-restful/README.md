```shell
docker build -f ./19-springboot-restful/Dockerfile -t springboot-restful-service:1.0.0 .

docker run -d \
  -p 8080:8080 \
  --name springboot-app \
  springboot-restful-service:1.0.0

docker tag springboot-restful-service:1.0.0 localhost:32000/springboot-restful-service:1.0.0

microk8s ctr images rm localhost:32000/springboot-restful-service:1.0.0
 
docker push localhost:32000/springboot-restful-service:1.0.0

kubectl apply -f 2-deployment.yml 

# 查看 deployment 状态
kubectl get deployment springboot-restful-service -n demo-spring

# 查看 pods 状态
kubectl get pods -n demo-spring -l app=springboot-restful-service

kubectl get pods -n demo-spring -o wide
kubectl logs springboot-restful-service-5fbd5b6688-96vh9 -n demo-spring 

# 查看 deployment 详细信息
kubectl describe deployment springboot-restful-service -n demo-spring

kubectl describe svc springboot-restful-service -n demo-spring

kubectl get svc -n demo-spring
# 端口转发
kubectl port-forward svc/springboot-restful-service 8081:81 -n demo-spring

# 查看 Gateway 是否被 Controller 接管
kubectl get gateway -n demo-spring

kubectl get httproute -n demo-spring

# 通过 deployment 删除（会删除所有关联的 Pods）
kubectl delete deployment springboot-restful-service -n demo-spring

kubectl delete httproute spring-restful-service-route -n demo-spring

kubectl delete namespace demo-spring
```


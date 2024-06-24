# Дизајн и архитектура на софтвер, 2023/24г.

![logo](https://github.com/afilipovski/DiAnS/assets/108832569/3a4f0099-ea61-4256-bbfe-2a2f485011d5.png)


## **Тема:**

* Културно-историско наследство на Македонија

## **Автори:**

* Леонтина Пауновска (211106)
* Берна Хоџин (211001)
* Дејан Станчевски (211027)
* Александар Филиповски (211047)

## **Ментори:**

* Д-р. Петре Ламески
* Д-р. Љупчо Антовски

# Континуирана интеграција и испорака, 2023/24г.

## **Тема:**

* Culturedge - веб апликација за културно наследство во Македонија

## **Автор:**

* Александар Филиповски (211047)

## **Ментори:**

* проф. д-р. Милош Јовановиќ
* проф. д-р. Панче Рибарски

## **Команди**

```
docker compose up

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
cd .kubernetes-manifests
kubectl apply -f namespace.yml -f ingress.yml -f .

kubectl delete -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
kubectl delete -f .
```
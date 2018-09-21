Microservice Kubernetes Sample
=====================

This demo uses [Kubernetes](https://kubernetes.io/) as Docker
environment. Kubernetes also support service discovery and load
balancing. 

This project creates a complete micro service demo system in Docker
containers. The services are implemented in Java using Spring and
Spring Cloud.

It uses three microservices:
- `Order` to process orders.
- `Customer` to handle customer data.
- `Catalog` to handle the items in the catalog.


Remarks on the Code
-------------------

The microservices are:

- [microservice-kubernetes-demo-catalog](microservice-kubernetes-demo/microservice-kubernetes-demo-catalog) is the application to take care of items.
- [microservice-kubernetes-demo-customer](microservice-kubernetes-demo/microservice-kubernetes-demo-customer) is responsible for customers.
- [microservice-kubernetes-demo-order](microservice-kubernetes-demo/microservice-kubernetes-demo-order) does order processing. It uses
  microservice-kubernetes-demo-catalog and microservice-kubernetes-demo-customer.

The microservices use REST to communicate to each other.
See e.g. [CatalogClient](microservice-kubernetes-demo/microservice-kubernetes-demo-order/src/main/java/com/ewolff/microservice/order/clients/CatalogClient.java) .
The hostname is configurable to allow tests with stubs.
The default is `catalog` which works with Kubernetes.
Other microservices are found using Kubernetes built-in DNS.
Kubernetes does the load balancing on the IP level.

The microservices have a Java main application in `src/test/java` to
run them stand alone. `microservice-demo-order` uses a stub for the
other services then. 

In `microservice-kubernetes-demo-customer` and `microserivce-kubernetes-demo-catalog` they are used to verify the implemented
REST services.


Kubernetes:
Kubernetes is an open-source system for automating deployment, operations, and scaling of containerized applications using cluster system. Kubernetes allow run the application anywhere, giving you the freedom to take advantage of on-premise, hybrid, or public cloud infrastructure, letting you effortlessly move workloads to where it matters to you.

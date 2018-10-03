Microservice Kubernetes Integration
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

- [microservice-catalog](microservice-kubernetes-demo/microservice-catalog) is the application to take care of items.
- [microservice-customer](microservice-kubernetes-demo/microservice-customer) is responsible for customers.
- [microservice-order](microservice-kubernetes-demo/microservice-order) does order processing. It uses
  microservice-catalog and microservice-customer.

The microservices use REST to communicate to each other.
See e.g. CatalogClient:(microservice-kubernetes-demo/microservice-order/src/main/java/com/cts/microservice/order/clients/CatalogClient.java) .
The hostname is configurable to allow tests with stubs.
The default is `catalog` which works with Kubernetes.
Other microservices are found using Kubernetes built-in DNS.
Kubernetes does the load balancing on the IP level.

Kubernetes:
Kubernetes is an open-source system for automating deployment, operations, and scaling of containerized applications using cluster system. Kubernetes allow run the application anywhere, giving you the freedom to take advantage of on-premise, hybrid, or public cloud infrastructure, letting you effortlessly move workloads to where it matters to you.

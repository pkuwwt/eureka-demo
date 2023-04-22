
# Eureka Demo

A docker-compose demo for different clients. Supported clients include

  * spring boot
  * node
  * golang
  * python

Except spring-boot, all other clients are implemented by a sidecar, which support lauching multiple sidecar instances.


## Usage

Install docker and docker-compose, and then

```bash
make up
```


[![Build](https://github.com/laufermi/spring-in-action/actions/workflows/gradle.yml/badge.svg)](https://github.com/laufermi/spring-in-action/actions/workflows/gradle.yml?query=workflow%3A%22build%22+branch%3Amain)
[![codecov](https://codecov.io/gh/laufermi/spring-in-action/branch/main/graph/badge.svg?token=YB1G4RWQ2S)](https://codecov.io/gh/laufermi/spring-in-action)

# Getting Started

### Guide

* [Swagger API](http://localhost:8080/api/swagger-ui/)

### Started

#### Tech Stack
`Jdk11`, `SpringBoot`, `H2`, `Groovy`

#### Run in local environment

```bash
./gradlew clean build

./gradlew bootRun
```

#### Run in docker
```shell
docker build . --tag backend
```

#### Run test
```shell
./gradlew clean test
```
Report: `./build/reports/jacoco/html/`

#### Run checkstyle
```shell
./gradlew checkstyleMain
```
Report: `./build/reports/checkstyle/`

#### Run pmd
```shell
./gradlew pmdMain
```
Report: `./build/reports/pmd/`


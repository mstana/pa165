# Booking manager

Project for pa165.

## Requirements
* __Server__ - Apache Tomcat
* __Framework__ - Spring
* __Database__ - JavaDB (localhost:1527/pa165,user: pa165, pass: pa165)
* __Web app context__ - localhost:8080/pa165
* __REST context__ - localhost:8080/pa165/rest/

## Modules
* __pa165_booking_manager_api__ - API for whole app
* __pa165_booking_manager_service__ - Data and service layer
* __pa165_booking_manager_web__ - Web and REST server application
* __pa165_booking_manager_desktop__ - Desktop application

## Installation
Use maven 3.x, for running desktop and web application see other README.MD

```
mvn clean package install
```

## Repository
```
git clone https://github.com/mstana/pa165.git
```

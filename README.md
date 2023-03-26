# Boxinator
## Description
Boxinator is a service that provides shipping of mystery boxes. 
This repository contains the backend functionality of the system. 
The front end part can be found in this [repository](https://github.com/Milovane/Boxinator).

## User roles

Boxinator has the following types of roles:
* Guests 
* Registered users
* Admin users

Boxinator allows users to create an account, log in, create shipments and view their current shipments. 
Guest users can enter an email when creating shipments and will get a confirmation email when their shipment has been created. 
Registered users will be able to log in and view their shipments. 

Admin users have access to extra pages for managing shipments and their status. 
Admin users also have the ability to update and add new countries with a multiplier, which determines the price of the shipment process. 

## Authentication and authorization

The project is built with spring boot and is secured with spring security, JSON web tokens are used to authenticate and check authorization on http requests. 

When opening the web page, the users will be able to create an account via keycloak if they have not already created one. 
After a successful account creation, the user will need to register via boxinator to create an account at boxinator as well. 
After the second registration, the keycloak identity will be linked to a user account at boxinator with the help of the subject id recieved from keycloak. 

## Technologies
* Java 17
* Spring Web 
* Spring data JPA
* Spring security
* Postgres
* JWT
* Keycloak

## Contributors
* Ali Habesh
* Milovan Glisovic
* Seth Öberg
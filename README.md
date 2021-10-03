# Mobile Applications Service

A web service for storing mobile applications.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites - Required software
* Java 11

Clone this repository to your local machine using:

```shell
git clone https://github.com/V-Kaidash/MobileApplicationsService.git
```

## Endpoints

* /api/applications/{applicationId} - Create a new application
* /api/applications - Retrieve all applications
* /api/applications/{applicationId} - Get application by id
* /api/applications/{applicationId} - Delete application identified by id
* /api/applications/compare - Compare 2 or more apps identified by ids and return which app has the greatest version (e.g. 2.2.0 is greater than 1.6.8)
  Ids of 2 or more apps to compare has to be provided in request. If there is less than 2 ids in request, or id is invalid - error response will be returned.
* /api/applications/content-rate-stats - Return count of applications with specified content rates

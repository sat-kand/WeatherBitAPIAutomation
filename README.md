# API Test Automation Repository

This repository contains a Java-based test automation framework for API testing, utilizing Rest Assured, TestNG, and Maven.
This communicates to open Weather Bit API which provides current Weather Data for multiple places in the world.

## Prerequisites

Before running the tests, ensure that the following prerequisites are met:

- Java Development Kit (JDK) is installed on your machine.
- Maven is installed on your machine.
- An integrated development environment (IDE) such as IntelliJ or Eclipse is installed.

## Setup & Running Tests

1. Using terminal or cmd clone this repository to your local machine using the following command:

```
git clone https://github.com/sat-kand/WeatherBitAPIAutomation.git
```

2. Navigate to the root folder of cloned repo in terminal and execute the following command to build and run the tests

```
mvn clean install
```

## Reports

Test reports are generated automatically after each test run. The terminal will show an info like this:
```shell
Results :

Failed tests: validateResultsCountTest(com.qa.test.WeatherAPIPostCodeTest): API response showing incorrect count expected [24] but found [1]
  validateInvalidPostCodeTest(com.qa.test.WeatherAPIPostCodeTest): API response showing some data for invalid post code expected [0] but found [1]

Tests run: 11, Failures: 2, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE

```
The detailed reports can be found in the `/target/surefire-reports` directory. Open the `index.html` file in a web browser to view the detailed test results.

## Tools and Technology used:

- Java 8
- Rest assured library (3.0.6)
- TestNG (6.14.3),
- Maven (4.0.0)
- Log4j API(1.2.17)
- API used: https://www.weatherbit.io/api/swaggerui/weather-api-v2#/


## Test Structure

The tests are organized using the TestNG framework. The test classes can be found in the `src/test/java/com.qa.test` directory. You can create new test classes or modify existing ones according to your requirements.


## Continuous Integration

This repository is configured to integrate with popular continuous integration (CI) systems such as Jenkins or Travis CI. You can set up a CI pipeline to automatically trigger tests on every commit or periodically based on your requirements.

## Known Bugs

# Exercise

![](https://img.shields.io/badge/build-success-brightgreen.svg)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

QA Test Assessment

## Stacks

- [Java 17](http://www.oracle.com/technetwork/java/javase/downloads)
- [Maven](https://maven.apache.org)
- [Cucumber](https://cucumber.io/)
- [Selenium WebDriver](https://www.selenium.dev/)
- [WebDriverManager - Chrome Headless](https://bonigarcia.dev/webdrivermanager/)
- [JUnit 4](https://cucumber.io/docs/cucumber/api/?lang=java#junit)

## How to run the project

1. Prerequisite: Have maven installed on you computer. In case you do not, click **[here](https://mkyong.com/maven/how-to-install-maven-in-windows/)** to install it on Windows.
2. Execute the CLI: mvn test -Dcucumber.filter.tags="@order"
3. Screenshots are to be saved in the folder screenshot which is the project's root path. Example: ~\paymentFlowValidation\screenshot

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.

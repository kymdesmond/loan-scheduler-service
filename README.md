# Loan Calculator
Simple loan calculator that computes loan installments based of amount, frequency, period and start date.
## Tools and Requirements
This service has been developed using the following tools
1. Java 17
2. Spring boot 3.1.0
3. H2 DB
## Service Layering
This service is structured as below:
-  **config** - Data Access and Security Configurations
-  **controller** - Web APIs
-  **dto** - Data presentation models
-  **entity** - Data representation models
-  **model** 
-  **repository** - Data Access
-  **service** - Business logic layer
-  **util** - Utility classes

An additional sql files to import required schema and data have been added to classpath for quick start and testing. This files will be loaded upon application start and data imported for testing.
- schema.sql
- data.sql

## Running Application
`mvn spring-boot:run`
### Testing
`mvn clean test`
### Building
`mvn clean install`

A distributable jar file is created under */targets* folder

### Sample UseCases
1. Calculate loan installments for a monthly loan of Ksh. 100000 for a period of 6 Months, Reducing Balance Rate
```http request
curl -X "POST" "http://127.0.0.1:8090/calculate" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "amount": "100000",
  "repaymentFrequency": "monthly",
  "startDate": "2023-06-06",
  "period": "2",
  "interestType": "REDUCING_BALANCE"
}'
```
2. Calculate loan installments for a monthly loan of Ksh. 100000 for a period of 6 Months, Fixed Rate
```http request
curl -X "POST" "http://127.0.0.1:8090/calculate" \
     -H 'Content-Type: application/json; charset=utf-8' \
     -d $'{
  "amount": "100000",
  "repaymentFrequency": "monthly",
  "startDate": "2023-06-06",
  "period": "2",
  "interestType": "FIXED_RATE"
}'
```

ACCEPTED VALUES:
1. repaymentFrequency - daily,weekly,monthly,quarterly, bi-annually, annually
2. interestType - REDUCING_BALANCE,FIXED_RATE
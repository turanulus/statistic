# Statistic Summary Application

## 1. Informations
- Data is being recorded into H2 mock database. So, it is volatile.
- Databse url=jdbc:h2:mem:testdb
- DB Username=sa
- DB Password=password
- Authentication Username=turanulus
- Authentication Password=password

## 2. How to Run the Project
- Navigate to the root folder of the project
  - cd ~/statistics 
- Run docker compose
  - docker compose up

## 3. How to Run Integration Tests
- Navigate to the root folder of the project
  - cd ~/statistics
- Run integration tests
  - mvn integration-test

## 4. Endpoints And Sample Payloads via Curl

### 4.1. Authentication
Token can be retrieved from this API. It can be used within other APIs as Authorization header.
```
curl --location --request POST 'localhost:8082/authenticate' \
--header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931' \
--data-raw '{
    "username": "turanulus",
    "password": "password"
}'
```
### 4.2. Employee Creation
```
curl --location --request POST 'localhost:8082/api/v1/employees' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dXJhbnVsdXMiLCJleHAiOjE2NjAyMzMwNTksImlhdCI6MTY2MDIxNTA1OX0.ZER6py4I4W-8fPzcjndXgXPtUqlWij-3Z4PP_xBAeU5E2Q57pVCaTjL4u7WlDOslTYupH_0lQ3WlJwsMu210cw' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931' \
--data-raw '{
    "name": "Ulus",
    "salary": 1000,
    "currency": "INR",
    "department": "ENGINEERING",
    "subDepartment": "LOAN"
}'
```

### 4.3. Employee Deletation
```
curl --location --request DELETE 'localhost:8082/api/v1/employees/10' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dXJhbnVsdXMiLCJleHAiOjE2NjAyMzE5MzAsImlhdCI6MTY2MDIxMzkzMH0.fUjAM4miKsD-5YtZsbyYcG05um26qHxG-uRBPM1P2Ui7yJ-0eZeg3986T7kB3DSmddUGVplC0kXX1_BfMDUm4g' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931' \
--data-raw ''
```

### 4.4. Summary Sum for Entire Dataset
```
curl --location --request GET 'localhost:8082/api/v1/employees/summary-statistics' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dXJhbnVsdXMiLCJleHAiOjE2NjAyMzMwNTksImlhdCI6MTY2MDIxNTA1OX0.ZER6py4I4W-8fPzcjndXgXPtUqlWij-3Z4PP_xBAeU5E2Q57pVCaTjL4u7WlDOslTYupH_0lQ3WlJwsMu210cw' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931' \
--data-raw '{
    "policyId": 1,
    "requestDate": "03.08.2022"
}'
```

### 4.5. Summary Sum for On-Contracts
```
curl --location --request GET 'localhost:8082/api/v1/employees/summary-statistics/on-contract' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dXJhbnVsdXMiLCJleHAiOjE2NjAyMzMwNTksImlhdCI6MTY2MDIxNTA1OX0.ZER6py4I4W-8fPzcjndXgXPtUqlWij-3Z4PP_xBAeU5E2Q57pVCaTjL4u7WlDOslTYupH_0lQ3WlJwsMu210cw' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931'
```

### 4.6. Summary Sum for Departments
```
curl --location --request GET 'localhost:8082/api/v1/employees/summary-statistics/departments' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dXJhbnVsdXMiLCJleHAiOjE2NjAyMzMwNTksImlhdCI6MTY2MDIxNTA1OX0.ZER6py4I4W-8fPzcjndXgXPtUqlWij-3Z4PP_xBAeU5E2Q57pVCaTjL4u7WlDOslTYupH_0lQ3WlJwsMu210cw' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931'
```

### 4.7. Summary Sum for Departments and Subdepartments
```
curl --location --request GET 'localhost:8082/api/v1/employees/summary-statistics/department-and-subdepartments' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0dXJhbnVsdXMiLCJleHAiOjE2NjAyMzMwNTksImlhdCI6MTY2MDIxNTA1OX0.ZER6py4I4W-8fPzcjndXgXPtUqlWij-3Z4PP_xBAeU5E2Q57pVCaTjL4u7WlDOslTYupH_0lQ3WlJwsMu210cw' \
--header 'Cookie: JSESSIONID=E6B2AD02D0AB5E6E29EA90A683C46931'
```
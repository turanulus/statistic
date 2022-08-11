package com.clipboard.demo.controller;

import com.clipboard.demo.config.TestSecurityConfig;
import com.clipboard.demo.dto.*;
import com.clipboard.demo.model.Currency;
import com.clipboard.demo.model.Department;
import com.clipboard.demo.model.SubDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("test")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = TestSecurityConfig.class)
public class EmployeeControllerIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void testSummaryStatistics_successful() {
    ResponseEntity<SSResponse> response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/v1/employees/summary-statistics", SSResponse.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  void testSummaryStatisticsForDepartment_successful() {
    ResponseEntity<SSForDepartmentResponse> response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/v1/employees/summary-statistics/departments",
            SSForDepartmentResponse.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  void testSummaryStatisticsForDepartmentAndSubDepartment_successful() {
    ResponseEntity<SSForDepartmentAndSubDepartmentResponse> response =
        this.restTemplate.getForEntity(
            "http://localhost:"
                + port
                + "/api/v1/employees/summary-statistics/department-and-subdepartments",
            SSForDepartmentAndSubDepartmentResponse.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  void testSummaryStatisticsForOnContract() {
    ResponseEntity<SSResponse> response =
        this.restTemplate.getForEntity(
            "http://localhost:" + port + "/api/v1/employees/summary-statistics/on-contract",
            SSResponse.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  void testCreateEmployee_successful() {
    EmployeeReqBody employeeReqBody =
        EmployeeReqBody.builder()
            .name("Turan")
            .salary(BigDecimal.valueOf(10000))
            .currency(Currency.USD.toString())
            .onContract(true)
            .department(Department.ENGINEERING.toString())
            .subDepartment(SubDepartment.AGRICULTURE.toString())
            .build();

    ResponseEntity<EmployeeCreateResponse> response =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/v1/employees",
            employeeReqBody,
            EmployeeCreateResponse.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  void testDeleteEmployee_successful() {

    EmployeeReqBody employeeReqBody =
        EmployeeReqBody.builder()
            .name("Turan")
            .salary(BigDecimal.valueOf(10000))
            .currency(Currency.USD.toString())
            .onContract(true)
            .department(Department.ENGINEERING.toString())
            .subDepartment(SubDepartment.AGRICULTURE.toString())
            .build();

    ResponseEntity<EmployeeCreateResponse> createResponse =
        this.restTemplate.postForEntity(
            "http://localhost:" + port + "/api/v1/employees",
            employeeReqBody,
            EmployeeCreateResponse.class);

    ResponseEntity<Void> response =
        this.restTemplate.exchange(
            "http://localhost:"
                + port
                + "/api/v1/employees/"
                + createResponse.getBody().getEmployeeId(),
            HttpMethod.DELETE,
            null,
            Void.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
  }
}

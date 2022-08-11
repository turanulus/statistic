package com.clipboard.demo.controller;

import com.clipboard.demo.dto.*;
import com.clipboard.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<EmployeeCreateResponse> createEmployee(
      @Valid @RequestBody EmployeeReqBody employeeBody) {
    EmployeeCreateResponse employeeCreateResponse = employeeService.create(employeeBody);
    return ResponseEntity.ok(employeeCreateResponse);
  }

  @DeleteMapping(("/{employeeId}"))
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
    employeeService.delete(employeeId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/summary-statistics")
  public ResponseEntity<SSResponse> getSSForEntireDataSet() {
    return ResponseEntity.ok(employeeService.getSSForEntireDataSet());
  }

  @GetMapping("/summary-statistics/on-contract")
  public ResponseEntity<SSResponse> getSSForOnContract() {
    return ResponseEntity.ok(employeeService.getSSForOnContract());
  }

  @GetMapping("/summary-statistics/departments")
  public ResponseEntity<SSForDepartmentResponse> getSSForDepartment() {
    return ResponseEntity.ok(employeeService.getSSForDepartment());
  }

  @GetMapping("/summary-statistics/department-and-subdepartments")
  public ResponseEntity<SSForDepartmentAndSubDepartmentResponse>
      getSSForDepartmentAndSubDepartment() {
    return ResponseEntity.ok(employeeService.getSSForDepartmentAndSubDepartment());
  }
}

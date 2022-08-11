package com.clipboard.demo.service;

import com.clipboard.demo.dto.*;
import com.clipboard.demo.exception.EmployeeNotFoundException;
import com.clipboard.demo.model.Currency;
import com.clipboard.demo.model.Department;
import com.clipboard.demo.model.Employee;
import com.clipboard.demo.model.SubDepartment;
import com.clipboard.demo.repository.EmployeeRepository;
import com.clipboard.demo.repository.result.SSForDepartmentAndSubDepartmentResult;
import com.clipboard.demo.repository.result.SSForDepartmentResult;
import com.clipboard.demo.repository.result.SSResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeCreateResponse create(EmployeeReqBody employeeBody) {
    Employee employee = new Employee();
    employee.setName(employeeBody.getName());
    employee.setSalary(employeeBody.getSalary());
    employee.setCurrency(Currency.valueOf(employeeBody.getCurrency()));
    employee.setOnContract(employeeBody.isOnContract());
    employee.setDepartment(Department.valueOf(employeeBody.getDepartment()));
    employee.setSubDepartment(SubDepartment.valueOf(employeeBody.getSubDepartment()));

    Employee savedEmployee = employeeRepository.save(employee);
    return EmployeeCreateResponse.builder().employeeId(savedEmployee.getId()).build();
  }

  public void delete(Long employeeId) {
    try {
      employeeRepository.deleteById(employeeId);
    } catch (EmptyResultDataAccessException ex) {
      throw new EmployeeNotFoundException("Employee not found. Employee Id: " + employeeId);
    }
  }

  public SSResponse getSSForEntireDataSet() {
    SSResult ssResult = employeeRepository.getSSForEntireDataset();

    return SSResponse.builder()
        .mean(ssResult.getMean().intValue())
        .max(ssResult.getMax().intValue())
        .min(ssResult.getMin().intValue())
        .build();
  }

  public SSResponse getSSForOnContract() {
    SSResult ssResult = employeeRepository.getSSForOnContract();

    return SSResponse.builder()
        .mean(ssResult.getMean().intValue())
        .max(ssResult.getMax().intValue())
        .min(ssResult.getMin().intValue())
        .build();
  }

  public SSForDepartmentResponse getSSForDepartment() {
    List<SSForDepartmentResult> ssForDepartmentResultList = employeeRepository.getSSForDepartment();

    List<SSForDepartmentDTO> ssForDepartmentDTOList =
        ssForDepartmentResultList.stream()
            .map(
                ssForDepartmentResult ->
                    SSForDepartmentDTO.builder()
                        .department(ssForDepartmentResult.getDepartment())
                        .mean(ssForDepartmentResult.getMean().intValue())
                        .max(ssForDepartmentResult.getMax().intValue())
                        .min(ssForDepartmentResult.getMin().intValue())
                        .build())
            .collect(Collectors.toList());

    return SSForDepartmentResponse.builder().ssForDepartmentDTOs(ssForDepartmentDTOList).build();
  }

  public SSForDepartmentAndSubDepartmentResponse getSSForDepartmentAndSubDepartment() {
    List<SSForDepartmentAndSubDepartmentResult> ssForDepartmentAndSubDepartmentResultList =
        employeeRepository.getSSForDepartmentAndSubDepartment();

    List<SSForDepartmentAndSubDepartmentDTO> ssForDepartmentAndSubDepartmentDTOList =
        ssForDepartmentAndSubDepartmentResultList.stream()
            .map(
                ssForDepartmentAndSubDepartmentResult ->
                    SSForDepartmentAndSubDepartmentDTO.builder()
                        .department(ssForDepartmentAndSubDepartmentResult.getDepartment())
                        .subDepartment(ssForDepartmentAndSubDepartmentResult.getSubDepartment())
                        .mean(ssForDepartmentAndSubDepartmentResult.getMean().intValue())
                        .max(ssForDepartmentAndSubDepartmentResult.getMax().intValue())
                        .min(ssForDepartmentAndSubDepartmentResult.getMin().intValue())
                        .build())
            .collect(Collectors.toList());

    return SSForDepartmentAndSubDepartmentResponse.builder()
        .ssForDepartmentAndSubDepartmentDTOs(ssForDepartmentAndSubDepartmentDTOList)
        .build();
  }
}

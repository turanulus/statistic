package com.clipboard.demo.repository;

import com.clipboard.demo.model.Employee;
import com.clipboard.demo.repository.result.SSForDepartmentAndSubDepartmentResult;
import com.clipboard.demo.repository.result.SSForDepartmentResult;
import com.clipboard.demo.repository.result.SSResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  @Query("SELECT AVG(e.salary) AS mean, MAX(e.salary) AS max, MIN(e.salary) AS min FROM Employee e")
  SSResult getSSForEntireDataset();

  @Query(
      "SELECT AVG(e.salary) AS mean, MAX(e.salary) AS max, MIN(e.salary) AS min FROM Employee e WHERE e.onContract=true")
  SSResult getSSForOnContract();

  @Query(
      "SELECT e.department AS department, AVG(e.salary) AS mean, MAX(e.salary) AS max, "
          + "MIN(e.salary) AS min FROM Employee e GROUP BY e.department")
  List<SSForDepartmentResult> getSSForDepartment();

  @Query(
      "SELECT e.department AS department, e.subDepartment AS subDepartment, AVG(e.salary) AS mean, MAX(e.salary) AS max, "
          + "MIN(e.salary) AS min FROM Employee e GROUP BY e.department, e.subDepartment")
  List<SSForDepartmentAndSubDepartmentResult> getSSForDepartmentAndSubDepartment();
}

package com.clipboard.demo.repository.result;

import java.math.BigDecimal;

public interface SSForDepartmentAndSubDepartmentResult {
  String getDepartment();

  String getSubDepartment();

  BigDecimal getMean();

  BigDecimal getMax();

  BigDecimal getMin();
}

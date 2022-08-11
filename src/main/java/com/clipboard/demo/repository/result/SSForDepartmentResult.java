package com.clipboard.demo.repository.result;

import java.math.BigDecimal;

public interface SSForDepartmentResult {
  String getDepartment();

  BigDecimal getMean();

  BigDecimal getMax();

  BigDecimal getMin();
}

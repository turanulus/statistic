package com.clipboard.demo.dto;

import com.clipboard.demo.model.Currency;
import com.clipboard.demo.model.Department;
import com.clipboard.demo.model.SubDepartment;
import com.clipboard.demo.validator.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Builder
@Data
public class EmployeeReqBody {
  @NotEmpty(message = "Name cannot be empty")
  @Size(max = 80, message = "Name length cannot be bigger than 80")
  @JsonProperty("name")
  private String name;

  @NotNull(message = "Salary must not be null")
  @JsonProperty("salary")
  private BigDecimal salary;

  @ValueOfEnum(enumClass = Currency.class)
  @JsonProperty("currency")
  private String currency;

  @ValueOfEnum(enumClass = Department.class)
  @JsonProperty("department")
  private String department;

  @JsonProperty("onContract")
  private boolean onContract;

  @ValueOfEnum(enumClass = SubDepartment.class)
  @Column(name = "subDepartment")
  private String subDepartment;
}

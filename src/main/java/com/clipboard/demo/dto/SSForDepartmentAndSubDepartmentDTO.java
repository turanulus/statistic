package com.clipboard.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SSForDepartmentAndSubDepartmentDTO {
  @JsonProperty("department")
  private String department;

  @JsonProperty("sub_department")
  private String subDepartment;

  @JsonProperty("mean")
  private int mean;

  @JsonProperty("min")
  private int min;

  @JsonProperty("max")
  private int max;
}

package com.clipboard.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateResponse {
  @JsonProperty("employeeId")
  private Long employeeId;
}

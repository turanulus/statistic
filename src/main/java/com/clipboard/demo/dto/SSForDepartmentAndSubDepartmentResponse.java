package com.clipboard.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SSForDepartmentAndSubDepartmentResponse {
  @JsonProperty("ssForDepartmentAndSubDepartments")
  private List<SSForDepartmentAndSubDepartmentDTO> ssForDepartmentAndSubDepartmentDTOs;
}

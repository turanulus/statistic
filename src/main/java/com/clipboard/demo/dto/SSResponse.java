package com.clipboard.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SSResponse {
  @JsonProperty("mean")
  private int mean;

  @JsonProperty("min")
  private int min;

  @JsonProperty("max")
  private int max;
}

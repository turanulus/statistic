package com.clipboard.demo.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiError {
  private String message;
}

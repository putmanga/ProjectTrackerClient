package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private Integer errorStatus;
//    private ErrorResponse errorResponse;
    private Map<String, List<String>> errorResponse;
    private String message;
}

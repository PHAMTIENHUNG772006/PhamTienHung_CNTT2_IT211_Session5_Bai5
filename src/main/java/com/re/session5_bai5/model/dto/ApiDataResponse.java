package com.re.session5_bai5.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDataResponse <T>{
    private boolean success;
    private T data;
    private String message;
    private HttpStatus status;
}

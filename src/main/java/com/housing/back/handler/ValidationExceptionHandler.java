package com.housing.back.handler;

import com.housing.back.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class}) //{}쳐서 배열로 전달
    public ResponseEntity<ResponseDto> validationExceptionHandler(Exception exception){
        return ResponseDto.validationFail();
    }
}

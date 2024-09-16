package com.housing.back.service;

import com.housing.back.dto.request.auth.IdCheckRequestDto;
import com.housing.back.dto.response.auth.IdCheckResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
}

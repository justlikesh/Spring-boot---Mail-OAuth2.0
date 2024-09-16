package com.housing.back.controller;

import com.housing.back.dto.request.auth.IdCheckRequestDto;
import com.housing.back.dto.response.auth.IdCheckResponseDto;
import com.housing.back.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이녀석을 RequestBody 를 반환해주는 컨트롤러로 인식 그냥 해주면 html 을 반환하는 컨트롤러로 인식
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;  //Ioc 를 통해서 DI 해오고

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }
}

package com.housing.back.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {

    @NotBlank      // 빈 문자열이나 null 값을 허용하지 않도록 하는 역할
    private String id;

}

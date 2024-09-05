package com.housing.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="user") // 어떤 테이블과 매핑시키겠다
@Entity(name="user")
public class UserEntity {
    @Id //userId 키가 프라이머리 키다
    @Column(name="user_id")
    private String userId;
    private String password;
    private String email;
    private String type;
    private String role;

}

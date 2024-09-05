package com.housing.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name="certification")
@Table(name="certification")
public class CertificationEntity {
    @Id
    private String userId;
    private String email;
    private String certificationNumber;
}

package com.housing.back.repository;

import com.housing.back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 자바빈으로 등록 하는 역할은 같은데 명시하기 위함
public interface UserRepository extends JpaRepository<UserEntity, String> {  //제네릭 타입으로 지정

}

package com.example.demo.repository;

import com.example.demo.entity.UserFieldConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFieldConfigRepository extends JpaRepository<UserFieldConfig, String> {

    long countByField(String field);
}

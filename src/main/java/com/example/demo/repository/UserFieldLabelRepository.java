package com.example.demo.repository;

import com.example.demo.entity.UserFieldLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFieldLabelRepository extends JpaRepository<UserFieldLabel, UserFieldLabel.IdKey> {
}

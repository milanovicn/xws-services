package com.example.adninservice.repository;

import com.example.adninservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository< User,Long> {
}

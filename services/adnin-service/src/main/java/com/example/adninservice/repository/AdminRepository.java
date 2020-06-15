package com.example.adninservice.repository;

import com.example.adninservice.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository< Admin,Long> {
    Admin findByEmail(String email);
}

package com.example.adninservice.service;

import com.example.adninservice.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAll();
    Admin findById(Long id);
    Admin findByEmail(String email);
}

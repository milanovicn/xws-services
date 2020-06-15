package com.example.adninservice.service;

import com.example.adninservice.model.Admin;
import com.example.adninservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements  AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        for(Admin a : adminRepository.findAll()){
            admins.add(a);
        }
        return admins;
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseGet(null);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}

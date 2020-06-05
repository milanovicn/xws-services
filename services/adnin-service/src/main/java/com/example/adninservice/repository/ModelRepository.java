package com.example.adninservice.repository;


import com.example.adninservice.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}

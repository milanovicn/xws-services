package com.example.adninservice.repository;


import com.example.adninservice.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {
    List<Cenovnik> findByAutor(Long id);
    Cenovnik findByNaziv(String naziv);
}

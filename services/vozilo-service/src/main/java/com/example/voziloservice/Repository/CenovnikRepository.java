package com.example.voziloservice.Repository;

import com.example.voziloservice.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {
}

package com.example.voziloservice.Repository;

import com.example.voziloservice.model.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OcenaRepository extends JpaRepository<Ocena,Long> {

    Collection<Ocena> findByIdVozila(Long id);
}

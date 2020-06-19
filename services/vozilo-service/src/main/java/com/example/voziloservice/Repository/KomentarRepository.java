package com.example.voziloservice.Repository;

import com.example.voziloservice.model.Komentar;
import com.example.voziloservice.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface KomentarRepository extends JpaRepository<Komentar,Long> {
    Collection<Komentar> findByIdVozila(Long id);

}

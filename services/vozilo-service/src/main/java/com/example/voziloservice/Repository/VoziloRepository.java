package com.example.voziloservice.Repository;

import com.example.voziloservice.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoziloRepository extends JpaRepository<Vozilo,Long> {
    List<Vozilo> findByIznajmljivacId(Long id);
    List<Vozilo> findByIznajmljivacMail(String mail);

    Vozilo findByPomId(Long id);
}

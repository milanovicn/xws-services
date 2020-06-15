package com.example.zahtevsevices.repository;

import com.example.zahtevsevices.model.Zahtev;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZahtevRepository extends JpaRepository<Zahtev,Long> {
    List<Zahtev> findByPodnosilac(Long id);
    List<Zahtev> findByIzdavac(Long id);
    List<Zahtev> findByIdVozila(Long id);
}

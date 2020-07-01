package com.example.voziloservice.Repository;

import com.example.voziloservice.model.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZahtevRepository extends JpaRepository<Zahtev,Long> {

    List<Zahtev> findByPodnosilac(Long id);

    List<Zahtev> findByIzdavac(Long id);

    List<Zahtev> findByIzdavacMail(String mail);

   // List<Zahtev> findByIdVozila(Long id);

}
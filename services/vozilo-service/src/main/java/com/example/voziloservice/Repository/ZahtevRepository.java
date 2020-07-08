package com.example.voziloservice.Repository;

import com.example.voziloservice.model.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZahtevRepository extends JpaRepository<Zahtev,Long> {

    List<Zahtev> findByPodnosilac(Long id);

    List<Zahtev> findByIzdavac(Long id);

    @Query("SELECT z FROM Zahtev z WHERE z.izdavacMail = :mail")
    List<Zahtev> findByIzdavacMail(@Param("mail") String mail);

   // List<Zahtev> findByIdVozila(Long id);

}

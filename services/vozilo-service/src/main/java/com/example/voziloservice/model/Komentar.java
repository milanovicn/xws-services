package com.example.voziloservice.model;

import com.example.voziloservice.model.StanjeKomentara;
import javax.persistence.*;

@Entity
public class Komentar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;

    @Column(name = "Komentar", nullable = false)
    private String komentar;

    @Column(name = "Stanje", nullable = false)
    private StanjeKomentara stanje;




    public Komentar() {
    }

    public Komentar(Long idVozila, String komentar) {
        this.idVozila = idVozila;
        this.komentar = komentar;
        this.stanje = StanjeKomentara.OBJAVLJEN;
    }

    public StanjeKomentara getStanje() {
        return stanje;
    }

    public void setStanje(StanjeKomentara stanje) {
        this.stanje = stanje;
    }

    public Long getId() {
        return id;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

}
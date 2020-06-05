package com.example.adninservice.model;

import javax.persistence.*;

@Entity
public class Marka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Naziv", nullable = false)
    private String naziv;

    public Marka(String naziv) {
        this.naziv = naziv;
    }

    public Marka() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}

package com.example.adninservice.model;

import javax.persistence.*;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Naziv", nullable = false)
    private String naziv;

    @Column(name = "Marka", nullable = false)
    private String marka;

    public Model() {
    }

    public Model(String naziv, String marka) {
        this.naziv = naziv;
        this.marka = marka;
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

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }
}

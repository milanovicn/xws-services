package com.example.voziloservice.model;

import javax.persistence.*;

@Entity
public class Cenovnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Naziv", nullable = false)
    private String naziv;

    @Column(name = "CenaDan", nullable = false)
    private double cenaDan;

    @Column(name = "CenaPrekoraceniKm", nullable = false)
    private double cenaPrekoraceniKm;

    @Column(name = "CenaCDW", nullable = false)
    private double cenaCDW;

    @Column(name = "PopustProcenat", nullable = false)
    private int popustProcenat;

    @Column(name = "Autor", nullable = false)
    private String autor;

    public Cenovnik(String naziv, double cenaDan, double cenaPrekoraceniKm, double cenaCDW, int popustProcenat, String autor) {
        this.naziv = naziv;
        this.cenaDan = cenaDan;
        this.cenaPrekoraceniKm = cenaPrekoraceniKm;
        this.cenaCDW = cenaCDW;
        this.popustProcenat = popustProcenat;
        this.autor = autor;
    }

    public Cenovnik() {
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

    public double getCenaDan() {
        return cenaDan;
    }

    public void setCenaDan(double cenaDan) {
        this.cenaDan = cenaDan;
    }

    public double getCenaPrekoraceniKm() {
        return cenaPrekoraceniKm;
    }

    public void setCenaPrekoraceniKm(double cenaPrekoraceniKm) {
        this.cenaPrekoraceniKm = cenaPrekoraceniKm;
    }

    public double getCenaCDW() {
        return cenaCDW;
    }

    public void setCenaCDW(double cenaCDW) {
        this.cenaCDW = cenaCDW;
    }

    public int getPopustProcenat() {
        return popustProcenat;
    }

    public void setPopustProcenat(int popustProcenat) {
        this.popustProcenat = popustProcenat;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}

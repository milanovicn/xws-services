package com.example.voziloservice.model;


import com.example.voziloservice.model.Stanje;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zahtev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "VOZILO_ZAHTEV",
            joinColumns = @JoinColumn(name = "zahtev_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vozilo_id", referencedColumnName = "id"))
    private Set<Vozilo> vozila = new HashSet<Vozilo>();

    @Column(name = "Stanje", nullable = false)
    private Stanje stanje;

    @Column(name = "DatumOd", nullable = false)
    private LocalDateTime  datumOd;

    @Column(name = "DatumDo", nullable = false)
    private LocalDateTime datumDo;

    @Column(name = "VremeOdobrenja", nullable = true)
    private LocalDateTime vremeOdobrenja;

    @Column(name = "VremeKreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "Podnosilac", nullable = false)
    private Long podnosilac;

    @Column(name = "Izdavac", nullable = false)
    private Long izdavac;

    @Column(name = "IzdavacMail", nullable = false)
    private String izdavacMail;

    public Zahtev() {
    }

    public Zahtev(Set<Vozilo> vozila, LocalDateTime datumOd, LocalDateTime datumDo, Long podnosilac, Long izdavac,String izdavacMail) {
        this.vozila = vozila;
        this.stanje = Stanje.PENDING;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.podnosilac = podnosilac;
        this.izdavac= izdavac;
        this.izdavacMail=izdavacMail;
       // this.vremeKreiranja=vremeKreiranja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stanje getStanje() {
        return stanje;
    }

    public void setStanje(Stanje stanje) {
        this.stanje = stanje;
    }

    public LocalDateTime getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDateTime datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDateTime getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDateTime datumDo) {
        this.datumDo = datumDo;
    }

    public Long getPodnosilac() {
        return podnosilac;
    }

    public void setPodnosilac(Long podnosilac) {
        this.podnosilac = podnosilac;
    }

    public Long getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(Long izdavac) {
        this.izdavac = izdavac;
    }

    public LocalDateTime getVremeOdobrenja() {
        return vremeOdobrenja;
    }

    public void setVremeOdobrenja(LocalDateTime vremeOdobrenja) {
        this.vremeOdobrenja = vremeOdobrenja;
    }

    public LocalDateTime getVremeKreiranja() {
        return vremeKreiranja;
    }

    public void setVremeKreiranja(LocalDateTime vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public String getIzdavacMail() {
        return izdavacMail;
    }

    public void setIzdavacMail(String izdavacMail) {
        this.izdavacMail = izdavacMail;
    }

    public Set<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(Set<Vozilo> vozila) {
        this.vozila = vozila;
    }
}

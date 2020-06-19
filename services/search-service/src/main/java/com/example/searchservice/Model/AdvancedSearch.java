package com.example.searchservice.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class AdvancedSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Mesto", nullable = false)
    private String mesto;

    @Column(name = "DatumOd", nullable = false)
    private LocalDateTime datumOd;

    @Column(name = "DatumDo", nullable = false)
    private LocalDateTime datumDo;

    @Column(name = "Marka", nullable = false)
    private String marka;

    @Column(name = "TipMenjaca", nullable = false)
    private String tipMenjaca;

    @Column(name = "Model", nullable = false)
    private String model;

    @Column(name = "TipGoriva", nullable = false)
    private String tipGoriva;

    @Column(name = "BrojSedistaZaDecu", nullable = false)
    private int brojSedistaZaDecu;

    @Column(name = "CDWProtection", nullable = false)
    private boolean CDWProtection;

    //@Column(name = "OgranicenaKilometraza", nullable = false)
    //private String ogranicenaKilometraza;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;


    public AdvancedSearch(String mesto, LocalDateTime datumOd, LocalDateTime datumDo, String marka, String tipMenjaca, String model, String tipGoriva, int brojSedistaZaDecu, boolean CDWProtection, Long idVozila) {
        this.mesto = mesto;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.marka = marka;
        this.tipMenjaca = tipMenjaca;
        this.model = model;
        this.tipGoriva = tipGoriva;
        this.brojSedistaZaDecu = brojSedistaZaDecu;
        this.CDWProtection = CDWProtection;
        //this.ogranicenaKilometraza = ogranicenaKilometraza;
        this.idVozila = idVozila;
    }

    public AdvancedSearch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
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

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTipMenjaca() {
        return tipMenjaca;
    }

    public void setTipMenjaca(String tipMenjaca) {
        this.tipMenjaca = tipMenjaca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTipGoriva() {
        return tipGoriva;
    }

    public void setTipGoriva(String tipGoriva) {
        this.tipGoriva = tipGoriva;
    }

    public int getBrojSedistaZaDecu() {
        return brojSedistaZaDecu;
    }

    public void setBrojSedistaZaDecu(int brojSedistaZaDecu) {
        this.brojSedistaZaDecu = brojSedistaZaDecu;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public boolean isCDWProtection() {
        return CDWProtection;
    }

    public void setCDWProtection(boolean CDWProtection) {
        this.CDWProtection = CDWProtection;
    }



}

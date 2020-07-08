package com.example.voziloservice.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vozilo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Marka", nullable = false)
    private String marka;

    @Column(name = "Model", nullable = false)
    private String model;

    @Column(name = "TipGoriva", nullable = false)
    private String tipGoriva;

    @Column(name = "TipMenjaca", nullable = false)
    private String tipMenjaca;

    @Column(name = "KlasaVozila", nullable = false)
    private String klasaVozila;

    @Column(name = "CenovnikId", nullable = false)
    private String cenovnikId;

    @Column(name = "PredjenaKilometraza", nullable = false)
    private double redjenaKilometraza;

    @Column(name = "OgranicenaKilometraza", nullable = false)
    private String ogranicenaKilometraza;

    @Column(name = "CDWProtection", nullable = false)
    private boolean CDWProtection;

    @Column(name = "BrojSedistaDeca", nullable = false)
    private int brojSedistaDeca;

    @Column(name = "IznajmljivacId", nullable = false)
    private Long iznajmljivacId;

    @Column(name = "IznajmljivacMail", nullable = false)
    private String iznajmljivacMail;

    @Column(name = "VaziOd", nullable = false)
    private LocalDateTime vaziOd;

    @Column(name = "VaziDo", nullable = false)
    private LocalDateTime vaziDo;

    @Column(name = "Mesto", nullable = false)
    private String mesto;

    @Column(name = "PomId", nullable = false)
    private Long pomId;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "VOZILO_ZAHTEV",
            joinColumns = @JoinColumn(name = "vozilo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "zahtev_id", referencedColumnName = "id"))
    private Set<Zahtev> zahtevi = new HashSet<Zahtev>();

    public Vozilo() {
        this.zahtevi = new HashSet<Zahtev>();
    }

    public Vozilo(String marka, String model, String tipGoriva, String tipMenjaca, String klasaVozila, String cenovnikId, double redjenaKilometraza, String ogranicenaKilometraza, boolean CDWProtection, int brojSedistaDeca, LocalDateTime vaziOd, LocalDateTime vaziDo, String mesto,Long oglasavacId,String iznajmljivacMail, Long pomId) {
        this.marka = marka;
        this.model = model;
        this.tipGoriva = tipGoriva;
        this.tipMenjaca = tipMenjaca;
        this.klasaVozila = klasaVozila;
        this.cenovnikId = cenovnikId;
        this.redjenaKilometraza = redjenaKilometraza;
        this.ogranicenaKilometraza = ogranicenaKilometraza;
        this.CDWProtection = CDWProtection;
        this.brojSedistaDeca = brojSedistaDeca;
        this.vaziOd = vaziOd;
        this.vaziDo = vaziDo;
        this.mesto = mesto;
        this.iznajmljivacId=oglasavacId;
        this.iznajmljivacMail=iznajmljivacMail;
        this.pomId = pomId;
        this.zahtevi = new HashSet<Zahtev>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
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

    public String getTipMenjaca() {
        return tipMenjaca;
    }

    public void setTipMenjaca(String tipMenjaca) {
        this.tipMenjaca = tipMenjaca;
    }

    public String getKlasaVozila() {
        return klasaVozila;
    }

    public void setKlasaVozila(String klasaVozila) {
        this.klasaVozila = klasaVozila;
    }

    public String getCenovnikId() {
        return cenovnikId;
    }

    public void setCenovnikId(String cenovnikId) {
        this.cenovnikId = cenovnikId;
    }

    public double getRedjenaKilometraza() {
        return redjenaKilometraza;
    }

    public void setRedjenaKilometraza(double redjenaKilometraza) {
        this.redjenaKilometraza = redjenaKilometraza;
    }

    public String getOgranicenaKilometraza() {
        return ogranicenaKilometraza;
    }

    public void setOgranicenaKilometraza(String ogranicenaKilometraza) {
        this.ogranicenaKilometraza = ogranicenaKilometraza;
    }

    public boolean isCDWProtection() {
        return CDWProtection;
    }

    public void setCDWProtection(boolean CDWProtection) {
        this.CDWProtection = CDWProtection;
    }

    public int getBrojSedistaDeca() {
        return brojSedistaDeca;
    }

    public void setBrojSedistaDeca(int brojSedistaDeca) {
        this.brojSedistaDeca = brojSedistaDeca;
    }

    public Long getIznajmljivacId() {
        return iznajmljivacId;
    }

    public void setIznajmljivacId(Long iznajmljivacId) {
        this.iznajmljivacId = iznajmljivacId;
    }

    public LocalDateTime getVaziOd() {
        return vaziOd;
    }

    public void setVaziOd(LocalDateTime vaziOd) {
        this.vaziOd = vaziOd;
    }

    public LocalDateTime getVaziDo() {
        return vaziDo;
    }

    public void setVaziDo(LocalDateTime vaziDo) {
        this.vaziDo = vaziDo;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getIznajmljivacMail() {
        return iznajmljivacMail;
    }

    public void setIznajmljivacMail(String iznajmljivacMail) {
        this.iznajmljivacMail = iznajmljivacMail;
    }

    public Set<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(Set<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }

    public Long getPomId() {
        return pomId;
    }

    public void setPomId(Long pomId) {
        this.pomId = pomId;
    }

    @Override
    public String toString() {
        return "Vozilo{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", tipGoriva='" + tipGoriva + '\'' +
                ", tipMenjaca='" + tipMenjaca + '\'' +
                ", klasaVozila='" + klasaVozila + '\'' +
                ", cenovnikId='" + cenovnikId + '\'' +
                ", redjenaKilometraza=" + redjenaKilometraza +
                ", ogranicenaKilometraza='" + ogranicenaKilometraza + '\'' +
                ", CDWProtection=" + CDWProtection +
                ", brojSedistaDeca=" + brojSedistaDeca +
                ", iznajmljivacId=" + iznajmljivacId +
                ", iznajmljivacMail='" + iznajmljivacMail + '\'' +
                ", vaziOd=" + vaziOd +
                ", vaziDo=" + vaziDo +
                ", mesto='" + mesto + '\'' +
                ", pomId=" + pomId +
                ", zahtevi=" + zahtevi +
                '}';
    }
}

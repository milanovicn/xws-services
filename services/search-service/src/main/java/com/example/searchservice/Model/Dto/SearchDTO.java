package com.example.searchservice.Model.Dto;

import java.util.Date;

public class SearchDTO {


    private String mesto;


    private Date datumOd;


    private Date datumDo;


    private String marka;


    private String tipMenjaca;


    private String model;


    private String tipGoriva;


    private int brojSedistaZaDecu;


    private String CDWProtection;



    //private Long idVozila;

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
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

    public String getCDWProtection() {
        return CDWProtection;
    }

    public void setCDWProtection(String CDWProtection) {
        this.CDWProtection = CDWProtection;
    }



    public SearchDTO() {
    }

    public SearchDTO(String mesto, Date datumOd, Date datumDo, String marka, String tipMenjaca, String model, String tipGoriva, int brojSedistaZaDecu, String CDWProtection) {
        this.mesto = mesto;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.marka = marka;
        this.tipMenjaca = tipMenjaca;
        this.model = model;
        this.tipGoriva = tipGoriva;
        this.brojSedistaZaDecu = brojSedistaZaDecu;
        this.CDWProtection = CDWProtection;
    }
}

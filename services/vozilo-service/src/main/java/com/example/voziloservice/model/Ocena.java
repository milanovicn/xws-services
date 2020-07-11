package com.example.voziloservice.model;

import javax.persistence.*;


@Entity
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;

    @Column(name = "Ocena", nullable = false)
    private int ocena;

    public Ocena() {
    }

    public Ocena(Long idVozila, int ocena) {
        this.idVozila = idVozila;
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Ocena{" +
                "id=" + id +
                ", idVozila=" + idVozila +
                ", ocena=" + ocena +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}

package com.example.voziloservice.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class ZauzeceVozila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Od", nullable = false)
    private Date od;

    @Column(name = "ZauzetoDo", nullable = false)
    private Date zauzetoDo;

    @Column(name = "IdVozila", nullable = false)
    private Long idVozila;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOd() {
        return od;
    }

    public void setOd(Date od) {
        this.od = od;
    }

    public Date getZauzetoDo() {
        return zauzetoDo;
    }

    public void setZauzetoDo(Date zauzetoDo) {
        this.zauzetoDo = zauzetoDo;
    }

    public Long getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Long idVozila) {
        this.idVozila = idVozila;
    }

    public ZauzeceVozila() {
    }

    public ZauzeceVozila(Date od, Date zauzetoDo, Long idVozila) {
        this.od = od;
        this.zauzetoDo = zauzetoDo;
        this.idVozila = idVozila;
    }
}

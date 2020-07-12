package com.example.voziloservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PretragaZauzecaDTO {

    private LocalDateTime DatumOd;
    private LocalDateTime DatumDo;
    ArrayList<Long> ids = new ArrayList<Long>();

    public PretragaZauzecaDTO() {
    }

    public PretragaZauzecaDTO(LocalDateTime datumOd, LocalDateTime datumDo, ArrayList<Long> ids) {
        DatumOd = datumOd;
        DatumDo = datumDo;
        this.ids = ids;
    }

    public LocalDateTime getDatumOd() {
        return DatumOd;
    }

    public void setDatumOd(LocalDateTime datumOd) {
        DatumOd = datumOd;
    }

    public LocalDateTime getDatumDo() {
        return DatumDo;
    }

    public void setDatumDo(LocalDateTime datumDo) {
        DatumDo = datumDo;
    }

    public ArrayList<Long> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Long> ids) {
        this.ids = ids;
    }
}

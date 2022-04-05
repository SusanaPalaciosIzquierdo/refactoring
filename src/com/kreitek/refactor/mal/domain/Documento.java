package com.kreitek.refactor.mal.domain;

import com.kreitek.refactor.mal.TIPODOCUMENTO;

import java.util.Date;

public abstract class Documento {
    private String numDocumento;
    private Date fechaValidez;
    private TIPODOCUMENTO tipoDoc;

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public Date getFechaValidez() {
        return fechaValidez;
    }

    public void setFechaValidez(Date fechaValidez) {
        this.fechaValidez = fechaValidez;
    }

    public TIPODOCUMENTO getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TIPODOCUMENTO tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public abstract boolean esValido();

}

package com.kreitek.refactor.mal.domain;

import com.kreitek.refactor.mal.TIPODOCUMENTO;

import java.util.Date;

public class DNI extends Documento{

    public DNI(String numDocumento, Date fechaValidez){
        super.setNumDocumento(numDocumento);
        super.setFechaValidez(fechaValidez);
        super.setTipoDoc(TIPODOCUMENTO.DNI);
    }

    @Override
    public boolean esValido() {
        boolean esValido = true;
        if (super.getNumDocumento().length()>= 9) {
            String numerosDNI = super.getNumDocumento().trim().replaceAll(" ", "").substring(0, 8);
            if ( !esNumero(numerosDNI) || this.esLetraValida(numerosDNI)) {
                esValido = false;
            }
        } else{
            esValido = false;
        }
        return esValido;
    }

    private boolean esLetraValida (String numerosDNI){
        String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";
        char letraDNI = super.getNumDocumento().charAt(8);
        int valNumDni = Integer.parseInt(numerosDNI) % 23;
        return dniChars.charAt(valNumDni)!= letraDNI;
    }

    private boolean esNumero(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}

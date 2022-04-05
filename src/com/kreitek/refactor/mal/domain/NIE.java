package com.kreitek.refactor.mal.domain;

import com.kreitek.refactor.mal.TIPODOCUMENTO;

import java.util.Date;

public class NIE extends Documento {

    public NIE(String numDocumento, Date fechaValidez){
        super.setNumDocumento(numDocumento);
        super.setFechaValidez(fechaValidez);
        super.setTipoDoc(TIPODOCUMENTO.NIE);
    }

    @Override
    public boolean esValido() {
        return esValidaLongitud() && esValidaLetraInicio() && esValidoAscii() && esValidaLetraFin();
    }

    private boolean esValidaLongitud() {
        return super.getNumDocumento().length() == 9 && Character.isLetter(super.getNumDocumento().charAt(8));
    }

    private boolean esValidaLetraInicio() {
        return super.getNumDocumento().substring(0,1).equalsIgnoreCase("X")
                || super.getNumDocumento().substring(0,1).equalsIgnoreCase("Y")
                || super.getNumDocumento().substring(0,1).equalsIgnoreCase("Z");
    }

    private boolean esValidoAscii() {
        boolean esValido;
        int caracterASCII;
        int i = 1;

        do {
            caracterASCII = super.getNumDocumento().codePointAt(i);
            esValido = (caracterASCII > 47 && caracterASCII < 58);
            i++;
        } while(i < super.getNumDocumento().length() - 1 && esValido);
        return esValido;
    }

    private boolean esValidaLetraFin(){
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X','B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        char letra = Character.toUpperCase(super.getNumDocumento().charAt(8));
        int miNIE = Integer.parseInt(super.getNumDocumento().substring(1,8));
        int resto = miNIE % 23;
        return (letra == asignacionLetra[resto]);
    }
}

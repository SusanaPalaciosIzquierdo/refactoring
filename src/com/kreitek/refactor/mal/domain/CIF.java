package com.kreitek.refactor.mal.domain;

import com.kreitek.refactor.mal.TIPODOCUMENTO;
import com.kreitek.refactor.mal.TipoUltCaracter;
import com.kreitek.refactor.mal.utils.Calculos;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CIF extends Documento {

    public CIF(String numDocumento, Date fechaValidez){
        super.setNumDocumento(numDocumento);
        super.setFechaValidez(fechaValidez);
        super.setTipoDoc(TIPODOCUMENTO.CIF);
    }

    @Override
    public boolean esValido() {
        final String cifUP = super.getNumDocumento().toUpperCase();
        TipoUltCaracter tipUltCar;
        final char primerCar = super.getNumDocumento().toUpperCase().charAt(0);
        final char ultimoCar = super.getNumDocumento().toUpperCase().charAt(super.getNumDocumento().toUpperCase().length() - 1);
        return noNulo() && esValidaLetraInicio() && esPatronValido() && (tipUltCar = esValidaUltimaLetra(primerCar, ultimoCar))!=null && esValidoControl(tipUltCar, ultimoCar);
    }

    private boolean noNulo(){
        return super.getNumDocumento() != null;
    }

    private boolean esValidaLetraInicio(){
        return "ABCDEFGHJKLMNPQRSUVW".indexOf(super.getNumDocumento().toUpperCase().charAt(0))>-1 ;
    }

    private boolean esPatronValido(){
        final Pattern mask = Pattern
                .compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
        final Matcher matcher = mask.matcher(super.getNumDocumento().toUpperCase());
        return matcher.matches();
    }

    private TipoUltCaracter esValidaUltimaLetra(final char primerCar, final char ultimoCar){
        TipoUltCaracter tipUltCar;
        if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W') {
            tipUltCar = TipoUltCaracter.LETRA;
            if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
                tipUltCar = null;
            }
        } else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E'
                || primerCar == 'H') {
            tipUltCar = TipoUltCaracter.NUMERO;
            if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
                tipUltCar = null;
            }
        } else {
            tipUltCar = TipoUltCaracter.AMBOS;
        }
        return tipUltCar;
    }

    private boolean esValidoControl(final TipoUltCaracter tipUltCar, final char ultimoCar){
        boolean esValido;
        int numControl = Calculos.calcularNumControl(this);
        int pos = numControl == 10? 0:numControl;
        final char carControl = "JABCDEFGHI".charAt(pos);

        if (tipUltCar == TipoUltCaracter.NUMERO) {
            esValido = this.esValidoNumUltCar(ultimoCar, pos);
        } else if (tipUltCar == TipoUltCaracter.LETRA) {
            esValido = this.esValidoLetraUltCar(carControl, ultimoCar);
        } else {
            esValido = this.esValidoAmbosUltCar( ultimoCar, pos, carControl);
        }
        return esValido;
    }

    private boolean esValidoNumUltCar(final char ultimoCar, int pos){
        boolean esValido = true;
        final int ultCar = Integer.parseInt(Character
                .toString(ultimoCar));
        if (pos != ultCar) {
            esValido = false;
        }
        return esValido;
    }

    private boolean esValidoLetraUltCar(final char carControl, final char ultimoCar) {
        return carControl == ultimoCar;
    }

    private boolean esValidoAmbosUltCar(final char ultimoCar, int pos, final char carControl) {
        boolean esValido = true;
        int ultCar  = "JABCDEFGHI".indexOf(ultimoCar);

        if (ultCar < 0){
            ultCar = Integer.parseInt(Character.toString(ultimoCar));
            if (pos != ultCar) {
                esValido=false;
            }
        }
        if ((pos != ultCar) && (carControl != ultimoCar)) {
            esValido=false;
        }
        return esValido;
    }
}

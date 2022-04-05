package com.kreitek.refactor.mal.utils;

import com.kreitek.refactor.mal.domain.CIF;


public class Calculos{

    public static int calcularNumControl(CIF cif) {
        final String digitos = cif.getNumDocumento().toUpperCase().substring(1, cif.getNumDocumento().toUpperCase().length() - 1);
        int sumaPares = getCalcularSumaPares(digitos);
        int sumaImpares = getCalcularSumaImpares(digitos);
        final int total = sumaPares + sumaImpares;
        return 10 - (total % 10);
    }

    private static int getCalcularSumaImpares(String digitos) {
        int sumaImpares = 0;
        for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
            int cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
            if (Integer.toString(cal).length() > 1) {
                cal = Integer.parseInt(Integer.toString(cal).substring(0, 1))
                        + Integer.parseInt(Integer.toString(cal).substring(1, 2));
            }
            sumaImpares += cal;
        }
        return sumaImpares;
    }

    private static int getCalcularSumaPares(final String digitos) {
        int sumaPares = 0;
        for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
            sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
        }
        return sumaPares;
    }
}

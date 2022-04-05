package com.kreitek.refactor.mal.utils;

import com.kreitek.refactor.mal.domain.Documento;

public class Impresora {

    public static void imprimirCabecera(){
        System.out.println("=====================");
        System.out.println("Vamos a refactorizar!");
        System.out.println("=====================");
    }

    public static void imprimirDocumento(Documento doc){
        System.out.println(doc.getTipoDoc() + " " + doc.getNumDocumento() + " es: " + doc.esValido());
    }
}
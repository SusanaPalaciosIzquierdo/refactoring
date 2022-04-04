package com.kreitek.refactor.mal;

import com.kreitek.refactor.mal.domain.*;
import com.kreitek.refactor.mal.utils.Impresora;
import java.util.ArrayList;
import java.util.List;

class  Main
{
    public static void main(String[] args)
    {
        Impresora.imprimirCabecera();

        List<Documento> listaDoc = new ArrayList<>();
        listaDoc.add(new DNI("11111111H", null));
        listaDoc.add(new DNI("24324356A", null));
        listaDoc.add(new NIE("X0932707B", null));
        listaDoc.add(new NIE("Z2691139Z", null));
        listaDoc.add(new CIF("W9696294I", null));
        listaDoc.add(new CIF("W9696294A", null));

        for(Documento doc: listaDoc){
            Impresora.imprimirDocumento(doc);
        }
    }
}
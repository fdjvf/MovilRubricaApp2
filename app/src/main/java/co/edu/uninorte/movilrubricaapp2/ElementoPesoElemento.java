package co.edu.uninorte.movilrubricaapp2;


import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.PesoElemento;
import co.edu.uninorte.movilrubricaapp2.Model.Elemento;

/**
 * Created by fdjvf on 4/19/2017.
 */

public class ElementoPesoElemento {
    public Elemento elemento;
    public PesoElemento pesoElemento;
    public String ID;
    DatabaseReference elementopesoelementos;

    public static void SaveList(ArrayList<ElementoPesoElemento> elementoPesoElementos) {
        for (ElementoPesoElemento elementoPesoElemento : elementoPesoElementos) {
            elementoPesoElemento.pesoElemento.save();
        }

    }

    public void Save(){
        elementopesoelementos.child(ID).setValue(this);
    }
}

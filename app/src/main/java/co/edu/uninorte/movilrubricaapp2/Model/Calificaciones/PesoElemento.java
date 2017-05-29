package co.edu.uninorte.movilrubricaapp2.Model.Calificaciones;

import com.google.firebase.database.IgnoreExtraProperties;

import co.edu.uninorte.movilrubricaapp2.Model.Elemento;

/**
 * Created by fdjvf on 4/16/2017.
 */
@IgnoreExtraProperties

public class PesoElemento {

    Elemento elemento;
    String Peso;

    public PesoElemento(Elemento elemento, String peso) {
        this.elemento = elemento;
        Peso = peso;
    }

    public PesoElemento() {

    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public float getPesoFloat() {
        return Float.valueOf(Peso);
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }


}

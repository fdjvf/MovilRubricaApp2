package co.edu.uninorte.movilrubricaapp2.Model.Calificaciones;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import co.edu.uninorte.movilrubricaapp2.Model.Categoria;

/**
 * Created by fdjvf on 4/16/2017.
 */
@IgnoreExtraProperties
public class PesoCategoria {

    public Categoria categoria;
    public ArrayList<PesoElemento> pesoElementos;
    String Peso;


    public PesoCategoria() {

    }
    public PesoCategoria(String peso) {
        this.Peso = peso;
        pesoElementos = new ArrayList<>();

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

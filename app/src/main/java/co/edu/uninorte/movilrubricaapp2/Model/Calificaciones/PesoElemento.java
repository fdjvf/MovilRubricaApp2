package co.edu.uninorte.movilrubricaapp2.Model.Calificaciones;

import com.orm.SugarRecord;

/**
 * Created by fdjvf on 4/16/2017.
 */

public class PesoElemento extends SugarRecord {


  public PesoCategoria pesoCategoria;
    String Peso;


    public PesoElemento(float Peso) {


    }

    public PesoElemento() {

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

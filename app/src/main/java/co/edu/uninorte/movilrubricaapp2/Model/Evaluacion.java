package co.edu.uninorte.movilrubricaapp2.Model;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.PesoCategoria;

/**
 * Created by fdjvf on 4/11/2017.
 */
@IgnoreExtraProperties
public class Evaluacion implements Serializable {


    String Nombre;
    Rubrica rubrica;
    ArrayList<PesoCategoria> pesoCategorias;

    public Evaluacion(String nombre, Rubrica rubrica, ArrayList<PesoCategoria> pesoCategorias) {
        Nombre = nombre;
        this.rubrica = rubrica;
        this.pesoCategorias = pesoCategorias;
    }

    public Evaluacion() {

    }


    public String getRubrica() {
        return rubrica.getName();
    }

    public void setRubrica(Rubrica rubrica) {
        this.rubrica = rubrica;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


}

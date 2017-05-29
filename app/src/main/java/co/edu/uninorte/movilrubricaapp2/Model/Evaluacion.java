package co.edu.uninorte.movilrubricaapp2.Model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by fdjvf on 4/11/2017.
 */

public class Evaluacion extends SugarRecord implements Serializable {


    String Nombre;
    Asignatura asignatura;
    Rubrica rubrica;
   // private PropertyChangeRegistry registry = new PropertyChangeRegistry();
    //Siempre antes de guardar estos, ya se debió haber guardado la rubrica y la asignatura

    //Siempre antes de guardar estos, ya se debdio haber guardado la rubrica y la asignatura
    public Evaluacion() {

    }

    public Evaluacion(String nombre, Asignatura curso) {
        this.Nombre = nombre;
        this.asignatura = curso;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;

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

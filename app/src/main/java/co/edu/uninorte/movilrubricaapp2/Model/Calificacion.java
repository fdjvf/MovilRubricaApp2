package co.edu.uninorte.movilrubricaapp2.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.CalCategoria;

/**
 * Created by fdjvf on 4/16/2017.
 */
@IgnoreExtraProperties
public class Calificacion {

    ArrayList<CalCategoria> calCategorias;
    Estudiante estudiante;

    public Calificacion() {

    }

    public Calificacion(float nota, Estudiante student) {
        this.estudiante = student;
        calCategorias = new ArrayList<>();
    }

    public float getNotaFinal() {
        float Sum = 0;

        for (CalCategoria caltegoria : calCategorias) {
            Sum = Sum + caltegoria.getNotaFinal();
        }

        return Sum;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }


}

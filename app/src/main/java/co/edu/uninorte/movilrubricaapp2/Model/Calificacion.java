package co.edu.uninorte.movilrubricaapp2.Model;

import com.orm.SugarRecord;

import java.util.List;

import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.CalCategoria;

/**
 * Created by fdjvf on 4/16/2017.
 */

public class Calificacion extends SugarRecord {

    Evaluacion evaluacion;
    Estudiante estudiante;

    public Calificacion() {

    }

    public Calificacion(float nota, Evaluacion eval, Estudiante student) {
        this.evaluacion = eval;
        this.estudiante = student;
    }

    public float getNotaFinal() {
        float Sum = 0;
        List<CalCategoria> notascal = CalCategoria.find(CalCategoria.class, "calificacion = ?", String.valueOf(getId()));

        for (CalCategoria caltegoria : notascal) {
            Sum = Sum + caltegoria.getNotaFinal();
        }

        return Sum;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }


}

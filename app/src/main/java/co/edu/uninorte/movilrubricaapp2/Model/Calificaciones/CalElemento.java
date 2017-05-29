package co.edu.uninorte.movilrubricaapp2.Model.Calificaciones;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by fdjvf on 4/16/2017.
 */
@IgnoreExtraProperties
public class CalElemento {

    float pesoElemento;
    float Nota;


    public CalElemento() {

    }

    public CalElemento(float pesoElemento, float nota) {
        this.pesoElemento = pesoElemento;
        Nota = nota;
    }


    public float getNota() {
        return Nota;
    }

    public void setNota(float nota) {
        Nota = nota;
    }

    public float getPesoElemento() {
        return pesoElemento;
    }

    public void setPesoElemento(float pesoElemento) {
        this.pesoElemento = pesoElemento;
    }

    public float getNotaFinal() {
        return Nota * pesoElemento;
    }
}

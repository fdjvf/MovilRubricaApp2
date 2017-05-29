package co.edu.uninorte.movilrubricaapp2.Model.Calificaciones;

import com.orm.SugarRecord;

/**
 * Created by fdjvf on 4/16/2017.
 */

public class CalElemento extends SugarRecord {

    float pesoElemento;
    float Nota;
    CalCategoria calCategoria;

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

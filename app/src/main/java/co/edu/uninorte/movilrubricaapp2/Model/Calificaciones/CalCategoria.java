package co.edu.uninorte.movilrubricaapp2.Model.Calificaciones;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by fdjvf on 4/16/2017.
 */
@IgnoreExtraProperties
public class CalCategoria {


    List<CalElemento> calElementos;
    float pesoCategoria;

    public CalCategoria() {

    }

    public float getPesoCategoria() {
        return pesoCategoria;
    }

    public void setPesoCategoria(float value) {
        pesoCategoria = value;
    }

    public float getNota() {
        //Nota de la categoria con sus elementos
        float Suma = 0;
        for (CalElemento element : calElementos) {
            Suma = Suma + element.getNotaFinal();
        }
        return Suma;
    }

    public float getNotaFinal() {  //Nota de la categoria con respecto a la rubrica
        return getNota() * pesoCategoria;
    }


}

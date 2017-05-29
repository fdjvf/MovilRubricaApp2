package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.ObservableArrayList;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by fdjvf on 4/11/2017.
 */
@IgnoreExtraProperties
public class Categoria {

    public ObservableArrayList<Elemento> ObservableListElements;
    String name;
    String descripcion;

    public Categoria(ObservableArrayList<Elemento> observableListElements, String name, String descripcion) {
        ObservableListElements = observableListElements;
        this.name = name;
        this.descripcion = descripcion;
    }

    public Categoria() {

    }


    public String getName() {
        return name;
    }//Implementar Binding tWO wAY

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}

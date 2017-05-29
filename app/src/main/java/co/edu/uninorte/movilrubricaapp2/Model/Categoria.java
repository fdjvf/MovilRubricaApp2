package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.ObservableArrayList;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by fdjvf on 4/11/2017.
 */

public class Categoria extends SugarRecord {

    public Rubrica rubrica;
    public ObservableArrayList<Object> ObservableListElements;
    String name;
    String descripcion;
    public Categoria() {

    }

    public Categoria(String name, String descripcion, Rubrica rubrica1) {
        this.name = name;
        this.descripcion = descripcion;
        this.rubrica = rubrica1;

    }

    public static ObservableArrayList<Object> getCategorias(int nivel) {

        List<Categoria> categorias = Categoria.find(Categoria.class, "Level = ?", Integer.toString(nivel));
        ObservableArrayList<Object> observableArrayList = new ObservableArrayList();
        observableArrayList.addAll(categorias);
        return observableArrayList;
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

    public boolean getElementos() {
        ObservableListElements = new ObservableArrayList<>();
        List<Elemento> elementos = Elemento.find(Elemento.class, "categoria = ?", String.valueOf(this.getId()));
        return ObservableListElements.addAll(elementos);

    }

    public List<Elemento> getElementoslista() {

        return Elemento.find(Elemento.class, "categoria = ?", String.valueOf(this.getId()));

    }

}

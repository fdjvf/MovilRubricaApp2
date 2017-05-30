package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.ObservableArrayList;
import android.media.JetPlayer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by fdjvf on 4/11/2017.
 */
@IgnoreExtraProperties
public class Categoria {

    public ObservableArrayList<Object> ObservableListElements;
    String name;
    String descripcion;
    int ID;
    DatabaseReference Categorias;//Falta inicializar
    public Categoria(String name, String descripcion, int ID) {
        this.name = name;
        this.descripcion = descripcion;
        this.ID = ID;
    }

    public Categoria() {

    }

    public Elemento FindOneElement(String UID) {
        for (Object temp : ObservableListElements) {
            Elemento a = (Elemento) temp;
            if (a.getUID().equals(UID)) {
                return a;
            }
        }
        return null;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

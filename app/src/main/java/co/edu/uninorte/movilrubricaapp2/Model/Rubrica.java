package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.PropertyChangeRegistry;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

import co.edu.uninorte.movilrubricaapp2.BR;

/**
 * Created by fdjvf on 4/11/2017.
 */
@IgnoreExtraProperties
public class Rubrica implements Observable {

    public static ObservableArrayList<Object> ObservableListRubrica = new ObservableArrayList<>();
    public  ObservableArrayList<Object> ObservableListCategorias;
    public int EscalaMaxima;
    public String ID;
    String name;
    String descripcion;

    DatabaseReference Rubricas;//Obtener Asignaturas
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    public Rubrica() {
    }
    public Rubrica(String name, int niveles, String descripcion) {
        this.name = name;
        this.EscalaMaxima = niveles;
        this.descripcion = descripcion;
        Rubricas= App.getRubricas();
    }

    public static List<String> getListNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < ObservableListRubrica.size(); i++) {
  //TODO:   pasar a object        names.add(ObservableListRubrica.get(i).getName());
        }
        return names;
    }
    public static Rubrica FindOne(String UID) {
        for (Object temp : ObservableListRubrica) {
            Rubrica a = (Rubrica) temp;
            if (a.ID.equals(UID)) {
                return a;
            }
        }
        return null;
    }
    public  Categoria FindOneCategoria(int UID) {
        return (Categoria) ObservableListCategorias.get(UID);
    }
    @Bindable
    public String getName() {
        return name;
    }//Implementar Observable en el Display

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        registry.notifyChange(this, BR.rubricamodel);

    }

    @Bindable
    public String getDescripcion() {
        return descripcion;

    }

    @Bindable
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        registry.notifyChange(this, BR.rubricamodel);
    }

    public void Save() {Rubricas.child(ID).setValue(this);}

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {

        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {

        registry.remove(onPropertyChangedCallback);
    }
}

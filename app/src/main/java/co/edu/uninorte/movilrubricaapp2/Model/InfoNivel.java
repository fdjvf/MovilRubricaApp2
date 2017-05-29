package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.orm.SugarRecord;

import co.edu.uninorte.movilrubricaapp2.BR;

/**
 * Created by fdjvf on 4/12/2017.
 */

public class InfoNivel extends SugarRecord implements Observable {

    String descripcion;
    int nivel;
    Elemento elemento;
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    public InfoNivel(String descripcion, int nivel) {
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public InfoNivel() {

    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }


    @Bindable
    public String getDescripcion() {
        return descripcion;
    }

    @Bindable
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        registry.notifyChange(this, BR.elementInfo);
        registry.notifyChange(this, BR.descripcionInfoNivel);
    }

    @Bindable
    public int getNivel() {
        return nivel;
    }

    @Bindable
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {

        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {

        registry.remove(onPropertyChangedCallback);
    }
}

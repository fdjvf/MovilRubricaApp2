package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

import co.edu.uninorte.movilrubricaapp2.BR;

/**
 * Created by fdjvf on 4/12/2017.
 */
@IgnoreExtraProperties
public class InfoNivel extends BaseObservable implements Serializable{

    String descripcion;
    int nivel;



    public InfoNivel(String descripcion, int nivel) {
        this.descripcion = descripcion;
        this.nivel = nivel;
    }
    public InfoNivel() {

    }
    @Bindable
    public String getDescripcion() {
        return descripcion;
    }

    @Bindable
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        notifyPropertyChanged(BR.elementInfo);
        notifyPropertyChanged(BR.descripcionInfoNivel);
    }

    @Bindable
    public int getNivel() {
        return nivel;
    }

    @Bindable
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}

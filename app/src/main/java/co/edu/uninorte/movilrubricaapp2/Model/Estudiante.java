package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.orm.SugarRecord;

import co.edu.uninorte.movilrubricaapp2.BR;


public class Estudiante extends SugarRecord implements Observable {



    String name = "";
    Boolean state;
    Asignatura asignatura;
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    public Estudiante() {

    }

    public Estudiante(String name, Boolean state, Asignatura asignatura) {
        this.name = name;
        this.state = state;
        this.asignatura = asignatura;

    }

    //Siempre guardar el ID
    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        registry.notifyChange(this, BR.studenthint);
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
         //registry.notifyChange(this, BR.bar);
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

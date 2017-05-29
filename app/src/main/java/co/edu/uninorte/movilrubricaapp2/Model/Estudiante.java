package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.PropertyChangeRegistry;

import com.google.firebase.database.IgnoreExtraProperties;

import co.edu.uninorte.movilrubricaapp2.BR;

@IgnoreExtraProperties
public class Estudiante implements Observable {



    String name = "";
    Boolean state;
    String UID;
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    public Estudiante(String name, Boolean state, String UID) {
        this.name = name;
        this.state = state;
        this.UID = UID;
    }

    public Estudiante() {

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

    public ObservableArrayList<Asignatura> getAsignaturas() {
        ObservableArrayList<Asignatura> estudianteAsignaturas = new ObservableArrayList<>();
  /*      for(Asignatura temp:Asignatura.ObserVableAsignaturas)
        {
            ObservableArrayList<Estudiante> estudiantes=temp.ObservableEstudiantesCurso;
            boolean exist=estudiantes.stream().anyMatch(x->x.UID.equals(UID));
            if (exist)
            {
                estudianteAsignaturas.add(temp);
            }

        }*/
        return estudianteAsignaturas;
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

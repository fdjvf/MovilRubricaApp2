package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.PropertyChangeRegistry;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;

import co.edu.uninorte.movilrubricaapp2.BR;

/**
 * Created by fdjvf on 4/11/2017.
 */


@IgnoreExtraProperties
public class Asignatura implements Observable {
    //Manejo de Binding

    public static ObservableArrayList<Object> ObserVableAsignaturas = new ObservableArrayList<>();    //Todas las asignaturas
    public Hashtable<String, ArrayList<Calificacion>> Calificaciones; //Calificioanes
    public  ObservableArrayList<Object> ObservableEstudiantesCurso; //Estudiantes
    public ObservableArrayList<Object> ObservableEvaluacionesCurso; //Evaluaciones
    String name = "";
    String description = "";
    Boolean isVisible;
    String UID;
    DatabaseReference Asignaturas;//Obtener Asignaturas
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    public Asignatura(PropertyChangeRegistry registry) {
        this.registry = registry;
    }

    public Asignatura(String name, String description, Boolean isVisible, String UID) {
        this.name = name;
        this.description = description;
        this.isVisible = isVisible;
        this.UID = UID;
        Calificaciones = new Hashtable<>();
        ObservableEstudiantesCurso = new ObservableArrayList<>();
        ObservableEvaluacionesCurso = new ObservableArrayList<>();
    }


    public Asignatura() {

    }

    public static Asignatura FindOne(String UID) {
        for (Object temp : ObserVableAsignaturas) {
            Asignatura a = (Asignatura) temp;
            if (a.UID.equals(UID)) {
                return a;
            }
        }
        return null;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        registry.notifyChange(this, BR.coursemodel);//Permite doble binding
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    @Bindable
    public void setDescription(String description) {
        this.description = description;
        registry.notifyChange(this, BR.coursemodel);
    }

    //Solo utilizar para creacion, de resto utilizar el save
    public void Save()
    {
        Asignaturas.child(UID).setValue(this);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);

    }

    public ObservableArrayList<Evaluacion> getCalificaciones(String estudianteId) {
        Set<String> keysets = Calificaciones.keySet();
        ObservableArrayList<Evaluacion> evaluaciones = new ObservableArrayList<>();
        for (String keyeval : keysets) {

        }
        return evaluaciones;
    }

}

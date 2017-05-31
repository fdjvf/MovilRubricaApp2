package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.ObservableArrayList;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by fdjvf on 4/11/2017.
 */

@IgnoreExtraProperties
public class Elemento implements Serializable {

    public ObservableArrayList<InfoNivel> ObservableDescricionNivel;
    String name;
    int UID;


    public Elemento(String name, int id) {
        ObservableDescricionNivel = new ObservableArrayList<>();
        this.UID = id;
        this.name = name;
    }


    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

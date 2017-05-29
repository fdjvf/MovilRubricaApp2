package co.edu.uninorte.movilrubricaapp2.Model;

import android.databinding.ObservableArrayList;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by fdjvf on 4/11/2017.
 */

public class Elemento extends SugarRecord {

    public ObservableArrayList<Object> ObservableDescricionNivel;
    public Categoria categoria;
    String name;

    public Elemento() {

    }


    public Elemento(String name, Float peso, String descripcion, Categoria categoria) {
        this.name = name;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InfoNivel> getInfoNivel() {
        ObservableDescricionNivel = new ObservableArrayList<>();
        List<InfoNivel> infoNivelList = InfoNivel.find(InfoNivel.class, "elemento = ?", String.valueOf(this.getId()));
        ObservableDescricionNivel.addAll(infoNivelList);
        return infoNivelList;
    }

}

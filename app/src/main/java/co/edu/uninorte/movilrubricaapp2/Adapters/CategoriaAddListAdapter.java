package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.edu.uninorte.movilrubricaapp2.Model.Categoria;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.RubricaCategoriaFilaBinding;

/**
 * Created by fdjvf on 4/16/2017.
 */

public class CategoriaAddListAdapter extends ListAdapter {

    public CategoriaAddListAdapter(ObservableArrayList<Object> list) {
        super(list);
    }

    @BindingAdapter("bind:NewCategoria")
    public static void bindList(ListView view, ObservableArrayList<Object> list) {
        CategoriaAddListAdapter adapter = new CategoriaAddListAdapter(list);
        view.setAdapter(adapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        RubricaCategoriaFilaBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.rubrica_categoria_fila, parent, false);
        binding.setMycategory((Categoria) list.get(position));
        return binding.getRoot();
    }
}

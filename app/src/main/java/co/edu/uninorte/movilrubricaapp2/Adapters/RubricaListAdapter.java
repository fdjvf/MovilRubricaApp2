package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.RubricaFilaBinding;

/**
 * Created by fdjvf on 4/14/2017.
 */

public class RubricaListAdapter extends ListAdapter {

    public RubricaListAdapter(ObservableArrayList<Object> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        RubricaFilaBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.rubrica_fila, parent, false);
        binding.setRubrica((Rubrica) list.get(position));
        return binding.getRoot();
    }
}

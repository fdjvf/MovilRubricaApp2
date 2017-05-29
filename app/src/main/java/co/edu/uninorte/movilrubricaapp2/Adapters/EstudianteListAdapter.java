package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uninorte.movilrubricaapp2.Model.Estudiante;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.EstudianteFilaBinding;

/**
 * Created by Melanis on 18/04/2017.
 */

public class EstudianteListAdapter extends ListAdapter {
    public EstudianteListAdapter(ObservableArrayList<Object> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        EstudianteFilaBinding binding;

        binding = DataBindingUtil.inflate(inflater, R.layout.estudiante_fila, parent, false);
        binding.setEstudiante((Estudiante)  list.get(position));
        return binding.getRoot();
    }
}

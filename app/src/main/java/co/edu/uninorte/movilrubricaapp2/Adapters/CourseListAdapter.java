package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.CursoFilaBinding;

/**
 * Created by fdjvf on 4/13/2017.
 */

public class CourseListAdapter extends ListAdapter {

    public CourseListAdapter(ObservableArrayList<Object> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        CursoFilaBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.curso_fila, parent, false);

        binding.setCourse((Asignatura) list.get(position));
        return binding.getRoot();
    }
}

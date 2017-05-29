package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.uninorte.movilrubricaapp2.Model.Evaluacion;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.EvaluacionFilaBinding;

/**
 * Created by Melanis on 18/04/2017.
 */

public class EvaluacionListAdapter extends ListAdapter {
    public EvaluacionListAdapter(ObservableArrayList<Object> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        EvaluacionFilaBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.evaluacion_fila, parent, false);
        binding.setEvaluacion((Evaluacion)  list.get(position));
        return binding.getRoot();
    }
}

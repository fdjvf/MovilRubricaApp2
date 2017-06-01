package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.edu.uninorte.movilrubricaapp2.Model.InfoNivel;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.ElementoElementinfoFilaBinding;

/**
 * Created by fdjvf on 4/17/2017.
 */

public class ElementInfoListAdapter<T> extends ListAdapter {

    public ElementInfoListAdapter(ObservableArrayList<T> list) {
        super(list);
    }

    @BindingAdapter("bind:NewInfoElement")
    public static void bindList(ListView view, ObservableArrayList<InfoNivel> list) {
        ElementInfoListAdapter adapter = new ElementInfoListAdapter<InfoNivel>(list);
        view.setAdapter(adapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        ElementoElementinfoFilaBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.elemento_elementinfo_fila, parent, false);
        binding.setElementInfo((InfoNivel) list.get(position));
        return binding.getRoot();
    }


}

package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.edu.uninorte.movilrubricaapp2.Model.Estudiante;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.CursoAddstudentRowBinding;


/**
 * Created by fdjvf on 4/13/2017.
 */

public class StudentAddAdapter extends ListAdapter {

    protected ObservableArrayList<Estudiante> list;

    public StudentAddAdapter(ObservableArrayList<Estudiante> list) {
        super();
        this.list = list;
    }

    @BindingAdapter("bind:NewStudent")
    public static void bindList(ListView view, ObservableArrayList<Estudiante> list) {
        StudentAddAdapter adapter = new StudentAddAdapter(list);
        view.setAdapter(adapter);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        CursoAddstudentRowBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.curso_addstudent_row, parent, false);
        binding.DeleteStudentRow.setTag(position);
        binding.setStudenthint(list.get(position));
        return binding.getRoot();
    }
}

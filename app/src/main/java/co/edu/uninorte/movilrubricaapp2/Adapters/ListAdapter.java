package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by fdjvf on 4/13/2017.
 */

public class ListAdapter extends BaseAdapter {

    protected ObservableArrayList<Object> list;
    protected LayoutInflater inflater;

    public ListAdapter(ObservableArrayList<Object> list) {
        this.list = list;
    }

    public ListAdapter() {

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

        return null;
    }
}

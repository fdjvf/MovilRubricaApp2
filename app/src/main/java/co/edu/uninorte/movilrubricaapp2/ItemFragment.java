package co.edu.uninorte.movilrubricaapp2;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.edu.uninorte.movilrubricaapp2.Adapters.CourseListAdapter;
import co.edu.uninorte.movilrubricaapp2.Adapters.RubricaListAdapter;
import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.databinding.FragmentListMainBinding;

/**
 * A fragment representing a ObservableListCategoria of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListElementClick}
 * interface.
 */
public class ItemFragment extends ListFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_PAGE = "pagina";
    public ObservableArrayList<Object> list;
    //private static final String ARG_LISTA = "vector";
    // TODO: Customize parameters
    private int pagina = 1;
    private OnListElementClick mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    @BindingAdapter("bind:CourseItems")
    public static void bindList(ListView view, ObservableArrayList<Object> list) {
        int pag = (int) view.getTag();
        switch (pag) {
            case 1:
                //        Asignatura.GetAll();
                view.setAdapter(new CourseListAdapter(Asignatura.ObserVableAsignaturas));
                break;
            case 2:
                //         Rubrica.GetAll();
                view.setAdapter(new RubricaListAdapter(Rubrica.ObservableListRubrica));
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentListMainBinding bindingFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_list_main, container, false);

        switch (pagina) {
            case 1:
                Asignatura.GetAll();
                list = Asignatura.ObserVableAsignaturas;
                break;
            case 2:
                Rubrica.GetAll();
                list = Rubrica.ObservableListRubrica;
                break;
        }

        bindingFragment.setCourse(this);
        return bindingFragment.getRoot();
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pagina = getArguments().getInt(ARG_PAGE);

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.onListFragmentInteraction(position);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListElementClick) {
            mListener = (OnListElementClick) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListElementClick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListElementClick {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position);
    }
}

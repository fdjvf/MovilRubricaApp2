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

import co.edu.uninorte.movilrubricaapp2.Adapters.EstudianteListAdapter;
import co.edu.uninorte.movilrubricaapp2.Adapters.EvaluacionListAdapter;
import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.databinding.FragmentListEvalestBinding;



/**
 * Created by Melanis on 18/04/2017.
 */

public class ItemFragmentEvalEst extends ListFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_PAGE = "pagina";
    private static final String ARG_COURSEID = "myCourse";
    private static String myCourse;
    public ObservableArrayList<Object> list;
    //private static final String ARG_LISTA = "vector";
    // TODO: Customize parameters
    private int pagina = 1;
    private OnListElementClick2 mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragmentEvalEst() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragmentEvalEst newInstance(int columnCount, String course) {
        ItemFragmentEvalEst fragment = new ItemFragmentEvalEst();
        Bundle args = new Bundle();
    //course.ObservableEstudiantesCurso;

        args.putInt(ARG_PAGE, columnCount);
        args.putString(ARG_COURSEID, course);
        fragment.setArguments(args);
        return fragment;
    }
//TODO:Crear Adapters y asignarlos
    @BindingAdapter("bind:EvalItems")
    public static void bindList(ListView view, ObservableArrayList<Object> list) {
        int pag = (int) view.getTag();

        Asignatura as = Asignatura.FindOne(myCourse);

        //lalala
        switch (pag) {
            case 1:
                //as.getEvaluaciones();
                view.setAdapter(new EvaluacionListAdapter(as.ObservableEvaluacionesCurso));
                break;
            case 2:
                //as.getEstudiante();
                view.setAdapter(new EstudianteListAdapter(as.ObservableEstudiantesCurso));
                break;

        }
    }

    //TODO:Asignar las listas observables correspondientes
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentListEvalestBinding bindingFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_list_evalest, container, false);
        Asignatura as = Asignatura.FindOne(myCourse);


        switch (pagina) {
            case 1:
               //as.getEvaluaciones();
                list = as.ObservableEvaluacionesCurso;
                break;
            case 2:
             //as.getEstudiante();
                list = as.ObservableEstudiantesCurso;
                break;
        }

       bindingFragment.setEval(this);
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
            myCourse=getArguments().getString(ARG_COURSEID);

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.onListFragmentInteraction(position);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListElementClick2) {
            mListener = (OnListElementClick2) context;
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
    public interface OnListElementClick2 {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position);
    }
}

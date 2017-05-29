package co.edu.uninorte.movilrubricaapp2.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.edu.uninorte.movilrubricaapp2.CategoriaPesoCategoria;
import co.edu.uninorte.movilrubricaapp2.ElementoPesoElemento;
import co.edu.uninorte.movilrubricaapp2.R;
import co.edu.uninorte.movilrubricaapp2.databinding.ElementoPorcentajeFilaBinding;
import co.edu.uninorte.movilrubricaapp2.databinding.EvaluacionCategoriaFilaBinding;
import co.edu.uninorte.movilrubricaapp2.databinding.EvaluacionPesocategoriaInputBinding;

/**
 * Created by fdjvf on 4/19/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public List<CategoriaPesoCategoria> pesoCategorias;
    Context context;
    HashMap<Integer, ArrayList<ElementoPesoElemento>> pesoElementos;

    public ExpandableListAdapter(Context context, List<CategoriaPesoCategoria> pesoCategorias, HashMap<Integer, ArrayList<ElementoPesoElemento>> hash) {
        this.context = context;
        this.pesoCategorias = pesoCategorias;
        this.pesoElementos = hash;

    }

    @Override
    public int getGroupCount() {
        return pesoCategorias.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return pesoElementos.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return pesoCategorias.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return pesoElementos.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final CategoriaPesoCategoria pcp = pesoCategorias.get(groupPosition);
        EvaluacionCategoriaFilaBinding evaluacionCategoriaFilaBinding;


        final LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        evaluacionCategoriaFilaBinding = DataBindingUtil.inflate(infalInflater, R.layout.evaluacion_categoria_fila, parent, false);


        evaluacionCategoriaFilaBinding.setNombreCategoria(pcp.categoria);
        evaluacionCategoriaFilaBinding.setCategoriapesoModel(pcp.pesoCategoria);


        evaluacionCategoriaFilaBinding.lblListHeader.setTypeface(null, Typeface.BOLD);

        evaluacionCategoriaFilaBinding.pesoCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder Alertbuilder = new AlertDialog.Builder(
                        context, R.style.Theme_AppCompat_Dialog_Alert);
                EvaluacionPesocategoriaInputBinding texboxinputBinding;

                texboxinputBinding = DataBindingUtil.inflate(infalInflater, R.layout.evaluacion_pesocategoria_input, null, false);
                texboxinputBinding.setPesoCategoria(pcp.pesoCategoria);
                Alertbuilder.setTitle("Ingresar peso de categoria");
                Alertbuilder.setCancelable(false);
                Alertbuilder.setView(texboxinputBinding.getRoot());
                Alertbuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                Alertbuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                AlertDialog dialog = Alertbuilder.create();
                dialog.show();


            }
        });


        return evaluacionCategoriaFilaBinding.getRoot();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        List<ElementoPesoElemento> epe = pesoElementos.get(groupPosition);
        ElementoPorcentajeFilaBinding elementoPorcentajeFilaBinding;

            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        elementoPorcentajeFilaBinding = DataBindingUtil.inflate(infalInflater, R.layout.elemento_porcentaje_fila, parent, false);

        elementoPorcentajeFilaBinding.setNombrElemento(epe.get(childPosition).elemento);
        elementoPorcentajeFilaBinding.setPesoElementoModel(epe.get(childPosition).pesoElemento);


        return elementoPorcentajeFilaBinding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

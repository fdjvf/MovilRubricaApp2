package co.edu.uninorte.movilrubricaapp2;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import co.edu.uninorte.movilrubricaapp2.Adapters.ElementInfoListAdapter;
import co.edu.uninorte.movilrubricaapp2.Model.Categoria;
import co.edu.uninorte.movilrubricaapp2.Model.Elemento;
import co.edu.uninorte.movilrubricaapp2.Model.InfoNivel;
import co.edu.uninorte.movilrubricaapp2.databinding.ElementoCreacionActivityBinding;
import co.edu.uninorte.movilrubricaapp2.databinding.ElementoCreacionContentBinding;
import co.edu.uninorte.movilrubricaapp2.databinding.ElementoDescripcionInputBinding;

public class ElementoCreacion extends AppCompatActivity {


    public ObservableArrayList<Object> ElementInfoList;
    ElementoDescripcionInputBinding texboxinputBinding;
    ElementoCreacionContentBinding elementoCreacionContentBinding;
    Elemento elemento;
    int Nivel = 0;
    boolean isEditable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ElementoCreacionActivityBinding elementoCreacionActivityBinding = DataBindingUtil.setContentView(this, R.layout.elemento_creacion_activity);
        elementoCreacionContentBinding = elementoCreacionActivityBinding.elementoContent;
        Intent intent = getIntent();
        ElementInfoList = new ObservableArrayList<>();
        isEditable = intent.getBooleanExtra("Edicion", true);
        Nivel = intent.getIntExtra("Nivel", 1);
        if (isEditable) {
            elemento = intent.getParcelableExtra("elementoedit");
        } else {
            Categoria categoria = (Categoria) intent.getSerializableExtra("Categoria");
            elemento = new Elemento("", categoria.ObservableListElements.size());


        }
        LoadList();
        Toolbar toolbar = elementoCreacionActivityBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        elementoCreacionContentBinding.setElementonewBinding(elemento);
        elementoCreacionContentBinding.setInfoelementList(this);
        elementoCreacionContentBinding.ElementosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = position;
                final AlertDialog.Builder Alertbuilder = new AlertDialog.Builder(
                        ElementoCreacion.this, R.style.Theme_AppCompat_Dialog_Alert);
                texboxinputBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.elemento_descripcion_input, null, false);
                ((InfoNivel) ElementInfoList.get(pos)).setDescripcion("");
                texboxinputBinding.setDescripcionInfoNivel((InfoNivel) ElementInfoList.get(pos));

                Alertbuilder.setTitle("Ingresar descripcion");
                Alertbuilder.setCancelable(false);
                Alertbuilder.setView(texboxinputBinding.getRoot());
                Alertbuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ElementInfoListAdapter.bindList(elementoCreacionContentBinding.ElementosListView, ElementInfoList);
                    }
                });

                Alertbuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((InfoNivel) ElementInfoList.get(pos)).setDescripcion("Breve Descripción");
                        ElementInfoListAdapter.bindList(elementoCreacionContentBinding.ElementosListView, ElementInfoList);

                    }
                });
                AlertDialog dialog = Alertbuilder.create();
                dialog.show();

            }
        });
    }

    public void LoadList() {
        //Modo edicion o modo nuevo
        if (isEditable) {

            ElementInfoList.addAll(elemento.ObservableDescricionNivel);
        } else {
            for (int i = 1; i <= Nivel; i++) {
                ElementInfoList.add(new InfoNivel("Breve Descripcion", i));
            }
        }

    }

    private void SaveObserVableInfoElements() {
        for (int i = 0; i < ElementInfoList.size(); i++) {
            InfoNivel t = (InfoNivel) ElementInfoList.get(i);
            elemento.ObservableDescricionNivel.set(i, t);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = getIntent();

        if (isEditable) {

            SaveObserVableInfoElements();

            myIntent.putExtra("editcElemento", elemento);

            setResult(RESULT_OK, myIntent);
        } else {
            if (!elemento.getName().isEmpty()) {

                SaveObserVableInfoElements();
                myIntent.putExtra("NuevoElemento", elemento);
                setResult(RESULT_OK, myIntent);
            } else {

                setResult(RESULT_CANCELED, myIntent);
            }
        }
        finish();
        return true;
    }
}

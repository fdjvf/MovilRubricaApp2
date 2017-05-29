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


    public ObservableArrayList<Object> ElementList = new ObservableArrayList<>();
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
        isEditable = intent.getBooleanExtra("Edicion", true);

        if (isEditable) {
            long idele = intent.getLongExtra("elementoedit", 1);
            elemento = Elemento.findById(Elemento.class, idele);
        } else {
            long id = intent.getLongExtra("Categoria", 0);
            Categoria categoria = Categoria.findById(Categoria.class, id);
            Nivel = categoria.rubrica.EscalaMaxima;
            elemento = new Elemento();
            elemento.setName("");
            elemento.setCategoria(categoria);
            elemento.save();

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
                ((InfoNivel) ElementList.get(pos)).setDescripcion("");
                texboxinputBinding.setDescripcionInfoNivel((InfoNivel) ElementList.get(pos));

                Alertbuilder.setTitle("Ingresar descripcion");
                Alertbuilder.setCancelable(false);
                Alertbuilder.setView(texboxinputBinding.getRoot());
                Alertbuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ElementInfoListAdapter.bindList(elementoCreacionContentBinding.ElementosListView, ElementList);
                    }
                });

                Alertbuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((InfoNivel) ElementList.get(pos)).setDescripcion("Breve Descripción");
                        ElementInfoListAdapter.bindList(elementoCreacionContentBinding.ElementosListView, ElementList);

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
            elemento.getInfoNivel();
            ElementList.addAll(elemento.ObservableDescricionNivel);
        } else {
            for (int i = 1; i <= Nivel; i++) {
                ElementList.add(new InfoNivel("Breve Descripcion", i));
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = getIntent();

        if (isEditable) {
            elemento.save();
            for (int i = 0; i < ElementList.size(); i++) {
                ((InfoNivel) ElementList.get(i)).setElemento(elemento);
                ((InfoNivel) ElementList.get(i)).save();
                //Validaciones de que este vacio
            }

            myIntent.putExtra("editcElemento", elemento.getId());

            setResult(RESULT_OK, myIntent);
        } else {
            if (!elemento.getName().isEmpty()) {
                elemento.save();
                for (int i = 1; i < Nivel; i++) {
                    ((InfoNivel) ElementList.get(i - 1)).setElemento(elemento);
                    ((InfoNivel) ElementList.get(i - 1)).save();
                    //Validaciones de que este vacio
                }

                myIntent.putExtra("NuevoElemento", elemento.getId());
                setResult(RESULT_OK, myIntent);
            } else {
                elemento.delete();
                setResult(RESULT_CANCELED, myIntent);
            }
        }
        finish();
        return true;
    }
}

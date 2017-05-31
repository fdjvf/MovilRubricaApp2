package co.edu.uninorte.movilrubricaapp2;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.firebase.database.DatabaseReference;

import co.edu.uninorte.movilrubricaapp2.Model.App;
import co.edu.uninorte.movilrubricaapp2.Model.Categoria;
import co.edu.uninorte.movilrubricaapp2.Model.Elemento;
import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.databinding.CategoriaCreacionActivityBinding;
import co.edu.uninorte.movilrubricaapp2.databinding.CategoriaCreacionContentBinding;
import co.edu.uninorte.movilrubricaapp2.databinding.CategoriaDescripcionInputBinding;


public class CategoriaCreacion extends AppCompatActivity {

    public ObservableArrayList<Object> ElementList = new ObservableArrayList<>();
    CategoriaDescripcionInputBinding texboxinputBinding;
    Categoria categoria;
    Rubrica myrubrica;
    DatabaseReference mRubricas;
    int Nivel = 0;
    boolean IsEditable;
    boolean isNew;
    boolean elementedited;
    int LastClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final CategoriaCreacionActivityBinding categoriaCreacionActivityBinding = DataBindingUtil.setContentView(this, R.layout.categoria_creacion_activity);
        final CategoriaCreacionContentBinding categoriaCreacionContentBinding = categoriaCreacionActivityBinding.categoriaContent;

        mRubricas = App.getRubricas();
        Intent intent = getIntent();
        IsEditable = intent.getBooleanExtra("Edicion", true);
        isNew = intent.getBooleanExtra("Nuevo", true);


        if (!isNew) {
            categoriaCreacionActivityBinding.AgregarElemento.setEnabled(false);
        }
        if (IsEditable) {


            categoria = (Categoria) getIntent().getSerializableExtra("CateEdit");
            ElementList = categoria.ObservableListElements;

        } else {
            myrubrica = (Rubrica) intent.getSerializableExtra("Rubrica");
            Nivel = myrubrica.EscalaMaxima;
            categoria = new Categoria("", "", myrubrica.ObservableListCategorias.size());


        }
        Toolbar toolbar = categoriaCreacionActivityBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoriaCreacionContentBinding.setCategoriabinding(categoria);
        categoriaCreacionContentBinding.setElementList(this);
        categoriaCreacionContentBinding.ElementosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent temp = new Intent(CategoriaCreacion.this, ElementoCreacion.class);
                temp.putExtra("Edicion", true);
                temp.putExtra("Nivel", Nivel);
                elementedited = true;
                LastClicked = position;
                Elemento elemento = (Elemento) ElementList.get(position);
                temp.putExtra("elementoedit", elemento);
                startActivityForResult(temp, 1);


            }
        });

        categoriaCreacionActivityBinding.AgregarElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Actividad de creacion de elementos
                Intent temp = new Intent(CategoriaCreacion.this, ElementoCreacion.class);
                temp.putExtra("Edicion", false);
                temp.putExtra("Categoria", categoria);
                temp.putExtra("Nivel", Nivel);
                startActivityForResult(temp, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (elementedited) {
        //TODO: mas tarde

            Elemento elemento = (Elemento) data.getSerializableExtra("editcElemento");
            categoria.ObservableListElements.set(elemento.getUID(), elemento);

            elementedited = false;
        } else {

            if (resultCode == RESULT_OK) {

                Elemento newElement = (Elemento) data.getSerializableExtra("NuevoElemento");
                categoria.ObservableListElements.add(newElement);

            }


        }
        //super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Agrega todo
        //Verificar el nombre del item
        if (item.getTitle() != null) {

            final AlertDialog.Builder Alertbuilder = new AlertDialog.Builder(
                    CategoriaCreacion.this, R.style.Theme_AppCompat_Dialog_Alert);
            texboxinputBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.categoria_descripcion_input, null, false);
            texboxinputBinding.setDescripcionCategoria(categoria);
            Alertbuilder.setTitle("Ingresar descripcion");
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
                    categoria.setDescripcion("");

                }
            });
            AlertDialog dialog = Alertbuilder.create();
            dialog.show();
        } else {

            Intent myIntent = getIntent();
            if (IsEditable) {
                //TODO: FABIO NO SÉ QUÉ HACE ESTO ,AMARTE
                myIntent.putExtra("editcategoria", categoria);
                setResult(RESULT_OK, myIntent);
            } else {
                if (!categoria.ObservableListElements.isEmpty()) {
                    if (categoria.getDescripcion().isEmpty()) {
                        categoria.setDescripcion("Breve Descripción");
                    }
                    if (categoria.getName().isEmpty()) {
                        categoria.setName("Categoria " + myrubrica.ObservableListCategorias.size());
                    }

                    myIntent.putExtra("NewCategoria", categoria);

                    setResult(RESULT_OK, myIntent);
                } else {
                    setResult(RESULT_CANCELED, myIntent);

                }

            }

            finish();
        }

        return true;
    }
}
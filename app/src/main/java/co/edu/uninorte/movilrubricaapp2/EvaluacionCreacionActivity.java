package co.edu.uninorte.movilrubricaapp2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.edu.uninorte.movilrubricaapp2.Adapters.ExpandableListAdapter;
import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.PesoCategoria;
import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.PesoElemento;
import co.edu.uninorte.movilrubricaapp2.Model.Categoria;
import co.edu.uninorte.movilrubricaapp2.Model.CategoriaPesoCategoria;
import co.edu.uninorte.movilrubricaapp2.Model.Elemento;
import co.edu.uninorte.movilrubricaapp2.Model.ElementoPesoElemento;
import co.edu.uninorte.movilrubricaapp2.Model.Evaluacion;
import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.databinding.EvaluacionCreacionActivityBinding;

/**
 * Created by fdjvf on 4/19/2017.
 */

public class EvaluacionCreacionActivity extends AppCompatActivity {
    Evaluacion evaluacion;
    Rubrica r;
    Asignatura asignatura;
    EvaluacionCreacionActivityBinding binding;
    List<CategoriaPesoCategoria> categoriaPesoCategorias;
    HashMap<Integer, ArrayList<ElementoPesoElemento>> miHash;
    private Boolean doSomething = false;
    ArrayList<PesoCategoria> pesosxCateg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.evaluacion_creacion_activity);


        Intent i = getIntent();
        String myId = i.getStringExtra("myCourseId");
        asignatura = Asignatura.FindOne(myId);
        evaluacion = new Evaluacion();


        List<String> strings = Rubrica.getListNames();
        strings.add(0, "Seleccione categoria");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerRubricas.setAdapter(adapter);
        binding.spinnerRubricas.setSelection(0);
        binding.spinnerRubricas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (!doSomething) {
                    doSomething = true;
                } else {
                    r = (Rubrica) Rubrica.ObservableListRubrica.get(position - 1);
                    List<Object> categorias = r.ObservableListCategorias;
                    categoriaPesoCategorias = new ArrayList<CategoriaPesoCategoria>();
                    miHash = new HashMap<Integer, ArrayList<ElementoPesoElemento>>();
                    pesosxCateg = new ArrayList<>();

                    for (int i = 0; i < categorias.size(); i++) {
                        Categoria cat = (Categoria) categorias.get(i);

                        CategoriaPesoCategoria catpeso = new CategoriaPesoCategoria();
                        PesoCategoria pcat = new PesoCategoria();

                        pcat.setPeso("");
                        pcat.categoria = cat;
                        // pcat.categoria = cat;
                        // pcat.evaluacion = evaluacion;


                        //Rellena arraylist para la evaluación


                        //Rellenar CatPespCat
                        catpeso.categoria = cat;
                        catpeso.pesoCategoria = pcat;

                        categoriaPesoCategorias.add(catpeso);

                        List<Object> elementos = cat.ObservableListElements;
                        ArrayList<ElementoPesoElemento> elementoPesoElementos = new ArrayList<>();
                        ArrayList<PesoElemento> pesosxElemen= new ArrayList<>();
                        for (int j =0; j<elementos.size();j++) {
                            Elemento var = (Elemento) elementos.get(j);

                            PesoElemento pesoElemento = new PesoElemento();

                            pesoElemento.setPeso("");
                            pesosxElemen.add(pesoElemento);
                            //pesoElemento.pesoCategoria = pcat;

                            ElementoPesoElemento elepeso = new ElementoPesoElemento();
                            elepeso.elemento = var;
                            elepeso.pesoElemento = pesoElemento;
                            elementoPesoElementos.add(elepeso);

                        }
                        pcat.pesoElementos= pesosxElemen;
                        pesosxCateg.add(pcat);

                        miHash.put(i, elementoPesoElementos);

                    }

                    ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(EvaluacionCreacionActivity.this, categoriaPesoCategorias, miHash);
                    binding.exprubrica.setAdapter(expandableListAdapter);
                    binding.spinnerRubricas.setEnabled(false);
                    binding.nombreEvalEt.setFocusable(true);
                    //TODO: desactivar el spinner cuando cargue la rubrica
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.setNombreEval(evaluacion);
        asignatura.ObservableEvaluacionesCurso.add(evaluacion);
        // evaluacion.setAsignatura(asignatura);
        binding.nombreEvalEt.setFocusable(false);
        Toolbar toolbar = binding.toolbar3;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String name = binding.nombreEvalEt.getText().toString();
        if (!name.isEmpty()) {
            float Porcentaje = 0f;
            int i = 0;
            for (CategoriaPesoCategoria var : categoriaPesoCategorias) {

                PesoCategoria pesoCategoria = var.pesoCategoria;
                if (pesoCategoria.getPeso().isEmpty()) {
                    Toast.makeText(EvaluacionCreacionActivity.this, "Por favor rellene todos los campos", Toast.LENGTH_LONG).show();
                    break;
                } else {

                    Porcentaje = Porcentaje + pesoCategoria.getPesoFloat();
                    if (Porcentaje > 100) {
                        Toast.makeText(EvaluacionCreacionActivity.this, "La suma de porcentajes no puede ser de 100%", Toast.LENGTH_LONG).show();
                        break;
                    }
                    ArrayList<ElementoPesoElemento> PesosElemento = miHash.get(i);
                    float SumaElementoPeso = 0f;
                    for (ElementoPesoElemento peso : PesosElemento) {
                        if (peso.pesoElemento.getPeso().isEmpty()) {
                            Toast.makeText(EvaluacionCreacionActivity.this, "Por favor rellene todos los campos", Toast.LENGTH_LONG).show();
                            return true;
                        } else {
                            SumaElementoPeso = peso.pesoElemento.getPesoFloat() + SumaElementoPeso;

                            if (SumaElementoPeso > 100) {
                                Toast.makeText(EvaluacionCreacionActivity.this, "La suma de porcentajes no puede ser mayor de 100%", Toast.LENGTH_LONG).show();
                                return true;
                            }
                        }
                    }
                    if (SumaElementoPeso != 100f) {
                        Toast.makeText(EvaluacionCreacionActivity.this, "La suma de porcentajes debe ser igual a 100%", Toast.LENGTH_LONG).show();
                        break;
                    }


                }
                i++;
            }
            if (Porcentaje != 100) {
                Toast.makeText(EvaluacionCreacionActivity.this, "La suma de porcentajes debe ser igual a 100%", Toast.LENGTH_LONG);
                return true;
            }
            //Si llego hasta aca, es que puede guardar
            i = 0;
            evaluacion.setRubrica(r);
            //evaluacion.save();

            pesosxCateg= new ArrayList<>();
            for (CategoriaPesoCategoria var : categoriaPesoCategorias) {
                PesoCategoria pesoCategoria = var.pesoCategoria;
                // ArrayList<ElementoPesoElemento> PesosElemento = miHash.get(i);
                //ElementoPesoElemento.SaveList(PesosElemento);
                //i++;
                pesosxCateg.add(pesoCategoria);

            }
            evaluacion.setPesoCategorias(pesosxCateg);
            asignatura.ObservableEvaluacionesCurso.add(evaluacion);

            Intent mt = getIntent();
            //mt.putExtra("EvalId", evaluacion.getId());
            setResult(RESULT_OK, getIntent());
            finish();
            return true;

        } else {

            //evaluacion.delete();
        }
        finish();
        return true;
    }


}

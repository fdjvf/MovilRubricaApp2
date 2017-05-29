package co.edu.uninorte.movilrubricaapp2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orm.SugarContext;

import java.io.Serializable;

import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.Model.Calificacion;
import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.CalCategoria;
import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.CalElemento;
import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.PesoCategoria;
import co.edu.uninorte.movilrubricaapp2.Model.Calificaciones.PesoElemento;
import co.edu.uninorte.movilrubricaapp2.Model.Categoria;
import co.edu.uninorte.movilrubricaapp2.Model.Elemento;
import co.edu.uninorte.movilrubricaapp2.Model.Estudiante;
import co.edu.uninorte.movilrubricaapp2.Model.InfoNivel;
import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListElementClick, Serializable {


    MainActivityBinding binding;
    Asignatura t;
    boolean EditingRubrica;
    MyPagerAdapter myPagerAdapter;
    boolean EditingCurso;

    public static void fill() {
        Asignatura.deleteAll(Asignatura.class);
        Rubrica.deleteAll(Rubrica.class);
        Estudiante.deleteAll(Estudiante.class);
        Categoria.deleteAll(Categoria.class);
        Elemento.deleteAll(Elemento.class);
        InfoNivel.deleteAll(InfoNivel.class);
        PesoCategoria.deleteAll(PesoCategoria.class);
        PesoElemento.deleteAll(PesoElemento.class);
        CalElemento.deleteAll(CalElemento.class);
        CalCategoria.deleteAll(CalCategoria.class);
        Calificacion.deleteAll(Calificacion.class);
        for (int i = 1; i < 11; i++) {
            Rubrica r = new Rubrica();
            r.setName("Rubrica " + i);
            r.Save();

            for (int j = 1; j < 3; j++) {
                Categoria cat = new Categoria();
                cat.setName("Categoria " + j);
                cat.rubrica = r;
                cat.save();

                for (int k = 1; k < 3; k++) {
                    Elemento el = new Elemento();
                    el.setName("Elemento " + k);
                    el.categoria = cat;
                    el.save();
                }
            }
        }
        for (int i = 1; i < 10; i++) {
            Asignatura temp = new Asignatura();
            temp.setDescription("Brevee Descripcion");
            temp.setName("Curso " + i);
            temp.Save();

            for (int j = 1; j < 5; j++) {
                Estudiante estudiante = new Estudiante();
                estudiante.setName("Estudiante " + j);
                estudiante.setAsignatura(temp);
                estudiante.setState(false);
                estudiante.save();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SugarContext.init(this);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        fill();
        //Cuando se crea el elemento ya queda registradoo, puede modificarlo y luego guardarlo de nuevoo
        binding.viewpager.setAdapter(myPagerAdapter);

        // binding.CoursesList.setSelection(0);//Permite que la lista comience en una posicion espeficia


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            binding.viewpager.setAdapter(myPagerAdapter);
            binding.viewpager.setCurrentItem(1);
            //     ItemFragment.bindList(   myPagerAdapter.Rubricas.getListView(),Rubrica.ObservableListRubrica);
        }
    }

    public void StartNewCreationActivity(View view) {

        int page = binding.viewpager.getCurrentItem();


        if (page == 0) {

            Intent myIntent = new Intent(this, NewCourse.class);
            startActivity(myIntent);

        } else {

            Intent myIntent = new Intent(this, RubricaCreacion.class);
            myIntent.putExtra("Edicion", false);
            myIntent.putExtra("Nuevo", true);
            startActivity(myIntent);

        }
    }

    @Override
    public void onListFragmentInteraction(int position) {
        int page = binding.viewpager.getCurrentItem();
        if (page == 0) {
            Intent myIntent = new Intent(this, EvaluacionEstudianteActivity.class);
            Asignatura selectedCourse= (Asignatura) Asignatura.ObserVableAsignaturas.get(position);
            long selectedCourseId= selectedCourse.getId();
            myIntent.putExtra("myCourseId", selectedCourseId);
            startActivity(myIntent);
            EditingCurso = true;
        } else {

            Intent myIntent = new Intent(this, RubricaCreacion.class);
            Rubrica selectedRubrica = (Rubrica) Rubrica.ObservableListRubrica.get(position);
            long idRubrica = selectedRubrica.getId();
            myIntent.putExtra("Edicion", true);
            EditingRubrica = true;
            myIntent.putExtra("Nuevo", false);
            myIntent.putExtra("rubricaId", idRubrica);
            startActivityForResult(myIntent, 1);
            //Comenzar actividad para la rubrica
        }

    }

}

package co.edu.uninorte.movilrubricaapp2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.Serializable;

import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListElementClick, Serializable {


    MainActivityBinding binding;
    MyPagerAdapter myPagerAdapter;


    public static void fill() {

     /*   for (int i = 1; i < 11; i++) {
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
*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        fill();
        binding.viewpager.setAdapter(myPagerAdapter);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            binding.viewpager.setAdapter(myPagerAdapter);
            binding.viewpager.setCurrentItem(1);

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
            Asignatura selectedCourse = (Asignatura) Asignatura.ObserVableAsignaturas.get(position);
            myIntent.putExtra("Course", selectedCourse);
            startActivity(myIntent);

        } else {

            Intent myIntent = new Intent(this, RubricaCreacion.class);
            Rubrica selectedRubrica = (Rubrica) Rubrica.ObservableListRubrica.get(position);
            myIntent.putExtra("Edicion", true);
            myIntent.putExtra("Nuevo", false);
            myIntent.putExtra("Rubrica", selectedRubrica);
            startActivityForResult(myIntent, 1);
            //Comenzar actividad para la rubrica
        }

    }

}

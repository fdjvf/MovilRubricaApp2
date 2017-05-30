package co.edu.uninorte.movilrubricaapp2;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import co.edu.uninorte.movilrubricaapp2.Adapters.EstudianteListAdapter;
import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.databinding.CalificacionEstudiantesActivityBinding;

public class EstudiantesCalificacionActivity extends AppCompatActivity {
    public static long actualCourse;
    public static Asignatura as;


    @BindingAdapter("bind:EstudianteItems")
    public static void bindList(ListView view, ObservableArrayList<Object> list) {
        as = Asignatura.FindOne(String.valueOf(actualCourse));
        view.setAdapter(new EstudianteListAdapter(as.ObservableEstudiantesCurso));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        actualCourse = intent.getLongExtra("myCourseId", 0);
        as = Asignatura.FindOne(String.valueOf(actualCourse));
        CalificacionEstudiantesActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.calificacion_estudiantes_activity);
        binding.setNombreEst(as);
        binding.mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EstudiantesCalificacionActivity.this, "SAD", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EstudiantesCalificacionActivity.this, calificacionevaluacion.class);


                //Calificacion de evaluacion
            }
        });

    }
}

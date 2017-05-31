package co.edu.uninorte.movilrubricaapp2;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.Model.Estudiante;
import co.edu.uninorte.movilrubricaapp2.databinding.CursoActivityNuevoCursoBinding;


public class NewCourse extends AppCompatActivity {

    public ObservableArrayList<Estudiante> CourseStudents = new ObservableArrayList<>();
    public Estudiante FirstStudent = new Estudiante();
    public Asignatura newAsignatura;
    CursoActivityNuevoCursoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.curso_activity_nuevo_curso);

        newAsignatura = new Asignatura();
        binding.setStudentModel(this);
        binding.setCoursemodel(newAsignatura);
        binding.setFirststudent(FirstStudent);

    }


    public void NewEntryStudent(View view) {
        //    StudentHints.add(new StudentHint("Estudiante " + StudentHint.Count));//Floatin Button
   /*     Estudiante estudiante = new Estudiante();
        estudiante.setName(FirstStudent.getName());
        binding.StudentNametbx.setText("");

        CourseStudents.add(estudiante);*/
    }

    public void SaveCourse(View view) {
        newAsignatura.ObservableEstudiantesCurso.addAll(CourseStudents);
        newAsignatura.Save();
        this.finish();


    }

    public void DeleteEntryStudent(View view) {
        int pos = (int) view.getTag();
        CourseStudents.remove(pos);
        //Remueve un estudiante
    }

}

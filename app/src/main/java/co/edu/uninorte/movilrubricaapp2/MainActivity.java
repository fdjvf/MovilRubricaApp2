package co.edu.uninorte.movilrubricaapp2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import co.edu.uninorte.movilrubricaapp2.Model.App;
import co.edu.uninorte.movilrubricaapp2.Model.Asignatura;
import co.edu.uninorte.movilrubricaapp2.Model.Rubrica;
import co.edu.uninorte.movilrubricaapp2.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListElementClick, Serializable {


    private static final int RC_SIGN_IN = 123;
    private static final String ADMIN_MELANIS = "iaSM35xJipbjYCogJr9v5VWJ7jX2";
    private static final String ADMIN_FABIO = "cqN8P5na2DXIs0G7BDDzvWjXru12";
    private static final String PROF_MELANIS = "8ASmj5sq5BUYK7LKBkn73u9pKNG2";
    private static final String PROF_FABIO = "yl0n7uOTUWaS2djMEySeGwGbuTq2";
    private static List<String> ADMINS = Arrays.asList(ADMIN_FABIO, ADMIN_MELANIS);
    private static List<String> PROFS = Arrays.asList(PROF_FABIO, PROF_MELANIS);
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
        if (App.getAuth().getCurrentUser() != null) {
            //BeginIntent
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder().
                            setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                            .setTheme(R.style.DarkTheme)
                            .build(), RC_SIGN_IN);
        }

    }

    public void SelectedIntentForUsers() {

        String UID = App.getAuth().getCurrentUser().getUid();

        if (ADMINS.contains(UID)) {
            Log.d("Adming Log", "Inicio sesion admin");
            //// TODO: 5/31/2017 INICIAR SESION CON PODER DE ADMINISTRADOR
            //Inicio con poder de administrador
            //Reacomodar vista del administrador
            //Iniciar Intent de Administrador
        } else if (PROFS.contains(UID)) {
            //// TODO: 5/31/2017 INICIAR SESION CON PODER DE PROFESOR
            //Reacomodar vista del PROFESOR
            //Iniciar Intent de PROFESOR

        } else {
            //// TODO: 5/31/2017 INICIAR SESION COMO ESTUDIANTE
            //Es estudiante
        }
        //  finish();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //// TODO: 5/31/2017 Manejo de pantallas de acuerdo al rol que ha iniciad sesion
        if (resultCode == RESULT_OK) {
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            binding.viewpager.setAdapter(myPagerAdapter);
            binding.viewpager.setCurrentItem(1);
        }
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (requestCode == ResultCodes.OK || requestCode == RC_SIGN_IN) {
            Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
            SelectedIntentForUsers();
            finish();

        } else {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                Toast.makeText(this, "El inicio de sesión ha sido cancelado", Toast.LENGTH_SHORT).show();
                return;
            }
            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
    public void StartNewCreationActivity(View view) {

        int page = binding.viewpager.getCurrentItem();

        //// TODO: 5/31/2017 Manejo de actividades de creación solo disponibles para profesor
        ///TODO DESACTIVAR EL BOTON DE AGREGADO PARA ESTUDIANTES Y COORDINADORES EN EL CASO DE RUBRICAS
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
        //// TODO: 5/31/2017 Manejo de actividades de edicion solo disponibles para profesor
        ///TODO SOLO EL PROFESOR PUEDE EDITAR RUBRICAS y manejar las solicitudes pendientes de estudiantes
        /// TODO EL coordinador solo puede agregar cursos
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

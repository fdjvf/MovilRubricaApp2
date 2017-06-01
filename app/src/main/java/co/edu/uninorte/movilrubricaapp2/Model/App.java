package co.edu.uninorte.movilrubricaapp2.Model;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by fdjvf on 5/29/2017.
 */

public class App extends Application {
    private static App mInstance;
    FirebaseAuth Auth;
    private DatabaseReference Asignaturas;
    private DatabaseReference Rubricas;
    private DatabaseReference connectedRef;

    public static synchronized App getInstance(){
        return mInstance;
    }

    public static DatabaseReference getConnectedRefInstance() {
        return getInstance().connectedRef;
    }

    public static DatabaseReference getAsignaturas() {
        return getInstance().Asignaturas;
    }

    public static DatabaseReference getRubricas() {
        return getInstance().Rubricas;
    }

    public static FirebaseAuth getAuth() {
        return getInstance().Auth;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        mInstance = this;
        Asignaturas = FirebaseDatabase.getInstance().getReference("Asignaturas");
        Asignaturas.keepSynced(true);
        Rubricas = FirebaseDatabase.getInstance().getReference("Rubricas");
        Rubricas.push().setValue("2");
        Rubricas.keepSynced(true);
        Auth = FirebaseAuth.getInstance();
        connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
    }

    public void OnChangedAsignaturas() {
        Asignaturas.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Generar listas de Asignatura
                //Gennerar estudiantes
                //Generar

                Asignatura newAsignatura = dataSnapshot.getValue(Asignatura.class);
                Asignatura.ObserVableAsignaturas.add(newAsignatura);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Asignatura newAsignatura = dataSnapshot.getValue(Asignatura.class);
                for (int i = 0; i < Asignatura.ObserVableAsignaturas.size(); i++) {
                    Asignatura asignatura = (Asignatura) Asignatura.ObserVableAsignaturas.get(i);
                    if (newAsignatura.getUID().equals(asignatura.getUID())) {
                        Asignatura.ObserVableAsignaturas.set(i, newAsignatura);
                        break;
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void OnChangedRubricas() {
        Rubricas.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Rubrica newRubrica = dataSnapshot.getValue(Rubrica.class);
                Rubrica.ObservableListRubrica.add(newRubrica);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Rubrica newRubrica = dataSnapshot.getValue(Rubrica.class);
                for (int i = 0; i < Rubrica.ObservableListRubrica.size(); i++) {
                    Rubrica temp = (Rubrica) Rubrica.ObservableListRubrica.get(i);
                    if (newRubrica.ID.equals(temp.ID)) {

                        Rubrica.ObservableListRubrica.set(i, newRubrica);
                        break;
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}

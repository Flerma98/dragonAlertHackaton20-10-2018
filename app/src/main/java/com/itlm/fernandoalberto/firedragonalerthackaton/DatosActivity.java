package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatosActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Datos> datos;
    AdapterRVDatos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        rv= (RecyclerView)findViewById(R.id.rvDatos);
        rv.setLayoutManager(new LinearLayoutManager(this));

        datos= new ArrayList<>();
        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        adapter= new AdapterRVDatos(datos);
        rv.setAdapter(adapter);

        database.getReference(FireBaseReference.REFERENCIA).child(FireBaseReference.TABLADATOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datos.removeAll(datos);
                for(DataSnapshot snapshot :
                        dataSnapshot.getChildren()){
                    Datos dato= snapshot.getValue(Datos.class);
                    if(dato.getFuego()==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(DatosActivity.this);
                        builder.setMessage("HAY FUEGO EN EL SENSOR (" + dato.getID() + ")");
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    datos.add(dato);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

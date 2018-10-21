package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Usuarios> usuarios;
    AdapterRV adapter;
    EditText txtBNombre;
    Button btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        rv= (RecyclerView)findViewById(R.id.rvUsuarios);
        rv.setLayoutManager(new LinearLayoutManager(this));

        usuarios= new ArrayList<>();
        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        txtBNombre= (EditText)findViewById(R.id.txtBNombre);
        btnBorrar= (Button)findViewById(R.id.btnBorrar);
        adapter= new AdapterRV(usuarios);
        rv.setAdapter(adapter);

        database.getReference(FireBaseReference.REFERENCIA).child(FireBaseReference.TABLAUSUARIOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuarios.removeAll(usuarios);
                for(DataSnapshot snapshot :
                        dataSnapshot.getChildren()){
                    Usuarios usuario= snapshot.getValue(Usuarios.class);

                    usuarios.add(usuario);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference(FireBaseReference.REFERENCIA).child(FireBaseReference.TABLAUSUARIOS).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        usuarios.removeAll(usuarios);
                        for(DataSnapshot snapshot :
                                dataSnapshot.getChildren()){
                            Usuarios usuario= snapshot.getValue(Usuarios.class);
                            if(usuario.getNombre().equals(txtBNombre.getText().toString())){
                                DatabaseReference myRef= database.getReference(FireBaseReference.REFERENCIA);
                                myRef.child(FireBaseReference.TABLAUSUARIOS).child(snapshot.getKey().toString()).removeValue();
                            }
                            usuarios.add(usuario);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}

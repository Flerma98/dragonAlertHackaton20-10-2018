package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registrar_Usuario extends AppCompatActivity {

    EditText txtNombre, txtCorreo;
    Button btnRegistrar;
    static Boolean repetido= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__usuario);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference myRef= database.getReference(FireBaseReference.REFERENCIA);
        txtNombre= (EditText)findViewById(R.id.txtRNombre);
        txtCorreo= (EditText)findViewById(R.id.txtRCorreo);
        btnRegistrar= (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNombre.getText().toString().isEmpty() || txtCorreo.getText().toString().isEmpty()) {
                    Toast.makeText(Registrar_Usuario.this, "Completa los cuadros", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseDatabase database= FirebaseDatabase.getInstance();
                    database.getReference(FireBaseReference.REFERENCIA).child(FireBaseReference.TABLAUSUARIOS).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot :
                                    dataSnapshot.getChildren()){
                                Usuarios usuario= snapshot.getValue(Usuarios.class);
                                if(usuario.getCorreo().equals(txtCorreo.getText().toString())){
                                    repetido= true;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    if(repetido){
                        Toast.makeText(Registrar_Usuario.this, "Ese Correo ya está en uso", Toast.LENGTH_SHORT).show();
                    }else{
                        Llenar();
                        Usuarios usuario= new Usuarios(txtNombre.getText().toString(), txtCorreo.getText().toString());
                        myRef.child(FireBaseReference.TABLAUSUARIOS).push().setValue(usuario);
                        startActivity(new Intent(Registrar_Usuario.this, MainActivity.class));
                    }
                }
            }
        });
    }

    public void Llenar(){
        BaseHelper baseHelper = new BaseHelper(Registrar_Usuario.this, "UsuarioRegistrado", null, 1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
        db.execSQL("delete from Usuario");
        if (db != null) {
            ContentValues registronuevo = new ContentValues();
            registronuevo.put("Registrado", 1);
            long i = db.insert("Usuario", null, registronuevo);
            if (i > 0) {
                //Toast.makeText(this, "Registro Nuevo Insertado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.support.annotation.NonNull;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre, txtCorreo;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            BaseHelper baseHelper = new BaseHelper(this, "UsuarioRegistrado", null, 1);
            SQLiteDatabase db = baseHelper.getWritableDatabase();
            if (db != null) {
                Cursor c= db.rawQuery("select * from Usuario", null);
                if(c.moveToFirst()) {
                        if(c.getInt(0)==1){
                            startActivity(new Intent(MainActivity.this, DatosActivity.class));
                        }
                        }
                }
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference myRef= database.getReference(FireBaseReference.REFERENCIA);
        txtNombre= (EditText)findViewById(R.id.txtRNombre);
        txtCorreo= (EditText)findViewById(R.id.txtRCorreo);
        btnRegistrar= (Button) findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNombre.getText().toString().isEmpty() || txtCorreo.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Completa los cuadros", Toast.LENGTH_SHORT).show();
                }else{
                        BaseHelper baseHelper = new BaseHelper(MainActivity.this, "UsuarioRegistrado", null, 1);
                        SQLiteDatabase db = baseHelper.getWritableDatabase();
                        if (db != null) {
                            ContentValues registronuevo = new ContentValues();
                            registronuevo.put("Registrado", 1);
                            long i = db.insert("Usuario", null, registronuevo);
                            if (i > 0) {
                                //Toast.makeText(this, "Registro Nuevo Insertado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    Usuarios usuario= new Usuarios(txtNombre.getText().toString(), txtCorreo.getText().toString());
                    myRef.child(FireBaseReference.TABLAUSUARIOS).push().setValue(usuario);
                    startActivity(new Intent(MainActivity.this, DatosActivity.class));
                }
            }
        });
    }
}

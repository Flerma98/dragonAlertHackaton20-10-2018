package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.content.ContentValues;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.app.NotificationManager;
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AlertDialog;
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

    Button btnQHA, btnQHDU, btnQHDE, btnTE;
    NotificationCompat.Builder notification;
    private static final int idUnica= 51623;

    RecyclerView rv;
    ArrayList<Datos> datos;
    AdapterRVDatos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnQHA= (Button)findViewById(R.id.btnQHA);
        btnQHDE= (Button)findViewById(R.id.btnQHDE);
        btnQHDU= (Button)findViewById(R.id.btnQHDU);
        btnTE= (Button)findViewById(R.id.btnTE);

        notification= new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);


        btnQHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Que_Hacer_Antes.class));
            }
        });

        btnQHDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity_Que_Hacer_durante.class));
            }
        });

        btnQHDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity_Que_Hacer_Despues.class));
            }
        });

        btnTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity_Telefonos.class));
            }
        });

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
                        notification.setSmallIcon(R.drawable.logo4);
                        notification.setTicker("ALERTA");
                        notification.setWhen(System.currentTimeMillis());
                        notification.setContentTitle("ALERTA DE FUEGO");
                        notification.setContentText("Se detectó fuego en el sensor (" + dato.getID() + ")");
                        Intent intent= new Intent(MainActivity.this, MainActivity.class);
                        PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        notification.setContentIntent(pendingIntent);
                        NotificationManager nm =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(idUnica,notification.build());
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("HAY FUEGO EN EL SENSOR (" + dato.getID() + ")\n\nPORFAVOR TOME PRECAUCIONES");
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

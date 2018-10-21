package com.itlm.fernandoalberto.firedragonalerthackaton;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class Activity_Telefonos extends AppCompatActivity {

    Button btnBOMB, btnEmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__telefonos);
        btnBOMB= (Button)findViewById(R.id.btnBomb);
        btnEmer= (Button)findViewById(R.id.btnEmergencia);

        btnBOMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Llamar("6688164000");
            }
        });
        btnEmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Llamar("911");
            }
        });
    }

    public void Llamar(String numero){
        String phoneNo = numero;
        if(!TextUtils.isEmpty(phoneNo)) {
            String dial = "tel:" + phoneNo;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }else {
            Toast.makeText(Activity_Telefonos.this, "", Toast.LENGTH_SHORT).show();
        }
    }
}

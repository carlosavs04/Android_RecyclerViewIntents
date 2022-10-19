package com.example.recyclerviewintents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.recyclerviewintents.Modelos.Intent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IntentAdaptador.ItemListener {

    String numeroTelefono = "+528713321257";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Intent> i = new ArrayList<>();

        i.add(new Intent("Abrir Google", 1));
        i.add(new Intent("Abrir cámara", 2));
        i.add(new Intent("Abrir mapas", 3));
        i.add(new Intent("Enviar correo electrónico", 4));
        i.add(new Intent("Llamar", 5));
        i.add(new Intent("Mandar información", 6));
        i.add(new Intent("Llamar permiso", 7));

        IntentAdaptador adIntent = new IntentAdaptador(this, i, this);
        LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(adIntent);
        recycler.setLayoutManager(linearManager);
        recycler.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(Intent item) {
        Toast.makeText(getApplicationContext(), item.getId() + " is clicked", Toast.LENGTH_SHORT).show();

        switch (item.getId()) {
            case 1:
                String url = "https://www.google.com.mx/?hl=es-419";
                Uri webGoogle = Uri.parse(url);
                android.content.Intent abrirGoogle = new android.content.Intent(android.content.Intent.ACTION_VIEW, webGoogle);

                if (abrirGoogle.resolveActivity(getPackageManager()) != null) {
                    startActivity(abrirGoogle);
                }
                break;

            case 2:
                android.content.Intent abrirCamara = new android.content.Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                if (abrirCamara.resolveActivity(getPackageManager()) != null) {
                    startActivity(abrirCamara);
                }
                break;

            case 3:
                Uri ubicacion = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                android.content.Intent abrirUbicacion = new android.content.Intent(android.content.Intent.ACTION_VIEW, ubicacion);
                abrirUbicacion.setPackage("com.google.android.apps.maps");
                if (abrirUbicacion.resolveActivity(getPackageManager()) != null) {
                    startActivity(abrirUbicacion);
                }
                break;

            case 4:
                android.content.Intent enviarCorreo = new android.content.Intent(android.content.Intent.ACTION_SEND);
                enviarCorreo.setType("*/*");

                if (enviarCorreo.resolveActivity(getPackageManager()) != null) {
                    startActivity(enviarCorreo);
                }
                break;

            case 5:
                android.content.Intent llamar = new android.content.Intent(android.content.Intent.ACTION_DIAL);
                llamar.setData(Uri.parse("tel:" + numeroTelefono));

                if(llamar.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(llamar);
                }
                break;

            case 6:
                startActivity(new android.content.Intent(this,mailto.class));
                break;

            case 7:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        llamar();
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    break;
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode)
        {
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    llamar();
                }

                else {
                    System.out.println("No se tienen los permisos necesarios para realizar esta acción.");
                }

                return;
        }
    }
    
    public void llamar()
    {
        android.content.Intent llamada = new android.content.Intent(android.content.Intent.ACTION_CALL);
        llamada.setData(Uri.parse("tel:" + numeroTelefono));
        startActivity(llamada);
    }
}
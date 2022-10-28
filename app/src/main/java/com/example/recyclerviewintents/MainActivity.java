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

public class MainActivity extends AppCompatActivity {

    String numeroTelefono = "+528713321257";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://www.google.com.mx/?hl=es-419";
        Uri webGoogle = Uri.parse(url);
        android.content.Intent abrirGoogle = new android.content.Intent(android.content.Intent.ACTION_VIEW, webGoogle);

        android.content.Intent abrirCamara = new android.content.Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);

        Uri ubicacion = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        android.content.Intent abrirUbicacion = new android.content.Intent(android.content.Intent.ACTION_VIEW, ubicacion);
        abrirUbicacion.setPackage("com.google.android.apps.maps");

        android.content.Intent enviarCorreo = new android.content.Intent(android.content.Intent.ACTION_SEND);
        enviarCorreo.setType("*/*");

        android.content.Intent llamar = new android.content.Intent(android.content.Intent.ACTION_DIAL);
        llamar.setData(Uri.parse("tel:" + numeroTelefono));

        android.content.Intent mailto = new android.content.Intent(this, mailto.class);

        List<Intent> i = new ArrayList<>();

        i.add(new Intent("Abrir Google", 1, abrirGoogle));
        i.add(new Intent("Abrir cámara", 2, abrirCamara));
        i.add(new Intent("Abrir mapas", 3, abrirUbicacion));
        i.add(new Intent("Enviar correo electrónico", 4, enviarCorreo));
        i.add(new Intent("Llamar", 5, llamar));
        i.add(new Intent("Mandar información", 6, mailto));

        IntentAdaptador adIntent = new IntentAdaptador(i);
        LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(adIntent);
        recycler.setLayoutManager(linearManager);
        recycler.setHasFixedSize(true);
    }
}
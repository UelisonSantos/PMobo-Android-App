package com.example.uelis.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CriandoTarefa extends AppCompatActivity {

    // Replace below with your own subscription key
    private static String speechSubscriptionKey = "9acd533089504ee3833e448dc3728daa";
    // Replace below with your own service region (e.g., "westus").
    private static String serviceRegion = "westus";

     String projeto;
     String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_listening_criandotarefa);


        projeto = intent.getStringExtra("Projeto");
        nome = intent.getStringExtra("Nome");

        TextView textViewProjeto = findViewById(R.id.textViewProjeto);
        TextView textViewNome = findViewById(R.id.textViewNome);

        textViewProjeto.setText("Projeto: " + projeto);
        textViewNome.setText("Nome: " + nome);


        }

    //Create Project Here

}
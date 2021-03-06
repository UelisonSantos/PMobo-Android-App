package com.example.uelis.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;
import android.util.Log;

import org.json.JSONException;


public class CriandoProjeto extends AppCompatActivity {

    // Replace below with your own subscription key
    private static String speechSubscriptionKey = "9acd533089504ee3833e448dc3728daa";
    // Replace below with your own service region (e.g., "westus").
    private static String serviceRegion = "westus";

    String ept;
    String nomeProjeto;
    String inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_listening_criandoprojeto);
        ept = intent.getStringExtra("EPT");
        nomeProjeto = intent.getStringExtra("NomeProjeto");
        inicio = intent.getStringExtra("Data");
        TextView textViewEPT = findViewById(R.id.textViewProjeto);
        TextView textViewNomeProjeto = findViewById(R.id.textViewNome);
        TextView textViewInicio = findViewById(R.id.textViewInicio);
        textViewEPT.setText("Tipo: " + ept);
        textViewNomeProjeto.setText("Nome: " + nomeProjeto);
        textViewInicio.setText("Data: " + inicio);


        RestHandler rh = new RestHandler();
        Project projeto = new Project();
        projeto.Name = nomeProjeto;
        projeto.EPTGuid = "09fa52b4-059b-4527-926e-99f9be96437a";
        try {
            rh.CreateProject(this, projeto);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);

    }

    //Create Project Here

}
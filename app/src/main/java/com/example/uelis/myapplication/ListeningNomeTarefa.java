package com.example.uelis.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;

import java.util.concurrent.Future;

public class ListeningNomeTarefa extends AppCompatActivity {

    // Replace below with your own subscription key
    private static String speechSubscriptionKey = "9acd533089504ee3833e448dc3728daa";
    // Replace below with your own service region (e.g., "westus").
    private static String serviceRegion = "westus";

    String projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_nome_tarefa);

        Intent intent = getIntent();
        projeto = intent.getStringExtra("Projeto");
        TextView textViewProjeto = findViewById(R.id.textViewProjeto);
        textViewProjeto.setText("Projeto: " + projeto);

    }

    public void onSpeechButtonClicked(View v) {


        try {
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            assert(config != null);
            config.setSpeechRecognitionLanguage("pt-BR");

            SpeechRecognizer reco = new SpeechRecognizer(config);
            assert(reco != null);

            Future<SpeechRecognitionResult> task = reco.recognizeOnceAsync();
            assert(task != null);

            // Note: this will block the UI thread, so eventually, you want to
            //        register for the event (see full samples)
            SpeechRecognitionResult result = task.get();
            assert(result != null);

            if (result.getReason() == ResultReason.RecognizedSpeech) {

                Intent intent = new Intent(this, CriandoTarefa.class);
                intent.putExtra("Projeto", projeto);
                intent.putExtra("Nome", result.getText());
                startActivity(intent);

            }
            else {
                //error
            }

            reco.close();
        } catch (Exception ex) {
            Log.e("SpeechSDKDemo", "unexpected " + ex.getMessage());
            assert(false);
        }
    }
}
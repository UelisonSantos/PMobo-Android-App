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

public class ListeningDataInicio extends AppCompatActivity {

    // Replace below with your own subscription key
    private static String speechSubscriptionKey = "9acd533089504ee3833e448dc3728daa";
    // Replace below with your own service region (e.g., "westus").
    private static String serviceRegion = "westus";

    String ept;
    String nomeProjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_data_inicio);
        Intent intent = getIntent();
        ept = intent.getStringExtra("EPT");
        nomeProjeto = intent.getStringExtra("NomeProjeto");
        TextView textViewEPT = findViewById(R.id.textViewProjeto);
        TextView textViewNomeProjeto = findViewById(R.id.textViewNome);
        textViewEPT.setText("Tipo: "+ ept);
        textViewNomeProjeto.setText("Nome: " + nomeProjeto);

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

                Intent intent = new Intent(this, CriandoProjeto.class);
                intent.putExtra("NomeProjeto", nomeProjeto);
                intent.putExtra("EPT", ept);
                intent.putExtra("Data", result.getText());
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
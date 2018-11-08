package com.example.uelis.myapplication;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.os.Bundle;
import android.widget.TextView;

import com.microsoft.cognitiveservices.speech.ResultReason;
        import com.microsoft.cognitiveservices.speech.SpeechConfig;
        import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
        import com.microsoft.cognitiveservices.speech.SpeechRecognizer;

        import java.util.concurrent.Future;
        import java.util.ArrayList;

public class ListeningEPT extends AppCompatActivity {

    // Replace below with your own subscription key
    private static String speechSubscriptionKey = "9acd533089504ee3833e448dc3728daa";
    // Replace below with your own service region (e.g., "westus").
    private static String serviceRegion = "westus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_ept);


        RestHandler rh = new RestHandler();
        //rh.GetProjects(this);
        ArrayList <EPT> epts = rh.GetEPTs(this);
        Log.e("Rest size", "" + epts.size());

        TextView textViewEPTs = findViewById(R.id.textViewEPTs);

        for (EPT ept : epts)
        {
            Log.e("Rest " + ept.Id, ept.Name + " - " + ept.Id);
            //TextView textView = new TextView(this);
            //textView.setText(ept.Name);
            textViewEPTs.setText(textViewEPTs.getText() + ept.Name + System.getProperty ("line.separator"));
        }

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

                Intent intent = new Intent(this, ListeningNomeProjeto.class);
                intent.putExtra("EPT", result.getText());
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
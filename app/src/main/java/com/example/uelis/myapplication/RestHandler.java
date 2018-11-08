package com.example.uelis.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class RestHandler {

    public String URL;


    public void GetProjects(Context context)
    {
        String URL = "https://pmobo.azurewebsites.net/api/v1/Projects?userName=guilherme.soster@supria.com.br&password=Minha$enh@18&pwaPath=https://supriatecnologia.sharepoint.com/sites/pwailabs";
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject project = response.getJSONObject(i);
                                Project p = new Project();
                                // Get the current student (json object) data
                                String Name = project.getString("Name");
                                //String lastName = project.getString("Id");
                                //String age = project.getString("age");

                                // Display the formatted json data in text view
                                Log.e("Rest Response", Name.toString());
                                p.Name = Name;
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.e("Rest Response", error.toString());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }





    public ArrayList<EPT> GetEPTs(Context context)
    {
        String URL = "https://pmobo.azurewebsites.net/api/v1/EPT?userName=guilherme.soster@supria.com.br&password=Minha$enh@18&pwaPath=https://supriatecnologia.sharepoint.com/sites/pwailabs";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final ArrayList<EPT> EPTList = new ArrayList<EPT>();

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject objResponse = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String Name = objResponse.getString("Name");
                                String Id = objResponse.getString("Id");

                                EPT ept = new EPT();
                                ept.Name = Name;
                                ept.Id = Id;
                                EPTList.add(ept);

                                // Display the formatted json data in text view
                                //Log.e("Rest Response", Name.toString());
                                Log.e("Rest Response", "SUCESSO!");
                                Log.e("Rest Response2", ept.Name + " - " + ept.Id);

                            }//fim do for

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.e("Rest Response", error.toString());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
        return EPTList;
    }



    public void CreateProject(Context context, Project projectToBeCreated) throws JSONException {
        String URL = "https://pmobo.azurewebsites.net/api/v1/Projects?userName=guilherme.soster@supria.com.br&password=Minha$enh@18&pwaPath=https://supriatecnologia.sharepoint.com/sites/pwailabs";
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        //String json = "{'Name':'"+projectToBeCreated.Name+"'}";
        JSONObject json = new JSONObject();

        json.put("Name", projectToBeCreated.Name);

        /*

        JSONObject jsonBody;
        try {

            jsonBody = new JSONObject(json);

            Log.d("My App", jsonBody.toString());

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
            jsonBody = new JSONObject();
        }

        //jsonBody.put("Name", projectToBeCreated.Name);
        //jsonBody.put("EPTGuid", projectToBeCreated.EPTGuid);
        */


        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST,
                URL,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("POST Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("POST Response", error.toString());
                    }
                }
        );

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(objectRequest);
    }

    public void CreateTask(Context context, Task taskToBeCreated) throws JSONException {
        String URL = "https://pmobo.azurewebsites.net/api/v1/Tasks?userName=guilherme.soster@supria.com.br&password=Minha$enh@18&pwaPath=https://supriatecnologia.sharepoint.com/sites/pwailabs";
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //String json = "{'Name':'"+projectToBeCreated.Name+"'}";
        JSONObject json = new JSONObject();

        json.put("TaskName", taskToBeCreated.TaskName);
        json.put("ProjectName", taskToBeCreated.ProjectName);
        /*

        JSONObject jsonBody;
        try {

            jsonBody = new JSONObject(json);

            Log.d("My App", jsonBody.toString());

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
            jsonBody = new JSONObject();
        }

        //jsonBody.put("Name", projectToBeCreated.Name);
        //jsonBody.put("EPTGuid", projectToBeCreated.EPTGuid);
        */


        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST,
                URL,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("POST Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("POST Response", error.toString());
                    }
                }
        );

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(objectRequest);
    }




}

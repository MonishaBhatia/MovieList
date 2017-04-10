package com.monisha.android.moviefinder.home;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.api.ApiEndPoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monisha on 08/04/17.
 */

public class HomeService {

    String url;
    Gson gson;
    boolean flag;
    HomeViewInterface view;
    int count;
    List<String> responseList;
    String[] movies;

    public void callApiToFetchDetails(HomeViewInterface view, String mName, String type){

        this.view = view;
        movies = mName.split(",");
        count = 0;
        view.showProgressDialog();
        responseList = new ArrayList<>();
        for(String name : movies) {
            url = ApiEndPoint.BASE_URL + name + "&type=" + type;
            sendRequest();
        }
    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        view.showNotFoundError(R.string.not_found_error);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String strresponse){

        gson = new Gson();
        Log.d("##Response", strresponse);
        count++;


            try {
                JSONObject jsonResultObject = new JSONObject(strresponse);

                if (jsonResultObject.getString("Response").equalsIgnoreCase("False")) {

                    view.showNotFoundError(R.string.not_found_error);

                } else {

                    responseList.add(strresponse);
                }
            } catch (JSONException | NullPointerException e) {
                Log.d("Error in HomeService:", e.getMessage());
            }

            if (count == movies.length) {
                view.startDetailActivity(responseList);
            }

    }

    public boolean isFlag(){

        return count == movies.length? true : false;
    }
}

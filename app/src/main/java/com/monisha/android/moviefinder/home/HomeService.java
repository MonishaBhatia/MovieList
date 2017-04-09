package com.monisha.android.moviefinder.home;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.api.ApiEndPoint;
import com.monisha.android.moviefinder.general.WebServiceAsyncTask;
import com.monisha.android.moviefinder.general.WebServiceResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monisha on 08/04/17.
 */

public class HomeService implements WebServiceResponseListener {

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
        responseList = new ArrayList<>();
        for(String name : movies) {
            url = ApiEndPoint.BASE_URL + name + "&type=" + type;
            new WebServiceAsyncTask(this, url, ApiEndPoint.BASE_URL_ID).execute();
        }

    }

    @Override
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException, NullPointerException {

        gson = new Gson();
        Log.d("##Response", strresponse);
        count++;

        if(urlId == ApiEndPoint.BASE_URL_ID) {
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
    }

    @Override
    public void onError() throws NullPointerException {

    }

    @Override
    public void slowInternetConnction() throws NullPointerException {

    }

    public boolean isFlag(){

        return count == movies.length? true : false;
    }
}

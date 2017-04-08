package com.monisha.android.moviefinder.home;

import android.os.AsyncTask;
import android.util.Log;

import com.monisha.android.moviefinder.interfaces.WebServiceResponseListener;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by monisha on 08/04/17.
 */

public class HomeAsyncTask extends AsyncTask<String, String, String> {

    /*
     * Listener to catch and parse response
     */
    WebServiceResponseListener mListener;
    String url;
    int urlId;

    HomeAsyncTask(WebServiceResponseListener mListener, String url, int urlId){
        this.mListener = mListener;
        this.url = url;
        this.urlId = urlId;
    }

    @Override
    protected void onPreExecute() {
        super .onPreExecute();

    }


    @Override
    protected String doInBackground(String... strings) {


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "b74e2b11-4b63-3c97-3927-226144f63333")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.toString());
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();

            //mListener.onError();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);


        Log.d("called","onPost");
        if(response != null)
        {
            if(response == "")
            {
                mListener.onError();
            }

        }
        if (response != null) {

            mListener.responseWithId(response, "get", urlId);

        }
    }
}

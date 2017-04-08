package com.monisha.android.moviefinder.home;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.api.ApiEndPoint;
import com.monisha.android.moviefinder.interfaces.WebServiceResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by monisha on 08/04/17.
 */

public class HomeService implements WebServiceResponseListener {

    String url;
    Gson gson;
    boolean flag;



    public void callback(String name, String type){
        url = ApiEndPoint.BASE_URL + name + "&type=" + type;

        new HomeAsyncTask(this, url, ApiEndPoint.BASE_URL_ID).execute();

    }

    @Override
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException, NullPointerException {

        gson = new Gson();
        Log.d("##Response", strresponse);
        Log.d("called","1st");

        try {
            JSONObject jsonResultObject = new JSONObject(strresponse);

            if (jsonResultObject.getString("Response").equalsIgnoreCase("False")) {


                setFlag(false);

            }else {


                setFlag(true);
            }
        }catch (JSONException | NullPointerException e) {
            Log.d("Error in ReviewReason:", e.getMessage());
        }

    }

    @Override
    public void onError() throws NullPointerException {

    }

    @Override
    public void slowInternetConnction() throws NullPointerException {

    }



    public void setFlag(boolean flag) {
        Log.d("called","2nd");
        this.flag = flag;
    }

    public boolean isFlag() {
        Log.d("called","3rd");
        return flag;
    }
}

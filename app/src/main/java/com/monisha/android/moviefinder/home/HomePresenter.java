package com.monisha.android.moviefinder.home;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.api.ApiEndPoint;
import com.monisha.android.moviefinder.interfaces.WebServiceResponseListener;
import com.monisha.android.moviefinder.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by monisha on 08/04/17.
 */

public class HomePresenter implements WebServiceResponseListener{


    private HomeView view;
    private HomeService service;
    String url;
    Gson gson;



    boolean flag;

    HomePresenter(HomeView view, HomeService service){
        this.view = view;
        this.service = service;
    }

    public void callback(String name, String type){
        url = ApiEndPoint.BASE_URL + name + "&type=" + type;

        new HomeAsyncTask(this, url, ApiEndPoint.BASE_URL_ID).execute();

    }

    public void onSubmitClicked() {

        String mName = view.getMovieOrSeriesName();
        if (mName.isEmpty()) {
            view.showNoNameError(R.string.no_name_error);
            return;
        }

        String mType = view.getMovieOrSeriesType();
        if(mType.equals(Constants.TYPE)){
            view.showNoTypeError(R.string.no_type_error);
            return;
        }

        service.callback(mName,mType);

        boolean succeeded = service.isFlag();
        if (succeeded) {
            view.startDetailActivity();
            return;
        }
        else
        {
            view.showError(R.string.not_found_error);
        }



        // callback(mName,mType);



    }

    @Override
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException, NullPointerException {

        gson = new Gson();
        Log.d("##Response", strresponse);
        Log.d("called","1st");

        try {
            JSONObject jsonResultObject = new JSONObject(strresponse);

            if (jsonResultObject.getString("Response").equalsIgnoreCase("False")) {

            flag = false;

                view.showError(R.string.not_found_error);


            }else
                {

                flag = true;
                    view.startDetailActivity();


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


    public boolean isFlag() {
        return flag;
    }
}



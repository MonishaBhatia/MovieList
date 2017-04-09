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

public class HomePresenter{


    private HomeView view;
    private HomeService service;
    String url;
    Gson gson;



    boolean flag;

    HomePresenter(HomeView view, HomeService service){
        this.view = view;
        this.service = service;
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

        service.callback(view, mName,mType);

    }
}



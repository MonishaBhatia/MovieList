package com.monisha.android.moviefinder.home;

import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.utils.Constants;

/**
 * Created by monisha on 08/04/17.
 */

public class HomePresenter{


    private HomeViewInterface view;
    private HomeService service;


    HomePresenter(HomeViewInterface view, HomeService service){
        this.view = view;
        this.service = service;
    }

    public void onSubmitClicked() {

        // Here, getting movie or series name, if empty then return the error
        String mName = view.getMovieOrSeriesName();
        if (mName.isEmpty()) {
            view.showNoNameError(R.string.no_name_error);
            return;
        }

        // Here, getting movie or series type, if not selected any, then return error
        String mType = view.getMovieOrSeriesType();
        if(mType.equals(Constants.TYPE)){
            view.showNoTypeError(R.string.no_type_error);
            return;
        }

        // calling function to call the OMDb API to fetch the details of the given movie(s) oe series
        service.callApiToFetchDetails(view, mName,mType);

    }
}



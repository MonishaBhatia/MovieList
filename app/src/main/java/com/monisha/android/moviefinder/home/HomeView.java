package com.monisha.android.moviefinder.home;

/**
 * Created by monisha on 08/04/17.
 */

public interface HomeView {

    String getMovieOrSeriesName();

    String getMovieOrSeriesType();

    void showNoNameError(int noNameError);

    void showNoTypeError(int noTypeError);

    void startDetailActivity();

    void showError(int resId);
}

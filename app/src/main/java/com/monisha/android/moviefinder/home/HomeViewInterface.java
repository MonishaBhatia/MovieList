package com.monisha.android.moviefinder.home;

import java.util.List;

/**
 * Created by monisha on 08/04/17.
 */

public interface HomeViewInterface {

    String getMovieOrSeriesName();

    String getMovieOrSeriesType();

    void showNoNameError(int noNameError);

    void showNoTypeError(int noTypeError);

    void startDetailActivity(List<String> response);

    void showNotFoundError(int resId);
}

package com.monisha.android.moviefinder.home;

import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by monisha on 08/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    private HomePresenter presenter;
    @Mock
    private HomeView view;
    @Mock
    private HomeService service;


    @Before
    public void setUp() throws Exception {

        presenter = new HomePresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty() throws Exception{
        when(view.getMovieOrSeriesName()).thenReturn("");
        presenter.onSubmitClicked();

        verify(view).showNoNameError(R.string.no_name_error);
    }


    @Test
    public void shouldShowErrorMessageWhenTypeIsEmpty() throws Exception{
        when(view.getMovieOrSeriesName()).thenReturn("Friends");
        when(view.getMovieOrSeriesType()).thenReturn(Constants.TYPE);
        presenter.onSubmitClicked();

        verify(view).showNoTypeError(R.string.no_type_error);
    }

    @Test
    public void shouldShowErrorWhenMovieOrSeriesIsValid() throws Exception {
        when(view.getMovieOrSeriesName()).thenReturn("Friends");
        when(view.getMovieOrSeriesType()).thenReturn("Series");
        service.callback("Friends", "Series");
        when(service.isFlag()).thenReturn(true);
        presenter.onSubmitClicked();

        verify(view).startDetailActivity();
    }

    @Test
    public void shouldShowErrorWhenMovieOrSeriesIsInvalid() throws Exception {
        when(view.getMovieOrSeriesName()).thenReturn("Friends");
        when(view.getMovieOrSeriesType()).thenReturn("Series");
        service.callback("Friends", "series");
        when(service.isFlag()).thenReturn(false);
        presenter.onSubmitClicked();
        verify(view).showError(R.string.not_found_error);
    }

}
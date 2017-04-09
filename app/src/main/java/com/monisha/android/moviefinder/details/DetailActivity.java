package com.monisha.android.moviefinder.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.gsonmodels.MovieDetailModel;
import com.monisha.android.moviefinder.gsonmodels.SeriesDetailModel;
import com.monisha.android.moviefinder.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.rv_movie_list)
    RecyclerView rvMovieList;

    @BindView(R.id.tv_multiple_movie_text)
    TextView tvMultipleMovieText;


    Gson gson ;
    private String strResponse;
    private String type;

    List<DetailModel> detailModelList = new ArrayList<>();

    MovieDetailModel movieDetailModel;
    SeriesDetailModel seriesDetailModel;
    String title;
    String genre;
    String releasedDate;
    String plot;
    String rating;
    String image;

    DetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        gson = new Gson();
        strResponse = getIntent().getStringExtra(Constants.RESPONSE);
        type = getIntent().getStringExtra(Constants.TYPE);

        parseJsonAndUpdateList();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMovieList.setLayoutManager(layoutManager);

        mAdapter = new DetailAdapter(this, detailModelList);
        rvMovieList.setAdapter(mAdapter);

    }

    public void parseJsonAndUpdateList(){

        if(type.equals(Constants.MOVIE)){

            movieDetailModel = gson.fromJson(strResponse, MovieDetailModel.class);
            title = movieDetailModel.getTitle();
            genre = movieDetailModel.getGenre();
            releasedDate = movieDetailModel.getReleased();
            plot = movieDetailModel.getPlot();
            rating = movieDetailModel.getImdbRating();
            image = movieDetailModel.getPoster();


        }else{
            seriesDetailModel = gson.fromJson(strResponse, SeriesDetailModel.class);

            title = seriesDetailModel.getTitle();
            genre = seriesDetailModel.getGenre();
            releasedDate = seriesDetailModel.getReleased();
            plot = seriesDetailModel.getPlot();
            rating = seriesDetailModel.getImdbRating();
            image = seriesDetailModel.getPoster();

        }

        detailModelList.add(new DetailModel(title,genre,releasedDate,plot,rating,image));

    }
}

package com.monisha.android.moviefinder.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.monisha.android.moviefinder.R;
import com.monisha.android.moviefinder.details.DetailActivity;
import com.monisha.android.moviefinder.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.et_movie_name)
    EditText etName;
    @BindView(R.id.spinner_type)
    Spinner spinner;

    HomePresenter presenter;

    List<String> type = new ArrayList<>();
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        type.add(Constants.TYPE);
        type.add(Constants.MOVIE);
        type.add(Constants.SERIES);


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        presenter = new HomePresenter(this, new HomeService());

    }

    // On clicking Submit Button
    @OnClick(R.id.btn_submit)
    public void submit(Button button) {

        presenter.onSubmitClicked();
    }


    @OnItemSelected(R.id.spinner_type)
    public void spinnerItemSelected(Spinner spinner, int position) {

        item = spinner.getItemAtPosition(position).toString();

    }
    @Override
    public String getMovieOrSeriesName() {
        return etName.getText().toString();
    }

    @Override
    public String getMovieOrSeriesType() {
        return item;
    }

    @Override
    public void showNoNameError(int noNameError) {
        etName.setError(getString(noNameError));
    }

    @Override
    public void showNoTypeError(int noTypeError) {
        ((TextView)spinner.getSelectedView()).setError(getString(noTypeError));

        Toast.makeText(this, getString(noTypeError), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void startDetailActivity(String response) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.RESPONSE, response);
        intent.putExtra(Constants.TYPE, getMovieOrSeriesType());
        startActivity(intent);
    }

    @Override
    public void showError(int resId) {

        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
}

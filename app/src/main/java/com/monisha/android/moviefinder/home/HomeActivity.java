package com.monisha.android.moviefinder.home;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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

public class HomeActivity extends AppCompatActivity implements HomeViewInterface {

    @BindView(R.id.et_movie_name)
    EditText etName;
    @BindView(R.id.spinner_type)
    Spinner spinner;

    @BindView(R.id.content_frame_layout)
    ScrollView mContentContainer;

    @BindView(R.id.progress_layout)
    ViewGroup progressLayout;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.view_no_connection)
    ViewGroup viewNoConnection;

    @BindView(R.id.no_connection_button)
    Button btnNoConnection;

    HomePresenter presenter;

    List<String> type = new ArrayList<>();
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ButterKnife to bind all the views
        ButterKnife.bind(this);

        /*Adding the spinner values
        TYPE is default value, user can select any value from Movie or Series
         */
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

    @Override
    protected void onResume() {
        super.onResume();

        try {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            unregisterReceiver(mNetworkReceiver);
            // unregisterReceiver(GcmReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // On clicking Submit Button
    @OnClick(R.id.btn_submit)
    public void submit(Button button) {

        presenter.onSubmitClicked();
    }

    @OnClick(R.id.no_connection_button)
    public void checkConnection(Button button) {

        //it will take to phone settings to check mobile internet or wifi connection.
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

    }

    //Get the selected type from Spinner
    @OnItemSelected(R.id.spinner_type)
    public void spinnerItemSelected(Spinner spinner, int position) {

        item = spinner.getItemAtPosition(position).toString();

    }

    // Return movie or series name from edit text
    @Override
    public String getMovieOrSeriesName() {
        return etName.getText().toString();
    }

    //Return the value of the selected item from spinner
    @Override
    public String getMovieOrSeriesType() {
        return item;
    }

    //Shows error if Movie Name field is empty
    @Override
    public void showNoNameError(int noNameError) {
        etName.setError(getString(noNameError));
    }

    // Show error if type is not selected
    @Override
    public void showNoTypeError(int noTypeError) {
        ((TextView) spinner.getSelectedView()).setError(getString(noTypeError));

        Toast.makeText(this, getString(noTypeError), Toast.LENGTH_SHORT).show();

    }

    /* Move to next activity i.e Detail Activity,
    we pass list of responses in case of multiple movies and selected type
    */
    @Override
    public void startDetailActivity(List<String> response) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putStringArrayListExtra(Constants.RESPONSE, (ArrayList<String>) response);
        intent.putExtra(Constants.TYPE, getMovieOrSeriesType());
        startActivity(intent);
        dismissProgressDialog();
    }

    // This method displays error if movie or series is not found
    @Override
    public void showNotFoundError(int resId) {

        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }


    //Receiver for Checking Internet Connection
    private final BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


            // connected to the internet
            if (activeNetwork != null) {
                viewNoConnection.setVisibility(View.GONE);
                mContentContainer.setVisibility(View.VISIBLE);

                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's plan

                }
            } else {

                viewNoConnection.setVisibility(View.VISIBLE);
                mContentContainer.setVisibility(View.GONE);
                progressLayout.setVisibility(View.GONE);
            }
        }

    };


    @Override
    public void dismissProgressDialog() {

        if (progressLayout != null) {
            progressLayout.setVisibility(View.GONE);
            mContentContainer.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showProgressDialog() {

        if (progressLayout != null) {
            mContentContainer.setVisibility(View.GONE);
            progressLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

}

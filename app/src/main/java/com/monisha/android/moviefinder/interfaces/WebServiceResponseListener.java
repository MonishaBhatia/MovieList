package com.monisha.android.moviefinder.interfaces;

/**
 * Created by monisha on 07/04/17.
 */


import com.google.gson.JsonSyntaxException;
/**
 * interface to catch response of WebServiceAsync class
 *
 */
public interface WebServiceResponseListener {

    /**
     * method to catch response
     *
     * @param strresponse
     *            response get from any web-service
     */
    public void responseWithId(String strresponse, String via, int urlId) throws JsonSyntaxException,NullPointerException;
    /***
     * If Error or Exception occured
     */
    public void onError() throws NullPointerException;

    public void slowInternetConnction() throws NullPointerException;
}
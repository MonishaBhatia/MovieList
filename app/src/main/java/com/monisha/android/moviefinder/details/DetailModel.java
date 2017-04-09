package com.monisha.android.moviefinder.details;

/**
 * Created by monisha on 08/04/17.
 */

public class DetailModel {

    private String title;
    private String genre;
    private String releasedDate;
    private String plot;
    private String rating;
    private String image;

    public DetailModel(String title, String genre, String releasedDate, String plot, String rating, String image) {
        this.title = title;
        this.genre = genre;
        this.releasedDate = releasedDate;
        this.plot = plot;
        this.rating = rating;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

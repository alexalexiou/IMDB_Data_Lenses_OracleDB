/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poppulate;

/**
 *
 * @author alex
 */
public class MovieTag {
    private String movieID;
    private String tagID;
    private int mWeight;

    public MovieTag(String movieID, String tagID, int mWeight) {
        this.movieID = movieID;
        this.tagID = tagID;
        this.mWeight = mWeight;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getTagID() {
        return tagID;
    }

    public int getmWeight() {
        return mWeight;
    }

    @Override
    public String toString() {
        return "MovieTag{" + "movieID=" + movieID + ", tagID=" + tagID + ", mWeight=" + mWeight + '}';
    }
    
    
}

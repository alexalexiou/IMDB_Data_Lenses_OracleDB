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
public class MovieGenre {
    
    private String movieID;
    private String genre;

    public MovieGenre(String movieID, String genre) {
        this.movieID = movieID;
        this.genre = genre;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getGenre() {
        return genre;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieGenre{" + "movieID=" + movieID + ", genre=" + genre + '}';
    }
    
    
}

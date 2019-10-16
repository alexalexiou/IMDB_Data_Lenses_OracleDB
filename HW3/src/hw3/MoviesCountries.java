/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

/**
 *
 * @author alex
 */
public class MoviesCountries {
    
    private String movie_id;
    private String country;

    public MoviesCountries(String movie_id, String country) {
        this.movie_id = movie_id;
        this.country = country;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MoviesCountries{" + "movie_id=" + movie_id + ", country=" + country + '}';
    }
    
    
}

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
public class Movie {

  

    private String movieID = null;
    private String title = null;
    private String year = null;

    public Movie(String movieID, String title,String year){
        this.movieID = movieID;
        this.title = title;
        this.year = year;
    } 
        
   
  
    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{" + "movieID=" + movieID + ", title=" + title + ", year=" + year + '}';
    }

 

   
}

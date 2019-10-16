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
public class MovieDirector {
  private String  movieID;
  private String directorID;
  private String directorName;

    public MovieDirector(String movieID, String directorID, String directorName) {
        this.movieID = movieID;
        this.directorID = directorID;
        this.directorName = directorName;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getDirectorID() {
        return directorID;
    }

    public String getDirectorName() {
        return directorName;
    }

    @Override
    public String toString() {
        return "MovieDirector{" + "movieID=" + movieID + ", directorID=" + directorID + ", directorName=" + directorName + '}';
    }
  
  
}

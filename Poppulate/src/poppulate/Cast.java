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
public class Cast {
    private String movieID;
    private String actorID;
    private String actorName;

    public Cast(String movieID, String actorID, String actorName) {
        this.movieID = movieID;
        this.actorID = actorID;
        this.actorName = actorName;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getActorID() {
        return actorID;
    }

    public String getActorName() {
        return actorName;
    }

    @Override
    public String toString() {
        return "Cast{" + "movieID=" + movieID + ", actorID=" + actorID + ", actorName=" + actorName + '}';
    }
    
    
}

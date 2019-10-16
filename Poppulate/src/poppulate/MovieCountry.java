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
public class MovieCountry {
    
    private String movieID=null;
    private String country=null;

    

   
    public String getMovieID() {
        return movieID;
    }

    public String getCountry() {
        return country;
    }
    
    private MovieCountry(Builder b){
       this.movieID =  b.movieID;
       this.country= b.country;
    }
    
    
    @Override
    public String toString() {
        return "MovieCountry{" + "movieID=" + movieID + ", country=" + country + '}';
    }
    
    public static class Builder{
        private String movieID;
        private String country;

        public Builder() {
        }
        
        
        public Builder setMovieID(String movieID) {
            this.movieID = movieID;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public MovieCountry build(){
            return new MovieCountry(this);
        }
        
        
    }
}

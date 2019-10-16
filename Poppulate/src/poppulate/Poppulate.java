/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poppulate;

import java.sql.SQLException;

/**
 *
 * @author alex
 */
public class Poppulate {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InsertData ind = new InsertData();
        ind.readMoviesFromFile("movies.dat");
        ind.insertMoviesIntoDB();
        ind.readMovieGenreFromFile("movie_genres.dat");
        ind.insertMovieGenreIntoDB();
        ind.readTagsFromFile("tags.dat");
        ind.insertTagsIntoDB();
        ind.readMovieCountriesFromFile("movie_countries.dat");
        ind.insertMovieCountriesIntoDB();
        ind.readMovieTagsFromFile("movie_tags.dat");
        ind.insertMovieTagsIntoDB();
        ind.readMovieDirectorsFromFile("movie_directors.dat");
        ind.insertMovieDirectorsIntoDB();
        ind.readCastFromFile("movie_actors.dat");
        ind.insertCastIntoDB();
    }

}

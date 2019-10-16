/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.paint.Color;

/**
 *
 * @author alex
 */
public class FXMLDocumentController implements Initializable {

    private ObservableList<String> lSelectedGenres = FXCollections.observableArrayList();

    private ObservableList<String> obsListCountries = FXCollections.observableArrayList();
    private ObservableList<String> lMovieIDsOfCountries = FXCollections.observableArrayList();
    private ObservableList<String> lSelectedCountries = FXCollections.observableArrayList();

    private ObservableList<String> obsListCast = FXCollections.observableArrayList();
    private ObservableList<String> lSelectedCast = FXCollections.observableArrayList();

    private ObservableList<String> obsListDirectors = FXCollections.observableArrayList();
    private ObservableList<String> lSelectedDirectors = FXCollections.observableArrayList();

    private ObservableList<String> obsListTags = FXCollections.observableArrayList();
    private ObservableList<String> lSelectedTags = FXCollections.observableArrayList();

    private ObservableList<String> obsListMovies = FXCollections.observableArrayList();
    private ObservableList<String> lSelectedMovies = FXCollections.observableArrayList();

    //private ObservableList<String> obsListGenreDateQuery = FXCollections.observableArrayList();
     private ObservableList<MoviesCountries> obsListMC = FXCollections.observableArrayList();
    // private ObservableList<String> lcastMoviesID = FXCollections.observableArrayList();
    private int startYear = 2005; // hardcoded for testing purposes
    private int endYear = 2018; // hardcoded for testing purposes

    @FXML
    private Label label;    // is the id in the XML
    @FXML
    private Button button;  // id in XML
    @FXML
    private ListView<String> genresListView;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button simple;
    @FXML
    private Button simple2;
    @FXML
    private Button simple3;
    @FXML 
    private Button simple4;
    @FXML
    private ListView<String> castListView;

    @FXML
    private ListView<String> countriesListView;
    @FXML
    private ListView<String> directorsListView;
    @FXML
    private ListView<String> tagsListView;
    @FXML
    private ListView<String> moviesListView;

    @FXML
    private void button3Acttion() {
//        System.out.println("Alex clicked me");
//       countriesListView.refresh();
//       
    }
    
    
    @FXML 
    private void handleTagsToMoviesSimpleButton(ActionEvent e){
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        
        String genreSelectionBuffer = "";
        genreSelectionBuffer=getGenreSelectionBuffer(genreSelectionBuffer);
        
        String countriesSelectionBuffer = "";
        countriesSelectionBuffer = getCountriesSelectionBuffer(countriesSelectionBuffer);

        String castSelectionBuffer = "";
        castSelectionBuffer = getCastSelectionBuffer(castSelectionBuffer);

        String directorsSelectionBuffer = "";
        directorsSelectionBuffer = getDirectorsSelectionBuffer(directorsSelectionBuffer);
        
        String tagsSelectionBuffer = "";
        tagsSelectionBuffer = getTagsSelectionBuffer(tagsSelectionBuffer);
        

        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();
   
        
        }catch (SQLException exception) {

        } finally {
            DBConnect.closeConnection(con);
        }
    }
    
    

    @FXML
    private void handleCastToTagsSimpleButton3(ActionEvent acEvent) {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;     
        String genreSelectionBuffer = "";
        genreSelectionBuffer=getGenreSelectionBuffer(genreSelectionBuffer);      
        String countriesSelectionBuffer = "";
        countriesSelectionBuffer = getCountriesSelectionBuffer(countriesSelectionBuffer);
        String castSelectionBuffer = "";
        castSelectionBuffer = getCastSelectionBuffer(castSelectionBuffer);
        String directorsSelectionBuffer = "";
        directorsSelectionBuffer = getDirectorsSelectionBuffer(directorsSelectionBuffer);

        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            sqlQuery = "SELECT t.tagID, t.tagValue "
                    + "FROM Tag t "
                    + "WHERE t.tagID IN "
                    + "(SELECT mt.tagID "
                    + "FROM Movie_Tag mt "
                    + "WHERE mt.movieID IN "
                    + "(SELECT c.movieid "
                    + "FROM Cast c "
                    + "WHERE c.movieID IN "
                    + "(SELECT m.movieID FROM movie m, movie_genre mg, cast c, movie_country mc "
                    + "WHERE m.movieID = mg.movieID AND m.movieID = c.movieID AND c.movieID = mg.movieID AND m.movieid = mc.movieid "
                    + "AND mg.movieid = mc.movieid AND mc.country IN "
                    + "(" + countriesSelectionBuffer + ") AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + ") "
                    + "AND c.actorName IN (" +castSelectionBuffer+ "))))";
            
            System.out.println("buttor 3 sql "+sqlQuery);
           
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListTags.clear();
            tagsListView.getItems().clear();
            while (result.next()) {
               obsListTags.add(result.getString(1));
                obsListTags.add(result.getString(2));
               tagsListView.getItems().addAll(result.getString(1)+ " "+result.getString(2));
               
            }
            tagsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedTags = tagsListView.getSelectionModel().getSelectedItems();
            System.out.println("");
            System.out.println("obsListTags " + obsListTags.toString());
            System.out.println("lSelectedTags " + lSelectedTags.size());
            stmt.close();
        } catch (SQLException exception) {

        } finally {
            DBConnect.closeConnection(con);
        }
    }

    public void countriesToMovies() {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";
        String countriesSelectionBuffer = "";
        genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);
        countriesSelectionBuffer = getCountriesSelectionBuffer(countriesSelectionBuffer);

        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            sqlQuery = "SELECT m.movieID, m.title, m.year "
                    + "FROM Movie m, Movie_Tag mt "
                    + "WHERE m.movieID = mt.movieID AND mt.tagID IN( "
                    + "SELECT t.tagID "
                    + "FROM Tag t "
                    + "WHERE t.tagID IN "
                    + "(SELECT mt.tagID "
                    + "FROM Movie_Tag mt "
                    + "WHERE mt.movieID IN "
                    + "(SELECT c.movieid "
                    + "FROM Cast c "
                    + "WHERE c.movieID IN (SELECT m.movieID "
                    + "FROM movie m, movie_genre mg, cast c, movie_country mc "
                    + "WHERE m.movieID = mg.movieID "
                    + "AND m.movieID = c.movieID AND c.movieID = mg.movieID "
                    + "AND m.movieid = mc.movieid AND mg.movieid = mc.movieid AND mc.country IN (" + countriesSelectionBuffer + ") "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + ")))))";

            System.out.println(sqlQuery);
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListMovies.clear();
            moviesListView.getItems().clear();
            while (result.next()) {
                obsListMovies.add(result.getString(1));
                obsListMovies.add(result.getString(2));
                obsListMovies.add(result.getString(3));
                moviesListView.getItems().addAll(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
            }
            moviesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedMovies = moviesListView.getSelectionModel().getSelectedItems();
            System.out.println("obsListMovies " + obsListMovies.size());
            System.out.println("lSelectedMovies " + lSelectedMovies.size());
            System.out.println("");
            stmt.close();
        } catch (SQLException exception) {

        } finally {
            DBConnect.closeConnection(con);
        }
    }

    public void countriesToTags() {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";
        String countriesSelectionBuffer = "";
        genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);
        countriesSelectionBuffer = getCountriesSelectionBuffer(countriesSelectionBuffer);
        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            sqlQuery = "SELECT t.tagID, t.tagValue "
                    + "FROM Tag t "
                    + "WHERE t.tagID IN "
                    + "(SELECT mt.tagID "
                    + "FROM Movie_Tag mt "
                    + "WHERE mt.movieID IN "
                    + "(SELECT c.movieid "
                    + "FROM Cast c "
                    + "WHERE c.movieID IN (SELECT m.movieID "
                    + "FROM movie m, movie_genre mg, cast c, movie_country mc "
                    + "WHERE m.movieID = mg.movieID "
                    + "AND m.movieID = c.movieID AND c.movieID = mg.movieID "
                    + "AND m.movieid = mc.movieid AND mg.movieid = mc.movieid AND mc.country IN (" + countriesSelectionBuffer + ") "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + "))))";
            System.out.println("countries to tags " + sqlQuery);
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListTags.clear();
            tagsListView.getItems().clear();
            while (result.next()) {
                obsListTags.add(result.getString(1));
                obsListTags.add(result.getString(2));
                tagsListView.getItems().addAll(result.getString(1) + " " + result.getString(2));
                
               
            }
            tagsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedTags = tagsListView.getSelectionModel().getSelectedItems();
            System.out.println("");
            System.out.println("obsListTags " + obsListTags.size());
            System.out.println("lSelectedTags " + lSelectedTags.size());
            stmt.close();
        } catch (SQLException exception) {

        } finally {
            DBConnect.closeConnection(con);
        }
    }

    @FXML
    private void handleCountriesToCastSimple2Button(ActionEvent ecEvent) {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";
        String countriesSelectionBuffer = "";

        

            try {
                con = DBConnect.openConnection();
                stmt = con.createStatement();

                genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);
                countriesSelectionBuffer = getCountriesSelectionBuffer(countriesSelectionBuffer);

                System.out.println("currently testing " + countriesSelectionBuffer);

                sqlQuery = "SELECT c.actorID, c.actorName "
                        + "FROM Cast c "
                        + "WHERE c.movieID IN (SELECT m.movieID "
                        + "FROM movie m, movie_genre mg, cast c, movie_country mc "
                        + "WHERE m.movieID = mg.movieID AND m.movieID = c.movieID "
                        + "AND c.movieID = mg.movieID AND m.movieid = mc.movieid AND mg.movieid = mc.movieid "
                        + "AND mc.country IN (" + countriesSelectionBuffer + ") "
                        + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + "))";

                System.out.println(sqlQuery);
                System.out.println("");
                ResultSet result = stmt.executeQuery(sqlQuery);
                obsListCast.clear();
                castListView.getItems().clear();                 // maybe clear first the castListView and then the obsList
                while (result.next()) {
                    obsListCast.add(result.getString(2));
                    castListView.getItems().addAll(result.getString(2));
                }
                System.out.println("");
                //  System.out.println("obsListCast "+ obsListCast.toString());
                System.out.println("lSelectedCountries " + lSelectedCountries.toString());
                castListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                lSelectedCast = castListView.getSelectionModel().getSelectedItems();
                System.out.println("");
                stmt.close();
                genresDatesCountriesDirector();  // call the director methood where it chaecks if
                countriesToTags();
                countriesToMovies();
            } catch (SQLException exception) {

            } finally {
                DBConnect.closeConnection(con);
            }
        
    }


    /* 
     1)maybe private field for Connection and connection open and closes 1 time in the first method genresDate()  
     AND maybe Statement stmt the same, open and close each time
     2)Observable List items saved as classes(with fields) when we have more than 1 String result
     */
    public void genresDatesCountriesCastTagsMovies() {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";
        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);
            sqlQuery = " SELECT m.movieID, m.title, m.year "
                    + "FROM movie m, movie_genre mg "
                    + "WHERE m.movieID = mg.movieID "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + ")";
            System.out.println(sqlQuery);
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListMovies.clear();
            moviesListView.getItems().clear();
            while (result.next()) {
                obsListMovies.add(result.getString(1));
                obsListMovies.add(result.getString(2));
                obsListMovies.add(result.getString(3));
                moviesListView.getItems().addAll(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
            }
            moviesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedMovies = moviesListView.getSelectionModel().getSelectedItems();
            System.out.println("obsListMovies " + obsListMovies.size());
            System.out.println("lSelectedMovies " + lSelectedMovies.size());
            System.out.println("");
            stmt.close();
        } catch (SQLException e) {
        } finally {
            DBConnect.closeConnection(con);
        }
    }

    public void genresDatesCountriesCastTags() {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";
        String countriesSelectionBuffer = "";
        String castSelectionBuffer = "";

        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);

            sqlQuery = "SELECT DISTINCT t.tagID, t.tagValue "
                    + "FROM Movie m, Tag t, Movie_Tag mt "
                    + "WHERE  mt.movieID IN (SELECT m.movieID "
                    + "FROM movie m, movie_country mc, movie_genre mg "
                    + "WHERE m.movieID = mg.movieID AND m.movieID = mc.movieID AND mc.movieID=mg.movieID "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + ") AND mt.tagID = t.tagID)";
            System.out.println(sqlQuery);
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListTags.clear();
            tagsListView.getItems().clear();
            while (result.next()) {
                obsListTags.add(result.getString(1));
                //obsListTags.add(result.getString(2));
                tagsListView.getItems().addAll(result.getString(1) + " " + result.getString(2));
            }
            tagsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedTags = tagsListView.getSelectionModel().getSelectedItems();
            System.out.println("");
            System.out.println("obsListTags " + obsListTags.size());
            System.out.println("lSelectedTags " + lSelectedTags.size());
            stmt.close();
        } catch (SQLException e) {
        } finally {
            DBConnect.closeConnection(con);
        }
    }

    public void genresDatesCountriesDirector() {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";

        genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);
        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();
            if (lSelectedCountries.isEmpty()) {

                sqlQuery = "SELECT md.directorID, md.directorName "
                        + "FROM Movie_Director md "
                        + "WHERE md.movieID IN (SELECT m.movieID "
                        + "FROM movie m, movie_genre mg, Movie_Director md "
                        + "WHERE m.movieID = mg.movieID AND m.movieID = md.movieID AND md.movieID = mg.movieID "
                        + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + "))";
            } else {
                String countriesSelectionBuffer = "";
                countriesSelectionBuffer = getCountriesSelectionBuffer(countriesSelectionBuffer);
                sqlQuery = "SELECT md.directorID, md.directorName "
                        + "FROM Movie_Director md "
                        + "WHERE md.movieID IN (SELECT m.movieID "
                        + "FROM movie m, movie_genre mg, movie_country mc "
                        + "WHERE m.movieID = mg.movieID AND m.movieID = mc.movieID "
                        + "AND mc.movieID = mg.movieID "
                        + "AND mc.country IN (" + countriesSelectionBuffer + ") "
                        + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + "))";

            }
            System.out.println(sqlQuery);
            System.out.println("");
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListDirectors.clear();
            directorsListView.getItems().clear();
            while (result.next()) {
                obsListDirectors.add(result.getString(2));
                directorsListView.getItems().addAll(result.getString(2));
            }
            System.out.println("");
            System.out.println("ObsListDirectors " + obsListDirectors.size());
            System.out.println("lSelectedDirectors " + lSelectedDirectors.size());
            directorsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedDirectors = directorsListView.getSelectionModel().getSelectedItems();
            stmt.close();
        } catch (SQLException exception) {

        } finally {
            DBConnect.closeConnection(con);
        }
    }

    public void genresDatesCountriesCast() {
        Connection con = null;
        Statement stmt = null;
        String sqlQuery = null;
        String genreSelectionBuffer = "";
        genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);

        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            sqlQuery = "SELECT c.actorID, c.actorName "
                    + "FROM Cast c "
                    + "WHERE c.movieID IN (SELECT m.movieID "
                    + "FROM movie m, movie_genre mg, cast c "
                    + "WHERE m.movieID = mg.movieID AND m.movieID = c.movieID AND c.movieID = mg.movieID "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + "))";

            System.out.println(sqlQuery);
            System.out.println("");
            ResultSet result = stmt.executeQuery(sqlQuery);
            obsListCast.clear();
            castListView.getItems().clear();                 // maybe clear first the castListView and then the obsList
            while (result.next()) {
                obsListCast.add(result.getString(2));
                castListView.getItems().addAll(result.getString(2));
            }
            System.out.println("");
            //  System.out.println("obsListCast "+ obsListCast.toString());
            System.out.println("lSelectedCountries " + lSelectedCountries.toString());
            castListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedCast = castListView.getSelectionModel().getSelectedItems();

            stmt.close();
        } catch (SQLException exception) {

        } finally {
            DBConnect.closeConnection(con);
        }
    }

    @FXML
    private void handleSimpleButtonAction(ActionEvent event) {
        Connection con = null;
        Statement stmt = null;
        Statement stmt2 = null;
        String genreSelectionBuffer = "";
        String sqlQuery = null;
        String sqlQueryCountries = null;
        try {
            con = DBConnect.openConnection();
            stmt = con.createStatement();

            LocalDate endDateLimit = endDate.getValue();
            // endYear = endDateLimit.getYear();
            LocalDate startDateLimit = startDate.getValue();
            // startYear = startDateLimit.getYear();

/////////////////////////////////!!!!!!!!!!!!
            genreSelectionBuffer = getGenreSelectionBuffer(genreSelectionBuffer);
/////////////////////////////////            

            sqlQuery = "SELECT m.movieID, mc.country "
                    + "FROM movie m, movie_country mc, movie_genre mg "
                    + "WHERE m.movieID = mg.movieID AND m.movieID = mc.movieID AND mc.movieID=mg.movieID "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + ")";
            System.out.println("---------------------------------------------------");
            System.out.println(sqlQuery);
            System.out.println("");

            /* query that selects the movieIDs, derived from genre and date selections. The base line data*/
            ResultSet result = stmt.executeQuery(sqlQuery);

            while (result.next()) {
                lMovieIDsOfCountries.add(result.getString(1));
            }

            stmt.close();

            sqlQueryCountries = "SELECT DISTINCT s.country "
                    + "FROM ( "
                    + "SELECT m.movieID, mc.country "
                    + "FROM movie m, movie_country mc, movie_genre mg "
                    + "WHERE m.movieID = mg.movieID AND m.movieID = mc.movieID AND mc.movieID=mg.movieID "
                    + "AND m.year BETWEEN " + startYear + " AND " + endYear + " AND mg.genre IN (" + genreSelectionBuffer + "))s";

            System.out.println(sqlQueryCountries);

            countriesListView.getItems().clear();
            //  lSelectedGenres.clear(); NOT this ONE
            stmt2 = con.createStatement();
            obsListCountries.clear();
            ResultSet result2 = stmt2.executeQuery(sqlQueryCountries);
            while (result2.next()) {
                obsListCountries.add(result2.getString(1));
                countriesListView.getItems().addAll(result2.getString(1));
            }

            System.out.println("");
            System.out.println("listSelected genres  " + getListSelectedGenres().toString());
            System.out.println("");
            System.out.println("obsListCountries  " + getObsListCountries().toString());
            //System.out.println("lMovieIDsOfCountries "+ lMovieIDsOfCountries.toString());
            System.out.println("------------------------------------------------------");
            countriesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedCountries = countriesListView.getSelectionModel().getSelectedItems();

            stmt2.close();
            genresDatesCountriesCast();
            genresDatesCountriesDirector();
            genresDatesCountriesCastTags();
            genresDatesCountriesCastTagsMovies();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
                stmt2.close();
                DBConnect.closeConnection(con);

            } catch (SQLException ex) {
                System.out.println("==============last catch (after close)");
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        Connection con = DBConnect.openConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT DISTINCT Genre FROM Movie_Genre");

            while (result.next()) {
                genresListView.getItems().addAll(result.getString(1));
            }
            stmt.close();
            genresListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lSelectedGenres = genresListView.getSelectionModel().getSelectedItems();

        } catch (Exception ex) {
            System.out.println("exception" + ex.toString());
        } finally {
            DBConnect.closeConnection(con);
        }
    }
    
     public String getTagsSelectionBuffer(String tagsBuffer) {
        tagsBuffer = "'" + lSelectedTags.get(0) + "'";
        if (lSelectedTags.size() > 1) {
            for (int i = 1; i < lSelectedTags.size(); i++) {
                tagsBuffer = tagsBuffer + ", '" + lSelectedTags.get(i) + "'";
            }
        }
        return tagsBuffer;
    }

    public String getCastSelectionBuffer(String castBuffer) {
        castBuffer = "'" + lSelectedCast.get(0) + "'";
        if (lSelectedCast.size() > 1) {
            for (int i = 1; i < lSelectedCast.size(); i++) {
                castBuffer = castBuffer + ", '" + lSelectedCast.get(i) + "'";
            }
        }
        return castBuffer;
    }

    public String getDirectorsSelectionBuffer(String directorsBuffer) {
        directorsBuffer = "'" + lSelectedDirectors.get(0) + "'";
        if (lSelectedDirectors.size() > 1) {
            for (int i = 1; i < lSelectedDirectors.size(); i++) {
                directorsBuffer = directorsBuffer + ", '" + lSelectedDirectors.get(i) + "'";
            }
        }
        return directorsBuffer;
    }

    public String getCountriesSelectionBuffer(String countriesSelectionBuffer) {
        countriesSelectionBuffer = "'" + lSelectedCountries.get(0) + "'";
        if (lSelectedCountries.size() > 1) {
            for (int i = 1; i < lSelectedCountries.size(); i++) {
                countriesSelectionBuffer = countriesSelectionBuffer + ", '" + lSelectedCountries.get(i) + "'";
            }
        }
        return countriesSelectionBuffer;
    }

    public String getGenreSelectionBuffer(String genreSelectionBuffer) {
        genreSelectionBuffer = "'" + lSelectedGenres.get(0) + "'";
        if (lSelectedGenres.size() > 1) {
            for (int i = 1; i < lSelectedGenres.size(); i++) {
                genreSelectionBuffer = genreSelectionBuffer + ", '" + lSelectedGenres.get(i) + "'";
            }
        }
        return genreSelectionBuffer;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public ObservableList<String> getListSelectedGenres() {
        return lSelectedGenres;
    }

    public ObservableList<String> getObsListCountries() {
        return obsListCountries;
    }

}

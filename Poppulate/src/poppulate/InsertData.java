/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poppulate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class InsertData {

    private static List<Movie> movieList = new ArrayList<Movie>();
    private static List<MovieGenre> mGenreList = new ArrayList<MovieGenre>();
    private static List<Tag> tagList = new ArrayList<Tag>();
    private static List<MovieCountry> mCountryList = new ArrayList<MovieCountry>();
    private static List<MovieTag> mTagList = new ArrayList<MovieTag>();
    private static List<MovieDirector> mDirectorList = new ArrayList<MovieDirector>();
    private static List<Cast> castList = new ArrayList<Cast>();

    public void readMoviesFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");
                Movie m = new Movie(ar[0], ar[1], ar[5]);
                movieList.add(m);
                i++;
            }
            System.out.println("num of lines = " + i);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertMoviesIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Movie");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + " " + movieList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Movie VALUES(?,?,?)");
            stmt.setString(1, movieList.get(i).getMovieID());
            stmt.setString(2, movieList.get(i).getTitle());
            stmt.setInt(3, Integer.parseInt(movieList.get(i).getYear()));
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        movieList = null;
    }

    public void readMovieGenreFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");
                MovieGenre mg = new MovieGenre(ar[0], ar[1]);
                mGenreList.add(mg);
                i++;
            }
            System.out.println("num of lines in 'movie_genres.dat' file= " + i);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertMovieGenreIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Movie_Genre");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < mGenreList.size(); i++) {
            System.out.println((i + 1) + " " + mGenreList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Movie_Genre VALUES(?,?)");
            stmt.setString(1, mGenreList.get(i).getMovieID());
            stmt.setString(2, mGenreList.get(i).getGenre());
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        mGenreList = null;
    }

    public void readTagsFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");
                Tag tag = new Tag(ar[0], ar[1]);
                tagList.add(tag);
                i++;
            }
            System.out.println("num of lines in 'tags.dat' file= " + i);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertTagsIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Tag");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < tagList.size(); i++) {
            System.out.println((i + 1) + " " + tagList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Tag VALUES(?,?)");
            stmt.setString(1, tagList.get(i).getTagID());
            stmt.setString(2, tagList.get(i).getTagValue());
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        tagList = null;
    }

    public void readMovieCountriesFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");

                if (ar.length < 2) {
                    MovieCountry mc = new MovieCountry.Builder().setMovieID(ar[0]).build();
                    mCountryList.add(mc);
                    i++;
                } else {

                    // MovieCountry mc = new MovieCountry(ar[0], ar[1]);
                    MovieCountry mc = new MovieCountry.Builder().setMovieID(ar[0]).setCountry(ar[1]).build();
                    mCountryList.add(mc);
                    i++;
                }
            }
            System.out.println("num of lines in 'movie_countries.dat' file= " + i);
            // System.out.println(mCountryList);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertMovieCountriesIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Movie_Country");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < mCountryList.size(); i++) {
            System.out.println((i + 1) + " " + mCountryList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Movie_Country VALUES(?,?)");

            stmt.setString(1, mCountryList.get(i).getMovieID());
            if (mCountryList.get(i).getCountry() != null) {
                stmt.setString(2, mCountryList.get(i).getCountry());
            } else {
                stmt.setString(2, " ");
            }
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        mCountryList = null;
    }

    public void readMovieTagsFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");
                //System.out.println(line);
                MovieTag mt = new MovieTag(ar[0], ar[1], Integer.parseInt(ar[2]));              
                mTagList.add(mt);
                i++;
            }
            System.out.println("num of lines in 'movie_tags.dat' file= " + i);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void insertMovieTagsIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Movie_Tag");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < mTagList.size(); i++) {
            System.out.println((i + 1) + " " + mTagList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Movie_Tag VALUES(?,?,?)");

            stmt.setString(1, mTagList.get(i).getMovieID());
            stmt.setString(2, mTagList.get(i).getTagID());
            stmt.setInt(3, mTagList.get(i).getmWeight());          
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        mTagList = null;
    }

     
     public void readMovieDirectorsFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");
                //System.out.println(line);
                MovieDirector md = new MovieDirector(ar[0], ar[1], ar[2]);              
                mDirectorList.add(md);
                i++;
            }
            System.out.println("num of lines in 'movie_directors.dat' file= " + i);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
      public void insertMovieDirectorsIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Movie_Director");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < mDirectorList.size(); i++) {
            System.out.println((i + 1) + " " + mDirectorList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Movie_Director VALUES(?,?,?)");

            stmt.setString(1, mDirectorList.get(i).getMovieID());
            stmt.setString(2, mDirectorList.get(i).getDirectorID());
            stmt.setString(3, mDirectorList.get(i).getDirectorName());          
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        mDirectorList = null;
    }
     
      
      
      public void readCastFromFile(String filename) {
        FileReader fReader = null;
        BufferedReader br = null;
        String path = "C:\\Users\\alex\\Desktop\\hetrec2011-movielens-2k-v2\\" + filename;
        path = path.replace("\\", "/");
        try {
            fReader = new FileReader(path);
            br = new BufferedReader(fReader);
            String line;
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                String ar[] = line.split("\t");
               // System.out.println(line);
                Cast cast = new Cast(ar[0], ar[1], ar[2]);              
                castList.add(cast);
                i++;
            }
            System.out.println("num of lines in 'movie_actors.dat' file= " + i);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fReader.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      
       public void insertCastIntoDB() throws SQLException, ClassNotFoundException {
        Connection con = DBCon.openConnection();
        Statement st = con.createStatement();
        st.executeQuery("DELETE FROM Cast");
        st.close();
        PreparedStatement stmt = null;
        for (int i = 0; i < castList.size(); i++) {
            System.out.println((i + 1) + " " + castList.get(i).toString());
            stmt = con.prepareStatement("INSERT INTO Cast VALUES(?,?,?)");

            stmt.setString(1, castList.get(i).getMovieID());
            stmt.setString(2, castList.get(i).getActorID());
            stmt.setString(3, castList.get(i).getActorName());          
            stmt.executeUpdate();
            stmt.close();
        }
        DBCon.closeConnection(con);
        castList = null;
    }
     
}

package IMDb;

import java.util.*;
import java.io.*;

public class database {
    
    private BufferedReader dataset;
    private HashMap<String, movie> allMovies;
    private HashMap<String, ArrayList<String>> moviesByGenre;


    public database(String fileName) {
        try {
            dataset = new BufferedReader(new FileReader(fileName));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        allMovies = new HashMap<>();
        moviesByGenre = new HashMap<>();
        this.initializeGenreList();
    }

 
    private void initializeGenreList(){

        this.moviesByGenre.put("Action", new ArrayList<String>());
        this.moviesByGenre.put("Adventure", new ArrayList<String>());
        this.moviesByGenre.put("Animation", new ArrayList<String>());
        this.moviesByGenre.put("Biography", new ArrayList<String>());
        this.moviesByGenre.put("Comedy", new ArrayList<String>());
        this.moviesByGenre.put("Crime", new ArrayList<String>());
        this.moviesByGenre.put("Documentary", new ArrayList<String>());
        this.moviesByGenre.put("Drama", new ArrayList<String>());
        this.moviesByGenre.put("Family", new ArrayList<String>());
        this.moviesByGenre.put("Film Noir", new ArrayList<String>());
        this.moviesByGenre.put("History", new ArrayList<String>());
        this.moviesByGenre.put("Horror", new ArrayList<String>());
        this.moviesByGenre.put("Musical", new ArrayList<String>());
        this.moviesByGenre.put("Mystery", new ArrayList<String>());
        this.moviesByGenre.put("Romance", new ArrayList<String>());
        this.moviesByGenre.put("Sci-Fi", new ArrayList<String>());
        this.moviesByGenre.put("Short Film", new ArrayList<String>());
        this.moviesByGenre.put("Sport", new ArrayList<String>());
        this.moviesByGenre.put("Superhero", new ArrayList<String>());
        this.moviesByGenre.put("Thriller", new ArrayList<String>());
        this.moviesByGenre.put("War", new ArrayList<String>());
        this.moviesByGenre.put("Western", new ArrayList<String>());
        
    }
}

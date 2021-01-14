package IMDb;

import java.util.*;
import java.io.*;

public class database {
    
    private BufferedReader dataset;
    private HashMap<String, movie> allMovies;
    private HashMap<String, ArrayList<String>> moviesByGenre;


    public database(String fileName) throws IOException{
        
        try {
            dataset = new BufferedReader(new FileReader(fileName));
            allMovies = new HashMap<>();
            moviesByGenre = new HashMap<>();
            this.initializeGenreList();
            this.fillDatabase();
        }
        catch (Exception e) {
            System.out.println(e);
        }

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

    private void fillDatabase() throws IOException {
        String line;
        int marker;
        ArrayList<String> info = new ArrayList<>();
        Boolean shouldIgnore;
        int lineNum;
        lineNum = 1;
        
        line = dataset.readLine();

        while ((line = dataset.readLine()) != null) {
            lineNum++;
            marker = 0;
            shouldIgnore = false;
            info.clear();

            for(int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '"') {
                    if (shouldIgnore) {
                        shouldIgnore = false;
                    }
                    else {
                        shouldIgnore = true;
                    }
                }
                else if (!shouldIgnore && line.charAt(i) == ',') {
                    info.add(line.substring(marker, i));
                    marker = i + 1;
                }
            }

            try {
                allMovies.put(info.get(0), new movie(info.get(1), info.get(7), info.get(8), info.get(9), info.get(13), Integer.parseInt(info.get(3)), Integer.parseInt(info.get(6))));
            }
            catch (Exception e) {
                System.out.println("Error in dataset on line: " + lineNum);
                System.out.println(e);
                
            }   

        }
    }
}

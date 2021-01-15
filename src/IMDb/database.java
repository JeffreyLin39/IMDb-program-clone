package IMDb;

import java.util.*;
import java.io.*;

public class database {
    
    private BufferedReader dataset;
    private ArrayList<String> movieList;
    private HashSet<String> filters;
    private HashMap<String, movie> allMovies;
    private HashMap<String, ArrayList<String>> moviesByGenre;


    public database(String fileName) throws IOException{
        
        try {
            dataset = new BufferedReader(new FileReader(fileName));
            allMovies = new HashMap<>();
            moviesByGenre = new HashMap<>();
            movieList = new ArrayList<>();
            filters = new HashSet<String>();
            this.fillDatabase();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    private void fillDatabase() throws IOException {
        String line;
        int marker;
        ArrayList<String> info = new ArrayList<>();
        Boolean shouldIgnore;
        int lineNum;
        String genre;
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
                movieList.add(info.get(0));
                allMovies.put(info.get(0), new movie(info.get(1), info.get(5), info.get(7), info.get(8), info.get(9), info.get(13), Integer.parseInt(info.get(3)), Integer.parseInt(info.get(6))));
                
                marker = 1;
                for(int i = 1; i < info.get(5).length(); i++) {
                    if (info.get(5).charAt(i) == ',' || info.get(5).charAt(i) == '"') {
                        genre = info.get(5).substring(marker, i);
                        if (!moviesByGenre.containsKey(genre)) {
                            moviesByGenre.put(genre, new ArrayList<String>());
                        }
                        moviesByGenre.get(genre).add(info.get(0));
                        marker = i + 2;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Error in dataset on line: " + lineNum);
                System.out.println(e);
            }   
        }
    }

    public ArrayList<String> getMovieByGenre(String genre){
        return this.moviesByGenre.get(genre);
    }

    public Set<String> getGenreList(){
        return this.moviesByGenre.keySet();
    }

    public void sort(int value, int start, int end, ArrayList<String> temp) {
        int middle;

        if (end - start > 0) {
            middle = (start + end) / 2;

            sort(value, start, middle, temp);
            sort(value, middle + 1, end, temp);

            merge(value, start, middle, end, temp);
            
        }
    }

    public void merge(int value, int start, int middle, int end, ArrayList<String> temp) {
        int pos1;
        int pos2;

        pos1 = start;
        pos2 = middle + 1;

        while(pos1 <= middle && pos2 <= end) {
            if(value > 4){
                if (Integer.parseInt(this.allMovies.get(this.movieList.get(pos1)).getParameter(value)) < Integer.parseInt(this.allMovies.get(this.movieList.get(pos2)).getParameter(value))) {
                    temp.add(this.movieList.get(pos1));
                    pos1++;
                }
                else {
                    temp.add(this.movieList.get(pos2));
                    pos2++;
                }
            } 
            else {
                if (this.allMovies.get(this.movieList.get(pos1)).getParameter(value).compareTo(this.allMovies.get(this.movieList.get(pos2)).getParameter(value)) < 1) {
                    temp.add(this.movieList.get(pos1));
                    pos1++;
                }
                else {
                    temp.add(this.movieList.get(pos2));
                    pos2++;
                }
            }
        }

       while (pos1 <= middle) {
           temp.add(this.movieList.get(pos1));
           pos1++;
       }
      
       while (pos2 <= end) {
            temp.add(this.movieList.get(pos2));
            pos2++;
        }
      
        for(int i = start; start <= end; start++) {
            this.movieList.set(i, temp.get(i));
        }

    }

}

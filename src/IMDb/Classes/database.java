package IMDb.Classes;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class database {
    
    private BufferedReader dataset;
    private ArrayList<String> movieList;
    private HashSet<String> genreFilters;
    private HashMap<String, movie> allMovies;
    private int minDur, maxDur, minYear, maxYear;
    private double minScore;
    private double maxScore;


    public database(String fileName, int minDur, int maxDur, int minYear, int maxYear, double minScore, double maxScore) {
        
        try {
            dataset = new BufferedReader(new FileReader(fileName));
            allMovies = new HashMap<>();
            movieList = new ArrayList<>();
            genreFilters = new HashSet<String>();
            this.minDur = minDur;
            this.maxDur = maxDur;
            this.minYear = minYear;
            this.maxYear = maxYear;
            this.minScore = minScore;
            this.maxScore = maxScore;
            this.fillDatabase();
        }

        catch (Exception e) {
            System.out.println(e);
        }

    }

    private void fillDatabase() throws IOException {

        String line;
        int marker;
        Boolean shouldIgnore;
        Boolean ignoreLast;
        int lineNum;
        lineNum = 1;
        ignoreLast = false;
        
        line = dataset.readLine();

        while ((line = dataset.readLine()) != null) {

            lineNum++;
            marker = 0;
            shouldIgnore = false;
            ArrayList<String> info = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == '"') {
                    if (shouldIgnore) {
                        ignoreLast = true;
                        shouldIgnore = false;
                    }
                    else {
                        shouldIgnore = true;
                        marker++;
                    }
                }
                else if (!shouldIgnore && line.charAt(i) == ',') {
                    if (ignoreLast) {
                        info.add(line.substring(marker, i - 1));
                        ignoreLast = false;
                    }
                    else {
                        info.add(line.substring(marker, i));
                    }
                    marker = i + 1;
                }
            }

            try {
                movieList.add(info.get(0));
                allMovies.put(info.get(0), new movie(info.get(2), info.get(5), info.get(7), info.get(8), info.get(9), info.get(13), Integer.parseInt(info.get(3)), Integer.parseInt(info.get(6)), Double.parseDouble(info.get(14))));
            }
            catch (Exception e) {
                System.out.println("Error in dataset on line: " + lineNum);
                System.out.println(e);
            }   
        }
    }

    public int getSize() {
        return this.allMovies.size();
    }

    public void setIntegerFilters(double minScore, double maxScore, int minYear, int maxYear, int minDur, int maxDur) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.minDur = minDur;
        this.maxDur = maxDur;
    }

    public void sort(int value, int start, int end) {
        int middle;

        if (start < end) {
            middle = (start + end) / 2;

            sort(value, start, middle);
            sort(value, middle + 1, end);
            merge(value, start, middle, end);
            
        }
    }

    public void merge(int value, int start, int middle, int end) {

        int pos1;
        int pos2;
        int count;
        ArrayList<String> temp = new ArrayList<>();

        pos1 = start;
        pos2 = middle + 1;
        count = start;

        while (pos1 <= middle && pos2 <= end) {

            if (this.allMovies.get(this.movieList.get(pos1)).getParameter(value).compareTo(this.allMovies.get(this.movieList.get(pos2)).getParameter(value)) < 0) {
                temp.add(this.movieList.get(pos1));
                pos1++;
            }
            else {
                temp.add(this.movieList.get(pos2));
                pos2++;
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
        
        for (String item: temp) {
            this.movieList.set(count, item);
            count++;
        }

    }

    public movie getMovie(String id) {
        return this.allMovies.get(id);
    }

    public void addGenreFilter(String filter) {
        this.genreFilters.add(filter);
    }

    public void removeGenreFilter(String filter) {
        this.genreFilters.remove(filter);
    }

    public ObservableList<movie> searchMovies(String search) {

        ObservableList<movie> searchResults = FXCollections.observableArrayList();
        
        for (String key: this.allMovies.keySet()) {
            if (this.allMovies.get(key).getTitle().toLowerCase().contains(search)) {
                searchResults.add(this.allMovies.get(key));
            }
        }

        return searchResults;
    }

    public ObservableList<movie> searchFiltered(boolean isInverse) {

        ObservableList<movie> filterResults = FXCollections.observableArrayList();
        String[] genres;
        boolean isFiltered;
        
        for (String key: this.movieList) {

            genres = this.allMovies.get(key).getGenre().split(", ");
            isFiltered = false;

            for (String genre: genres) {
                if (genreFilters.contains(genre)) {
                    isFiltered = true;
                }
            }
            int curDur = this.allMovies.get(key).getDuration();
            int curYear = this.allMovies.get(key).getYear();
            double curScore = this.allMovies.get(key).getScore();

            if (!isFiltered && (this.minDur <= curDur  && curDur <= this.maxDur) && (this.minScore <= curScore && curScore <= this.maxScore ) && (this.minYear <= curYear && curYear <= this.maxYear)) {
                filterResults.add(this.allMovies.get(key));
            }
        }

        if (isInverse) {
            FXCollections.reverse(filterResults);
        }

        return filterResults;
    }

    public ArrayList<String> getMovieList() {
        return this.movieList;
    }

}

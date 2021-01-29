package IMDb.Classes;

/**
* Database class file
* @author: J. Lin
* 
*/

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class database {
    
    // Instance variables
    private BufferedReader dataset;
    private ArrayList<String> movieList;
    private HashSet<String> genreFilters;
    private HashMap<String, movie> allMovies;
    private int minDur, maxDur, minYear, maxYear;
    private double minScore;
    private double maxScore;

    /**
    * Constructor - creates new instance of a database object
    *
    * @param fileName - the path of the file that should be loaded into the database
    * @param minDur - minimum duration of movies to show, when searching with filters
    * @param maxDur - maximum duration of movies to show, when searching with filters
    * @param minYear - minimum release year to show, when searching with filters
    * @param maxYear - maximum release year to show, when searching with filters
    * @param minScore - minimum score to show, when searching with filters
    * @param maxScore - maximum score to show, when searching with filters
    */
    public database(String fileName, int minDur, int maxDur, int minYear, int maxYear, double minScore, double maxScore) {
        
        // try and catch statement in case file path is not valid
        try {
            // initialize file reader, map of all movies, list to hold sorted movies, and set of filters to ignore
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

    /**
    * Goes through the file given when initialized, turns each line into a movie object, and adds it to the data base
    */
    private void fillDatabase() throws IOException {

        // Variable initialization and declaration 
        String line;
        int marker;
        Boolean shouldIgnore;
        Boolean ignoreLast;
        int lineNum;
        lineNum = 1;
        ignoreLast = false;
        ArrayList<String> info = new ArrayList<>();
        line = dataset.readLine();

        // Read each line in the file until it is empty
        while ((line = dataset.readLine()) != null) {

            // keep track of the number of lines
            lineNum++;
            marker = 0;
            shouldIgnore = false;

            // seperate each line by commas and add each entry into the arraylist
            info.clear();
            for (int i = 0; i < line.length(); i++) {

                // If there are quotes, take everything from the current quotes to the next quotes and add it to the arraylist
                // ignore the commas imbetween the quotes
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

                // Add the specific string inbetween the commas and add it to the arraylist
                else if (!shouldIgnore && line.charAt(i) == ',') {
                    if (ignoreLast) {
                        info.add(line.substring(marker, i - 1));
                        ignoreLast = false;
                    }
                    else {
                        info.add(line.substring(marker, i));
                    }
                    // current location where the next string of info will start
                    marker = i + 1;
                }
            }

            try {
                // get the data from the arraylist and add the id of the movie to the list containing all movies, and the map containing all movies
                // the key for the map is the id of the movie and the value associated with the key is a movie object
                movieList.add(info.get(0));
                allMovies.put(info.get(0), new movie(info.get(2), info.get(5), info.get(7), info.get(8), info.get(9), info.get(13), Integer.parseInt(info.get(3)), Integer.parseInt(info.get(6)), Double.parseDouble(info.get(14))));
            }
            catch (Exception e) {
                // used for troubleshooting, i.e some lines in the data set were incorrectly formatted
                System.out.println("Error in dataset on line: " + lineNum);
                System.out.println(e);
            }   
        }

        // close the dataset
        dataset.close();
    
    }

    /**
    * get the number of movies
    */
    public int getSize() {
        return this.allMovies.size();
    }

    /**
    * Set the filters for the fields that are numbers, which are score, year, and duration
    *
    * @param minDur - minimum duration of movies to show, when searching with filters
    * @param maxDur - maximum duration of movies to show, when searching with filters
    * @param minYear - minimum release year to show, when searching with filters
    * @param maxYear - maximum release year to show, when searching with filters
    * @param minScore - minimum score to show, when searching with filters
    * @param maxScore - maximum score to show, when searching with filters
    */
    public void setNumberFilters(double minScore, double maxScore, int minYear, int maxYear, int minDur, int maxDur) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.minDur = minDur;
        this.maxDur = maxDur;
    }

    /**
    * Merge sort algorithm, for sorting the movie by different values
    *
    * @param value - the value to sort by, i.e title, year, etc
    * @param start - used to keep track of the start of the current list to help find where to further split the list
    * @param end - used to keep track of the end of the current list to help find where to further split the list
    */
    public void sort(int value, int start, int end) {

        // variable declaration
        int middle;

        if (start < end) {

            // find the start of the current section of the list
            middle = (start + end) / 2;

            // split first half with a recursive call
            sort(value, start, middle);
            // split the second half with a recursive call
            sort(value, middle + 1, end);
            // merge the two halves together
            merge(value, start, middle, end);
            
        }
    }

    /**
    * Merging algorithm, merges two halfs of the list in sorted order
    *
    * @param value - the value to sort by, i.e title, year, etc
    * @param start - used to keep track of the start of the current section of the list to know where to sort from
    * @param middle - used to keep track of the middle of the list to know where to end sorting for first half and start sorting for second half
    * @param end - used to keep track of end of the list to know when to stop sorting for second half
    */
    public void merge(int value, int start, int middle, int end) {

        // variable declaration
        int pos1;
        int pos2;
        int count;
        ArrayList<String> temp = new ArrayList<>();

        // find where to start and end when sorting each half
        pos1 = start;
        pos2 = middle + 1;
        count = start;

        // merge the two halves until one of them is fully sorted
        while (pos1 <= middle && pos2 <= end) {

            // sort by comparing each value as a string, and put the one alphabetically greater at the front
            if (this.allMovies.get(this.movieList.get(pos1)).getParameter(value).compareTo(this.allMovies.get(this.movieList.get(pos2)).getParameter(value)) < 0) {
                temp.add(this.movieList.get(pos1));
                pos1++;
            }
            // else put the other one at the front
            else {
                temp.add(this.movieList.get(pos2));
                pos2++;
            }
            
        }

        // add the remaining items in either of the halfves into the sorted list
        while (pos1 <= middle) {
           temp.add(this.movieList.get(pos1));
           pos1++;
        }
      
        while (pos2 <= end) {
            temp.add(this.movieList.get(pos2));
            pos2++;
        }
        
        // replace the actual list with the temparary sorted list
        for (String item: temp) {
            this.movieList.set(count, item);
            count++;
        }

    }

    /**
    * Gets the movie object based on the movie id
    *
    * @param id - the id of the movie
    *
    * @return movie - the movie object to return 
    */
    public movie getMovie(String id) {
        return this.allMovies.get(id);
    }

    /**
    * Add a genre to the filters, so it is not included in search
    *
    * @param filter - genre to exclude
    */
    public void addGenreFilter(String filter) {
        this.genreFilters.add(filter);
    }

    /**
    * remove a genre from the filters, so it is included in search
    *
    * @param filter - genre to remove
    */
    public void removeGenreFilter(String filter) {
        this.genreFilters.remove(filter);
    }

    /**
    * search movies based on the title of the movie
    *
    * @param search - title of the movie to find
    *
    * @return ObservableList<movie> of the list of movies that include the provided string in their title
    */
    public ObservableList<movie> searchMovies(String search) {

        // list declaration and initialization
        ObservableList<movie> searchResults = FXCollections.observableArrayList();
        
        // go through each movie and see if the title contains the given string
        for (String key: this.allMovies.keySet()) {
            if (this.allMovies.get(key).getTitle().toLowerCase().contains(search)) {
                searchResults.add(this.allMovies.get(key));
            }
        }

        // return the search results
        return searchResults;
    }

    /**
    * search movies based on the the given filters
    *
    * @param isInverse - whether the list should be in descending or ascending order
    *
    * @return ObservableList<movie> of the list of movies that fit the given filters
    */
    public ObservableList<movie> searchFiltered(boolean isInverse, String search) {

        // variable declaration
        ObservableList<movie> filterResults = FXCollections.observableArrayList();
        String[] genres;
        boolean isFiltered;
        int curDur;
        int curYear;
        double curScore;

        // go through each movie in the data base
        for (String key: this.movieList) {

            // split the genre property by commas
            genres = this.allMovies.get(key).getGenre().split(", ");
            isFiltered = false;

            // check if the genres in the movie match the ones in the filter
            for (String genre: genres) {
                if (genreFilters.contains(genre)) {
                    isFiltered = true;
                }
            }

            // check the numeric stats of the movie
            curDur = this.allMovies.get(key).getDuration();
            curYear = this.allMovies.get(key).getYear();
            curScore = this.allMovies.get(key).getScore();

            // if the movie doesn't match/violate any of the filters, than add it to the search list
            if (this.allMovies.get(key).getTitle().toLowerCase().contains(search) && !isFiltered && (this.minDur <= curDur  && curDur <= this.maxDur) && (this.minScore <= curScore && curScore <= this.maxScore ) && (this.minYear <= curYear && curYear <= this.maxYear)) {
                filterResults.add(this.allMovies.get(key));
            }
        }

        // reverse the results if the search should be reversed
        if (isInverse) {
            FXCollections.reverse(filterResults);
        }

        // return results
        return filterResults;
    }

    /**
    * get the list of all movie id's
    *
    * @return ArrayList<String> of the list of all the id's of the movies
    */
    public ArrayList<String> getMovieList() {
        return this.movieList;
    }

}

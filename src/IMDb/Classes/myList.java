package IMDb.Classes;

/**
* myList class file
* @author: J. Lin
* 
*/

import java.util.HashMap;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class myList {

    // Instance variables
    private ObservableList<movie> planToWatch;
    private ObservableList<movie> completed;
    private HashSet<movie> planToWatchList;
    private HashSet<movie> completedList;
    private HashMap<String, Integer> genreSize;
    private int numCompleted;
    private int numToWatch;
    private double avgRating;
    private int totalWatchTime;

    /**
    * constructor - creates new instance of myList object, no parameters because list will be empty at the start no matter what
    */ 
    public myList(){

        // Initialize all parameters
        planToWatch = FXCollections.observableArrayList();
        completed = FXCollections.observableArrayList();
        planToWatchList = new HashSet<movie>();
        completedList = new HashSet<movie>();
        genreSize = new HashMap<>();
        numCompleted = 0;
        numToWatch = 0;
        avgRating = 0;
        totalWatchTime = 0;

    }

    /**
    * Get the number of movies the user has watched or plans to watch in each genre
    *
    * @return HashMap<String, Integer> of the genres and the number of movies associated with those genres in the user's list
    */
    public HashMap<String, Integer> getGenres() {
        return this.genreSize;
    }

    /**
    * Add a completed movie to the user's list
    *
    * @param mov - movie to add to completed list
    */
    public void addCompleted(movie mov) {

        String[] temp;

        // add to completed list
        completed.add(mov);
        completedList.add(mov);
        this.numCompleted++;
        // add to other stats like watch time, and total score
        totalWatchTime += mov.getDuration();
        avgRating += mov.getScore();

        // split the genre's by commas and increase the respective values of the map with those genre's as keys by 1
        temp = mov.getGenre().split(", ");

        for (String genre: temp) {
            // check if key already exists
            if (!genreSize.containsKey(genre)) {
                genreSize.put(genre, 1);
            }
            // otherwise increase current value of that key by 1
            else {
                genreSize.put(genre, genreSize.get(genre) + 1);
            }
        }

    }

    /**
    * Add a planned to watch movie to the user's list
    *
    * @param mov - movie to add to plan to watch list
    */
    public void addPlanToWatch(movie mov) {

        String[] temp;

        // add movie to list and map
        planToWatch.add(mov);
        planToWatchList.add(mov);

        // split the genre's by commas and increase the respective values of the map with those genre's as keys by 1
        temp = mov.getGenre().split(", ");

        // increase number of movies that are planned to watch
        this.numToWatch++;

        // check if key already exists 
        for (String genre: temp) {
            if (!genreSize.containsKey(genre)) {
                genreSize.put(genre, 1);
            }
            // otherwise increase current value of that key by 1
            else {
                genreSize.put(genre, genreSize.get(genre) + 1);
            }
        }
    }

    /**
    * remove a movie from the user's completed list
    *
    * @param mov - movie to remove from completed list
    */
    public void removeCompleted(movie mov) {

        String[] temp;

        // remove movie from lists and decrease other stats
        completed.remove(mov);
        completedList.remove(mov);
        this.numCompleted--;
        totalWatchTime -= mov.getDuration();
        avgRating -= mov.getScore();

        // split genres by comma and decreases values in genre map by 1
        temp = mov.getGenre().split(", ");

        for (String genre: temp) {
            genreSize.put(genre, genreSize.get(genre) - 1);
        }
        
    }

    /**
    * remove a movie from the user's plan to watch list
    *
    * @param mov - movie to remove from plan to watch list
    */
    public void removePlanToWatch(movie mov) {

        String[] temp;

        // remove movie from lists and decrease other stats
        planToWatch.remove(mov);
        planToWatchList.remove(mov);
        this.numToWatch--;

        // split genres by commas and decrease values in the genre map by 1
        temp = mov.getGenre().split(", ");

        for (String genre: temp) {
            genreSize.put(genre, genreSize.get(genre) - 1);
        }
    }

    /**
    * get the list of movies that are in the plan to watch list
    *
    * @return ObservableList<movie> - list of movies in the plan to watch list
    */
    public ObservableList<movie> getPlanToWatch(){
        return this.planToWatch;
    }

    /**
    * get the list of movies that are in the completed list
    *
    * @return ObservableList<movie> - list of movies in the completed list
    */
    public ObservableList<movie> getCompleted(){
        return this.completed;
    }

    /**
    * check whether or not a movie has been completed
    *
    * @return boolean of whether the movie has been completed or not
    */
    public boolean hasCompleted(movie mov) {
        if (completedList.contains(mov)) {
            return true;
        }
        return false;
    }

    /**
    * check whether or not a movie is on the plan to watcg list
    *
    * @return boolean of whether the movie is on the plan to watcg list or not
    */
    public boolean hasPlanned(movie mov) {
        if (planToWatchList.contains(mov)) {
            return true;
        }
        return false;
    }

    /**
    * get the total amount of time the user has spent watching movies
    *
    * @return int of the total amount of time the user has spent watching movies
    */
    public int getWatchTime() {
        return this.totalWatchTime;
    }

    /**
    * get the total sum of the scores of the user's movies
    *
    * @return double of the sum of the scores of the user's movies
    */
    public double getAvgScore() {
        return this.avgRating;
    }

    /**
    * get the number of movies the user has completed
    *
    * @return int of the number of movies completed by the user
    */
    public int getNumCompleted() {
        return this.numCompleted;
    }

    /**
    * get the number of movies the user has planned to watch
    *
    * @return int of the number of movies user has planned to watch
    */
    public int getNumPlanned() {
        return this.numToWatch;
    }
    
}

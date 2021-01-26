package IMDb.Classes;

import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class myList {

    private ObservableList<movie> planToWatch;
    private ObservableList<movie> completed;
    private HashSet<movie> planToWatchList;
    private HashSet<movie> completedList;
    private HashMap<String, Integer> genreSize;
    private int numCompleted;
    private int numToWatch;
    private double avgRating;
    private int totalWatchTime;

    public myList(){

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

    public HashMap<String, Integer> getGenres() {
        return this.genreSize;
    }

    public void addCompleted(movie mov) {

        completed.add(mov);
        completedList.add(mov);
        this.numCompleted++;
        totalWatchTime += mov.getDuration();
        avgRating += mov.getScore();

        String[] temp = mov.getGenre().split(", ");

        for (String genre: temp) {
            if (!genreSize.containsKey(genre)) {
                genreSize.put(genre, 1);
            }
            else {
                genreSize.put(genre, genreSize.get(genre) + 1);
            }
        }

    }

    public void addPlanToWatch(movie mov) {

        planToWatch.add(mov);
        planToWatchList.add(mov);

        String[] temp = mov.getGenre().split(", ");

        this.numToWatch++;

        for (String genre: temp) {
            if (!genreSize.containsKey(genre)) {
                genreSize.put(genre, 1);
            }
            else {
                genreSize.put(genre, genreSize.get(genre) + 1);
            }
        }
    }

    public void removeCompleted(movie mov) {

        completed.remove(mov);
        completedList.remove(mov);
        this.numCompleted--;
        totalWatchTime -= mov.getDuration();
        avgRating -= mov.getScore();

        String[] temp = mov.getGenre().split(", ");

        for (String genre: temp) {
            genreSize.put(genre, genreSize.get(genre) - 1);
        }
        
    }

    public void removePlanToWatch(movie mov) {

        planToWatch.remove(mov);
        planToWatchList.remove(mov);
        this.numToWatch--;
        String[] temp = mov.getGenre().split(", ");

        for (String genre: temp) {
            genreSize.put(genre, genreSize.get(genre) - 1);
        }
    }

    public ObservableList<movie> getPlanToWatch(){
        return this.planToWatch;
    }

    public ObservableList<movie> getCompleted(){
        return this.completed;
    }

    public boolean hasCompleted(movie mov) {
        if (completedList.contains(mov)) {
            return true;
        }
        return false;
    }

    public boolean hasPlanned(movie mov) {
        if (planToWatchList.contains(mov)) {
            return true;
        }
        return false;
    }

    public int getWatchTime() {
        return this.totalWatchTime;
    }

    public double getAvgScore() {
        return this.avgRating;
    }

    public int getNumCompleted() {
        return this.numCompleted;
    }

    public int getNumPlanned() {
        return this.numToWatch;
    }
    
}

package IMDb.Classes;

import java.util.HashMap;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class myList {

    ObservableList<movie> planToWatch;
    ObservableList<movie> completed;
    HashSet<movie> planToWatchList;
    HashSet<movie> completedList;
    HashMap<String, Integer> genreSize;

    public myList(){
        planToWatch = FXCollections.observableArrayList();
        completed = FXCollections.observableArrayList();
        planToWatchList = new HashSet<movie>();
        completedList = new HashSet<movie>();
        genreSize = new HashMap<>();
    }

    public HashMap<String, Integer> getGenres(){
        return this.genreSize;
    }

    public void addCompleted(movie mov){
        completed.add(mov);
        completedList.add(mov);

        String[] temp = mov.getGenre().split(", ");

        for(String genre: temp){
            if(!genreSize.containsKey(genre)){
                genreSize.put(genre, 1);
            }
            else{
                genreSize.put(genre, genreSize.get(genre) + 1);
            }
        }

    }

    public void addPlanToWatch(movie mov){
        planToWatch.add(mov);
        planToWatchList.add(mov);

        String[] temp = mov.getGenre().split(", ");

        for(String genre: temp){
            if(!genreSize.containsKey(genre)){
                genreSize.put(genre, 1);
            }
            else{
                genreSize.put(genre, genreSize.get(genre) + 1);
            }
        }
    }

    public void removeCompleted(movie mov){
        completed.remove(mov);
        completedList.remove(mov);

        String[] temp = mov.getGenre().split(", ");

        for(String genre: temp){

            genreSize.put(genre, genreSize.get(genre) - 1);

        }
        
    }

    public void removePlanToWatch(movie mov){
        planToWatch.remove(mov);
        planToWatchList.remove(mov);

        String[] temp = mov.getGenre().split(", ");

        for(String genre: temp){

            genreSize.put(genre, genreSize.get(genre) - 1);

        }
    }

    public ObservableList<movie> getPlanToWatch(){
        return this.planToWatch;
    }

    public ObservableList<movie> getCompleted(){
        return this.completed;
    }

    public boolean hasCompleted(movie mov){
        if(completedList.contains(mov)){
            return true;
        }
        return false;
    }

    public boolean hasPlanned(movie mov){
        if(planToWatchList.contains(mov)){
            return true;
        }
        return false;
    }
    
}

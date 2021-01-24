package IMDb.Classes;

import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class myList {

    ObservableList<movie> planToWatch;
    ObservableList<movie> completed;
    HashSet<movie> planToWatchList;
    HashSet<movie> completedList;

    public myList(){
        planToWatch = FXCollections.observableArrayList();
        completed = FXCollections.observableArrayList();
        planToWatchList = new HashSet<movie>();
        completedList = new HashSet<movie>();
    }

    public void addCompleted(movie mov){
        completed.add(mov);
    }

    public void addPlanToWatch(movie mov){
        planToWatch.add(mov);
    }

    public void removeCompleted(int index){
        completed.remove(index);
    }

    public void removePlanToWatch(int index){
        planToWatch.remove(index);
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

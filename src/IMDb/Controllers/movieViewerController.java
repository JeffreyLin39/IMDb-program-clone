package IMDb.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import IMDb.main;
import IMDb.Classes.movie;

public class movieViewerController implements Initializable {

    // holds the previous scene, so program knows which scene to go to when the exit button is pressed
    private static int previousScene;
    public TableView<movie> dataTable;
    public TextField searchBar;
    public Button planToWatch;
    public Button completed;
    
    /**
    * Initialize method, runs when movieInfo scene is created
    *
    * @param location - location of fxml file
    * @param resources - reference to java ResourceBundle
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         return;  
    }

    /**
    * Sets the previous scene
    *
    * @param scene - integer representing the previous scene
    */
    public static void setScene(int scene) {
        previousScene = scene;
    }

    /**
    * Search for movies based on the text entered in the search bar
    *
    * @param event - the event made by the user, i.e hitting enter on the keyboard
    */
    public void onEnter(ActionEvent event) {
        
        TableView dataTable = (TableView<movie>) main.getBrowse().lookup("#dataTable");

        // check if search bar is empty
        if(searchBar.getText().equals("")) {
            main.resetTable();
        }
        // otherwise search the database and replace the entries in the table based on the results
        else {
            ObservableList<movie> searchResults = main.getDatabase().searchMovies(searchBar.getText().toLowerCase());
            dataTable.setItems(searchResults);
        }

        // switch to the browse scene
        loadBrowse();

    }

    /**
    * Add the current movie that is being viewed to the completed list
    */
    public void addToCompleted() {

        // check if the item in the list has already been completed
        // if not add it to the completed list, and change button text to remove
        if(!main.getList().hasCompleted(main.getCurMovie())) {
            main.getList().addCompleted(main.getCurMovie());
            completed.setText("Remove");
            // check if it is on the plan to watch list, if it is take it off
            if(main.getList().hasPlanned(main.getCurMovie())) {
                addToPlan();
            }
        }
        // otherwise remove from completed and change button text to completed
        else {
            main.getList().removeCompleted(main.getCurMovie());
            completed.setText("Completed");
        }
        // update the completed list in the list scene
        main.updateList();
    }

    /**
    * Add the current movie that is being viewed to the plan to watch list
    */
    public void addToPlan() {

        // check if movie is already on the list, add it if so, and remove it if not
        if(!main.getList().hasPlanned(main.getCurMovie())) {
            main.getList().addPlanToWatch(main.getCurMovie());
            planToWatch.setText("Remove");
            // check if movie is on completed list, if so remove it
            if(main.getList().hasCompleted(main.getCurMovie())) {
                addToCompleted();
            }
        }
        // remove list from completed
        else {
            main.getList().removePlanToWatch(main.getCurMovie());
            planToWatch.setText("Plan To Watch");
        }
        // update tables in list scene
        main.updateList();
    }

    /**
    * load the browse scene
    */
    public void loadBrowse() {
        main.loadBrowse();
    }

    /**
    * load the home scene
    */
    public void loadHome() {
        main.loadHome();
    }

    /**
    * load the list scene
    */
    public void loadList() {
        main.loadList();
    }

    /**
    * load the profile scene
    */
    public void loadProfile() {
        main.loadProfile();
    }

    /**
    * load the previous scene
    */
    public void loadPrevious() {
        if(previousScene == 1) {
            main.loadBrowse();
        }
        else{
            main.loadList();
        }
    }

}

     
     
     
     
     
     
     
     
     
     
     
     
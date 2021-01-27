package IMDb.Controllers;

/**
* Controller for profile fxml page
* @author: J. Lin
* 
*/

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import IMDb.main;
import IMDb.Classes.movie;

public class profileController implements Initializable {
    
    // objects in profile.fxml file
    public TextField searchBar;
    public PieChart pieGraph;
    public BarChart barGraph;

    /**
    * Initialize method, runs when movieInfo scene is created
    *
    * @param location - location of fxml file
    * @param resources - reference to java ResourceBundle
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // hide the labels and legend of the graphs to remove clutter
        pieGraph.setLabelsVisible(false);
        barGraph.setLegendVisible(false);
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

}

     
     
     
     
     
     
     
     
     
     
     
     
package IMDb.Controllers;

/**
* Controller for home fxml page
* @author: J. Lin
* 
*/

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import IMDb.main;
import IMDb.Classes.movie;

public class homeController implements Initializable {

    // objects in the home.fxml file
    public TableView<movie> dataTable;
    public TextField searchBar;

    /**
    * Initialize method, runs when home scene is created
    *
    * @param location - location of fxml file
    * @param resources - reference to java ResourceBundle
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         return;  
    }

    /**
    * load the browse scene
    */
    public void loadBrowse() {
        main.loadBrowse();
    }

    /**
    * load the user's list scene
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
    * Search for movies based on the text entered in the search bar
    *
    * @param event - the event made by the user, i.e hitting enter on the keyboard
    */
    public void onEnter(ActionEvent event) {
        
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

     
     
     
     
     
     
     
     
     
     
     
     
package IMDb.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;


import IMDb.main;
import IMDb.Classes.movie;

public class profileController implements Initializable {
    
    public TextField searchBar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void loadBrowse(){
        main.loadBrowse();
    }

    public void loadHome(){
        main.loadHome();
    }

    public void loadList(){
        main.loadList();
    }

    public void onEnter(ActionEvent event) {

        TableView dataTable = (TableView<movie>) main.getBrowse().lookup("#dataTable");
        if(searchBar.getText().equals("")){
            main.resetTable();
        }
        else{
            ObservableList<movie> searchResults = main.getDatabase().searchMovies(searchBar.getText().toLowerCase());
            dataTable.setItems(searchResults);
            
        }
        loadBrowse();
    }

}

     
     
     
     
     
     
     
     
     
     
     
     
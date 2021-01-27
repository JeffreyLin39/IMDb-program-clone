package IMDb.Controllers;

/**
* Controller for list fxml page
* @author: J. Lin
* 
*/

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import java.net.URL;
import java.util.ResourceBundle;
import IMDb.main;
import IMDb.Classes.movie;

public class listController implements Initializable {

    // objects in list.fxml file
    public TableView<movie> completedTable;
    public TableView<movie> planTable;
    public TextField searchBar;

    /**
    * Initialize method, runs when list scene is created
    *
    * @param location - location of fxml file
    * @param resources - reference to java ResourceBundle
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // create two sets of columns, one for completed movies the other for plan to watch

        // title column
        TableColumn<movie, String> titleColumn = new TableColumn<>("Title"); 
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);
        titleColumn.setMinWidth(200);
        titleColumn.setMaxWidth(200);

        // genre column
        TableColumn<movie, String> genreColumn = new TableColumn<>("Genre"); 
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setSortable(false);
        
        // country column
        TableColumn<movie, String> countryColumn = new TableColumn<>("Country"); 
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setSortable(false);

        // language column
        TableColumn<movie, String> languageColumn = new TableColumn<>("Language"); 
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn.setSortable(false);
        
        // director column
        TableColumn<movie, String> directorColumn = new TableColumn<>("Director"); 
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        directorColumn.setSortable(false);
        
        // year column
        TableColumn<movie, Integer> yearColumn = new TableColumn<>("Year"); 
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.setSortable(false);
        yearColumn.setMinWidth(70);
        yearColumn.setMaxWidth(70);

        // duration column
        TableColumn<movie, Integer> durationColumn = new TableColumn<>("Duration"); 
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setSortable(false);
        durationColumn.setMinWidth(60);
        durationColumn.setMaxWidth(60);
        
        // score column
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);
        scoreColumn.setMinWidth(50);
        scoreColumn.setMaxWidth(50);

        // title column, for completed table
        TableColumn<movie, String> titleColumn2 = new TableColumn<>("Title"); 
        titleColumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn2.setSortable(false);
        titleColumn2.setMinWidth(200);
        titleColumn2.setMaxWidth(200);

        // genre column, for completed table
        TableColumn<movie, String> genreColumn2 = new TableColumn<>("Genre"); 
        genreColumn2.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn2.setSortable(false);
        
        // country column, for completed table
        TableColumn<movie, String> countryColumn2 = new TableColumn<>("Country"); 
        countryColumn2.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn2.setSortable(false);

        // language column, for completed table
        TableColumn<movie, String> languageColumn2 = new TableColumn<>("Language"); 
        languageColumn2.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn2.setSortable(false);
        
        // director column, for completed table
        TableColumn<movie, String> directorColumn2 = new TableColumn<>("Director"); 
        directorColumn2.setCellValueFactory(new PropertyValueFactory<>("director"));
        directorColumn2.setSortable(false);
        
        // year column, for completed table
        TableColumn<movie, Integer> yearColumn2 = new TableColumn<>("Year"); 
        yearColumn2.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn2.setSortable(false);
        yearColumn2.setMinWidth(70);
        yearColumn2.setMaxWidth(70);

        // duration column, for completed table
        TableColumn<movie, Integer> durationColumn2 = new TableColumn<>("Duration"); 
        durationColumn2.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn2.setSortable(false);
        durationColumn2.setMinWidth(60);
        durationColumn2.setMaxWidth(60);
        
        // score column, for completed table
        TableColumn<movie, Double> scoreColumn2 = new TableColumn<>("Score"); 
        scoreColumn2.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn2.setSortable(false);
        scoreColumn2.setMinWidth(50);
        scoreColumn2.setMaxWidth(50);

        // user score column, for completed table
        TableColumn<movie, TextField> userScoreColumn2 = new TableColumn<>("Your Score"); 
        userScoreColumn2.setCellValueFactory(new PropertyValueFactory<>("userScore"));
        userScoreColumn2.setSortable(false);
        
        // add both sets of columns to table view
        planTable.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, durationColumn, scoreColumn);
        completedTable.getColumns().addAll(titleColumn2, genreColumn2, countryColumn2, languageColumn2, directorColumn2, yearColumn2, durationColumn2, scoreColumn2, userScoreColumn2);

        // check for clicks on user column and keyboard input
        completedTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            // open movie viewer scene if user double clicks on column
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                    movie rowData = row.getItem();
                    movieViewerController.setScene(2);
                    main.setMovie(rowData);     
                    main.loadMovieInfo();       
                }
            });
            
            // set the textfield parameter of the movie object if the user types in a score for a completed movie
            row.setOnKeyPressed(ke -> {
                KeyCode keyCode = ke.getCode();
                if (keyCode.equals(KeyCode.ENTER)) {
                    TextField text = row.getItem().getUserScore();
                    row.getItem().setUserScore(text);
                } 
            });
            return row;
        
        });    
        
        // check if user double clicks on the plan to watch table
        planTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                    movie rowData = row.getItem();
                    movieViewerController.setScene(2);
                    main.setMovie(rowData);     
                    main.loadMovieInfo();       
                }
            });
            return row;
         });    
    
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

     
     
     
     
     
     
     
     
     
     
     
     
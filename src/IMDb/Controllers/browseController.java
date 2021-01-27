package IMDb.Controllers;

/**
* Controller for browser fxml page
* @author: J. Lin
* 
*/

import IMDb.main;
import IMDb.Classes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class browseController implements Initializable {

    // objects in browse.fxml
    public TableView<movie> dataTable;
    public TextField searchBar;
    public ChoiceBox<String> genreFilter;
    public ChoiceBox<String> sortOptions;
    public TextField minScore;
    public TextField maxScore;
    public TextField minDur;
    public TextField maxDur;
    public TextField minYear;
    public TextField maxYear;

    /**
    * Initialize method, runs when browse file is created
    *
    * @param location - location of fxml file
    * @param resources - reference to java ResourceBundle
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // create table coloumns for the table in browse
        // set the width for some of them and make them all not sortable
        
        // column for title
        TableColumn<movie, String> titleColumn = new TableColumn<>("Title"); 
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);
        titleColumn.setMinWidth(200);
        titleColumn.setMaxWidth(200);

        // column for genre
        TableColumn<movie, String> genreColumn = new TableColumn<>("Genre"); 
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setSortable(false);
        
        // column for country
        TableColumn<movie, String> countryColumn = new TableColumn<>("Country"); 
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setSortable(false);

        // column for language
        TableColumn<movie, String> languageColumn = new TableColumn<>("Language"); 
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn.setSortable(false);
        
        // column for director
        TableColumn<movie, String> directorColumn = new TableColumn<>("Director"); 
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        directorColumn.setSortable(false);
        
        // column for year
        TableColumn<movie, Integer> yearColumn = new TableColumn<>("Year"); 
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.setSortable(false);
        yearColumn.setMinWidth(70);
        yearColumn.setMaxWidth(70);

        // column for duration
        TableColumn<movie, Integer> durationColumn = new TableColumn<>("Duration"); 
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setSortable(false);
        durationColumn.setMinWidth(60);
        durationColumn.setMaxWidth(60);
        
        // column for score
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);
        scoreColumn.setMinWidth(50);
        scoreColumn.setMaxWidth(50);
        
        // add all columns to table
        dataTable.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, durationColumn, scoreColumn);

        // listen for double click on table row
        dataTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                    // get row item
                    movie rowData = row.getItem();
                    // set the data in the movieInfo scene to the data of the current movie and load the scene
                    main.setMovie(rowData);     
                    movieViewerController.setScene(1);
                    main.loadMovieInfo();       
                }
            });
            return row;
        });

        // add options to genre filter
        genreFilter.getItems().add("X Action");
        genreFilter.getItems().add("X Adventure");
        genreFilter.getItems().add("X Animation");
        genreFilter.getItems().add("X Biography");
        genreFilter.getItems().add("X Comedy");
        genreFilter.getItems().add("X Crime");
        genreFilter.getItems().add("X Drama");
        genreFilter.getItems().add("X Documentary");
        genreFilter.getItems().add("X Family");
        genreFilter.getItems().add("X Fantasy");
        genreFilter.getItems().add("X History");
        genreFilter.getItems().add("X Horror");
        genreFilter.getItems().add("X Music");
        genreFilter.getItems().add("X Musical");
        genreFilter.getItems().add("X Mystery");
        genreFilter.getItems().add("X Romance");
        genreFilter.getItems().add("X Sci-Fi");
        genreFilter.getItems().add("X Sport");
        genreFilter.getItems().add("X Thriller");
        genreFilter.getItems().add("X War");
        genreFilter.getItems().add("X Western");

        // add options to sort filter
        sortOptions.getItems().add("Title - Ascending");
        sortOptions.getItems().add("Title - Descending");
        sortOptions.getItems().add("Genre - Ascending");
        sortOptions.getItems().add("Genre - Descending");
        sortOptions.getItems().add("Country - Ascending");
        sortOptions.getItems().add("Country - Descending");
        sortOptions.getItems().add("Language - Ascending");
        sortOptions.getItems().add("Language - Descending");
        sortOptions.getItems().add("Year - Ascending");
        sortOptions.getItems().add("Year - Descending");
        sortOptions.getItems().add("Duration - Ascending");
        sortOptions.getItems().add("Duration - Descending");
        sortOptions.getItems().add("Score - Ascending");
        sortOptions.getItems().add("Score - Descending");

    }

    /**
    * Add or remove genre from filter
    */
    public void addGenre() {

        // check which option was selected
        int selectedIndex = genreFilter.getSelectionModel().getSelectedIndex();
        String selectedGenre = genreFilter.getSelectionModel().getSelectedItem();

        // if option had an x in front of the genre, add it to the filter list so it doesn't show up
        if (selectedGenre.charAt(0) != 'X') {
            main.getDatabase().removeGenreFilter(selectedGenre);
            genreFilter.getItems().remove(selectedIndex);
            // change the text of the option
            genreFilter.getItems().add(selectedIndex, "X " + selectedGenre);            
        }
        // otherwise if the option did not have an x, remove it from the filter list
        else {
            main.getDatabase().addGenreFilter(selectedGenre.substring(2));
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, selectedGenre.substring(2));
        }

    }

    /**
    * load the home scene
    */
    public void loadHome() {
        main.loadHome();
    }

    /**
    * load the user's list scene
    */
    public void loadList() {
        main.loadList();
    }

    /**
    * load the user's profile scene
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

    }

    /**
    * Filter the entries in the table based on the filter options provided
    */
    public void filterTable() {

        // variable declaration and initialization
        boolean isInverse;
        isInverse = false;
        double curMinScore, curMaxScore;
        int curMinDur, curMaxDur, curMinYear, curMaxYear;
        
        // try and catch statement to make sure user entered valid values
        try {

            // check if text fields are empty, and if so replace them with values that do not affect search results
            if (minScore.getText().equals("")) {
                curMinScore = -1;
            }
            else {
                curMinScore = Double.parseDouble(minScore.getText());
            }
            if (maxScore.getText().equals("")) {
                curMaxScore = 11;
            }
            else {
                curMaxScore = Double.parseDouble(maxScore.getText());
            }
            if (minDur.getText().equals("")) {
                curMinDur = 0;
            }
            else {
                // round value in case the user enters in a decimal
                curMinDur = (int) Math.round(Double.parseDouble(minDur.getText()));
            }
            if (maxDur.getText().equals("")) {
                curMaxDur = 1000;
            }
            else {
                // round value in case the user enters in a decimal
                curMaxDur = (int) Math.round(Double.parseDouble(maxDur.getText()));
            }
            if (minYear.getText().equals("")) {
                curMinYear = 0;
            }
            else {
                // round value in case the user enters in a decimal
                curMinYear = (int) Math.round(Double.parseDouble(minYear.getText()));
            }
            if (maxYear.getText().equals("")) {
                curMaxYear = 3000;
            }
            else {
                // round value in case the user enters in a decimal
                curMaxYear = (int) Math.round(Double.parseDouble(maxYear.getText()));
            }
            // set the filters
            main.getDatabase().setNumberFilters(curMinScore, curMaxScore, curMinYear, curMaxYear, curMinDur, curMaxDur);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        // check if user wants to sort list
        int selectedIndex = sortOptions.getSelectionModel().getSelectedIndex();
        
        // find which sorting option they chose
        if(selectedIndex != -1) {

            if(selectedIndex % 2 != 0) {
                selectedIndex -= 1;
                isInverse = true;
            }
    
            selectedIndex /= 2;
            selectedIndex += 1;
            
            // sort the options in the list
            main.getDatabase().sort(selectedIndex, 0, main.getDatabase().getSize() - 1);
        }

        // find the entries in the sorted list that match the filters and the text in the search bar and replace the current table with this new data
        dataTable.setItems(main.getDatabase().searchFiltered(isInverse, searchBar.getText()));
        
    }

}

     
     
     
     
     
     
     
     
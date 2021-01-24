package IMDb.Controllers;

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class browseController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<movie, String> titleColumn = new TableColumn<>("Title"); 
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);
        titleColumn.setMinWidth(200);
        titleColumn.setMaxWidth(200);

        TableColumn<movie, String> genreColumn = new TableColumn<>("Genre"); 
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setSortable(false);
        
        TableColumn<movie, String> countryColumn = new TableColumn<>("Country"); 
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setSortable(false);

        TableColumn<movie, String> languageColumn = new TableColumn<>("Language"); 
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn.setSortable(false);
        
        TableColumn<movie, String> directorColumn = new TableColumn<>("Director"); 
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        directorColumn.setSortable(false);
        
        TableColumn<movie, Integer> yearColumn = new TableColumn<>("Year"); 
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.setSortable(false);
        yearColumn.setMinWidth(70);
        yearColumn.setMaxWidth(70);

        TableColumn<movie, Integer> durationColumn = new TableColumn<>("Duration"); 
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setSortable(false);
        durationColumn.setMinWidth(60);
        durationColumn.setMaxWidth(60);
        
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);
        scoreColumn.setMinWidth(50);
        scoreColumn.setMaxWidth(50);
        


        dataTable.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, durationColumn, scoreColumn);

        dataTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                    movie rowData = row.getItem();
                    main.setMovie(rowData);     
                    main.loadMovieInfo();       
                }
            });
            return row;
         });

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

    public void addGenre(){

        int selectedIndex = genreFilter.getSelectionModel().getSelectedIndex();
        String selectedGenre = genreFilter.getSelectionModel().getSelectedItem();
        if(selectedGenre.charAt(0) != 'X'){
            main.getDatabase().removeGenreFilter(selectedGenre);
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, "X " + selectedGenre);            
        }
        else {
            main.getDatabase().addGenreFilter(selectedGenre.substring(2));
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, selectedGenre.substring(2));
        }
    }

    public void loadHome() {
        main.loadHome();
    }

    public void loadList(){
        main.loadList();
    }

    public void onEnter(ActionEvent event) {
        
        if(searchBar.getText().equals("")){
            main.resetTable();
        }
        else{
            ObservableList<movie> searchResults = main.getDatabase().searchMovies(searchBar.getText().toLowerCase());
            dataTable.setItems(searchResults);
        }

    }

    public void filterTable(){

        boolean isInverse;
        isInverse = false;
        double curMinScore, curMaxScore;
        int curMinDur, curMaxDur, curMinYear, curMaxYear;
        
        try {

            if(minScore.getText().equals("")){
                curMinScore = -1;
            }
            else{
                curMinScore = Double.parseDouble(minScore.getText());
            }
            if(maxScore.getText().equals("")){
                curMaxScore = 11;
            }
            else {
                curMaxScore = Double.parseDouble(maxScore.getText());
            }
            if(minDur.getText().equals("")){
                curMinDur = 0;
            }
            else {
                curMinDur = Integer.parseInt(minDur.getText());
            }
            if(maxDur.getText().equals("")){
                curMaxDur = 1000;
            }
            else {
                curMaxDur = Integer.parseInt(maxDur.getText());
            }
            if(minYear.getText().equals("")){
                curMinYear = 0;
            }
            else {
                curMinYear = Integer.parseInt(minYear.getText());
            }
            if(maxYear.getText().equals("")){
                curMaxYear = 3000;
            }
            else {
                curMaxYear = Integer.parseInt(maxYear.getText());
            }
            main.getDatabase().setIntegerFilters(curMinScore, curMaxScore, curMinYear, curMaxYear, curMinDur, curMaxDur);
        }
        catch(Exception e){
            System.out.println(e);
        }

        int selectedIndex = sortOptions.getSelectionModel().getSelectedIndex();
        
        if(selectedIndex != -1){
            if(selectedIndex % 2 != 0) {
                selectedIndex -= 1;
                isInverse = true;
            }
    
            selectedIndex /= 2;
            selectedIndex += 1;
    
            main.getDatabase().sort(selectedIndex, 0, main.getDatabase().getSize() - 1);
        }

        dataTable.setItems(main.getDatabase().searchFiltered(isInverse));
        
    }

}

     
     
     
     
     
     
     
     
     
     
     
     
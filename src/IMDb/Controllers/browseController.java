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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        TableColumn<movie, String> titleColumn = new TableColumn<>("Title"); 
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);
        
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

        TableColumn<movie, Integer> durationColumn = new TableColumn<>("Duration"); 
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setSortable(false);
        
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);

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

         genreFilter.getItems().add("Action");
         genreFilter.getItems().add("Adult");
         genreFilter.getItems().add("Adventure");
         genreFilter.getItems().add("Animation");
         genreFilter.getItems().add("Biography");
         genreFilter.getItems().add("Comedy");
         genreFilter.getItems().add("Crime");
         genreFilter.getItems().add("Drama");
         genreFilter.getItems().add("Documentary");
         genreFilter.getItems().add("Family");
         genreFilter.getItems().add("Fantasy");
         genreFilter.getItems().add("Film-Noir");
         genreFilter.getItems().add("History");
         genreFilter.getItems().add("Horror");
         genreFilter.getItems().add("Music");
         genreFilter.getItems().add("Musical");
         genreFilter.getItems().add("Mystery");
         genreFilter.getItems().add("News");
         genreFilter.getItems().add("Romance");
         genreFilter.getItems().add("Sci-Fi");
         genreFilter.getItems().add("Sport");
         genreFilter.getItems().add("Thriller");
         genreFilter.getItems().add("War");
         genreFilter.getItems().add("Western");

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
            main.getDatabase().removeFilter(selectedGenre);
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, "X " + selectedGenre);            
        }
        else {
            main.getDatabase().addFilter(selectedGenre.substring(2));
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, selectedGenre.substring(2));
        }
    }

    public void loadHome(){
        main.loadHome();
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

     
     
     
     
     
     
     
     
     
     
     
     
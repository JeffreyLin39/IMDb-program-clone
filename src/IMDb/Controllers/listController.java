package IMDb.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;


import IMDb.main;
import IMDb.Classes.movie;

public class listController implements Initializable {

    public TableView<movie> completedTable;
    public TableView<movie> planTable;
    public TextField searchBar;
    
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

        TableColumn<movie, String> titleColumn2 = new TableColumn<>("Title"); 
        titleColumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn2.setSortable(false);
        titleColumn2.setMinWidth(200);
        titleColumn2.setMaxWidth(200);

        TableColumn<movie, String> genreColumn2 = new TableColumn<>("Genre"); 
        genreColumn2.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn2.setSortable(false);
        
        TableColumn<movie, String> countryColumn2 = new TableColumn<>("Country"); 
        countryColumn2.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn2.setSortable(false);

        TableColumn<movie, String> languageColumn2 = new TableColumn<>("Language"); 
        languageColumn2.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn2.setSortable(false);
        
        TableColumn<movie, String> directorColumn2 = new TableColumn<>("Director"); 
        directorColumn2.setCellValueFactory(new PropertyValueFactory<>("director"));
        directorColumn2.setSortable(false);
        
        TableColumn<movie, Integer> yearColumn2 = new TableColumn<>("Year"); 
        yearColumn2.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn2.setSortable(false);
        yearColumn2.setMinWidth(70);
        yearColumn2.setMaxWidth(70);

        TableColumn<movie, Integer> durationColumn2 = new TableColumn<>("Duration"); 
        durationColumn2.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn2.setSortable(false);
        durationColumn2.setMinWidth(60);
        durationColumn2.setMaxWidth(60);
        
        TableColumn<movie, Double> scoreColumn2 = new TableColumn<>("Score"); 
        scoreColumn2.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn2.setSortable(false);
        scoreColumn2.setMinWidth(50);
        scoreColumn2.setMaxWidth(50);

        TableColumn<movie, TextField> userScoreColumn2 = new TableColumn<>("Your Score"); 
        userScoreColumn2.setCellValueFactory(new PropertyValueFactory<>("userScore"));
        userScoreColumn2.setSortable(false);
        
        planTable.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, durationColumn, scoreColumn);
        completedTable.getColumns().addAll(titleColumn2, genreColumn2, countryColumn2, languageColumn2, directorColumn2, yearColumn2, durationColumn2, scoreColumn2, userScoreColumn2);

        completedTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                    movie rowData = row.getItem();
                    movieViewerController.setScene(2);
                    main.setMovie(rowData);     
                    main.loadMovieInfo();       
                }
            });
            
            row.setOnKeyPressed(ke -> {
                KeyCode keyCode = ke.getCode();
                if (keyCode.equals(KeyCode.ENTER)) {
                    TextField text = row.getItem().getUserScore();
                    row.getItem().setUserScore(text);
                } 
            });
            return row;
        
         });    

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

    public void loadBrowse(){
        main.loadBrowse();
    }

    public void loadHome(){
        main.loadHome();
    }

    public void loadProfile(){
        main.loadProfile();
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

     
     
     
     
     
     
     
     
     
     
     
     
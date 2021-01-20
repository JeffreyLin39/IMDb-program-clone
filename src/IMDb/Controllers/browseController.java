package IMDb.Controllers;

import IMDb.main;
import IMDb.Classes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class browseController implements Initializable {

   public TableView<movie> dataTable;
   public TextField searchBar;

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
        
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);

        dataTable.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, scoreColumn);

        dataTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
               if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                movie rowData = row.getItem();
                System.out.println(rowData);               }
            });
            return row;
         });
         
        
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

}

     
     
     
     
     
     
     
     
     
     
     
     
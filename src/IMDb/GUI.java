package IMDb;

import java.io.IOException;

import javafx.scene.control.TableColumn;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    static Stage window;
    TableView<movie> table;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("JMDb");

        TableColumn<movie, String> titleColumn = new TableColumn<>("Title"); 
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        TableColumn<movie, String> genreColumn = new TableColumn<>("Genre"); 
        genreColumn.setMinWidth(200);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        
        TableColumn<movie, String> countryColumn = new TableColumn<>("Country"); 
        countryColumn.setMinWidth(200);
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        TableColumn<movie, String> languageColumn = new TableColumn<>("Language"); 
        languageColumn.setMinWidth(200);
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        
        TableColumn<movie, String> directorColumn = new TableColumn<>("Director"); 
        directorColumn.setMinWidth(200);
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        
        TableColumn<movie, Integer> yearColumn = new TableColumn<>("Year"); 
        yearColumn.setMinWidth(100);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        
        table = new TableView();
        table.setItems(getMovie());
        table.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, scoreColumn);

        VBox layout = new VBox(table);
        layout.getChildren().addAll();

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }

    public ObservableList<movie> getMovie() throws IOException{
        ObservableList<movie> movies = FXCollections.observableArrayList();
        database data = new database("C:/Users/jeffr/Documents/ICS4U1/CPT/ics4u-cpt---data-visualization-jeffrey-lin/src/IMDb/dataset_full.csv");

        for(String id: data.getMovieList()) {
            movies.add(data.getMovie(id));
        }
        return movies;

    }
}
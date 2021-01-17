package IMDb;

import java.io.IOException;

import IMDb.Classes.database;
import IMDb.Classes.movie;
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
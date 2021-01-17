package IMDb;

import java.io.IOException;

import IMDb.Classes.database;
import IMDb.Classes.movie;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class main extends Application {

    static ProgressBar progressBar;
    static Button enterButton;
    static Scene home;
    static Stage window;
    static database data;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setMinHeight(667);
        window.setMinWidth(1000);
        Parent root = FXMLLoader.load(getClass().getResource("FXML/loadingScreen.fxml"));
        Scene loadingScreen = new Scene(root, 1000, 667);
        window.setTitle("Jeffrey's Movie Database");
        window.setScene(loadingScreen);
        progressBar = (ProgressBar)loadingScreen.lookup("#progressBar");
        enterButton = (Button)loadingScreen.lookup("#enterButton");
        window.show();

        root = FXMLLoader.load(getClass().getResource("FXML/home.fxml"));
        home = new Scene(root, 1000, 667);
        loadFile();
    }

    public static void loadHome() {
        window.setScene(home);
        window.show();
    }

    public static database getDatabase(){
        return data;
    }

    public static void loadFile() throws IOException{
        new Thread(){
            public void run() {
                TableView<movie>dataTable = (TableView<movie>)home.lookup("#dataTable");
                ObservableList<movie> movies = FXCollections.observableArrayList();
                try {
                    int num = 0;
                    data = new database("src/IMDb/Resources/dataset_full.csv");
                    final double size = data.getSize() * 1.0;
                    
                    for(String id: data.getMovieList()) {
                        num++;  
                        final double done = num;
                        if(num % (size/10) == 0){
                            Platform.runLater(() -> progressBar.setProgress( done/size ));
                            Thread.sleep(100);
                        }
                        movies.add(data.getMovie(id));
                    }
    
                    dataTable.setItems(movies);
                    Thread.sleep(200);
                    enterButton.setVisible(true);
                    enterButton.setDisable(false);
                }catch(Exception e){
                    System.out.println(e);
                }

            }
            
        }.start();
    }

}

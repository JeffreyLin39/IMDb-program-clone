package IMDb;

import java.io.IOException;

import IMDb.Classes.database;
import IMDb.Classes.movie;
import IMDb.Classes.myList;
import IMDb.Controllers.movieViewerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {

    private static Scene home;
    private static Scene loadingScreen;
    private static Scene browse;
    private static Scene movieInfo;
    private static Stage window;
    private static database data;
    private static myList userList;
    private static TableView<movie> dataTable;
    private static ObservableList<movie> movies;
    private static movie currentMovie;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setMinHeight(667);
        window.setMinWidth(1000);
        window.setTitle("Jeffrey's Movie Database");

        Parent root = FXMLLoader.load(getClass().getResource("FXML/loadingScreen.fxml"));
        loadingScreen = new Scene(root, 1000, 667);
        window.setScene(loadingScreen);
        window.show();

        root = FXMLLoader.load(getClass().getResource("FXML/home.fxml"));
        home = new Scene(root, 1000, 667);

        root = FXMLLoader.load(getClass().getResource("FXML/browse.fxml"));
        browse = new Scene(root, 1000, 667);
        
        currentMovie = new movie("title", "genre", "country", "language", "director", "description", 0, 0, 0);
        root = FXMLLoader.load(getClass().getResource("FXML/movieInfo.fxml"));
        movieInfo = new Scene(root, 1000, 667);

        loadFile();
    }

    public static void loadHome() {
        window.setScene(home);
        window.show();
    }

    public static void loadBrowse() {
        window.setScene(browse);
        window.show();
    }

    public static void loadMovieInfo() {

        Text  movieTitle = (Text)movieInfo.lookup("#movieTitle");
        Text  movieGenre = (Text)movieInfo.lookup("#movieGenre");
        Text movieCountry = (Text)movieInfo.lookup("#movieCountry");
        Text movieLanguage = (Text)movieInfo.lookup("#movieLanguage");
        Text movieDirector = (Text)movieInfo.lookup("#movieDirector");
        Text movieDescription = (Text)movieInfo.lookup("#movieDescription");
        Text movieYear = (Text)movieInfo.lookup("#movieYear");
        Text movieDuration = (Text)movieInfo.lookup("#movieDuration");
        Text movieScore = (Text)movieInfo.lookup("#movieScore");

        movieTitle.setText(currentMovie.getTitle());
        movieGenre.setText("Genre(s): " + currentMovie.getGenre());
        movieCountry.setText("Country: " + currentMovie.getCountry());
        movieLanguage.setText("Language: " + currentMovie.getLanguage());
        movieDirector.setText("Director: " + currentMovie.getDirector());
        movieDescription.setText(currentMovie.getDescription());
        movieYear.setText("Year: " + String.valueOf(currentMovie.getYear()));
        movieDuration.setText("Duration: " + String.valueOf(currentMovie.getDuration()) + " minutes");
        movieScore.setText("Score: " + String.valueOf(currentMovie.getScore()));       
        window.setScene(movieInfo);
        window.show();
    }

    public static Scene getBrowse(){
        return browse;
    }

    public static database getDatabase(){
        return data;
    }

    public static void resetTable(){
        dataTable.setItems(movies);
    }

    public static void setMovie(movie mov){
        currentMovie = mov;
    }

    private static void loadFile() throws IOException{

        ProgressBar progressBar = (ProgressBar)loadingScreen.lookup("#progressBar");

        Thread loadDataset = new Thread(){
            public void run() {
                dataTable = (TableView<movie>)browse.lookup("#dataTable");
                userList = new myList();
                movies = FXCollections.observableArrayList();
                try {
                    int num = 0;
                    data = new database("src/IMDb/Resources/dataset_full.csv", -99999999, 99999999, -99999999, 99999999, -99999999.9, 99999999.9);
                    final double size = data.getSize() * 1.0;
                    
                    for(String id: data.getMovieList()) {
                        num++;  
                        final double done = num;
                        if(num % (size/10) == 0){
                            Platform.runLater(() -> progressBar.setProgress( done/size ));
                            Thread.sleep(50);
                        }
                        movies.add(data.getMovie(id));
                    }
    
                    dataTable.setItems(movies);
                    Platform.runLater(()-> {
                        loadHome();
                    });

                }catch(Exception e){
                    System.out.println(e);
                }

            }
            
        };
        loadDataset.start();
    }

}

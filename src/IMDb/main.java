package IMDb;

/**
* Main simulator for program, allows all controllers to communicate with each other
* @author: J. Lin
* 
*/

import java.io.IOException;
import IMDb.Classes.database;
import IMDb.Classes.movie;
import IMDb.Classes.myList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {

    // variable declaration
    private static Scene home;
    private static Scene loadingScreen;
    private static Scene browse;
    private static Scene movieInfo;
    private static Scene list;
    private static Scene profile;
    private static Stage window;
    private static double userRating;
    private static database data;
    private static myList userList;
    private static TableView<movie> dataTable;
    private static ObservableList<movie> movies;
    private static movie currentMovie;
    
    /**
    * main method that launches program
    */
    public static void main(String[] args) {
        launch(args);
    }

    /**
    * starts and initailizes the window and scenes
    *
    * @param primaryStage - the stage that the scenes will be shown on
    */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // create variable to represent primaryStage
        window = primaryStage;
        window.setMinHeight(667);
        window.setMinWidth(1000);
        window.setTitle("Jeffrey's Movie Database");

        // loading screen - create and initialize fxml, controller, and scene
        Parent root = FXMLLoader.load(getClass().getResource("FXML/loadingScreen.fxml"));
        loadingScreen = new Scene(root, 1000, 667);
        window.setScene(loadingScreen);
        window.show();

        // home scene - create and initialize fxml, controller, and scene
        root = FXMLLoader.load(getClass().getResource("FXML/home.fxml"));
        home = new Scene(root, 1000, 667);

        // browse scene - create and initialize fxml, controller, and scene
        root = FXMLLoader.load(getClass().getResource("FXML/browse.fxml"));
        browse = new Scene(root, 1000, 667);
        
        // movieInfo scene - create and initialize fxml, controller, and scene
        currentMovie = new movie("title", "genre", "country", "language", "director", "description", 0, 0, 0);
        root = FXMLLoader.load(getClass().getResource("FXML/movieInfo.fxml"));
        movieInfo = new Scene(root, 1000, 667);

        // list scene - create and initialize fxml, controller, and scene
        root = FXMLLoader.load(getClass().getResource("FXML/list.fxml"));
        list = new Scene(root, 1000, 667);

        // profile scene - create and initialize fxml, controller, and scene
        root = FXMLLoader.load(getClass().getResource("FXML/profile.fxml"));
        profile = new Scene(root, 1000, 667);

        loadFile();

    }

    /**
    * changes scene to home
    */
    public static void loadHome() {
        window.setScene(home);
        window.show();
    }

    /**
    * changes scene to browse
    */
    public static void loadBrowse() {
        window.setScene(browse);
        window.show();
    }

    /**
    * changes scene to list
    */
    public static void loadList() {
        window.setScene(list);
        window.show();
    }

    /**
    * changes scene to profile
    */
    public static void loadProfile() {
        loadPieChart();
        loadBarChart();
        loadStats();
        window.setScene(profile);
        window.show();
    }

    /**
    * loads values for pie chart
    */
    public static void loadPieChart() {

        // declare and instantiate pie chart
        PieChart chart = (PieChart) profile.lookup("#pieGraph");
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();

        // add data to pie chart
        for (String genre: userList.getGenres().keySet()) {
            list.add(new PieChart.Data(genre, userList.getGenres().get(genre)));
        }

        // erase previous data and set new data to pie chart
        chart.getData().clear();
        chart.setData(list);

    }

    /**
    * loads values for bar chart
    */
    public static void loadBarChart(){

        // declare and instantiate variables
        userRating = 0;
        BarChart<String, Number> chart = (BarChart) profile.lookup("#barGraph");
        XYChart.Series list = new XYChart.Series<>();
        int[] score = new int[11];

        // go through the scores set by the user
        for (movie mov: userList.getCompleted()) {
            // if score isn't empty, round the score and add it to the bar graph
            if (!mov.getUserScore().getText().equals("")) {
                userRating += Double.parseDouble(mov.getUserScore().getText());
                score[(int) Math.round(Double.parseDouble(mov.getUserScore().getText()))] += 1;
            }
        }   

        // add data to the list
        for (int a = 0; a < 11; a++) {
            list.getData().add(new XYChart.Data(String.valueOf(a), score[a]));
        }   

        // clear previous graph and set the current data to the graph
        chart.getData().clear();
        chart.getData().addAll(list);

    }

    /**
    * loads stats for user such as average score, watch time, etc
    */
    public static void loadStats(){

        // variable declaration and initalization from the fxml file
        Label watchTime = (Label) profile.lookup("#watchTime");
        Label numCompleted = (Label) profile.lookup("#numCompleted");
        Label numToWatch = (Label) profile.lookup("#numToWatch");
        Label avgUserRating = (Label) profile.lookup("#avgUserRating");
        Label avgRating = (Label) profile.lookup("#avgRating");

        // set text for watch time, number of completed movies, and number of movies that are in the plan to watch list
        watchTime.setText("Total Watch Time: " + (int) ((userList.getWatchTime() / 1440.0) * 100) / 100.0 + " days");
        numCompleted.setText("Completed: " + userList.getNumCompleted() + " movies");
        numToWatch.setText("Planned: " + userList.getNumPlanned() + " movies");

        // find user average score
        if (userList.getNumCompleted() != 0) {

            // calculates the average score from all IMDb users by dividing sum of the scores by the number of movies completed
            avgRating.setText("Platform Average Rating: " + (int) (userList.getAvgScore()/(userList.getNumCompleted() * 1.0)) * 10 / 10.0);
            // perform the same calculation for user score
            if (userRating != 0) {
                avgUserRating.setText("Platform Average Rating: " + (int) (userRating/(userList.getNumCompleted() * 1.0)) * 10 / 10.0);
            }
            
        }

    }

    /**
    * loads the current movie that is being viewed onto the movieInfo scene
    */
    public static void loadMovieInfo() {

        // variable declaration and initalization from the fxml file
        Button completed = (Button) movieInfo.lookup("#completed");
        Button planToWatch = (Button) movieInfo.lookup("#planToWatch");
        Text  movieTitle = (Text) movieInfo.lookup("#movieTitle");
        Text  movieGenre = (Text) movieInfo.lookup("#movieGenre");
        Text movieCountry = (Text) movieInfo.lookup("#movieCountry");
        Text movieLanguage = (Text) movieInfo.lookup("#movieLanguage");
        Text movieDirector = (Text) movieInfo.lookup("#movieDirector");
        Text movieDescription = (Text) movieInfo.lookup("#movieDescription");
        Text movieYear = (Text) movieInfo.lookup("#movieYear");
        Text movieDuration = (Text) movieInfo.lookup("#movieDuration");
        Text movieScore = (Text) movieInfo.lookup("#movieScore");

        // set text for buttons depending on if the user has these movies in their list
        if (userList.hasCompleted(currentMovie)) {
            completed.setText("Remove");
        }
        else {
            completed.setText("Completed");
        }

        if (userList.hasPlanned(currentMovie)) {
            planToWatch.setText("Remove");
        }
        else {
            planToWatch.setText("Plan to Watch");
        }

        // set text for the stats of the current movie
        movieTitle.setText(currentMovie.getTitle());
        movieGenre.setText("Genre(s): " + currentMovie.getGenre());
        movieCountry.setText("Country: " + currentMovie.getCountry());
        movieLanguage.setText("Language: " + currentMovie.getLanguage());
        movieDirector.setText("Director: " + currentMovie.getDirector());
        movieDescription.setText(currentMovie.getDescription());
        movieYear.setText("Year: " + String.valueOf(currentMovie.getYear()));
        movieDuration.setText("Duration: " + String.valueOf(currentMovie.getDuration()) + " minutes");
        movieScore.setText("Score: " + String.valueOf(currentMovie.getScore()));       

        // change the scene
        window.setScene(movieInfo);
        window.show();

    }

    /**
    * get the browse scene to access the buttons, textfields, etc on the scene
    *
    * @return Scene - returns the browse scene
    */
    public static Scene getBrowse() {
        return browse;
    }

    /**
    * get the database of the program that holds all the movies
    *
    * @return database - returns the database object of the program
    */
    public static database getDatabase() {
        return data;
    }

    /**
    * resets the current items in the table to the original list
    */
    public static void resetTable() {
        dataTable.setItems(movies);
    }

    /**
    * Set the current movie that is being viewed by the user, so other controllers know which movie is being accessed
    *
    * @param mov - movie that is being viewed
    */
    public static void setMovie(movie mov) {
        currentMovie = mov;
    }

    /**
    * get the current movie that is being viewed by the user
    *
    * @return movie - movie that is being viewed
    */
    public static movie getCurMovie() {
        return currentMovie;
    }

    /**
    * get the myList object of the user for controllers to see what items are on the user's list
    *
    * @return myList - the myList object of this program
    */
    public static myList getList() {
        return userList;
    }

    /**
    * update the myList object because the user either added or removed something from their lists
    */
    public static void updateList() {

        // update table view entries with the current completed and plan to watch lists
        TableView<movie> completedTable = (TableView<movie>)list.lookup("#completedTable");
        completedTable.setItems(userList.getCompleted());
        TableView<movie> planTable = (TableView<movie>)list.lookup("#planTable");
        planTable.setItems(userList.getPlanToWatch());

    }

    /**
    * loads the dataset into the table
    */
    private static void loadFile() throws IOException {

        // get the progress bar from the loadingScreen scene
        ProgressBar progressBar = (ProgressBar) loadingScreen.lookup("#progressBar");
        

        // start a new thread so javafx main application thread doesn't freeze and become unresponsive
        Thread loadDataset = new Thread(){

            public void run() {
                double size;
                int steps = 0;

                // get table from browse scene, create new myList object, and new observable list
                dataTable = (TableView<movie>)browse.lookup("#dataTable");
                userList = new myList();
                movies = FXCollections.observableArrayList();

                try {

                    // instaniate new database object
                    data = new database("src/IMDb/Resources/dataset_full.csv", -99999999, 99999999, -99999999, 99999999, -99999999.9, 99999999.9);
                    size = data.getSize() * 1.0;
                    
                    // go through movies in database and add tehm to the observable list
                    for (String id: data.getMovieList()) {

                        steps++;  
                        final double temp = steps;
                        if (steps % (size / 10) == 0) {
                            // update progress bar in javafx main application thread
                            Platform.runLater(() -> progressBar.setProgress(temp / size));
                            Thread.sleep(50);
                        }
                        movies.add(data.getMovie(id));

                    }
                    
                    // set items to table and switch scenes to the home scene afterwards
                    dataTable.setItems(movies);
                    Platform.runLater(()-> {
                        loadHome();
                    });

                }
                catch (Exception e) {
                    System.out.println(e);
                }

            }
            
        };
        // start the thread
        loadDataset.start();

    }

}

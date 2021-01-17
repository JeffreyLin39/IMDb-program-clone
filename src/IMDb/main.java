package IMDb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/loadingScreen.fxml"));
        Scene loadingScreen = new Scene(root, Screen.getPrimary().getBounds().getMaxX()/2,Screen.getPrimary().getBounds().getMaxY()/2);
        primaryStage.setTitle("Jeffrey's Movie Database");
        primaryStage.setScene(loadingScreen);
        primaryStage.show();
        //primaryStage.close();
    }


}

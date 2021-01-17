package IMDb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

   public ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadFile();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    private static void sleep(int time) {

        try { 
          TimeUnit.MILLISECONDS.sleep(time);
        }
        catch (InterruptedException e) { 
            System.out.println(e);
        } 
        
      }

    public void loadFile() throws IOException{
        double done, size;
        done = 0.0;
        size = 0.0;
        ObservableList<movie> movies = FXCollections.observableArrayList();
        database data = new database("C:/Users/jeffr/Documents/ICS4U1/CPT/ics4u-cpt---data-visualization-jeffrey-lin/src/IMDb/dataset_full.csv");
        size = data.getSize() * 1.0;
            
        for(String id: data.getMovieList()) {
            
            done++;
            progressBar.setProgress(done/size);
            movies.add(data.getMovie(id));
        }

        


    }

}

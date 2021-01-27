package IMDb.Classes;

/**
* Movie class file
* @author: J. Lin
* 
*/

import javafx.scene.control.TextField;

public class movie {

    // Instance variables
    private String title, genre, country, language, director, description;
    private int year, duration;
    private double score;
    private TextField userScore;
     
    /**
    * Constructor - creates new instance of a movie object
    *
    * @param title - title of movie
    * @param genre - genre of movie
    * @param country - movie's country of origin
    * @param language - language of movie
    * @param director - director of movie
    * @param description - description of movie
    * @param year - release year of movie
    * @param duration - duration of movie
    * @param score - score of movie
    */    
    public movie (String title, String genre, String country, String language, String director, String description, int year, int duration, double score) {
        this.title = title;
        this.genre = genre;
        this.country = country;
        this.language = language;
        this.director = director;
        this.description = description;
        this.year = year;
        this.duration = duration;
        this.score = score;
        this.userScore = new TextField("");
    }

    /**
    * Returns a parameter of this object as a string for sorting purposes
    *
    * @param num - the number representing the parameter to return
    * 
    * @return string of the parameter of current object
    */ 
    public String getParameter(int num) {

        // switch case statement to get parameter
        switch (num) {
            case 1:
                return this.title;
            case 2:
                return this.genre;
            case 3:
                return this.country;
            case 4:
                return this.language;
            case 5:
                return String.valueOf(this.year);
            case 6:
                // if the 0 is not added, sorting algorithm will think 100 < 99 because 1 < 9
                // once 0 is added 099 is less than 100
                if (this.duration < 100) {
                    return "0" + String.valueOf(this.duration);
                }
                return String.valueOf(this.duration);
            case 7:
                return String.valueOf(this.score);
        }
        return "";
    }

    /**
    * returns title of movie, required by the table view to find these parameters
    * 
    * @return String - title of curent movie
    */ 
    public String getTitle() {
        return this.title;
    }

    /**
    * returns genre of movie, required by the table view to find these parameters
    * 
    * @return String - genre of current movie
    */ 
    public String getGenre() {
        return this.genre;
    }

    /**
    * returns country of origin of current movie, required by the table view to find these parameters
    * 
    * @return String - current movie's country of origin
    */ 
    public String getCountry() {
        return this.country;
    }

    /**
    * returns language of movie, required by the table view to find these parameters
    * 
    * @return String - current movie's language
    */ 
    public String getLanguage() {
        return this.language;
    }

    /**
    * returns director of movie, required by the table view to find these parameters
    * 
    * @return String - current movie's director
    */ 
    public String getDirector() {
        return this.director;
    }

    /**
    * returns year of release for current movie, required by the table view to find these parameters
    * 
    * @return int - current movie's year of release
    */ 
    public int getYear() {
        return this.year;
    }

    /**
    * returns duration of current movie, required by the table view to find these parameters
    * 
    * @return int - current movie's duration
    */ 
    public int getDuration() {
        return this.duration;
    }

    /**
    * returns score of current movie, required by the table view to find these parameters
    * 
    * @return int - current movie's score
    */ 
    public double getScore() {
        return this.score;
    }

    /**
    * returns description of current movie
    * 
    * @return String - current movie's description
    */ 
    public String getDescription() {
        return this.description;
    }

    /**
    * Sets the textfield parameter of this object, which holds the score of the movie set by the user
    * 
    * @param score - textfield from the table to set to this object
    */ 
    public void setUserScore(TextField score) {
        
        // Try and catch statement in case user types in something besides a number
        try {
            if (Double.parseDouble(score.getText()) < 0) {
                score.setText("0.0");
            }
            else if (Double.parseDouble(score.getText()) > 10) {
                score.setText("10.0");
            }
            this.userScore = score;
        }
        catch (Exception e) {
            this.userScore.setText("");
        }

    }

    /**
    * returns textfield holding user's score of current movie, required by the table view to find these parameters
    * 
    * @return textfield - current movie's textfield, holding the user's score
    */ 
    public TextField getUserScore() {
        return this.userScore;
    }

}
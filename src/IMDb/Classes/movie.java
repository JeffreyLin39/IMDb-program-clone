package IMDb.Classes;

public class movie {
    
    private String title, genre, country, language, director, description;
    private int year, duration;
    private double score;
     
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
    }

    public String getParameter(int num) {
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
                return String.valueOf(this.duration);
            case 7:
                return String.valueOf(this.score);
        }
        return "";
    }

    public String getTitle(){
        return this.title;
    }

    public String getGenre(){
        return this.genre;
    }

    public String getCountry(){
        return this.country;
    }

    public String getLanguage(){
        return this.language;
    }

    public String getDirector(){
        return this.director;
    }

    public int getYear(){
        return this.year;
    }

    public int getDuration(){
        return this.duration;
    }

    public double getScore(){
        return this.score;
    }



    public String toString() {
        return (this.title + " " + this.genre + " " + this.country + " " + this.language + " " + this.director + " " + this.year + " " + this.duration + " " + " " + this.description);
    }
}
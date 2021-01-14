package IMDb;

public class movie {

    private String id, title, country, language, director, description;
    private int year, duration, score;
     
    public movie (String id, String title, String country, String language, String director, String description, int year, int duration, int score) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.language = language;
        this.director = director;
        this.year = year;
        this.duration = duration;
        this.score = score;
    }
}
package IMDb;

public class movie {

    private String title, country, language, director, description;
    private int year, duration, score;
     
    public movie (String title, String country, String language, String director, String description, int year, int duration, int score) {
        this.title = title;
        this.country = country;
        this.language = language;
        this.director = director;
        this.description = description;
        this.year = year;
        this.duration = duration;
        this.score = score;
    }

    public String toString() {
        return (this.title + " " + this.country + " " + this.language + " " + this.director + " " + this.year + " " + this.duration + " " + this.score + " " + this.description);
    }
}
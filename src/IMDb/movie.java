package IMDb;

public class movie {

    private String title, country, language, director, description;
    private int year, duration;
     
    public movie (String title, String country, String language, String director, String description, int year, int duration) {
        this.title = title;
        this.country = country;
        this.language = language;
        this.director = director;
        this.description = description;
        this.year = year;
        this.duration = duration;
    }

    public String toString() {
        return (this.title + " " + this.country + " " + this.language + " " + this.director + " " + this.year + " " + this.duration + " " + " " + this.description);
    }
}
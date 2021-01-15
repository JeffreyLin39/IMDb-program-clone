package IMDb;

public class movie {

    private String title, genre, country, language, director, description;
    private int year, duration;
     
    public movie (String title, String genre, String country, String language, String director, String description, int year, int duration) {
        this.title = title;
        this.genre = genre;
        this.country = country;
        this.language = language;
        this.director = director;
        this.description = description;
        this.year = year;
        this.duration = duration;
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
        }
        return "";
    }

    public String toString() {
        return (this.title + " " + this.genre + " " + this.country + " " + this.language + " " + this.director + " " + this.year + " " + this.duration + " " + " " + this.description);
    }
}
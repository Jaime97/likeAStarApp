package jaa.com.likeastarapp.common.dao;

public class Film {
    String title;
    String[] actors;
    String director;
    int releaseYear;
    String location;
    String funFacts;
    String company;
    String distributor;

    public Film(String title, String director) {
        this.title = title;
        this.director = director;
    }

    public Film(String title, String[] actors, String director, int releaseYear, String location, String funFacts, String company, String distributor) {
        this.title = title;
        this.actors = actors;
        this.director = director;
        this.releaseYear = releaseYear;
        this.location = location;
        this.funFacts = funFacts;
        this.company = company;
        this.distributor = distributor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFunFacts() {
        return funFacts;
    }

    public void setFunFacts(String funFacts) {
        this.funFacts = funFacts;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
}

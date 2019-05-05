package domain;

import java.util.List;

public class Movie {
    private String name;
    private List<Comment> comments;
    private List<Rating> rates;
    private float rate;
    private List<Actor> actors;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rating> getRates() {
        return rates;
    }

    public void setRates(List<Rating> rates) {
        this.rates = rates;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }


}
package domain;

import java.util.List;

public class Actor {

    private int id;
    private String name;
    private List<Integer> moviesList;

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

    public List<Integer> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Integer> moviesList) {
        this.moviesList = moviesList;
    }


}

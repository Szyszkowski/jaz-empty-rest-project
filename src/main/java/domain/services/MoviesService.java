package domain.services;

import domain.Comment;
import domain.Movie;
import domain.Rating;

import java.util.ArrayList;
import java.util.List;

public class MoviesService {
    private static List<Movie> db = new ArrayList<Movie>();
    private static  int currentMovieId = 0;
    private static  int currentCommentId = 0;

    public List<Movie> getAll(){
        return db;
    }
    public Movie get(int id ){
        for (Movie m: db) {
            if(m.getId()==id){
                return m;
            }
        }
        return null;
    }

    public void add(Movie m ){
        m.setId(++currentMovieId);
        db.add(m);
    }

    public void update (Movie movie){
        for (Movie m: db) {
            if (m.getId()==movie.getId()){
                m.setName(movie.getName());
            }
        }
    }

    public Comment getComment (int movieId, int commentId){
        for (Movie m: db) {
            if(m.getId()==movieId){
                for (Comment c: m.getComments()){
                    if (c.getId() == commentId){
                        return c;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public boolean addComment (int movieId, Comment comment){
        Movie movie = get(movieId);
        if (movie == null){
            return false;
        }
        if (movie.getComments() == null){
            movie.setComments(new ArrayList<Comment>());
        }
        comment.setId(++currentCommentId);
        movie.getComments().add(comment);
        return true;
    }

    public boolean rate (int movieId, Rating rating){
        Movie movie = get(movieId);
        if (movie == null){
            return false;
        }
        List<Rating> rates = movie.getRates();
        if(rates == null){
            rates = new ArrayList<Rating>();
        }
        rates.add(rating);
        float rateSum = 0;
        for (Rating r: rates) {
            rateSum += r.getRate();
        }
        movie.setRate(rateSum/rates.size());
        movie.setRates(rates);
        return true;
    }
}



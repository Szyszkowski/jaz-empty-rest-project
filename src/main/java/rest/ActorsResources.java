package rest;


import domain.Actor;
import domain.Movie;
import domain.services.ActorsService;
import domain.services.MoviesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/actors")
public class ActorsResources {
    private ActorsService dbActors = new ActorsService();
    private MoviesService dbMovies = new MoviesService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Actor actor){
        dbActors.add(actor);
        return Response.ok(actor.getId()).build();
    }

    @POST
    @Path("/{actorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActorToMovie(@PathParam("actorId") int actorId, int movieId){
        Actor actor = dbActors.get(actorId);
        Movie movie = dbMovies.get(movieId);
        if (actor == null || movie == null){
            return Response.status(404).build();
        }
        List<Integer> movieList =  actor.getMoviesList();
        if (movieList == null){
            movieList = new ArrayList<Integer>();
        }
        movieList.add(movieId);
        actor.setMoviesList(movieList);

        List<Actor> actorList =  movie.getActors();
        if (actorList == null){
            actorList = new ArrayList<Actor>();
        }
        actorList.add(actor);
        movie.setActors(actorList);
        return Response.ok().build();
    }


    @GET
    @Path("/{actorId}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorMovies(@PathParam("actorId") int actorId){
        List<Movie> movies =  dbActors.getMovies(actorId);
        if (movies == null){
            return Response.status(404).build();
        }
        return  Response.ok(movies).build();

    }

}

package rest;

import domain.*;
import domain.services.MoviesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/movies")
public class MovieResources {
    private MoviesService db = new MoviesService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll(){
        return db.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id){
        Movie result = db.get(id);
        if (result == null){
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Movie movie){
        db.add(movie);
        return Response.ok(movie.getId()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie m){
        Movie result = db.get(id);
        if (result == null){
            return Response.status(404).build();
        }
        m.setId(id);
        db.update(m);
        return Response.ok().build();
    }

    @GET
    @Path("/{movieId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("movieId") int movieId){
        Movie result = db.get(movieId);
        if (result == null){
            return null;
        }
        if (result.getComments() == null){
            result.setComments(new ArrayList<Comment>());
        }
        return result.getComments();
    }

    @POST
    @Path("/{movieId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("movieId") int movieId, Comment comment){
        if (!db.addComment(movieId, comment)){
            return Response.status(404).build();
        }
        return Response.ok().build();

    }


    @DELETE
    @Path("/{movieId}/comments/{commentId}")
    public Response deleteComment(@PathParam("movieId") int movieId, @PathParam("commentId") int commentId){
        Comment result = db.getComment(movieId, commentId);
        if (result == null){
            return Response.status(404).build();
        }
        Movie movie = db.get(movieId);
        movie.getComments().remove(result);

        return Response.ok().build();
    }


    @POST
    @Path("/{movieId}/rate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("movieId") int movieId, Rating rating){
        if (!db.rate(movieId, rating)){
            return Response.status(404).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/{movieId}/actors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getActors(@PathParam("movieId") int movieId){
        Movie result = db.get(movieId);
        if (result == null){
            return null;
        }
        if (result.getActors() == null){
            result.setActors(new ArrayList<Actor>());
        }
        return result.getActors();
    }

}



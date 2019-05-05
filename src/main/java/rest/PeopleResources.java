package rest;

import domain.Car;
import domain.Person;
import domain.services.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/people")
public class PeopleResources {
    private PersonService db = new PersonService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getall(){
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Person person){
        db.add(person);
        return Response.ok(person.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id){
        Person result = db.get(id);
        if (result == null){
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Person p){
        Person result = db.get(id);
        if (result == null){
            return Response.status(404).build();
        }
        p.setId(id);
        db.update(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        Person result = db.get(id);
        if (result == null){
            return Response.status(404).build();
        }
        db.delete(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{personId}/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars(@PathParam("personId") int personId){
        Person result = db.get(personId);
        if (result == null){
            return null;
        }
        if (result.getCars() == null){
            result.setCars(new ArrayList<Car>());
        }
        return result.getCars();
    }
}


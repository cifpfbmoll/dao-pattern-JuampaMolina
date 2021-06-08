package org.acme.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Path("/fruits")
public class FruitResource {

    @Inject
    FruitService ar;

    public FruitResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(ar.getFruits(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFruit(@NotNull @PathParam("name") String name) {
        Optional<Fruit> fruit = ar.getFruit(name);
        return fruit.isPresent() ? Response.ok(fruit).build()
                : Response.status(Status.NOT_FOUND).entity("The fruit " + name + " doesn't exist.").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(Fruit fruit) {
        ar.addFruit(fruit);
        return Response.ok(fruit).build();

    }

    @DELETE
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@NotNull @PathParam("name") String name) {
        Optional<Fruit> fruit = ar.getFruit(name);

        return fruit.isPresent() ? Response.ok(ar.deleteFruit(fruit.get())).build()
                : Response.status(Status.NOT_FOUND).entity("The fruit " + name + " doesn't exist.").build();
    }
}
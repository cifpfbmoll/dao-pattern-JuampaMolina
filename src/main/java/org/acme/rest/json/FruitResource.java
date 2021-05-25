package org.acme.rest.json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
public class FruitResource {

    @Inject
    ActiveRecord ar;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> list() {
        return ar.getFruits();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @POST
    public List<Fruit> add(Fruit fruit) {
        ar.addFruit(fruit);
        return list();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @DELETE
    @Path("{name}")
    public List<Fruit> delete(@NotNull @PathParam("name") String name) {
        Fruit currentFruit = Fruit.find("name", name).firstResult();
        System.out.println(currentFruit);
        ar.deleteFruit(currentFruit);
        return list();
    }
}
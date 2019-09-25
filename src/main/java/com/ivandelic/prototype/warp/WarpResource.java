package com.ivandelic.prototype.warp;

import java.util.Collections;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ivandelic.prototype.warp.service.UniverseService;

@Path("/greet")
@RequestScoped
public class WarpResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final WarpProvider warpProvider;
    private final WarpApplication warpApplication;
    
    @Inject
    public WarpResource(WarpProvider warpProvider, WarpApplication warpApplication) {
        this.warpProvider = warpProvider;
        this.warpApplication = warpApplication;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject findHabitableDemo() {
    	Date t1 = new Date();
    	long count = UniverseService.findHabitableStarsInGalaxy(warpApplication.getUniverse().getGalaxies().get(0), warpProvider.getPlanetMinEsi());
    	Date t2 = new Date();
    	long x = t2.getTime() - t1.getTime();
    	System.out.println(String.format("Counted Time %d", x));
    	
    	return createResponse(count, x);
    }

    /*@Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getMessage(@PathParam("name") String name) {
        return createResponse(name);
    }

    @Path("/greeting")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGreeting(JsonObject jsonObject) {

        if (!jsonObject.containsKey("greeting")) {
            JsonObject entity = JSON.createObjectBuilder()
                    .add("error", "No greeting provided")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }

        String newGreeting = jsonObject.getString("greeting");

        greetingProvider.setMessage(newGreeting);
        return Response.status(Response.Status.NO_CONTENT).build();
    }*/

    private JsonObject createResponse(long count, long time) {
        return JSON.createObjectBuilder()
                .add("start", count)
                .add("time", time)
                .build();
    }
}

package com.ivandelic.prototype.warp;

import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ivandelic.prototype.warp.service.UniverseService;

@Path("/traverse")
@RequestScoped
public class WarpResource {

	private final static Logger log = Logger.getLogger(WarpResource.class.getTypeName());
	
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
    public JsonObject findHabitableDemo(
    		@QueryParam(value = "planetMinEsi") Double planetMinEsi,
    		@QueryParam(value = "refMassMin") Double refMassMin,
    		@QueryParam(value = "refMassMax") Double refMassMax,
    		@QueryParam(value = "refTempMin") Double refTempMin,
    		@QueryParam(value = "refTempMax") Double refTempMax) {
    	
    	if (planetMinEsi == null)
    		planetMinEsi = warpProvider.getPlanetMinEsi();
    	if (refMassMin == null)
    		refMassMin = warpProvider.getRefMassMin();
    	if (refMassMax == null)
    		refMassMax = warpProvider.getRefMassMax();
    	if (refTempMin == null)
    		refTempMin = warpProvider.getRefTempMin();
    	if (refTempMax == null)
    		refTempMax = warpProvider.getRefTempMax();
    	
    	long t1 = System.currentTimeMillis();
    	long count = UniverseService.traverseUniverse(warpApplication.getUniverse(), planetMinEsi, refMassMin, refMassMax, refTempMin, refTempMax);
    	long t2 = System.currentTimeMillis();
    	long tx = t2 - t1;
    	
    	log.info(String.format("Counted Time %d", tx));
    	
    	return createResponse(count, tx);
    }

    private JsonObject createResponse(long count, long time) {
        return JSON.createObjectBuilder()
        		.add("habitablePlanets", count)
        		.add("time", time)
        		.build();
    }
}

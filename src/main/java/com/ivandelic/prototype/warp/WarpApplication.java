package com.ivandelic.prototype.warp;

import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ivandelic.prototype.warp.model.Universe;
import com.ivandelic.prototype.warp.service.SerialziationService;
import com.ivandelic.prototype.warp.service.UniverseService;

import io.helidon.common.CollectionsHelper;

@ApplicationScoped
@ApplicationPath("/universe")
public class WarpApplication extends Application {
	
	private final static Logger log = Logger.getLogger(WarpApplication.class.getTypeName());
	
	private static final String GENERATE_UNIFORMED = "generateUniformed";
	private static final String GENERATE_DISPERSED = "generateDispersed";
	private static final String LOAD = "load";
	
	private final WarpProvider warpProvider;
	
	private final Universe universe;
	
	@Inject
	public WarpApplication(WarpProvider warpProvider) {
		this.warpProvider = warpProvider;
		
		String mode = warpProvider.getUniverseMode();
		
		if (LOAD.equals(mode)) {
			log.info(String.format("Loading universe..."));
			String filename = warpProvider.getUniverseFilename();
			universe = SerialziationService.deserialize(filename);
			log.info(String.format("Universe is loaded from %s!", filename));
		}
		else if (GENERATE_DISPERSED.equals(mode)) {
			log.info(String.format("Creating universe..."));
			universe = UniverseService.createUniverse();
			log.info(String.format("Universe is created!"));
		}
		else if (GENERATE_UNIFORMED.equals(mode)) {
			log.info(String.format("Creating universe..."));
			universe = UniverseService.createUniverse(10, 1_000_000, 5);
			log.info(String.format("Universe is created!"));
		}
		else {
			log.info(String.format("Creating simple universe..."));
			universe = Universe.UNIVERSE;
			log.info(String.format("Simple universe is created!"));
		}
	}
	
    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(WarpResource.class);
    }

	public Universe getUniverse() {
		return universe;
	}
}

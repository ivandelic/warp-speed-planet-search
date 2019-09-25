package com.ivandelic.prototype.warp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class WarpProvider {

	private final double planetMinEsi;
	private final String universeFilename;
	private final String universeMode;
	
    @Inject
    public WarpProvider(@ConfigProperty(name = "warp.planetMinEsi") double planetMinEsi,
    		@ConfigProperty(name = "warp.universeFilename") String universeFilename,
    		@ConfigProperty(name = "warp.universeMode") String universeMode) {
        this.planetMinEsi = planetMinEsi;
        this.universeFilename = universeFilename;
        this.universeMode = universeMode;
    }

	public double getPlanetMinEsi() {
		return planetMinEsi;
	}

	public String getUniverseFilename() {
		return universeFilename;
	}

	public String getUniverseMode() {
		return universeMode;
	}
}

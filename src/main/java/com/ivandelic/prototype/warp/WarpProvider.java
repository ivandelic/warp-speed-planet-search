package com.ivandelic.prototype.warp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class WarpProvider {

	private final double planetMinEsi;
	private final String universeFilename;
	private final String universeMode;
	private final double refMassMin;
	private final double refMassMax;
	private final double refTempMin;
	private final double refTempMax;
	
    @Inject
    public WarpProvider(
    		@ConfigProperty(name = "warp.planet.minEsi") double planetMinEsi,
    		@ConfigProperty(name = "warp.universe.filename") String universeFilename,
    		@ConfigProperty(name = "warp.universe.mode") String universeMode,
    		@ConfigProperty(name = "warp.star.refMassMin") double refMassMin,
    		@ConfigProperty(name = "warp.star.refMassMax") double refMassMax,
    		@ConfigProperty(name = "warp.star.refTempMin") double refTempMin,
    		@ConfigProperty(name = "warp.star.refTempMax") double refTempMax
    		) {
        this.planetMinEsi = planetMinEsi;
        this.universeFilename = universeFilename;
        this.universeMode = universeMode;
        this.refMassMin = refMassMin;
        this.refMassMax = refMassMax;
        this.refTempMin = refTempMin;
        this.refTempMax = refTempMax;
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

	public double getRefMassMin() {
		return refMassMin;
	}

	public double getRefMassMax() {
		return refMassMax;
	}

	public double getRefTempMin() {
		return refTempMin;
	}

	public double getRefTempMax() {
		return refTempMax;
	}
}

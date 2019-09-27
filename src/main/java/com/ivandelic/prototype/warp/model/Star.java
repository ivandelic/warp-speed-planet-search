package com.ivandelic.prototype.warp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Star entity
 * @author Ivan Delic
 */
public class Star implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Star SUN = new Star(1, 1, 1, Galaxy.MILKY_WAY, new Planet[] { Planet.EARTH });
	
	/**
	 * Mass of the star compared to the sun [0.01 - 1500] 
	 */
	private final float mass;
	
	/**
	 * Surface temperature [1.000K - 30.000K]
	 */
	private final float temperature;
	
	/*
	 * Luminosity compared to the Sun [10^-4 - 10^5] 
	 */
	private final float luminosity;
	
	private final Galaxy galaxy;
	
	private Planet[] planets;
	
	public float getMass() {
		return mass;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getLuminosity() {
		return luminosity;
	}

	public Galaxy getGalaxy() {
		return galaxy;
	}

	public Planet[] getPlanets() {
		return planets;
	}

	public void setPlanets(Planet[] planets) {
		this.planets = planets;
	}

	public Star(float mass, float temperature, float luminosity, Galaxy galaxy, Planet[] planets) {
		this.mass = mass;
		this.temperature = temperature;
		this.luminosity = luminosity;
		this.galaxy = galaxy;
		this.setPlanets(planets);
	}
	
	/**
	 * Calculates inner habitable zone radius measured by AU (1AU = distance from Earth to Sun)
	 * @return Inner radius of habitable zone
	 */
	public double innerHabitableRadius() {
		return Math.pow((this.luminosity / 1.1), 0.5);
	}
	
	/**
	 * Calculates outer habitable zone radius measured in by (1AU = distance from Earth to Sun)
	 * @return Outer radius of habitable zone
	 */
	public double outerHabitableRadius() {
		return Math.pow((this.luminosity / 0.53), 0.5);
	}
	
	/**
	 * Checks whether star can be habitable.
	 * @return True if the star is habitable, false otherwise.
	 */
	public boolean isHabbitable(double refMassMin, double refMassMax,double refTempMin, double refTempMax) {
		if (this.mass > refMassMin && this.mass < refMassMax) {
			if (this.temperature >= refTempMin && this.temperature <= refTempMax)
				return true;
		}
		return false;
	}
}

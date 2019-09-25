package com.ivandelic.prototype.warp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Star implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static Star SUN = new Star(1, 1, 1, Galaxy.MILKY_WAY, Arrays.asList(Planet.EARTH));
	
	/**
	 * Mass of the star compared to the sun [0.01 - 1500] 
	 */
	private float mass;
	
	/**
	 * Surface temperature [1.000K - 30.000K]
	 */
	private float temperature;
	
	/*
	 * Luminosity compared to the Sun [10^-4 - 10^5] 
	 */
	private float luminosity;
	
	private Galaxy galaxy;
	
	private List<Planet> planets = new ArrayList<>();
	
	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getLuminosity() {
		return luminosity;
	}

	public void setLuminosity(float luminosity) {
		this.luminosity = luminosity;
	}
	
	public Galaxy getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(Galaxy galaxy) {
		this.galaxy = galaxy;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
	
	public Star(float mass, float temperature, float luminosity, Galaxy galaxy, List<Planet> planets) {
		this.mass = mass;
		this.temperature = temperature;
		this.luminosity = luminosity;
		this.galaxy = galaxy;
		this.planets = planets;
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
	public boolean isHabbitable() {
		if (this.mass > 0.08 && this.mass < 1.4) {
			if (this.temperature >= 2400 && this.temperature <= 7500)
				return true;
		}
		return false;
	}
}

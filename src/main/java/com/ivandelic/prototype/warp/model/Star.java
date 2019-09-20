package com.ivandelic.prototype.warp.model;

import java.util.ArrayList;
import java.util.List;

public class Star {
	
	public static Star Sun = new Star(1, 1, 1, new ArrayList<>()); // TODO dlx

	/**
	 * Mass of the star compared to the sun [0.01 - 1500] 
	 */
	private double mass;
	
	/**
	 * Surface temperature [1.000K - 30.000K]
	 */
	private double temperature;
	
	/*
	 * Luminosity compared to the Sun [10^-4 - 10^5] 
	 */
	private double luminosity;
	
	List<Planet> planets = new ArrayList<>();
	
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getLuminosity() {
		return luminosity;
	}

	public void setLuminosity(double luminosity) {
		this.luminosity = luminosity;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
	
	public Star(double mass, double temperature, double luminosity, List<Planet> planets) {
		this.mass = mass;
		this.temperature = temperature;
		this.luminosity = luminosity;
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

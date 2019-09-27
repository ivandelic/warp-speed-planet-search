package com.ivandelic.prototype.warp.model;

import java.io.Serializable;

/**
 * Planet entity
 * @author Ivan Delic
 */
public class Planet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Planet EARTH = new Planet(1, 1, 1, 288, 1, 1, Star.SUN);
	
	/*
	 * Mean Radius of the Planet, as reference value the earth radius is taken.
	 */
	private final float radius;
	
	/*
	 * The Bulk density of the planet, with a reference value of the earth bulk density.
	 */
	private final float density;
	
	/*
	 * The escape velocity of the planet, with a reference value of earths escapes value.
	 */
	private final float velocity;
	
	/**
	 * The surface temperature of the planet, with a reference value of 288K (15°C).
	 */
	private final float temperature;
	
	/**
	 * The distance of planet from the sun in AU (1AU = distance from Earth to Sun).
	 */
	private final float distance;
	
	/**
	 * The ESI (Schulze-Makuch, 2011; University of Puerto Rico, 2015) is an index of earth-likeness for exoplanets
	 */
	private final float esi;
	
	private final Star star;
	
	public float getRadius() {
		return radius;
	}

	public float getDensity() {
		return density;
	}

	public float getVelocity() {
		return velocity;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getDistance() {
		return distance;
	}

	public float getEsi() {
		return esi;
	}

	public Star getStar() {
		return star;
	}

	/**
	 * Creates the planet.
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param temperature
	 */
	private Planet(float radius, float density, float velocity, float temperature, float distance, float esi, Star star) {
		this.radius = radius;
		this.density = density;
		this.velocity = velocity;
		this.temperature = temperature;
		this.distance = distance;
		this.esi = esi;
		this.star = star;
	}
	
	/**
	 * Creates the planet.
	 * @param radius
	 * @param density
	 * @param velocity
	 * @param temperature
	 */
	public Planet(float radius, float density, float velocity, float temperature, float distance, Star star) {
		this.radius = radius;
		this.density = density;
		this.velocity = velocity;
		this.temperature = temperature;
		this.distance = distance;
		this.star = star;
		this.esi = (float) calculateEsi();
	}
	
	/**
	 * Calculates the ESI index. The ESI (Schulze-Makuch, 2011; University of Puerto Rico, 2015) is an index of earth-likeness for exoplanets.
	 * The index shows a number between zero and one. A number of 0 determines no similarity with the earth, a number of 1 is identical to the earth.
	 * An exoplanet with an ESI value of above 0.8 can be determined as earth like, which means that it has a similar diameter and composition of the planet.
	 * @param planet Planet to copare with earts.
	 * @return The index is a number between zero and one.
	 */
	private double calculateEsi() {
		double esi = 
				Math.pow(1 - Math.abs((this.radius - EARTH.radius) / (this.radius + EARTH.radius)), (0.57 / 1)) *
				Math.pow(1 - Math.abs((this.density - EARTH.density) / (this.density + EARTH.density)), (1.07 / 2)) *
				Math.pow(1 - Math.abs((this.velocity - EARTH.velocity) / (this.velocity + EARTH.velocity)), (0.7 / 3)) *
				Math.pow(1 - Math.abs((this.temperature - EARTH.temperature) / (this.temperature + EARTH.temperature)), (5.58 / 4));
		return esi;	
	}
	
	/**
	 * Checks whether planet is in habitable zone.
	 * @return  True if the planet is habitable, false otherwise.
	 */
	public boolean isHabbitable(double planetMinEsi) {
		if (this.distance >= this.star.innerHabitableRadius() && this.distance <= this.star.outerHabitableRadius()) {
			if (getEsi() > planetMinEsi)
				return true;
		}
		return false;
	}
}

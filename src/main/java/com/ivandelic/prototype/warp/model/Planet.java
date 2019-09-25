package com.ivandelic.prototype.warp.model;

import java.io.Serializable;

public class Planet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static Planet EARTH = new Planet(1, 1, 1, 288, 1, Star.SUN);
	
	/*
	 * Mean Radius of the Planet, as reference value the earth radius is taken.
	 */
	private float radius;
	
	/*
	 * The Bulk density of the planet, with a reference value of the earth bulk density.
	 */
	private float density;
	
	/*
	 * The escape velocity of the planet, with a reference value of earths escapes value.
	 */
	private float velocity;
	
	/**
	 * The surface temperature of the planet, with a reference value of 288K (15°C).
	 */
	private float temperature;
	
	/**
	 * The distance of planet from the sun in AU (1AU = distance from Earth to Sun).
	 */
	private float distance;
	
	private Star star;
	
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
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
	}
	
	/**
	 * Calculates the ESI index. The ESI (Schulze-Makuch, 2011; University of Puerto Rico, 2015) is an index of earth-likeness for exoplanets.
	 * The index shows a number between zero and one. A number of 0 determines no similarity with the earth, a number of 1 is identical to the earth.
	 * An exoplanet with an ESI value of above 0.8 can be determined as earth like, which means that it has a similar diameter and composition of the planet.
	 * @param planet Planet to copare with earts.
	 * @return The index is a number between zero and one.
	 */
	public double getEsi() {
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

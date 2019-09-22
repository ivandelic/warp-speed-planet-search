package com.ivandelic.prototype.warp.model;

public class Planet {
	
	public static Planet earth = new Planet(1, 1, 1, 288, 1, Star.Sun);
	
	/*
	 * Mean Radius of the Planet, as reference value the earth radius is taken.
	 */
	private double radius;
	
	/*
	 * The Bulk density of the planet, with a reference value of the earth bulk density.
	 */
	private double density;
	
	/*
	 * The escape velocity of the planet, with a reference value of earths escapes value.
	 */
	private double velocity;
	
	/**
	 * The surface temperature of the planet, with a reference value of 288K (15°C).
	 */
	private double temperature;
	
	/**
	 * The distance of planet from the sun in AU (1AU = distance from Earth to Sun).
	 */
	private double distance;
	
	private Star star;
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
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
	public Planet(double radius, double density, double velocity, double temperature, double distance, Star star) {
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
				Math.pow(1 - Math.abs((this.radius - earth.radius) / (this.radius + earth.radius)), (0.57 / 1)) *
				Math.pow(1 - Math.abs((this.density - earth.density) / (this.density + earth.density)), (1.07 / 2)) *
				Math.pow(1 - Math.abs((this.velocity - earth.velocity) / (this.velocity + earth.velocity)), (0.7 / 3)) *
				Math.pow(1 - Math.abs((this.temperature - earth.temperature) / (this.temperature + earth.temperature)), (5.58 / 4));
		return esi;	
	}
	
	/**
	 * Checks whether planet is in habitable zone.
	 * @return  True if the planet is habitable, false otherwise.
	 */
	public boolean isHabbitable() {
		if (this.distance >= this.star.innerHabitableRadius() && this.distance <= this.star.outerHabitableRadius()) {
			if (getEsi() > 0.8)
				return true;
		}
		return false;
	}
	
	

}

package com.ivandelic.prototype.warp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ivandelic.prototype.warp.model.Galaxy;
import com.ivandelic.prototype.warp.model.Planet;
import com.ivandelic.prototype.warp.model.Star;

public class SkyCreator {
	
	public static Logger log = Logger.getLogger(SkyCreator.class.getName());
	
	public static final int GALAXY_STARS_MIN = 1;
	public static final int GALAXY_STARS_MAX = 2; // 1_000
	public static final double STAR_MASS_MIN = 0.01;
	public static final double STAR_MASS_MAX = 1500.00;
	public static final int STAR_TEMPERATURE_MIN = 1_000;
	public static final int STAR_TEMPERATURE_MAX = 30_000;
	public static final double STAR_LUMINOSITY_MIN = 0.0001;
	public static final double STAR_LUMINOSITY_MAX = 10_000;
	public static final int STAR_PLANETS_MIN = 5;
	public static final int STAR_PLANETS_MAX = 20;
	public static final double PLANET_RADIUS_MIN = 0.001;
	public static final double PLANET_RADIUS_MAX = 1_000;
	public static final double PLANET_DENSITY_MIN = 0.1;
	public static final double PLANET_DENSITY_MAX = 10;
	public static final double PLANET_VELOCITY_MIN = 0.1;
	public static final double PLANET_VELOCITY_MAX = 10;
	public static final int PLANET_TEMPERATURE_MIN = 0;
	public static final int PLANET_TEMPERATURE_MAX = 1000;
	public static final double PLANET_DISTANCE_MIN = 0.1;
	public static final double PLANET_DISTANCE_MAX = 10;
	
	public static void main(String[] args) {
		log.info("Start generating galaxies");
		List<Galaxy> galaxies = createGalaxy();
		log.info("Finished generating galaxies");
	}
	
	static final List<Galaxy> createGalaxy() {
		List<Galaxy> galaxies = new ArrayList<>();
		
		for (int i = 0; i <= 10; i++) {
			Galaxy galaxy = new Galaxy(null);
			List<Star> stars = createStars(galaxy);
			galaxy.setStars(stars);
		}
		
		return galaxies;
	}
	
	static final List<Star> createStars(Galaxy galaxy) {
		List<Star> stars = new ArrayList<>();
		long q = (long) ((GALAXY_STARS_MIN + Math.random() * (GALAXY_STARS_MAX - GALAXY_STARS_MIN)) * 1_000_000);
		
		for (int i = 0; i < q; i++) {
			double m = STAR_MASS_MIN + Math.random() * (STAR_MASS_MAX - STAR_MASS_MIN);
			int t = (int) (STAR_TEMPERATURE_MIN + Math.random() * (STAR_TEMPERATURE_MAX - STAR_TEMPERATURE_MIN));
			double l = STAR_LUMINOSITY_MIN + Math.random() * (STAR_LUMINOSITY_MAX - STAR_LUMINOSITY_MIN);
			Star star = new Star(m, t, l, null);
			List<Planet> planets = createPlanets(star);
			star.setPlanets(planets);
			stars.add(star);
		}
		
		return stars;
	}
	
	static final List<Planet> createPlanets(Star star) {
		List<Planet> planets = new ArrayList<>();
		long q = (long) (STAR_PLANETS_MIN + Math.random() * (STAR_PLANETS_MAX - STAR_PLANETS_MIN));
		
		for (int i = 0; i < q; i++) {
			double r = PLANET_RADIUS_MIN + Math.random() * (PLANET_RADIUS_MAX - PLANET_RADIUS_MIN);
			double d = PLANET_DENSITY_MIN + Math.random() * (PLANET_DENSITY_MAX - PLANET_DENSITY_MIN);
			double v = PLANET_VELOCITY_MIN + Math.random() * (PLANET_VELOCITY_MAX - PLANET_VELOCITY_MIN);
			int t = (int) (PLANET_TEMPERATURE_MIN + Math.random() * (PLANET_TEMPERATURE_MAX - PLANET_TEMPERATURE_MIN));
			double l  = PLANET_DISTANCE_MIN + Math.random() * (PLANET_DISTANCE_MAX - PLANET_DISTANCE_MIN);
			planets.add(new Planet(r, d, v, t, l, star));
		}
		
		return planets;
	}
}

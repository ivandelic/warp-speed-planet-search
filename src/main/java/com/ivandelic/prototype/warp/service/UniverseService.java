package com.ivandelic.prototype.warp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.ivandelic.prototype.warp.WarpApplication;
import com.ivandelic.prototype.warp.WarpProvider;
import com.ivandelic.prototype.warp.model.Galaxy;
import com.ivandelic.prototype.warp.model.Planet;
import com.ivandelic.prototype.warp.model.Star;
import com.ivandelic.prototype.warp.model.Universe;

public final class UniverseService {
	
	private final static Logger log = LogManager.getLogManager().getLogger(UniverseService.class.getTypeName());
	
	private static final int UNIVERSE_GALAXIES = 10;
	private static final int GALAXY_SCALE = 100; //1_000_000
	private static final int GALAXY_STARS_MIN = 1;
	private static final int GALAXY_STARS_MAX = 1_000;
	private static final double STAR_MASS_MIN = 0.01;
	private static final double STAR_MASS_MAX = 1500.00;
	private static final int STAR_TEMPERATURE_MIN = 1_000;
	private static final int STAR_TEMPERATURE_MAX = 30_000;
	private static final double STAR_LUMINOSITY_MIN = 0.0001;
	private static final double STAR_LUMINOSITY_MAX = 10_000;
	private static final int STAR_PLANETS_MIN = 5;
	private static final int STAR_PLANETS_MAX = 20;
	private static final double PLANET_RADIUS_MIN = 0.001;
	private static final double PLANET_RADIUS_MAX = 1_000;
	private static final double PLANET_DENSITY_MIN = 0.1;
	private static final double PLANET_DENSITY_MAX = 10;
	private static final double PLANET_VELOCITY_MIN = 0.1;
	private static final double PLANET_VELOCITY_MAX = 10;
	private static final int PLANET_TEMPERATURE_MIN = 0;
	private static final int PLANET_TEMPERATURE_MAX = 1000;
	private static final double PLANET_DISTANCE_MIN = 0.1;
	private static final double PLANET_DISTANCE_MAX = 10;
	
	private UniverseService() {}
	
	public static void main(String[] args) {
		boolean serialize = Arrays.stream(args).anyMatch(arg -> arg.compareTo("-s") == 0);
		
		if (serialize) {
			Universe universe = createUniverse();
			SerialziationService.serialize(universe, "universe.dat");
		}
	}
	
	public static final long findHabitableStarsInGalaxy(Galaxy galaxy, double planetMinEsi) {
		long count = galaxy.getStars().stream()/*.filter(star -> star.isHabbitable())*/
				.flatMap(star -> star.getPlanets().stream())
				.filter(planet -> planet.isHabbitable(planetMinEsi))
				.count();
		return count;
	}
	
	public static final Universe createUniverse() {
		Universe universe = new Universe(null);
		List<Galaxy> galaxies = createGalaxies(universe);
		universe.setGalaxies(galaxies);

		return universe;
	}
	
	private static final List<Galaxy> createGalaxies(Universe universe) {
		List<Galaxy> galaxies = new ArrayList<>();
		
		for (int i = 0; i <= UNIVERSE_GALAXIES; i++) {
			Galaxy galaxy = new Galaxy(universe, null);
			List<Star> stars = createStars(galaxy);
			galaxy.setStars(stars);
			galaxies.add(galaxy);
		}
		
		return galaxies;
	}
	
	private static final List<Star> createStars(Galaxy galaxy) {
		List<Star> stars = new ArrayList<>();
		long q = (long) ((GALAXY_STARS_MIN + Math.random() * (GALAXY_STARS_MAX - GALAXY_STARS_MIN)) * GALAXY_SCALE);
		
		for (int i = 0; i < q; i++) {
			float m = (float) (STAR_MASS_MIN + Math.random() * (STAR_MASS_MAX - STAR_MASS_MIN));
			int t = (int) (STAR_TEMPERATURE_MIN + Math.random() * (STAR_TEMPERATURE_MAX - STAR_TEMPERATURE_MIN));
			float l = (float) (STAR_LUMINOSITY_MIN + Math.random() * (STAR_LUMINOSITY_MAX - STAR_LUMINOSITY_MIN));
			Star star = new Star(m, t, (float) l, galaxy, null);
			List<Planet> planets = createPlanets(star);
			star.setPlanets(planets);
			stars.add(star);
		}
		
		return stars;
	}
	
	private static final List<Planet> createPlanets(Star star) {
		List<Planet> planets = new ArrayList<>();
		long q = (long) (STAR_PLANETS_MIN + Math.random() * (STAR_PLANETS_MAX - STAR_PLANETS_MIN));
		
		for (int i = 0; i < q; i++) {
			float r = (float) (PLANET_RADIUS_MIN + Math.random() * (PLANET_RADIUS_MAX - PLANET_RADIUS_MIN));
			float d = (float) (PLANET_DENSITY_MIN + Math.random() * (PLANET_DENSITY_MAX - PLANET_DENSITY_MIN));
			float v = (float) (PLANET_VELOCITY_MIN + Math.random() * (PLANET_VELOCITY_MAX - PLANET_VELOCITY_MIN));
			int t = (int) (PLANET_TEMPERATURE_MIN + Math.random() * (PLANET_TEMPERATURE_MAX - PLANET_TEMPERATURE_MIN));
			float l  = (float) (PLANET_DISTANCE_MIN + Math.random() * (PLANET_DISTANCE_MAX - PLANET_DISTANCE_MIN));
			planets.add(new Planet(r, d, v, t, l, star));
		}
		
		return planets;
	}
}

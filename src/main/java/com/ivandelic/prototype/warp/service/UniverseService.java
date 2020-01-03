package com.ivandelic.prototype.warp.service;

import java.util.Arrays;
import java.util.logging.Logger;

import com.ivandelic.prototype.warp.model.Galaxy;
import com.ivandelic.prototype.warp.model.Planet;
import com.ivandelic.prototype.warp.model.SearchResult;
import com.ivandelic.prototype.warp.model.Star;
import com.ivandelic.prototype.warp.model.Universe;

/**
 * Universe services for generating and traversing solar systems.
 * @author Ivan Delic
 */
public final class UniverseService {
	
	private final static Logger log = Logger.getLogger(UniverseService.class.getTypeName());
	
	private static final int UNIVERSE_GALAXIES = 10;
	private static final int GALAXY_SCALE = 1_000; //1_000_000
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
	
	/**
	 * Use only when you need to serialize universe to the file.
	 * Usage: {@code java -jar target.jar -s}
	 * @param args arguments
	 */
	public static void main(String[] args) {
		boolean serialize = Arrays.stream(args).anyMatch(arg -> arg.compareTo("-s") == 0);
		
		if (serialize) {
			Universe universe = createUniverse();
			SerialziationService.serialize(universe, "universe.dat");
		}
	}
	
	/**
	 * Find habitable planets in the universe traversing galaxies, stars and planets. Algorithm is using Java Streams.
	 * @param universe		universe to search within
	 * @param planetMinEsi	minimal ESI classification
	 * @param refMassMin	minimal mass
	 * @param refMassMax	maximal mass
	 * @param refTempMin	minimal temperature
	 * @param refTempMax	maximal temperature
	 * @return number of habitable planets
	 */
	public static final long traverseUniverseStream(Universe universe, double planetMinEsi, double refMassMin, double refMassMax, double refTempMin, double refTempMax) {
		long count = Arrays.stream(universe.getGalaxies())
				.flatMap(galaxy -> Arrays.stream(galaxy.getStars()))
				.filter(star -> star.isHabbitable(refMassMin, refMassMax, refTempMin, refTempMax))
				.flatMap(star -> Arrays.stream(star.getPlanets()))
				.filter(planet -> planet.isHabbitable(planetMinEsi))
				.count();
		return count;
	}
	
	/**
	 * Find habitable planets in the universe traversing galaxies, stars and planets. Algorithm is using classic looping and Java Streams.
	 * @param universe		universe to search within
	 * @param planetMinEsi	minimal ESI classification
	 * @param refMassMin	minimal mass
	 * @param refMassMax	maximal mass
	 * @param refTempMin	minimal temperature
	 * @param refTempMax	maximal temperature
	 * @return number of habitable planets
	 */
	public static final SearchResult traverseUniverse(Universe universe, double planetMinEsi, double refMassMin, double refMassMax, double refTempMin, double refTempMax) {
        long habitablePlanets = 0, habitableStars = 0;
        long start = System.currentTimeMillis(), last = start;
        
        Galaxy[] galaxies = universe.getGalaxies();
        
        if (galaxies != null)
	        for (int g = 0; g < galaxies.length; g++) {
	        	Galaxy galaxy = galaxies[g];
	        	if (g > 0) {
	        		long now = System.currentTimeMillis();
	                log.info(String.format("%d (%d ms)", g, now - last));
	                last = now;
	        	}
	        	if (galaxy != null) {
		        	Star[] stars = galaxy.getStars();
		        	if (stars != null)
			        	for (int s = 0; s < stars.length; s++) {
			        		Star star = stars[s];
			        		if (star != null) {
				        		if (star.isHabbitable(refMassMin, refMassMax, refTempMin, refTempMax))
				        			habitableStars++;
				        		habitablePlanets += Arrays.stream(star.getPlanets()).filter(planet -> planet.getEsi() >= planetMinEsi).count();
			        		}
		        		}
	        	}
	        }
        
        log.info(String.format("Habitable stars: %d, Habitable planets: %d (%d ms)", habitableStars, habitablePlanets, System.currentTimeMillis() - start));
        
        return new SearchResult(habitableStars, habitablePlanets);
	}
	
	/**
	 * Create the universe with random quantity of galaxies, stars and planets.
	 * @return
	 */
	public static final Universe createUniverse() {
		return createUniverse(null, null, null);
	}
	
	/**
	 * Create the universe with predefined quantity of galaxies, stars and planets.
	 * @param qg	quantity of galaxies in the universe; if {@code qg} is  {@code null} than the number of galaxies is randomized
	 * @param qs	quantity of stars around the galaxy; if {@code qs} is  {@code null} than the number of stars is randomized
	 * @param qp	quantity of planets around the star; if {@code qp} is  {@code null} than the number of planets is randomized
	 * @return	
	 */
	public static final Universe createUniverse(Integer qg, Integer qs, Integer qp) {
		Universe universe = new Universe(null);
		Galaxy[] galaxies = createGalaxies(universe, qg, qs, qp);
		universe.setGalaxies(galaxies);

		return universe;
	}
	
	/**
	 * Creates galaxies array with random quantity of galaxies, stars and planets in parent {@code universe}.
	 * @param universe	parent universe
	 * @return galaxy array
	 */
	private static final Galaxy[] createGalaxies(Universe universe) {
		return createGalaxies(universe, null, null, null);
	}
	
	/**
	 * Creates galaxies array with predefined quantity of galaxies, stars and planets in parent {@code universe}.
	 * @param universe 	parent universe
	 * @param qg		quantity of galaxies in the universe; if {@code qg} is  {@code null} than the number of galaxies is randomized
	 * @param qs		quantity of stars around the galaxy; if {@code qs} is  {@code null} than the number of stars is randomized
	 * @param qp		quantity of planets around the star; if {@code qp} is  {@code null} than the number of planets is randomized
	 * @return galaxy array
	 */
	private static final Galaxy[] createGalaxies(Universe universe, Integer qg, Integer qs, Integer qp) {
		if (qg == null)
			qg = UNIVERSE_GALAXIES;
		
		Galaxy[] galaxies = new Galaxy[qg];
		
		for (int i = 0; i < qg; i++) {
			Galaxy galaxy = new Galaxy(universe, null);
			Star[] stars = createStars(galaxy, qs, qp);
			galaxy.setStars(stars);
			galaxies[i] = galaxy;
		}
		
		return galaxies;
	}
	
	/**
	 * Creates stars array with random quantity of stars and planets in parent {@code galaxy}.
	 * @param galaxy	parent galaxy
	 * @return star array
	 */
	private static final Star[] createStars(Galaxy galaxy) {
		return createStars(galaxy, null, null);
	}
	
	/**
	 * Creates stars array with predefined quantity of stars and planets in parent {@code galaxy}.
	 * @param galaxy 	parent galaxy
	 * @param qs		quantity of stars around the galaxy; if {@code qs} is  {@code null} than the number of stars is randomized
	 * @param qp		quantity of planets around the star; if {@code qp} is  {@code null} than the number of planets is randomized
	 * @return star array		
	 */
	private static final Star[] createStars(Galaxy galaxy, Integer qs, Integer qp) {
		if (qs == null)
			qs = (int) ((GALAXY_STARS_MIN + Math.random() * (GALAXY_STARS_MAX - GALAXY_STARS_MIN)) * GALAXY_SCALE);
		
		Star[] stars = new Star[qs];
		
		for (int i = 0; i < qs; i++) {
			float m = (float) (STAR_MASS_MIN + Math.random() * (STAR_MASS_MAX - STAR_MASS_MIN));
			int t = (int) (STAR_TEMPERATURE_MIN + Math.random() * (STAR_TEMPERATURE_MAX - STAR_TEMPERATURE_MIN));
			float l = (float) (STAR_LUMINOSITY_MIN + Math.random() * (STAR_LUMINOSITY_MAX - STAR_LUMINOSITY_MIN));
			Star star = new Star(m, t, (float) l, galaxy, null);
			Planet[] planets = createPlanets(star, qp);
			star.setPlanets(planets);
			stars[i] = star;
		}
		
		return stars;
	}
	
	/**
	 * Creates planet array with random quantity of planets around parent {@code star}.
	 * @param star	parent star
	 * @return planet array
	 */
	private static final Planet[] createPlanets(Star star) {
		return createPlanets(star, null);
	}
	
	/**
	 * Creates planet array with predefined quantity of planets around parent {@code star}.
	 * @param star	parent star
	 * @param qp	quantity of planets around the star; if {@code qp} is  {@code null} than the number of planets is randomized
	 * @return planet array
	 */
	private static final Planet[] createPlanets(Star star, Integer qp) {
		if (qp == null)
			qp = (int) (STAR_PLANETS_MIN + Math.random() * (STAR_PLANETS_MAX - STAR_PLANETS_MIN));
		
		Planet[] planets = new Planet[qp];
		
		for (int i = 0; i < qp; i++) {
			float r = (float) (PLANET_RADIUS_MIN + Math.random() * (PLANET_RADIUS_MAX - PLANET_RADIUS_MIN));
			float d = (float) (PLANET_DENSITY_MIN + Math.random() * (PLANET_DENSITY_MAX - PLANET_DENSITY_MIN));
			float v = (float) (PLANET_VELOCITY_MIN + Math.random() * (PLANET_VELOCITY_MAX - PLANET_VELOCITY_MIN));
			int t = (int) (PLANET_TEMPERATURE_MIN + Math.random() * (PLANET_TEMPERATURE_MAX - PLANET_TEMPERATURE_MIN));
			float l  = (float) (PLANET_DISTANCE_MIN + Math.random() * (PLANET_DISTANCE_MAX - PLANET_DISTANCE_MIN));
			planets[i] = new Planet(r, d, v, t, l, star);
		}
		
		return planets;
	}
}

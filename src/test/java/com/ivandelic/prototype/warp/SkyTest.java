package com.ivandelic.prototype.warp;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ivandelic.prototype.warp.model.Galaxy;
import com.ivandelic.prototype.warp.model.Planet;
import com.ivandelic.prototype.warp.model.Star;

public class SkyTest {
	
	@Test
	public void testPlanetEsi_Earht() {
		Star star1 = new Star(1, 5000, 10, Galaxy.MILKY_WAY, null);
		Planet earth2 = new Planet(1, 1, 1, 288, 1, star1);
		star1.setPlanets(new Planet[] { earth2 });
		
		double esi = earth2.getEsi();
		
		Assertions.assertEquals(1.0, esi);
	}
	
	@Test
	public void testPlanetEsi_NotEarth() {
		Star star1 = new Star(1, 5000, 10, Galaxy.MILKY_WAY, null);
		Planet earth2 = new Planet(1, 1, 1, 287, 1, star1);
		star1.setPlanets(new Planet[] { earth2 });
		
		double esi = earth2.getEsi();
		
		Assertions.assertNotEquals(1.0, esi);
	}
	
	@Test
	public void testStarHabitability() {
		Star star1 = new Star(1, 5000, 10, Galaxy.MILKY_WAY, null);
		Star star2 = new Star(0.01f, 5000, 100, Galaxy.MILKY_WAY, null);
		
		boolean isHabitable1 = star1.isHabbitable(0.08, 1.4, 2400, 7500);
		Assertions.assertTrue(isHabitable1);
		
		boolean isHabitable2 = star2.isHabbitable(0.08, 1.4, 2400, 7500);
		Assertions.assertFalse(isHabitable2);
	}
	
	@Test
	public void testPlanetHabitability() {
		Star star1 = new Star(1, 5000, 1.4f, Galaxy.MILKY_WAY, null);
		Star star2 = new Star(0.01f, 5000, 100, Galaxy.MILKY_WAY, null);
		
		Planet planet1 = new Planet(1, 1, 1, 295, 1.4f, star1);
		Planet planet2 = new Planet(1, 1, 1, 100, 4, star2);
		
		boolean isHabitable1 = planet1.isHabbitable(0.8);
		Assertions.assertTrue(isHabitable1);
		
		boolean isHabitable2 = planet2.isHabbitable(0.8);
		Assertions.assertFalse(isHabitable2);
	}

}

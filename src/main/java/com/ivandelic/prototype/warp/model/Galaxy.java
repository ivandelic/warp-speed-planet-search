package com.ivandelic.prototype.warp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Galaxy entity
 * @author Ivan Delic
 */
public class Galaxy implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Galaxy MILKY_WAY = new Galaxy(Universe.UNIVERSE, new Star[] { Star.SUN });
	
	private Star[] stars;
	
	private final Universe universe;
	
	public Galaxy(Universe universe, Star[] stars) {
		this.universe = universe;
		this.stars = stars;
	}

	public Star[] getStars() {
		return stars;
	}

	public void setStars(Star[] stars) {
		this.stars = stars;
	}

	public Universe getUniverse() {
		return universe;
	}
}

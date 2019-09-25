package com.ivandelic.prototype.warp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Galaxy implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static Galaxy MILKY_WAY = new Galaxy(Universe.UNIVERSE, Arrays.asList(Star.SUN));
	
	private List<Star> stars = new ArrayList<>();
	private Universe universe;
	
	public Galaxy(Universe universe, List<Star> stars) {
		this.universe = universe;
		this.stars = stars;
	}

	public List<Star> getStars() {
		return stars;
	}

	public void setStars(List<Star> stars) {
		this.stars = stars;
	}
	
	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}
}

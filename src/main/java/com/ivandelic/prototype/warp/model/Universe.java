package com.ivandelic.prototype.warp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Universe implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static Universe UNIVERSE = new Universe(Arrays.asList(Galaxy.MILKY_WAY));
	
	private List<Galaxy> galaxies = new ArrayList<>();
	
	public Universe(List<Galaxy> galaxies) {
		this.galaxies = galaxies;
	}
	
	public List<Galaxy> getGalaxies() {
		return galaxies;
	}

	public void setGalaxies(List<Galaxy> galaxies) {
		this.galaxies = galaxies;
	}
}

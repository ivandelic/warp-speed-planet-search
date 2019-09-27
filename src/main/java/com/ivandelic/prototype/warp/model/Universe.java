package com.ivandelic.prototype.warp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Universe entity
 * @author Ivan Delic
 */
public class Universe implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Universe UNIVERSE = new Universe(new Galaxy[] { Galaxy.MILKY_WAY });
	
	private Galaxy[] galaxies;
	
	public Universe(Galaxy[] galaxies) {
		this.galaxies = galaxies;
	}

	public Galaxy[] getGalaxies() {
		return galaxies;
	}

	public void setGalaxies(Galaxy[] galaxies) {
		this.galaxies = galaxies;
	}
}

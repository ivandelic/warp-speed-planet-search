package com.ivandelic.prototype.warp.model;

import java.util.ArrayList;
import java.util.List;

public class Galaxy {
	
	List<Star> stars = new ArrayList<>();

	public List<Star> getStars() {
		return stars;
	}

	public void setStars(List<Star> stars) {
		this.stars = stars;
	}
	
	public Galaxy(List<Star> stars) {
		this.stars = stars;
	}
}

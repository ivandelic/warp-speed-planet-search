package com.ivandelic.prototype.warp.model;

public class SearchResult {
	
	private final long habitableStars;
	private final long habitablePlanets;
	
	public SearchResult(long habitableStars, long habitablePlanets) {
		this.habitableStars = habitableStars;
		this.habitablePlanets = habitablePlanets;
	}

	public long getHabitableStars() {
		return habitableStars;
	}

	public long getHabitablePlanets() {
		return habitablePlanets;
	}
	
}

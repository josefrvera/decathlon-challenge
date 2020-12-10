package com.josefrvera.decathlon.models.entities;

import java.io.Serializable;

public class Discipline implements Serializable {
	
	private int order;
	private double performance;
	private int points;

	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Double getPerformance() {
		return performance;
	}
//	public void setPerformance(double performance) {
//		this.performance = performance;
//	}
	public void setPerformance(String performance) {
		String[] time = performance.split(":");
		if (time.length > 1)
			this.performance = Integer.parseInt(time[0])*60 + Double.parseDouble(time[1]);
		else
			this.performance = Double.parseDouble(time[0]);
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

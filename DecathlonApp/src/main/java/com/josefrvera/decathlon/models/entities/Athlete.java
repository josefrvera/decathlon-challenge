package com.josefrvera.decathlon.models.entities;

import java.io.Serializable;
import java.util.List;

public class Athlete implements Serializable, Comparable< Athlete > {
	
	private String name;
	private List<Discipline> disciplines;
	private String printedOrder;
	private Integer totalPoints; 
	
	
	@Override
    public int compareTo(Athlete o) {
        return this.getTotalPoints().compareTo(o.getTotalPoints());
    }
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Discipline> getDisciplines() {
		return disciplines;
	}


	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}


	public String getPrintedOrder() {
		return printedOrder;
	}


	public void setPrintedOrder(String printedOrder) {
		this.printedOrder = printedOrder;
	}


	public Integer getTotalPoints() {
		return totalPoints;
	}


	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

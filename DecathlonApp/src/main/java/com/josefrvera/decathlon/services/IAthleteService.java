package com.josefrvera.decathlon.services;

import java.util.List;

import com.josefrvera.decathlon.models.entities.Athlete;

public interface IAthleteService {
	
	public List<Athlete> importData(String input, Object data);
	
	public Object exportData(String output, Object data, List<Athlete> athletes);

}

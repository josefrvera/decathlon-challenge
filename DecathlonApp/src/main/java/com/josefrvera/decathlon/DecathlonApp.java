package com.josefrvera.decathlon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.josefrvera.decathlon.models.entities.Athlete;
import com.josefrvera.decathlon.services.AthleteServiceImpl;
import com.josefrvera.decathlon.services.IAthleteService;
import com.josefrvera.decathlon.utils.Constants;

public class DecathlonApp 
{
	IAthleteService athleteService;

	public static void main(String[] args) {
		String input="";
		String inputData="";
		String output="";
		String outputData="";
		
		try {
			//Tries to get parameters from call args
			input = args[0];
			inputData = args[1];
			output = args[2];
			outputData = args[3];
			
		} catch (Exception e) {
			System.out.println("Loading default parameters parameters: \n"
					+ "CSV src/resources/results.csv XML src/resources/output/results.xml");
			
			//Sets default parameters
			input = "CSV";
			inputData = "src/main/resources/results.csv";
			output = "XML";
			outputData = "src/main/resources/output/results.xml";
		
		} 
			
			//Checks implemented input and output types
			if(!Constants.implementedInputTypes.contains(input)) {
				System.out.println(Constants.notImplementedInputTypeMessage);
				System.out.println(Constants.exitError);
				return;
			} 
			if(!Constants.implementedOutputTypes.contains(output)) {
				System.out.println(Constants.notImplementedOutputTypeMessage);
				System.out.println(Constants.exitError);
				return;
			} 
				
			IAthleteService athleteService = new AthleteServiceImpl();
			
			//Loads list according to input parameters
			List<Athlete> athletes = athleteService.importData(input, inputData);
			
			if (athletes == null) {
				System.out.println(Constants.exitError);
				return;
			}
			
			//Orders the list and assigns final position
			try {
				Collections.sort(athletes, Collections.reverseOrder());
				athletes = addPosition(athletes);
			} catch (Exception e) {
				System.out.println(Constants.unhandledOrder + e.getMessage());
			}
			
			//Exports data according to output parameters
			Object salida = athleteService.exportData(output, outputData, athletes);
		    
			if (salida == null)
				System.out.println(Constants.exitError);
			else
				System.out.println(Constants.exitOK);
			
		
		
	}

	private static List<Athlete> addPosition(List<Athlete> athletes) throws Exception {
		// Converts array in order to check the following rows for even scores  
		Athlete[] athleteArray = new Athlete[athletes.size()];
	    athletes.toArray(athleteArray);
		Integer position;
		// Will store all the positions with score equal to the current record 
		String sharedPosition = "";
		
	    for (int i = 0; i<athletes.size(); i++) {
	    	position = i+1;
	    	// Uses "-" at beginning and end to exclude parcial coincidences, will be cleaned afterwards
	    	// if the string already cointains the position, does not check following rows, it's already done
	    	if (!sharedPosition.contains("-" + position.toString() + "-")) {
		    	sharedPosition = "-" + position.toString() + "-";
		    	for(int j=i+1; 
		    			j<athletes.size() && athleteArray[i].getTotalPoints().intValue()==athleteArray[j].getTotalPoints().intValue();
		    			j++ ) {
		    		position = j+1;
		    		sharedPosition = sharedPosition + position.toString() + "-";
		    	}
	    	}
	    	// Cleans the "-" at the beginning and end
	    	athleteArray[i].setPrintedOrder(sharedPosition.substring(1, sharedPosition.length()-1));
	    }
	    // Converts back to List
		return Arrays.asList(athleteArray);
	}
}

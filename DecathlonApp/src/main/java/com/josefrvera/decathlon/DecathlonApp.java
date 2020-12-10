package com.josefrvera.decathlon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.josefrvera.decathlon.models.entities.Athlete;
import com.josefrvera.decathlon.services.AthleteServiceImpl;
import com.josefrvera.decathlon.services.IAthleteService;

/**
 * Hello world!
 *
 */
public class DecathlonApp 
{
	IAthleteService athleteService;

	public static void main(String[] args) {
		
		String input = "CSV";
		String inputData = "src/main/resources/results.csv";
		String output = "XML";
		String outputData = "src/main/resources/output/results.xml";
		try {
			input = args[0];
			inputData = args[1];
			output = args[2];
			outputData = args[3];
		} catch (Exception e) {
			System.out.println("You should use the following parameters: \n"
					+ "CSV src/resources/results.csv XML src/resources/output/results.xml");
		} finally {
			
			if(!input.equals("CSV")) {
				System.out.println("The received input is not yet implemented \n"
						+ "Try CSV");
				System.exit(0);
			}	
			
			if(!output.equals("XML")) {
				System.out.println("The received output is not yet implemented \n"
						+ "Try XML");
				System.exit(0);
			}
				
			
			IAthleteService athleteService = new AthleteServiceImpl();
			List<Athlete> athletes = athleteService.importData(input, inputData);
			
			for (Athlete a : athletes) {
				System.out.println(a.getName() + ": " + a.getTotalPoints());
			}
			
			Collections.sort(athletes, Collections.reverseOrder());
			
			athletes = addPosition(athletes);
			
			String salida = athleteService.exportData(output, outputData, athletes).toString();
		    
			for (Athlete a : athletes) {
				System.out.println(a.getPrintedOrder() + ": " + a.getName() + " con " + a.getTotalPoints());
			}
			System.out.println(salida);
		}
		
	}

	private static List<Athlete> addPosition(List<Athlete> athletes) {
		Athlete[] myArray = new Athlete[athletes.size()];
	    athletes.toArray(myArray);
		Integer position;
		String sharedPosition = "";
		
	    for (int i = 0; i<athletes.size(); i++) {
	    	position = i+1;
	    	if (!sharedPosition.contains("-" + position.toString() + "-")) {
		    	sharedPosition = "-" + position.toString() + "-";
		    	for(int j=i+1; 
		    			j<athletes.size() && myArray[i].getTotalPoints().intValue()==myArray[j].getTotalPoints().intValue();
		    			j++ ) {
		    		position = j+1;
		    		sharedPosition = sharedPosition + position.toString() + "-";
		    	}
	    	}
	    	myArray[i].setPrintedOrder(sharedPosition.substring(1, sharedPosition.length()-1));
	    }
		return Arrays.asList(myArray);
	}
}

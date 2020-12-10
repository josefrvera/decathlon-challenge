package com.josefrvera.decathlon.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	// Values for main call args 
	public static final List<String> implementedInputTypes = Arrays.asList(new String[] {"CSV"});
	public static final List<String> implementedOutputTypes = Arrays.asList(new String[] {"XML"});
	
	public static final String notImplementedInputTypeMessage = "The received input type is not yet implemented";
	public static final String notImplementedOutputTypeMessage = "The received output type is not yet implemented";
	
	//Output Messages
	public static final String exitOK = "The process finished correctly.";
	public static final String exitError = "The process finished with errors.";
	
	// Messages for loading input data
	// CSV
	public static final String invalidCSVInput = "The input directory or file does not exist";
	public static final String invalidCSVFormat = "The input file does not have the required format: ";
	public static final String unhandledCSVInput = "There was a problem reading the input CSV File: ";

	// Messages for ordering data
	public static final String unhandledOrder = "There was a problem ordering the data: ";
	
	// Messages for writing output data
	// XML
	public static final String invalidXMLOutput = "The output directory does not exist";
	public static final String unhandledXMLOutput = "There was a problem writing the output XML File: ";
	
	
	// Arrays for calculating each formula
	// Leaves the 0 position empty to match from 1 to 10 
	public static final String[] eventName = new String[] {"",
														"100 m", "Long jump",
														"Shot put", "High jump",
														"400 m", "110 m hurdles",
														"Discus throw","Pole vault", 
														"Javelin throw", "1500 m"};
	public static final double[] constantA = new double[] {0,
														25.4347, 0.14354,
														51.39, 0.8465,
														1.53775, 5.74352,
														12.91, 0.2797,
														10.14, 0.03768};
	public static final double[] constantB = new double[] {0,
														18, 220,
														1.5, 75, 
														82, 28.5, 
														4, 100, 
														7, 480};
	
	public static final double[] constantC = new double[] {0,
														1.81, 1.4,
														1.05, 1.42,
														1.81, 1.92,
														1.1, 1.35, 
														1.08, 1.85};
	public static final char[] unit = new char[] {'0',
												's', 'c',
												'm', 'c',
												's', 's',
												'm', 'c',
												'm', 's'};
	
	public static int qualify(int order, double performance) {
		//jump disciplines are expressed in meters, but the formula works with centimeters
		if (unit[order] == 'c')
			performance = performance * 100;
		if (unit[order]=='s') { 
			//track disciplines
			return(int) (constantA[order] * Math.pow(constantB[order] - performance,constantC[order]));
		} else {
			//field disciplines
			return(int) (constantA[order] * Math.pow(performance - constantB[order],constantC[order]));
		}
	}
	
}

package com.josefrvera.decathlon.services;

public class DisciplineServiceImpl implements IDisciplineService {
	
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
		if (unit[order] == 'c')
			performance = performance * 100;
		if (unit[order]=='s') { 
			//track
			return(int) (constantA[order] * Math.pow(constantB[order] - performance,constantC[order]));
		} else {
			//field
			return(int) (constantA[order] * Math.pow(performance - constantB[order],constantC[order]));
		}
	}
		
}

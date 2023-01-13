/**
 * Person.java, Created on Nov 18, 2002
 * Author Administrator
 */
package at.fhv.cts1614.importantExercises.exceptionHandling;

/**
 * @author Administrator
 *
 */
public class Person {
	private static final int TOO_COLD = 65;
	private static final int TOO_HOT = 85;
	
	/* Version 1: Print message on the screen
	 */
	public void drinkV1(CoffeeCup cup) {
	  int temperature = cup.getTemperature();
        if (temperature <= TOO_COLD) {
        	System.out.println("Coffee is too cold!");
        	return;
        }
        else if (temperature >= TOO_HOT) {
        	System.out.println("Coffee is too warm!");
        	return;        	
        }
        
        while (!cup.isEmpty()) {
	        cup.takeASip();
	        System.out.println("Ahhh. Tasty!");
	    }	     
	}

//	/* Version 2, Use Exception
//	 */
	public void drinkV2(CoffeeCup cup) throws Exception {
		int temperature = cup.getTemperature();

		if (temperature <= TOO_COLD) {
			throw new Exception("Too cold!");
		}
		else if (temperature >= TOO_HOT) {
			throw new Exception("Too hot!");
		}

		while (!cup.isEmpty()) {
			cup.takeASip();
			System.out.println("Ahhh. Tasty!");
		}

	}

//	/* Version 3, Use user defined exception TemperatureException
//	 */ 
	public void drinkV3(CoffeeCup cup) throws TemperatureException {
		int temperature = cup.getTemperature();

		if(temperature <= TOO_COLD){
			throw new TemperatureException("too cold");
		}
		else if(temperature >= TOO_HOT){
			throw new TemperatureException("too hot");
		}
		while (!cup.isEmpty()) {
			cup.takeASip();
			System.out.println("Ahhh. Tasty!");
		}

	}

//	/* Version 4, User defined exception TooHotException, TooColdException 
//	 * allowing specific reactions
//	 */ 
	public void drinkV4(CoffeeCup cup) throws TooHotException, TooColdException {
		int temperature = cup.getTemperature();

		if(temperature <= TOO_COLD){
			throw new TooColdException();
		}
		else if(temperature >= TOO_HOT){
			throw new TooHotException();
		}
		while (!cup.isEmpty()) {
			cup.takeASip();
			System.out.println("Ahhh. Tasty!");
		}
	}

	/*
		Die einzig sinnvolle Variante ist die Variante 4
	 */
	 
}



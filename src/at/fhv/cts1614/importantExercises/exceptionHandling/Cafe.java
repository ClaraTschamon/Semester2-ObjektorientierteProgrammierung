/**
 * Cafe.java, Created on Nov 18, 2002
 * Author Administrator
 */
package at.fhv.cts1614.importantExercises.exceptionHandling;

/**
 * @author Administrator
 *
 */
public class Cafe {
	public static void main(String[] args) {
		Person person = new Person();
		CoffeeCup cup = new CoffeeCup(86);
		
		
		/* Version 1
		 */
		assert(cup != null);
		person.drinkV1(cup);
		 
		/* Version 2, use drinkV2
		 */

		try {
			person.drinkV2(cup);
		} catch (Exception e) {
			e.printStackTrace(); //StackTrace sagt mir wo der Fehler aufgetaucht ist
		}

		/* Version 3, use drinkV3
		 */
		try{
			person.drinkV3(cup);
		} catch(Exception e){
			e.printStackTrace();
		}

		/* Version 4, use drinkV4
		 */
		try{
			person.drinkV4(cup);
		} catch(Exception e){
			e.printStackTrace();
		}

		//oder:
		try{
			person.drinkV4(cup);
		}catch(TooHotException excH){
			System.out.println("TooHotException");
		}catch(TooColdException excC){
			System.out.println("TooColdException");
		}catch(TemperatureException e){
			e.printStackTrace();
		}


		/* Version 4.1, use drinkV4, offer a refill in any case
		 */
		try{
			person.drinkV4(cup);
		}catch(TooHotException excH){
			//offer the person something cold;
		}catch(TooColdException excC){
			//would you like something cold
		}catch(TemperatureException e){
			//offer the person a new cup with the right temperature
		}finally{
			System.out.println("Would you like a refill?");
		}




	}
}

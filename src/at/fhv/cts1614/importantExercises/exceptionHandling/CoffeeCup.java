/**
 * CoffeeCup.java, Created on Nov 18, 2002
 * Author Administrator
 */
package at.fhv.cts1614.importantExercises.exceptionHandling;

/**
 * @author Administrator
 *
 */
public class CoffeeCup {
	private static final int DEFAULT_TEMPERATURE = 75;
	private static final int DEFAULT_QUANTITY = 100;
	
	private int m_Temperature;
	private int m_Quantity;
	
	/**
	 * @see java.lang.Object#Object()
	 */
	public CoffeeCup() {
		m_Temperature = DEFAULT_TEMPERATURE;
		m_Quantity = DEFAULT_QUANTITY;
	}
	
	/**
	 * Method CoffeeCup.
	 * @param temperature
	 */
	public CoffeeCup(int temperature) {
		this();
		m_Temperature = temperature;
	}
	
	/**
	 * Returns the temperature.
	 * @return int
	 */
	public int getTemperature() {
		return m_Temperature;
	}

	/**
	 * Sets the temperature.
	 * @param temperature The temperature to set
	 */
	public void setTemperature(int temperature) {
		m_Temperature = temperature;
	}
	
	/**
	 * Returns the quantity.
	 * @return int
	 */
	public int getQuantity() {
		return m_Quantity;
	}

	/**
	 * Sets the quantity.
	 * @param quantity The quantity to set
	 */
	public void setQuantity(int quantity) {
		m_Quantity = quantity;
	}

	/**
	 * Method takeASip.
	 * @return boolean
	 */
	public boolean takeASip() {
		if (!isEmpty()) {
			m_Quantity -= 10;
			
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Method isEmpty.
	 * @return boolean
	 */
	public boolean isEmpty() {
		return m_Quantity == 0;
	}

}

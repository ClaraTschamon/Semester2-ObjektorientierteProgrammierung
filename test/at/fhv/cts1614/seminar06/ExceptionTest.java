/**
 * ExceptionTest.java, Created on 31.07.2002
 * Author Bruce Eckel
 */
package at.fhv.cts1614.seminar06;

/**
 * @author Bruce Eckel
 *
 */
public class ExceptionTest {

	public void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	public static void main(String[] args) throws Exception {
		ExceptionTest lex = new ExceptionTest();
		try {
			lex.f();	
		} 
		finally {
			throw new UnImportantException();
		}
	}
}

class VeryImportantException extends Exception {
	public String toString() {
		return "A very important exception";
	}
};

class UnImportantException extends Exception {
	public String toString() {
		return "A really unimportant exception";
	}
};

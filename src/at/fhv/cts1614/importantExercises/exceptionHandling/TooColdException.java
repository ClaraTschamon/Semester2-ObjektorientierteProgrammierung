package at.fhv.cts1614.importantExercises.exceptionHandling;

public class TooColdException extends TemperatureException{

    public TooColdException(){
        super("too cold!");
    }
}

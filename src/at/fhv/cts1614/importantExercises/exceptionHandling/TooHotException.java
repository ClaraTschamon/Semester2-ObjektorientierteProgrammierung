package at.fhv.cts1614.importantExercises.exceptionHandling;

public class TooHotException extends TemperatureException{

    public TooHotException(){
        super("too hot!");
    }
}

package at.fhv.cts1614.importantExercises.BoundQueue;

public class IsEmptyException extends Exception {
    public IsEmptyException() {
        super("The queue is empty");
    }
}

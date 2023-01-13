package at.fhv.cts1614.importantExercises.Threads.printer;

// represents one print job which is sent to the printer
public class PrintJob {
    private final String _name;
    private final int _size;

    public PrintJob(String name, int size) {
        _name = name;
        _size = size;   // the size of a document will determine how long it takes to print it
    }

    public String getName() {
        return _name;
    }

    public int getSize() {
        return _size;
    }
}
package at.fhv.cts1614.importantExercises.Threads.printer;

import java.util.LinkedList;
import java.util.List;

// can print documents that are sent to it
public class Printer extends Thread {
    // inside this list we will keep track of incoming print job until we have time to print them
    private final List<PrintJob> _queue = new LinkedList<>();
    // these two objects only serve as a so-called "lock" to suspend access (see below)
    private final Object _waitLock;
    private final Object _addLock;
    // this is used to stop the printing process in the end (see shutdown method)
    private boolean _running;


    public Printer() {
        _waitLock = new Object();
        _addLock = new Object();
        _running = true;
    }

    // this is main process of the Printer; it prints documents waiting in the queue
    public void run() {
        try {
            // this will continue looping until we shut the Printer down
            while (_running) {
                // if there is a print job in the queue, print it!
                if (!_queue.isEmpty()) {
                    PrintJob currentPrint = _queue.remove(0);
                    Printer.print(currentPrint);
                } else { // if the queue is empty at the moment, wait
                    // printer waits for a notify signal on the waitLock
                    synchronized (_waitLock) {
                        _waitLock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(PrintJob document) {
        // only one thread at a time is allowed to manipulate the queue
        synchronized (_addLock) {
            _queue.add(document);
        }
        synchronized (_waitLock) {
            // notify the objects who are waiting for the
            // waitLock that they can continue execution
            _waitLock.notify();
        }
    }

    // this method simulates printing a document
    private static void print(PrintJob document) throws InterruptedException {
        // the size of a document determines how long it will take us to print it
        // the Printer sleeps for the time needed; in the real world it would be printing for that time
        Printer.sleep(document.getSize());
        System.out.println("Printed document_" + document.getName());
    }

    public void shutDown() {
        _running = false;
    }
}
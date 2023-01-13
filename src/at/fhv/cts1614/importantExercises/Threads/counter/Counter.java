package at.fhv.cts1614.importantExercises.Threads.counter;

public class Counter extends Thread {
    public static int COUNT = 0;

    public void run() {
        for (int i = 0; i < 100_000; i++) {
            // only allow access to the COUNT variable inside a synchronized block
            synchronized (Counter.class) {
                COUNT++;
            }
        }
    }

    // alternatively we could outsource the counting to a separate >synchronized< method
    private synchronized void count() {
        COUNT++;
    }
}
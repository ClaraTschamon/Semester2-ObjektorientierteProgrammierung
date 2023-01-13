package at.fhv.cts1614.importantExercises.Threads.printer;

import java.util.Random;

// a computer sends documents to the printer in a set interval of time
public class Computer extends Thread {
    private final int _delay;
    private Printer _printer;

    public Computer(Printer homePrinter) {
        Random random = new Random();
        // generates a random delay between 1000 and 12000 ms
        _delay = random.nextInt(11000) + 1000;
        _printer = homePrinter;
    }

    // the main process of a computer; it periodically sends new print jobs
    public void run() {
        // send 10 print jobs in total
        for (int i = 0; i < 10; i++) {
            try {
                // generate a random ID between 0 and 99
                Random rand = new Random();
                int randNum = rand.nextInt(100);
                String randID = String.valueOf(randNum);

                // create a new print job with the random ID and a random size between 500 and 3500
                PrintJob newDocument = new PrintJob(randID, rand.nextInt(3000) + 500);
                // send the document to printer in order to print it out
                _printer.send(newDocument);

                // show a message in the console and sleep for your set delay time
                System.out.println(this.getName() + " sent document_" + newDocument.getName());
                Computer.sleep(_delay);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
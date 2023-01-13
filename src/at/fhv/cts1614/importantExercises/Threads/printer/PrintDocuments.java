package at.fhv.cts1614.importantExercises.Threads.printer;

// this is our main method
public class PrintDocuments {
    public static void main(String[] args) throws InterruptedException {
        Printer hpPrinter = new Printer();

        Computer johannesPC = new Computer(hpPrinter);
        Computer tobiPC = new Computer(hpPrinter);
        Computer alexPC = new Computer(hpPrinter);

        hpPrinter.start();
        johannesPC.start();
        tobiPC.start();
        alexPC.start();

        hpPrinter.join();
        johannesPC.join();
        tobiPC.join();
        alexPC.join();

        hpPrinter.shutDown();
        System.out.println("Alle Druckaufträge erfolgreich abgearbeitet!");
    }
}
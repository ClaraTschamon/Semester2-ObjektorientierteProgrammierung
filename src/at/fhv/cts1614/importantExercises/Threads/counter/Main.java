package at.fhv.cts1614.importantExercises.Threads.counter;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // instantiate three Counter objects which inherit from the Thread class
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Counter counter3 = new Counter();

        // start the execution of all threads (in parallel)
        counter1.start();
        counter2.start();
        counter3.start();

        // wait until each thread has completed its work
        counter1.join();
        counter2.join();
        counter3.join();

        // after all that, print out the final state of the COUNT variable
        System.out.println(Counter.COUNT);
    }
}

/*
 Problemstellung:
 Wir wollen, dass drei Zähler parallel einen GEMEINSAMEN Counter hochzählen.
 Jeder Zähler soll 100_000 mal COUNT + 1 rechnen, somit sollten wir am Ende
 (nachdem alle drei Zähler 100_000 mal hochgezählt haben) bei der Zahl
 COUNT == 300_000 landen.

 Stattdessen beobachten wir ohne synchronized block/methode, dass wir am Ende
 bei einer scheinbar zufälligen Zahl landen, die weit unter 300_000 liegt.
 Der Grund hierfür ist, dass sich die drei Threads "dreinpfuschen" beim Zählen.

 Beispiel:
 Der COUNT zu Beginn auf 0. Thread 1 und Thread 2 lesen nun die aktuelle Zahl (0)
 ein und erhöhen diese um 1. Somit schreiben sie kurz danach beide die Zahl 1 zurück
 auf die COUNT Variable. Also haben jetzt beide Threads einmal hochgezählt und trotzdem
 ist der Zählerstand erst bei 1, nicht bei 2 -> ein Hochzählen ist verloren gegangen.
 Dies führt dazu, dass wir am Ende eben nicht die 400_000 erreichen.

 Lösung:
 Wir verwenden das Schlüsselwort synchronized. Dieses erlaubt immer nur einen
 Akteur innerhalb des synchronisierten Blocks. Somit muss Thread 2 kurz warten, wenn
 Thread 1 gerade die COUNT-Variable bearbeiten. Dadurch gibt es kein Durcheinander
 und Dreinpfuschen mehr.*/

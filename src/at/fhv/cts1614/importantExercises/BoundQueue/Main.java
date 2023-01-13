package at.fhv.cts1614.importantExercises.BoundQueue;

import java.util.Iterator;

public class Main {
    private static BoundQueue queue = new BoundQueue(4);

    public static void main(String[] args) throws IsFullException, IsEmptyException {
        queue.enqueue(new Job("Gärtner"));
        queue.enqueue(new Job("Bauarbeiter"));
        queue.enqueue(new Job("Bäcker"));
        queue.enqueue(new Job("Fotograph"));

        Iterator it = queue.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
        System.out.println(it.hasNext());

        doSomething();

    }

    private static void doSomething() throws IsEmptyException, IsFullException {
        queue.dequeue();
        queue.enqueue(new Job("Sekretär"));
        queue.dequeue();
    }
}

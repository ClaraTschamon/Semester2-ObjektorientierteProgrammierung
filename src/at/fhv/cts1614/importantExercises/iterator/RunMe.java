package at.fhv.cts1614.importantExercises.iterator;

import java.util.Iterator;

/**
 * Aufgabenstellung: wir haben ein Array, das vergleichbar ist mit der ArrayList,
 * wir wollen die for-each Schleife anwenden können. Dafür muss das iterable-interface
 * implementiert werden.
 */
public class RunMe {
    public static void main(String[] args) {
        IntArray values = new IntArray(3);
        values.set(0, 10);
        values.set(1, 20);
        values.set(2, 30);

        for ( int i : values) {
            System.out.println(i);
        }

        Iterator<Integer> iter = values.iterator();
        while(iter.hasNext()) {
            Integer currentValue = iter.next();
            System.out.println(currentValue);
        }
    }
}

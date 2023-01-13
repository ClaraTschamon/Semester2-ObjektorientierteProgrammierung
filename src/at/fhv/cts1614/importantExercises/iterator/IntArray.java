package at.fhv.cts1614.importantExercises.iterator;

import java.util.Iterator;

public class IntArray implements Iterable<Integer>{
    private Integer[] _values;

    public IntArray(int size) {
        _values = new Integer[size];
    }


    public void set(int idx, int value){
        _values[idx] = value;

    }

    public int get(int idx){
        return _values[idx];
    }

    public Iterator<Integer> iterator(){ //man ist dazu gezwungen diese Methode zum imlementieren
        //return new IntArrayIterator(this); <-- macht man nicht.
        return new Iterator<Integer>() { //anonyme Klasse.
            //dieses Objekt das mit der anonymen Klasse erstellt wird, hat vollen zugriff auf die Klasse IntArray.
            //man implementiert nicht die Klasse IntArrayIterator dafür.
            int _currIdx = 0;

           public boolean hasNext() {
               return (_currIdx < _values.length);

           }

           public Integer next() {
               return _values[_currIdx++];
           }
        };
    }

    public int getLength() {
        return _values.length;
    }

}
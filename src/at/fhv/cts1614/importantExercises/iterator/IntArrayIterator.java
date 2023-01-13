package at.fhv.cts1614.importantExercises.iterator;

import java.util.Iterator;

public class IntArrayIterator implements Iterator<Integer>{

    private IntArray _array;
    private int _currIdx;

    public IntArrayIterator(IntArray array) {
        _array = array;
    }

    @Override
    public boolean hasNext() {
        return (_currIdx < _array.getLength());
    }

    @Override
    public Integer next() {
        return _array.get(_currIdx++);
    }
}

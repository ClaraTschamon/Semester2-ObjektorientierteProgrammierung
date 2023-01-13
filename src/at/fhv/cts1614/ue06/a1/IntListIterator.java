package at.fhv.cts1614.ue06.a1;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IntListIterator implements Iterator<Integer> {

    private List<Integer> _mylist;
    private int _currIdx = 0;

    public IntListIterator(List<Integer> list) {
        _mylist = list;
    }

    /**
     * just for Test purpose!!!
     */
    public void set_currIdx(int currIdx) {
        _currIdx = currIdx;
    }

    @Override
    public boolean hasNext() {
        if(_currIdx < _mylist.size()){
            return true;
        }
        return false;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        while(hasNext() && (_mylist.get(_currIdx) % 2) != 0){
            _currIdx++;
        }
        return _mylist.get(_currIdx++);
    }
}

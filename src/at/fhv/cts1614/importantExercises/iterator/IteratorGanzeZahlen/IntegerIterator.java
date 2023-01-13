package at.fhv.cts1614.importantExercises.iterator.IteratorGanzeZahlen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IntegerIterator implements Iterable {

    List<Integer> _values = new LinkedList<>();

    public IntegerIterator(){
        for(int i = 0; i <= 10; i++){
            _values.add(i);
        }
    }

    @Override
    public Iterator <Integer> iterator() {
        return new Iterator<Integer>() {
            int _currIdx;
            Integer _next;
            @Override
            public boolean hasNext() {
                if(_next != null){
                    return true;
                }
                while(_currIdx < _values.size()){
                    if(_values.get(_currIdx) != null){
                        _next = _values.get(_currIdx);
                        _currIdx++;
                        return true;
                    }
                    _currIdx++;
                }
                return false;
            }

            @Override
            public Integer next() {
                if(_next == null){
                    hasNext();
                }
                Integer next = _next;
                _next = null;
                return next;
            }
        };
    }



    public static void main(String[] args) {
        Iterator iterator = new IntegerIterator().iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

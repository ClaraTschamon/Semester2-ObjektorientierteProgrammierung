package at.fhv.cts1614.importantExercises.iterator.IteratorGanzeZahlen;

import java.util.Iterator;

public class Main implements Iterable<Integer>{
    private Integer [] _list;

    public Main(){
        _list = new Integer[10];
        for(int i = 0; i < _list.length; i++){
            _list[i] = i;
        }
    }

    @Override
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>(){

            int currIdx;
            Integer _next;

            @Override
            public boolean hasNext(){
                if(_next != null){
                    return true;
                }
                while(currIdx < _list.length){
                    if(_list[currIdx] != null){
                        if(_list[currIdx] % 2 == 0) {
                            _next = _list[currIdx];
                            currIdx++;
                            return true;
                        }
                    }
                    currIdx++;
                }
             return false;
            }

            @Override
            public Integer next(){
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

        Iterator<Integer> iter = new Main().iterator();

        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}

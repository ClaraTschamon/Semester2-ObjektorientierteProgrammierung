package at.fhv.cts1614.importantExercises.BoundQueue;

import java.util.Iterator;

public class BoundQueue implements Iterable<Job>{ //=Warteschlange
    private Job[] _queue;
    private int _firstIndex;
    private int _addIndex;
    private int _numberOfElements;

    public BoundQueue(int size){
        _queue = new Job[size];
    }

    public boolean isEmpty(){
        return _numberOfElements <= 0;
    }

    public boolean isFull(){
        return _numberOfElements == _queue.length;
    }

    private int getNextIndex(int index){
        return (index + 1) % _queue.length;
    }

    public void enqueue(Job value) throws IsFullException{ //einreihen
        if(isFull()){
            throw new IsFullException("Queue is full!");
        }
        _queue[_addIndex] = value;
        _addIndex = getNextIndex(_addIndex);
        _numberOfElements++;
    }

    public void dequeue() throws IsEmptyException{ //ausreihen
        if(isEmpty()){
            throw new IsEmptyException();
        }
        int temp = _firstIndex % _queue.length;
        _queue[temp] = null;
        _firstIndex++;
        _numberOfElements--;
    }

    @Override
    public Iterator<Job> iterator() {
        return new Iterator<Job>() {
            int currIdx;
            Job _next;

            @Override
            public boolean hasNext() {
                if(_next != null){
                    return true;
                }
                while(currIdx < _queue.length){
                    if(_queue[currIdx] != null){
                        _next = _queue[currIdx];
                        currIdx++;
                        return true;
                    }
                    currIdx++;
                }
                return false;
            }

            @Override
            public Job next() {
                Job next;
                if(_next == null){
                   hasNext();
                }
                next = _next;
                _next = null;
                return next;
            }
        };
    }
}



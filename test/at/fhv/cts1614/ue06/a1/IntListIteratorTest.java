package at.fhv.cts1614.ue06.a1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntListIteratorTest {
    List<Integer> list = new ArrayList<>();
    IntListIterator myIterator = new IntListIterator(list);

    @Test
    void Test1() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(10);
        list.add(11);
        list.add(4);
        list.add(12);

        myIterator.set_currIdx(8);
        assertTrue(myIterator.hasNext());
    }

    @Test
    void Test2() {
        myIterator.set_currIdx(9);
        assertFalse(myIterator.hasNext());
    }

    @Test
    void Test3() {
        list.clear();
        assertFalse(myIterator.hasNext());
    }

    @Test
    void Test4(){
        list.add(1);
        list.add(11);
        list.add(13);
        list.add(4);

        myIterator.set_currIdx(2);
        assertEquals(4, myIterator.next());
    }

    @Test
    void Test5(){
        list.add(1);
        list.add(0);
        list.add(44);
        list.add(11);
        list.add(13);
        list.add(4);

        myIterator.set_currIdx(0);
        while(myIterator.hasNext()){
            System.out.println(myIterator.next());
        }
    }
}
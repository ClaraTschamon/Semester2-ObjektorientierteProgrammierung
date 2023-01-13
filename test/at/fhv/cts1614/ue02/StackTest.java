//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;
import at.fhv.cts1614.ue02.Calculator.Stack;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    public static void main(String[] args) {
        System.out.println("Java Stack Tester");
        Stack stack = new Stack();
        System.out.println("Leerer Stack: " + stack);

        //push values:
        stack.push(5);
        stack.push(7);
        for (int i = 0; i <= 20; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        assertEquals(20, stack.top());

        //pop values:
        stack.pop();
        assertEquals(19, stack.top());

        //isEmpty:
        assertEquals(false, stack.isEmpty());
        Stack emptyStack = new Stack(10);
        assertEquals(true, emptyStack.isEmpty());
        System.out.println("Empty Stack: " + emptyStack);

        //isFull:
        assertEquals(false, stack.isFull());
        Stack fullStack = new Stack(10);
        for (int i = 0; i < 10; i++){
            fullStack.push(i);
        }
        System.out.println("Full Stack: " + fullStack);
        assertEquals(true, fullStack.isFull());

    }
}
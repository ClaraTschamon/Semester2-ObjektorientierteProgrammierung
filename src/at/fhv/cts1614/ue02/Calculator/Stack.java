//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02.Calculator;

public class Stack {
    private static final int DEFAULT_STACKSIZE = 64;
    private int[] _values;
    private int _sp; //Stackpointer


    public Stack() {
        this(DEFAULT_STACKSIZE); //Wir rufen damit den eigenene Konstruktor auf
        //_values = new int[DEFAULT_STACKSIZE];

    }
    public Stack(int stacksize){
        _values = new int[stacksize];
    }

    /**
     Pushes the given value onto the Stack.
     NOTE: Throws an ArrayIndesxOutOfBounceExceptions if the stack is full!
     * @param value value to push
     */
    public void push(int value){
        _values[_sp++] = value;
    }

    /**
     Takes the value on top of the stack off.
     */
    public int pop() {
        return _values[--_sp]; //sp wird zuerst um eines verringert
    }

    /**
     Returns the value on top of the stack.
     */
    public int top() {
        return _values[_sp-1];
    }

    /**
     Returns true if the stack is empty.
     */
    public boolean isEmpty(){
        return (_sp <= 0);
    }

    /**
     returns true if the stack is full.
     */
    public boolean isFull() {
        return (_sp >= _values.length);
    }

    @Override //Methode in der Überklasse die ich ändern möchte
    public String toString() {
        String s = "Stack: sp = " + _sp + ": ";
        for (int i = 0; i< _sp; i += 1){
            s = s + _values[i] + ", ";
        }
        return s;
    }
}

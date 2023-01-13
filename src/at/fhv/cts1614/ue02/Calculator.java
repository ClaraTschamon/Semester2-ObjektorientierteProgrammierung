//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue02;

public class Calculator {
    public int calculate(String expr){

        System.out.println("Calc:" + expr);

        String[] parts = expr.split(" ");

        Stack operands = new Stack(expr.length());
        int op1;
        int op2;
        int result;
        for (String part : parts){//der doppelpunkt ist ein "in".. parts ist eine Liste
            switch(part){
                case "+":
                    op1 = operands.pop();
                    op2 = operands.pop();
                    result = op1 + op2;
                    operands.push(result);
                    break;
                case "-":
                    op1 = operands.pop();
                    op2 = operands.pop();
                    operands.push(op1 - op2);
                    break;
                case "*":
                    op1 = operands.pop();
                    op2 = operands.pop();
                    operands.push(op1 * op2);
                    break;
                case "/":
                    op1 = operands.pop();
                    op2 = operands.pop();
                    operands.push(op1 / op2);
                    break;
                case ";":
                    break;
                default:
                    int value = Integer.valueOf(part);
                    operands.push(value);
                    break;
            }
        }
        return operands.pop(); //weil das Ergebnis wieder auf den Stack gelegt wurde und somit der oberste Wert ist.
    }
}

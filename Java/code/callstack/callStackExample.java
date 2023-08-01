package callstack;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Deque;
public class callStackExample {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Deque deque = new LinkedList();
        firstMethod();
        System.out.println("end");
    }

    public static void firstMethod() {
        int firstVariable;
        firstVariable = 1;
        System.out.println(System.identityHashCode(firstVariable));
        int firstVariable2 = 2;
        System.out.println(System.identityHashCode(firstVariable2));
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        secondMethod(firstVariable, firstVariable2);
        thirdMethod(firstVariable, firstVariable2);
        System.out.println("end");
    }

    private static void thirdMethod(int num1, int num2) {
        int thirdVariable = num1 * num2;
        System.out.println("third" + thirdVariable);
    }

    private static void secondMethod(int num1, int num2) {
        int secondVariable = num1 + num2;
        System.out.println("two" + secondVariable);
    }


}

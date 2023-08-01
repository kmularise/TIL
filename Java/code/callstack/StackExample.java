package callstack;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        executeByStack();
        executeByLinkedList();
    }

    public static void executeByStack() {
        long before = System.currentTimeMillis();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < 10000 ; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 6000; i++) {
            stack.remove(0);
        }
        long duration = System.currentTimeMillis() - before;
        System.out.println("duration = " + duration);
    }

    public static void executeByLinkedList() {
        long before = System.currentTimeMillis();
        Deque<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10000 ; i++) {
            list.add(i);
        }
        for (int i = 0; i < 6000; i++) {
            list.removeFirst();
        }
        long duration = System.currentTimeMillis() - before;
        System.out.println("duration = " + duration);
    }

}

package generic.practice;

import java.util.ArrayList;

public class MaxFinder {
    public static void main(String[] args) {
        MaxFinder maxFinder = new MaxFinder();
        maxFinder.testGetMax();
        maxFinder.testGetMin();
    }
    public void testGetMax() {
        System.out.println(getMax(1, 2, 3));
        System.out.println(getMax(3, 1, 2));
        System.out.println(getMax(2, 3, 1));
        System.out.println(getMax("a", "b", "c"));
        System.out.println(getMax("b", "c", "a"));
        System.out.println(getMax("a", "b", "c"));
    }
    public void testGetMin() {
        System.out.println(getMin(1, 2, 3));
        System.out.println(getMin(3, 1, 2));
        System.out.println(getMin(2, 3, 1));
        System.out.println(getMin("a", "b", "c"));
        System.out.println(getMin("b", "c", "a"));
        System.out.println(getMin("a", "b", "c"));
    }

    public <T extends Comparable<T>> T getMax(T ... elements) {
        T maxT = elements[0];
        for (T tempT : elements) {
            if (tempT.compareTo(maxT) > 0) maxT = tempT;
        }
        return maxT;
    }

    public <T extends  Comparable<T>> T getMin(T ...elements) {
        T minT = elements[0];
        for (T tempT : elements) {
            if (tempT.compareTo(minT) < 0) minT = tempT;
        }
        return minT;
    }
}

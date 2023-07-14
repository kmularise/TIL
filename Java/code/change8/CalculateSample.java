package change8;

import java.util.Comparator;

public class CalculateSample {
    public static void main(String[] args) {
        CalculateSample sample = new CalculateSample();
        sample.calculateClassic();
        sample.runCommonThread();
    }

    private void calculateClassic() {

        Calculate calculateAdd = new Calculate() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
        int value = calculateAdd.operation(1, 2);
        System.out.println("value = " + value);
        Calculate calculate = (a, b) -> (a + b);
        System.out.println("calculate.operation(1, 2) = " + calculate.operation(1, 2));
        Calculate  calculateSubtract = (a, b) -> (a - b);
        System.out.println("calculateSubtract.operation(1, 2) = " + calculateSubtract.operation(1, 2));

    }
    private void runCommonThread() {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}

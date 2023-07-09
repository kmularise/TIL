package thread.sync;

public class CommonCalculate {
    private int amount;
    private Object lock = new Object();

    public CommonCalculate() {
        amount = 0;
    }

//    public synchronized void plus(int value) {
//        amount += value;
//    }
    public void plus(int value) {
        synchronized (lock) {
            amount += value;
        }
    }


//    public synchronized void minus(int value) {
//        amount -= value;
//    }
    public void minus(int value) {
        synchronized (lock) {
            amount -= value;
        }
    }
    public int getAmount() {
        return amount;
    }
}

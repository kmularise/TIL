package thread.support;

import java.math.BigDecimal;
import java.math.BigInteger;
import thread.RunMultiThreads;

public class RunSupportThreads {
    public static void main(String[] args) {
//        RunSupportThreads runSupportThreads = new RunSupportThreads();
//        runSupportThreads.checkThreadState1();
        //runSupportThreads.checkJoin();
        double a = 0.01;
        BigDecimal bigDecimal = new BigInteger(1233548349389028394028490283940283904283904);
        System.out.println("a = " + bigDecimal.toString());
    }

    public void checkThreadState1() {
        SleepThread thread = new SleepThread(2000);
        try {
            System.out.println("thread.getState() = " + thread.getState());
            thread.start();
            System.out.println("thread.getState() after start = " + thread.getState());

            Thread.sleep(1000);
            System.out.println("Thread.activeCount() = " + Thread.activeCount());
            System.out.println("thread.getState() after 1 sec = " + thread.getState());

            thread.join();
            thread.interrupt();
            System.out.println("thread.getState() after join = " + thread.getState());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void checkJoin() {
        SleepThread thread = new SleepThread(2000);
        try {
            thread.start();
            thread.join(500);
            thread.interrupt();
            System.out.println("thread.getState() = " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

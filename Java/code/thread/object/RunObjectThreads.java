package thread.object;

import thread.support.SleepThread;

public class RunObjectThreads {
    public static void main(String[] args) {
        RunObjectThreads sample = new RunObjectThreads();
//        sample.checkThreadState2();
//        sample.checkThreadState3();
        sample.groupThread();
    }

    public void groupThread() {
        try {
            SleepThread sleep1 = new SleepThread(5000);
            SleepThread sleep2 = new SleepThread(5000);

            ThreadGroup group = new ThreadGroup("Group1");
            Thread thread1 = new Thread(group, sleep1);
            Thread thread2 = new Thread(group, sleep2);

            thread1.start();
            thread2.start();
            Thread.sleep(1000);
            System.out.println("group = " + group.getName());
            int activeCount = group.activeCount();
            System.out.println("activeCount = " + activeCount);
            group.list();

            Thread[] tempThreadList = new Thread[activeCount];
            int result = group.enumerate(tempThreadList);
            System.out.println("result = " + result);
            for (Thread thread : tempThreadList) {
                System.out.println(thread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void checkThreadState3() {
        Object monitor = new Object();
        StateThread thread = new StateThread(monitor);
        StateThread thread2 = new StateThread(monitor);
        try {
            System.out.println("thread.getState() = " + thread.getState());
            thread.start();
            thread2.start();
            System.out.println("thread.getState() after start = " + thread.getState());

            Thread.sleep(100);
            System.out.println("thread.getState() after 0.1 sec = " + thread.getState());

            synchronized (monitor) {
//                monitor.notify();
//                monitor.notify();
                monitor.notifyAll();
            }
            Thread.sleep(100);
            System.out.println("thread.getState() after notify = " + thread.getState());

            thread.join();
            System.out.println("thread.getState() after join = " + thread.getState());
            thread2.join();
            System.out.println("thread2.getState() after join = " + thread2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkThreadState2() {
        Object monitor = new Object();
        StateThread thread = new StateThread(monitor);
        try {
            System.out.println("thread.getState() = " + thread.getState());
            thread.start();
            System.out.println("thread.getState() after start = " + thread.getState());

            Thread.sleep(100);
            System.out.println("thread.getState() after 0.1 sec = " + thread.getState());

            synchronized (monitor) {
                monitor.notify();
            }
            Thread.sleep(100);
            System.out.println("thread.getState() after notify = " + thread.getState());

            thread.join();
            System.out.println("thread.getState() after join = " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

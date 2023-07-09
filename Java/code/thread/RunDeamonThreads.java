package thread;

public class RunDeamonThreads {
    public static void main(String[] args) {
        RunDeamonThreads sample = new RunDeamonThreads();
        //sample.checkThreadProperty();
        sample.runDaemonThread();
    }


    public void runCommonThread() {
        DaemonThread thread = new DaemonThread();
        thread.start();
    }
    public void runDaemonThread() {
        DaemonThread thread = new DaemonThread();
        thread.setDaemon(true);
        thread.start();
    }

    private void checkThreadProperty() {
        ThreadSample thread1 = new ThreadSample();
        ThreadSample thread2 = new ThreadSample();
        ThreadSample daemonThread = new ThreadSample();
        System.out.println("thread1 = " + thread1.getId());
        System.out.println("thread2 = " + thread2.getId());
        System.out.println("thread1 name = " + thread1.getName());
        System.out.println("thread2 name= " + thread2.getName());
        System.out.println("thread1.getPriority() = " + thread1.getPriority());

        daemonThread.setDaemon(true);
        System.out.println("thread1 daemon= " + thread1.isDaemon());
        System.out.println("daemonThread daemon = " + daemonThread.isDaemon());

    }
}

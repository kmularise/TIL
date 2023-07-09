package thread;

public class RunThreads {
    public static void main(String[] args) {
        RunThreads threads = new RunThreads();
        threads.runBasic();
    }

    private void runBasic() {
        RunnableSample runnable = new RunnableSample();
        new Thread(runnable).start();

        ThreadSample threadSample = new ThreadSample();
        threadSample.start();
        System.out.println("RunThreads.runBasic() method is ended");
    }


}

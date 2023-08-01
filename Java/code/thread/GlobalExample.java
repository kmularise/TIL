package thread;

public class GlobalExample extends Thread{
    static int share;

    public static void main(String[] args) {
        GlobalExample thread1 = new GlobalExample();
        GlobalExample thread2 = new GlobalExample();

        thread1.start();
        thread2.start();
    }

    public void run() {
        for (int count = 0 ; count < 10; count++) {
            System.out.println(share++);
        }
        try {sleep(10000);}
        catch (InterruptedException e) {};
    }
}

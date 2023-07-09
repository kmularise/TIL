package thread;

public class NameThread extends Thread{
    private int number;
    public NameThread(String name, int number) {
        super(name);
        this.number=number;
    }

    @Override
    public void run() {
        number++;
    }
}

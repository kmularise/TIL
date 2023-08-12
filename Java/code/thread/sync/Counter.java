package thread.sync;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
    private AtomicLong count = new AtomicLong();
    public void increment() {
        count.getAndIncrement();
    }

    public long getCount() {
        return count.get();
    }
}

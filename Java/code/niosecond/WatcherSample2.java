package niosecond;


import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatcherSample2 extends Thread {
    String dirName;

    public static void main(String[] args) throws Exception {
        String dirName = "/Users/yuikim/IdeaProjects/TIL/Java/code/network";
        String fileName = "WatcherSample.txt";
        WatcherSample2 sample = new WatcherSample2(dirName);
        sample.setDaemon(true);
        sample.start();
        Thread.sleep(1000);
        for (int loop = 0; loop < 10; loop++) {
            sample.fileWriteDelete(dirName, fileName+loop);
        }
    }

    public WatcherSample2(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void run() {
        System.out.println("### Watcher thread is stared ###");
        System.out.format("Dir = %s\n", dirName);
        addWatcher();
    }

    public void addWatcher() {
        try {
            Path dir = Paths.get(dirName);
            WatchService watcher = FileSystems.getDefault().newWatchService();
            WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_CREATE, ENTRY_MODIFY);
            while (true) {
                key = watcher.take();
                List<WatchEvent<?>> eventList = key.pollEvents();
                System.out.println("eventList = " + eventList);
                for (WatchEvent<?> event : eventList) {
                    Path name = (Path) event.context();
                    if (event.kind() == ENTRY_CREATE) {
                        System.out.format("%s createad%n", name);
                    } else if (event.kind() == ENTRY_DELETE) {
                        System.out.format("%s deleted%n", name);
                    } else if (event.kind() == ENTRY_MODIFY) {
                        System.out.format("%s modified%n", name);
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fileWriteDelete(String dirName, String fileName) {
        Path path = Paths.get(dirName, fileName);
        String contents = "Watcher sample";
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        try {
            System.out.println("Write " + fileName);
            Files.write(path, contents.getBytes(), openOption);
//            Files.delete(path);
            Thread.sleep(150);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

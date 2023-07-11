package io;

import java.io.File;

public class FileFilterSample {
    public static void main(String[] args) {
        FileFilterSample sample = new FileFilterSample();
        String pathName = File.separator + "Users" + File.separator + "yuikim" + File.separator +"godofjava";
        sample.checkList(pathName);
    }

    private void checkList(String pathName) {
        File file;
        try {
            file = new File(pathName);
            //File[] files = file.listFiles();
            File[] files = file.listFiles(new JPGFileFilter());
            for (File file1 : files) {
                System.out.println("file.getName() = " + file1.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

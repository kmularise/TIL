package io;

import java.io.File;
import java.io.IOException;

public class FileManageClass {
    public static void main(String[] args) {
        FileManageClass sample = new FileManageClass();
        String pathName = File.separator + "Users" + File.separator + "yuikim" + File.separator +"godofjava";
        String fileName = "test";
        sample.checkFile(pathName, fileName);
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println("file.getAbsoluteFile() = " + file.getAbsoluteFile());
        }
    }

    private void checkFile(String pathName, String fileName) {
        File file = new File(pathName, fileName);
        try {
            System.out.println("file.createNewFile() = " + file.createNewFile());
            getFileInfo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFileInfo(File file) throws IOException {
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("file.getAbsoluteFile() = " + file.getAbsoluteFile());
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());
        System.out.println("file.getCanonicalFile() = " + file.getCanonicalFile());
        System.out.println("file.getParent() = " + file.getParent());
    }
}

package io;

import java.io.File;

public class FileSample {
    public static void main(String[] args) {
        FileSample sample = new FileSample();
        String pathName = File.separator + "Users" + File.separator + "yuikim" + File.separator +"godofjava" + File.separator + "text";
        System.out.println("pathName = " + pathName);
        System.out.println("File.separator = " + File.separator);
//        sample.checkPath(pathName);
//        sample.makeDir(pathName);
        sample.checkFileMethods(pathName);
        sample.checkFileMethods(pathName);
        sample.lastModified(pathName);
        sample.deleteDir(pathName);
    }

    private void deleteDir(String pathName) {
        File file = new File(pathName);
        boolean delete = file.delete();
        System.out.println("delete = " + delete);
    }

    private void lastModified(String pathName) {
        File file = new File(pathName);
        System.out.println("file.lastModified() = " + file.lastModified());
    }

    private void checkFileMethods(String pathName) {
        File file = new File(pathName);
        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.isFile() = " + file.isFile());
        System.out.println("file.isHidden() = " + file.isHidden());
    }

    private void checkPath(String pathName) {
        File file = new File(pathName);
        System.out.println("file.exists() = " + file.exists());
    }

    public void makeDir(String pathName) {
        File file = new File(pathName);
        System.out.println("file.mkdir() = " + file.mkdir());
    }
    
    public void canFileMethods(String pathName) {
        File file = new File(pathName);
        System.out.println("file.canRead() = " + file.canRead());
        System.out.println("file.canWrite() = " + file.canWrite());
        System.out.println("file.canExecute() = " + file.canExecute());
    }
}

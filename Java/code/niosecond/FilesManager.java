package niosecond;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilesManager {
    public static void main(String[] args) {
        FilesManager sample = new FilesManager();
        String fileName = "AboutThisBook2.txt";
        Path fromPath = sample.writeAndRead(fileName);
        sample.copyMoveDelete(fromPath, fileName);
    }

    public Path writeAndRead(String fileName) {
        Path returnPath = null;
        try {
            Path path = Paths.get(fileName);
            returnPath = writeFile(path);
            System.out.println("******create file contents ****");
            readFile(returnPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnPath;
    }

    public void readFile(Path path) throws Exception{
        Charset charset = Charset.defaultCharset();
        System.out.println("Charset.defaultCharset() = " + charset);
        //Charset charset = Charset.forName("EUC-KR");
        System.out.println("path = " + path);
        List<String> fileContents = Files.readAllLines(path, charset);
        for (String tempContents : fileContents) {
            System.out.println(tempContents);
        }
        System.out.println();
    }

    public Path writeFile(Path path) throws Exception{
        Charset charset = Charset.defaultCharset();
        List<String> contents = getContents();
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        return Files.write(path, contents, charset, openOption);
    }

    public List<String> getContents() {
        ArrayList<String> contents = new ArrayList<>();
        contents.add("이 책은 저자의 6번째 책입니다");
        contents.add("2222222");
        contents.add("333333333");
        contents.add("444444");
        contents.add("55555");
        contents.add("66666");
        contents.add("new Date = " + new Date());
        return contents;
    }

    public void copyMoveDelete(Path fromPath, String fileName) {
        try {
            Path toPath = fromPath.toAbsolutePath().getParent();

            Path copyPath = toPath.resolve("copied");
            if (!Files.exists(copyPath)) {
                Files.createDirectories(copyPath);
            }
            Path copiedFilePath = copyPath.resolve(fileName);
            StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;
            Files.copy(fromPath, copiedFilePath, copyOption);


            System.out.println("**** Copied file contents *****");
            System.out.println("copiedFilePath = " + copiedFilePath);
            readFile(copiedFilePath);

            Path movedFilePath = Files.move(copiedFilePath, copyPath.resolve("moved.txt"), copyOption);
            System.out.println("movedFilePath = " + movedFilePath);
            Files.delete(movedFilePath);
            Files.delete(copyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

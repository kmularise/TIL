package optional;


import java.util.Optional;
import java.util.Random;

public class OptionalTest {
    public static void main(String[] args) {
        String name1 = Optional.ofNullable("not empty")
                .orElse(getRandomName());
        System.out.println("name1 = " + name1);
        String name2 = Optional.ofNullable("not empty")
                .orElseGet(OptionalTest::getRandomName);
        System.out.println("name2 = " + name2);
    }
    public static String getSample() {
        return null;
    }
    public static String getRandomName() {
        System.out.println("running!!");
        return "euijin";
    }
}

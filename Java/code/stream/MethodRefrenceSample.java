package stream;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;

interface MakeString {
    String fromBytes(char[] chars);
}

public class MethodRefrenceSample {
    public static void main(String[] args) {
        MethodRefrenceSample sample = new MethodRefrenceSample();
        String[] stringArray = {"요다", "만두", "건빵"};
        sample.staticRefrence(stringArray);
        sample.objectReference(stringArray);
        sample.createInstance();
    }

    private void createInstance() {
        MakeString makeString = String::new;
        System.out.println("makeString = " + makeString);
        char[] chars = {'j','a','v','a'};
        String madeString = makeString.fromBytes(chars);
        System.out.println(madeString);
    }

    private void objectReference(String[] stringArray) {
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        Arrays.sort(stringArray, (ele, ele2) -> ele.compareToIgnoreCase(ele2));
        Arrays.asList(stringArray).stream().forEach(System.out::println);
        Arrays.asList(stringArray).stream().forEach(ele -> System.out.println(ele));
    }

    private static void printResult(String value) {
        System.out.println(value);
    }

    private void staticRefrence(String[] stringArray) {
        Stream.of(stringArray).forEach(MethodRefrenceSample::printResult);
    }
}

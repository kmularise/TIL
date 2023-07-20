package stream.lazy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamLazyTest {
    public void loopService() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> filteredIntegers = filter(integers);
        System.out.println("filteredIntegers = " + filteredIntegers);
    }

    public void streamService() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> filteredIntegers = streamFilter(integers);
    }

    private List<Integer> streamFilter(List<Integer> integers) {
        System.out.println("start = " + LocalDateTime.now());
        List<Integer> newIntegers = integers.stream()
                .filter(integer -> {
                    System.out.println("integer = " + integer);
                    return integer % 3 == 0;
                })
                .map(integer -> integer % 10)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("end = " + LocalDateTime.now());
        return newIntegers;
    }

    private List<Integer> filter(List<Integer> integers) {

        List<Integer> result = new ArrayList<>();

        System.out.println("start = " + LocalDateTime.now());
        int count = 0;
        for (Integer integer : integers) {
            System.out.println("integer = " + integer);
            if (integer % 3 == 0 && count++ < 3) {
                result.add(integer * 10);
            }
        }

        System.out.println("end = " + LocalDateTime.now());
        return result;
    }

    public static void main(String[] args) {
        StreamLazyTest streamLazyTest = new StreamLazyTest();
        streamLazyTest.loopService();
        streamLazyTest.streamService();
    }

}

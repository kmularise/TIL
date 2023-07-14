package change8;

import java.util.StringJoiner;

public class TestJoinerSample {
    public static void main(String[] args) {
        TestJoinerSample testJoinerSample = new TestJoinerSample();
        String[] strings = new String[] {"Kim", "Eui", "Jin"};
        testJoinerSample.joinString(strings);
    }

    private void joinString(String[] strings) {
        StringJoiner joiner = new StringJoiner(".", "[", "]");
        for (String word : strings) {
            joiner.add(word);
        }
        System.out.println("joiner = " + joiner);
    }
}

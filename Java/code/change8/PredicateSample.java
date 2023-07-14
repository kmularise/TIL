package change8;

import java.util.function.Predicate;

public class PredicateSample {
    public static void main(String[] args) {
        PredicateSample sample = new PredicateSample();
        Predicate<String> predicateLength5 = (a) -> a.length() > 5;
        Predicate<String> predicateContains = (a) -> a.contains("God");
        Predicate<String> predicateStart = (a) -> a.startsWith("God");

        String godOfJava = "GodOfJava";
        String goodJava = "GoodJava";

        sample.predicateTest(predicateLength5, godOfJava);
        sample.predicateTest(predicateLength5, goodJava);

        sample.predicateNegate(predicateLength5, godOfJava);
        sample.predicateNegate(predicateLength5, goodJava);

        sample.predicateAnd(predicateStart, predicateContains, goodJava);
        sample.predicateAnd(predicateStart, predicateContains, godOfJava);

        sample.predicateOr(predicateStart, predicateContains, goodJava);
        sample.predicateOr(predicateStart, predicateContains, godOfJava);

    }

    private void predicateAnd(Predicate<String> p1, Predicate<String> p2, String data) {
        System.out.println(p1.and(p2).test(data));
    }

    private void predicateOr(Predicate<String> p1, Predicate<String> p2, String data) {
        System.out.println(p1.or(p2).test(data));
    }

    private void predicateNegate(Predicate<String> p, String data) {
        System.out.println(p.negate().test(data));
    }

    private void predicateTest(Predicate<String> p, String data) {
        System.out.println(p.test(data));
    }


}

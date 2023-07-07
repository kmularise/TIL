package generic;

import java.util.HashSet;

public class CarWildcardSample {
    public static void main(String[] args) {
        CarWildcardSample sample = new CarWildcardSample();
        sample.callBoundedWildcardMethod();
    }
    public void callBoundedWildcardMethod() {
        WildcardGeneric<Car> carWildcardGeneric = new WildcardGeneric<>();
        carWildcardGeneric.setWildcard(new Bus("3434343"));
        boundedWildcardMethod(carWildcardGeneric);
    }

    public void boundedWildcardMethod(WildcardGeneric<? extends Car> c) {
        Car value = c.getWildcard();
        System.out.println("value = " + value);
    }
}

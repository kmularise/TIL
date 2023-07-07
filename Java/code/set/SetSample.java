package set;

import java.util.*;

public class SetSample {
    public static void main(String[] args) {
        SetSample sample = new SetSample();
        String[] strings = new String[]{
                "Tico", "Sonata", "BMW", "Benz",
                "Lexus", "Mustang", "Grandeure",
                "The Beetle", "Mini Cooper", "i30",
                "BMW", "Lexus", "Carnibal", "SM5",
                "SM7", "SM3", "Tico"
        };
        System.out.println(sample.getCarKinds(strings));


    }

    private int getCarKinds(String[] strings) {
        if (strings == null) return 0;
        if (strings.length == 1) return 1;
        Set<String> carSet = new HashSet<>();
        for (String car : strings) {
            carSet.add(car);
        }
        for (String car : carSet) {
            System.out.println("car = " + car);
        }
        Iterator<String> iterator = carSet.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }
        return carSet.size();
    }
}

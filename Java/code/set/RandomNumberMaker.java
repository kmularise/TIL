package set;

import java.util.HashMap;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.Hashtable;
import java.util.Random;

public class RandomNumberMaker {
    public static void main(String[] args) {
        RandomNumberMaker randomNumberMaker = new RandomNumberMaker();
        for (int i = 0 ;i < 10; i++) {
            HashSet<Integer> sixNumber = randomNumberMaker.getSixNumber();
            System.out.println("sixNumber.toString() = " + sixNumber.toString());
        }
    }
    
    public HashSet<Integer> getSixNumber() {
        HashSet<Integer> integers = new HashSet<>();
        Random random = new Random();
        while (integers.size() < 6) {
            int tempNumber = random.nextInt(45);
            integers.add(tempNumber);
        }
        return integers;
    }
}

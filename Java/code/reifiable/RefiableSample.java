package reifiable;

import java.util.ArrayList;
import java.util.Collections;

public class RefiableSample {
    public static void main(String[] args) {
        RefiableSample sample = new RefiableSample();
        sample.addData();
    }
    public void addData() {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> newDataList = new ArrayList<>();
        newDataList.add("a");
        Collections.addAll(list, newDataList);
        System.out.println("list = " + list);
    }
}

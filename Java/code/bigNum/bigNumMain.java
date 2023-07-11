package bigNum;
import java.util.LinkedList;
import java.util.Scanner;

class MyBigInteger {
    private static final int UNIT_LENGTH = 5;
    private static final int QUOTIENT = (int) Math.pow(10, UNIT_LENGTH);
    private int sign = 1;
    private int start = 0;
    private int[] numbers;


    public MyBigInteger(String data) {
        intiailize(data);
    }

    private MyBigInteger(int[] numbers, int sign) {
        this.numbers = numbers;
        this.start = sign == 1 ? 1 : 0;
        this.sign = sign;
    }

    public MyBigInteger add(MyBigInteger val) {
        if (val.sign == 1 && this.sign == 1) {
            return addPositiveNumbers(val, 1);
        } else if (val.sign == -1 && this.sign == - 1) {
            return addPositiveNumbers(val, -1);
        }
        return null;
    }

    private MyBigInteger addPositiveNumbers(MyBigInteger val, int sign) {
        int myLength = this.numbers.length;
        int otherLength = val.numbers.length;
        int before;
        int remainder = 0;
        LinkedList<Integer> targets = new LinkedList<>();
        for (int i = 0; i < Math.max(myLength, otherLength); i++) {
            if (i < Math.min(myLength, otherLength)) {
                before = this.numbers[myLength - 1 - i] + val.numbers[otherLength - 1 - i] + remainder;
            } else {
                if (i >= otherLength) {
                    before = this.numbers[myLength - 1 - i] + remainder;
                } else {
                    before = val.numbers[otherLength - 1 - i] + remainder;
                }
            }
            targets.addFirst(before % QUOTIENT);
            remainder = before / QUOTIENT;
        }
        if (remainder > 0) {
            targets.addFirst(remainder);
        }
        int[] newNumbers = targets.stream().mapToInt(ele -> ele).toArray();
        return new MyBigInteger(newNumbers, sign);
    }

    private void printArray() {
        for (int number : numbers) {
            System.out.println("number = " + number);
        }
    }

    private void intiailize(String data) {
        if (data.charAt(0) == '-') {
            sign = - 1;
            start++;
        }
        if (data.charAt(0) == '+') {
            start++;
        }
        int size = (data.length() - start - 1) / UNIT_LENGTH + 1;
        numbers = new int[size];
        int arrayIdx = size - 1;
        for (int idx = data.length() - 1; idx >= start ; idx-= UNIT_LENGTH) {
            numbers[arrayIdx] = Integer.valueOf(data.substring(Math.max(start, idx - UNIT_LENGTH + 1),idx + 1));
            arrayIdx--;
        }
    }

    @Override
    public String toString() {
        String result = "";
        if (sign == -1) {
            result += "-";
        }
        for (int idx = 0; idx < numbers.length; idx++) {
            String temp = String.valueOf(numbers[idx]);
            if (idx != 0) {
                for (int zeroCount = 0; zeroCount < UNIT_LENGTH - temp.length(); zeroCount++) {
                    result += "0";
                }
            }
            result += temp;
        }
        return result;
    }
}

public class bigNumMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] bigNumbers = sc.nextLine().trim().split(" ");
        MyBigInteger number = new MyBigInteger(bigNumbers[0]);
        MyBigInteger number2 = new MyBigInteger(bigNumbers[1]);
        MyBigInteger result = number.add(number2);
        System.out.println(result.toString());
    }
}

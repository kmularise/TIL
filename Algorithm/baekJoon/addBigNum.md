# 10757번 - 큰 수 A+B
* BigInteger의 더하기 기능을 직접 구현해보기
* 큰 수를 String으로 받으면 숫자 5개씩 잘라서 int 배열에 담았다. 제일 오른쪽의 숫자부터 순회하였고, 남은 숫자가 5개 미만인 경우에는 5개 미만만 배열에 저장하였다.
* 내가 만든 클래스의 경우에는 메모리를 제외하고 생각하면, 최대 $10^{5 * (2^{31} - 1)} - 1$ 정도까지 더하기 지원이 가능할 거 같다. 하지만 이 정도로 되면 메모리가 터질 거 같다.
* 큰 수 String에서 int 배열로 바꾸고, int 배열에서 다시 숫자로 이루어진 String으로 바꿀 때 예외 처리할 부분이 있었다.
    * 각각의 int 배열에 들어간 숫자의 자릿수가 5가 아닌 경우에 부족한 만큼 0을 붙여줘야 했다.
    * 더하기 연산 후 두 배열의 요소를 더한 결과 10^5 승 이상이 되면 그 점은 다음 왼쪽 숫자 칸에 반영을 해줘야 했다.
* 비트 연산을 이용해봤어도 좋았을 거 같으나 이 방법을 이용하려면 5개씩 숫자를 자르는 방식 말고 큰 숫자를 2의 거듭승으로 나눈 나머지를 계속해서 int 배열에 넣어줘야 하는데, 숫자 String을 int 배열로 바꿀 때 까따로울 거 같아서 10진법 수를 그대로 이용하였다.

```java
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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] bigNumbers = sc.nextLine().trim().split(" ");
        MyBigInteger number = new MyBigInteger(bigNumbers[0]);
        MyBigInteger number2 = new MyBigInteger(bigNumbers[1]);
        MyBigInteger result = number.add(number2);
        System.out.println(result.toString());
    }
}
```
# 67. Add Binary
https://leetcode.com/problems/add-binary/
* 백준 더하기 문제와 방식은 거의 비슷하다.
* 더하기만 구현하면 된다면, 더한 값을 int 배열로 저장하기 보다는 바로 String으로 바꾸는 게 속도 측면이나, 메모리 측면에서 더 효율적일 거 같다.
* 비교나 곱하기 같은 다른 연산이 들어간다면, int 배열로 저장하는게 더 효율적일 수도 있겠지만, 그 부분은 안해봐서 모르겠다.

```java
class MyBigInteger{
    private static final int MASK = 26;
    private static final int QUOTIENT = 1 << MASK;
    private int[] numbers;

    private int getNumber(String target) {
        int result = 0;
        for (int i = 0 ; i < target.length() ; i++) {
            result += target.charAt(i) - '0';
            if (i == target.length() - 1) {
                break ;
            }
            result = (result << 1);
        }
        return result;
    }

    public MyBigInteger(String data) {
        int size = (data.length() - 1) / MASK + 1;
        numbers = new int[size];
        int arrayIdx = size - 1;
        for (int idx = data.length() - 1; idx >= 0 ; idx-= MASK) {
            numbers[arrayIdx] = getNumber(data.substring(Math.max(0, idx - MASK + 1),idx + 1));
            arrayIdx--;
        }
    }
    private MyBigInteger(int[] numbers) {
        this.numbers = numbers;
    }

    private void printArray() {
        for (int number : numbers) {
            System.out.println(number);
        }
        System.out.println("end");
    }

    public MyBigInteger add(MyBigInteger val) {
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
        return new MyBigInteger(newNumbers);
    }

    private String getNumberString(int target) {
        if (target == 0) {
            return String.valueOf(0);
        }
        String temp = "";
        while (true) {
            if (target == 0) break ;
            temp = String.valueOf(target % 2) + temp;
            target = target >> 1;
        }
        return temp;
    }

    public String toString() {
        String result = "";
        for (int idx = 0; idx < numbers.length; idx++) {
            String temp = getNumberString(numbers[idx]);
            if (idx != 0) {
                for (int zeroCount = 0; zeroCount < MASK - temp.length(); zeroCount++) {
                    result += "0";
                }
            }
            result += temp;
        }
        return result;
    }
}

class Solution {
    public String addBinary(String a, String b) {
        MyBigInteger aNum = new MyBigInteger(a);
        MyBigInteger bNum = new MyBigInteger(b);
        MyBigInteger addNum = aNum.add(bNum);
        return addNum.toString();
    }
}
```
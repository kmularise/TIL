# lv1. 같은 숫자는 싫어
* 아주 기본적인 문제
* 큐는 스택이든 덱이는 데이터가 없을 때 예외 처리를 까먹지 말자.
* 오히려 int[] 배열 어떻게 바꾸는지 찾는 게 오래 걸렸다.
```java
import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> deque = new LinkedList();
        for (int number : arr) {
            if (deque.isEmpty() || number != deque.getLast()) {
                deque.add(number);
            }
        }
        return deque.stream().mapToInt(Integer::intValue).toArray();
    }
}
```
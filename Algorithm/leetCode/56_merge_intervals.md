https://leetcode.com/problems/merge-intervals/
* 시간 복잡도 : O(n)
* 처리하기 어려웠던 케이스 : [[1, 1], [2, 2]] 와 같이 시작과 동시에 끝나는 경우, 제한된 테케만 주는 곳이면 통과하지 못했을 거 같다.
* 이런 유형의 문제든 종종 보이는 거 같다. 익숙해지면 좋을 거 같다.
* 자바식으로 코테를 푼다는 건 정말 어려운 거 같다. start와 end도 1~10000범위이기 때문에 interval 개수도 10000개 이하 조건이라 배열 크기를 정해두고 풀었다. 배열 크기를 줄일려고 하면 인터벌 요소의 최댓값을 구하면 배열 크기가 줄어들거 같긴 하다.
```java
import java.util.*;

class Solution {
    private int[] numbers = new int[10004];
    public int[][] merge(int[][] intervals) {
        int[] changes = new int[10004];
        int[] negatives = new int[10004];
        for (int i = 0; i < 10004; i++) changes[i] = 0;
        for (int i = 0; i < 10004; i++) negatives[i] = 0;
        for (int[] interval : intervals) {
            changes[interval[0]] += 1;
            negatives[interval[1]] -= 1;
        }
        int start;
        int end;
        int val;
        val = 0;
        start = -1;
        List<int[]> results = new ArrayList<>();
        for (int i = 0; i < 10004; i++) {
            if (val == 0 && changes[i] > 0) {
                start = i;
            }
            val += changes[i];
            if (start != - 1 && val + negatives[i] == 0) {
                end = i;
                int[] newInterval = new int[2];
                newInterval[0] = start;
                newInterval[1] = end;
                results.add(newInterval);
                start = -1;
            }
            val += negatives[i];
        }
        return results.stream().toArray(int[][]::new);
    }
}
```
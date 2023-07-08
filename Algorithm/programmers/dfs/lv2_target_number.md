### 타겟 넘버 
* dfs 재귀방식으로 풀었음
* 숫자의 개수가 20개 이하라 최대 2^20 경우의 수라 가능한 풀이 같음
```java
class Solution {
    private int count = 0;
    private int direction[] = {-1, 1};
    private int target;
        
    private void dfs(int[] numbers, int prev, int idx) {
        if (idx == numbers.length)
        {
            if (prev == this.target) count++;
            return ;
        }
        for (int ele : direction) {
            prev += ele * numbers[idx];
            dfs(numbers, prev, idx + 1);
            prev -= ele * numbers[idx];
        }
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        this.target = target;
        dfs(numbers, 0, 0);
        return count;
    }
}
```
# level 3 단어 변환
* 다른 풀이에서는 단어 하나를 Node라는 클래스 만들고 풀던데, 그 방법도 해봐야 겠다.
* 이 문제에서 HashMap 굳이 쓸 필요가 있었을까 하는 생각이 든다. 리스트를 쓸려면 하나의 단어에 해당하는 string을 인덱스로 변환해줘야 하는 작업이 필요할 거 같아서 HashMap을 쓰긴 했다.
* 어차피 Deque은 인터페이스라 Deque 자료형으로 선언하지 않고 bfs 알고리즘을 쓰기 때문에 Queue로 선언하는게 더 나았을 거 같다. 

```java
import java.util.*;

class Solution {
    HashMap<String, List<String>> graph;
    int len;
    static final int INF = 1 << 30;
    
    private boolean isConnected(String target, String compared) {
        int count = 0;
        for (int i = 0; i < len ; i++) {
            if (target.charAt(i) != compared.charAt(i)) {
                count++;
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }
    
    private int getMin(String start, String target) {
        HashMap<String, Integer> distance = new HashMap<>();
        Deque<String> deque = new LinkedList<>();
        deque.add(start);
        distance.put(start, 0);
        int idx = 0;
        while (!deque.isEmpty()) {
            String current = deque.removeFirst();
            for (String next : graph.get(current)) {
                int comparedDist = distance.getOrDefault(next, INF);
                if (comparedDist > distance.get(current) + 1) {
                    distance.put(next, distance.get(current) + 1);
                    deque.add(next);
                }
            }
            idx++;
        }
        
        return distance.getOrDefault(target, 0);
    }
    
    public int solution(String begin, String target, String[] words) {
        graph = new HashMap<>();
        len = target.length();
        int isValid = 0;
        for (String word : words) {
            if (word.equals(target)) {
                isValid = 1;
            }
            graph.put(word, new ArrayList<>());
        }
        graph.put(begin, new ArrayList<>());
        if (isValid == 0) {
            return 0;
        }
        for (int i = 0 ; i < words.length ; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isConnected(words[i], words[j])) {
                    graph.get(words[i]).add(words[j]);
                    graph.get(words[j]).add(words[i]);
                }
            }
        }
        for (int i = 0 ; i < words.length ; i++) {
            if (isConnected(words[i], begin)) {
                graph.get(begin).add(words[i]);
            }
        }
        int answer = getMin(begin, target);
        
        return answer;
    }
}
```
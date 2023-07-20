# 스트림(Stream)
[Java 8 변화](./Java8Change.md)에 이어 추가적인 학습을 진행하였다.
* Oracle API 문서에 의하면 스트림은 다음과 같다.
> A sequence of elements supporting sequential and parallel aggregate operations
* Modern Java in Action에서는 데이터 처리 연산을 지원하도록 소스에서 추출된 요소라고 한다.
________________________
## 스트림의 특징
* 편리한 방식으로 데이터에 대한 대량 작업을 수행할 수 있다.
* 데이터를 저장하거나 원본을 변경하지 않는다.
* 파이프라인에서 함수형 프로그래밍 스타일의 연산을 지원한다.
* 스트림을 쓰기 전에는 외부반복을 사용했지만 스트림을 통해서 내부반복을 사용하게 되었다.
* 스트림은 특정 연산자를 사용할 때 여러 개의 조건이 중첩된 상황에서 값이 결정나면 불필요한 연산을 진행하지 않고 조건문을 빠져나와 실행속도를 높인다._________________________________
## 스트림의 주요 장점
### lazy evaluation
* 연산이 실제로 필요해할 때만 연산을 시작하는 것
* 필요하지 않은 연산을 하지 않는 것이 가능하게 된다.
* Stream은 연산을 곧바로 수행하지 않는다. 마지막에 어떤 연산이 필요한가를 판단한 후 연산을 시작한다.
> eager evaluation : 절차를 진행하다가 연산 관련 명령을 인지하면 바로 연산을 시작하는 것

[lazy evaluation에 관한 스트림 실습](./code/stream/lazy/)

### 병렬 처리 가능
* 병렬 처리 시 주의할 점
    * 작업을 병렬로 실행할 수 있는지 주의해야 한다.
        ```java
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Integer sumResult = numbers.parallelStream().reduce(5,Integer::sum);
        ```
        위의 코드와 같은 경우에는 쓰레드 1개당 5를 더하기 때문에 원래 의도와는 다른 값이 나올 수 있다.
        ```java
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Integer sumResult = numbers.parallelStream().reduce(0,Integer::sum) + 5;
        ```
        따라서 이 경우 외부에서 5를 더해줘야 한다.
    * 성능에 미치는 영향 : 병렬처리를 한다고 무조건 성능이 좋아지는 것은 아니다.<br>
    병렬 처리는 멀티 코어를 다 사용할 수 있다는 점에서 유익하다. 하지만 memory locality, 멀티 쓰레드 관리, 쓰레드 작업 분할(spliting, fork), 쓰레드 작업 병합(merging, join)에 드는 오버헤드도 고려해야 한다.
        * 분할 비용(splitting cost)<br>
        쓰레드를 작업을 분할하는데 드는 시간은 병렬 처리를 가능하게 하려면 반드시 수반되는 비용이다.
        * 병합 비용(merging cost)<br>
        병렬 작업을 모두 마치면 각 결과들을 합쳐야 한다.
        * memory locality<br>
        현대 컴퓨터는 자주 사용되는 데이터들을 프로세서가 접근하기 쉽게 하기 위해서 다양한 레벨의 캐시를 사용한다. 선형 메모리 접근 패턴이 감지되면, 하드웨어는 다음 줄의 데이터가 미리 필요할 것이라 가정하여 미리 다음 줄의 데이터를 가져온다. 병렬처리는 프로세서 코어를 유용한 작업을 바쁘게 수행하도록 할 때 이점이 있다. 캐시 미스를 기다리는 것은 유용한 작업이 아니므로 메모리에서 얼마나 읽어들일 수 있는지를 고려해야 한다.
> 참고 : memory locality<br>
>시간에 따른 Locality(Temporal Locality) : 가장 최근에 읽어온 데이터는 다시 읽어올 때도 빠르게 접근할 수 있다는 것<br>
>공간에 따른 Locality(Spatial Locality) : 한 번 참고한 영역은 다음에 참고할 때도 빠르게 접근할 수 있다는 것<br>

## ForkJoinPool 추가 - commonPool, customPool
Oracle 자바 API에 의하면 다음과 같다.
```
ForkJoinPool은 기본적으로 commonPool를 사용할 수 있으면 대부분의 애플리케이션에 적합하다. commonPool은 명시적으로 custom Pool을 지정하지 않은 모든 ForkJoin 작업에 사용된다. commonPool을 사용하면 일반적으로 자원 사용량이 줄어든다. 해당 쓰레드는 사용하지 않는 기간 동안 천천히 회수되고 이후 사용 시 복원된다. commonPool의 쓰레드 수(parallelism)는 사용가능한 프로세서 수(프로세서 코어 수에서 1개를 뺀 것)이다.
```

별도로 사용자 지정 ForkJoinPool이 필요한 경우 customPool을 따로 지정할 수는 있다.

    

## 참고자료
* https://www.baeldung.com/java-when-to-use-parallel-stream
* https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
* memory locality 관련
https://talkingaboutme.tistory.com/entry/Study-Memory-Hierarchy-1
* https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html
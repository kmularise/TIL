# 목차 : 동시성
* item78 : 공유중인 가변 데이터는 동기화해 사용해라
* item79 : 과도한 동기화는 피하라
* item80 : 스레드보다는 실행자, 태스크, 스트림을 애용하라
* item81 : wait와 notify보다는 동시성 유틸리티를 애용하라
* item82 : 스레드 안전성 수준을 문서화하라
* item83 : 지연 초기화는 신중히 사용하라
* item84 : 프로그램의 동작을 스레드 스케줄러에 기대지 말라

# 공유중인 가변 데이터는 동기화해 사용하라

## 스레드 동기화의 중요한 기능
* 배타적 실행 : 한 스레드가 변경 중이라서 상태가 일관되지 않을 때, 다른 스레드가 보지 못하게 막는 용도
    1. 한 객체가 일관된 상태를 가지고 생성됨
    2. 객체에 접근하는 메소드는 그 객체에 락을 건다.
    3. 락을 건 메소드는 객체의 상태를 확인하고 필요하면 수정한다.
    -> 즉, 객체를 하나의 일관된 상태에서 다른 일관된 상태로 변화시킨다.
* 가시성, 스레드 간 통신 : 한 스레드가 만든 변화를 다른 스레드에서 확인할 수 있다. 구체적으로 동기화된 메소드나 블록에 들어간 스레드는 같은 락의 보호 안에 수행된 이전 수정의 최종 결과를 볼 수 있다.
    * 참고로 한 스레드가 만든 변화가 다른 스레드에게 언제 어떻게 보이는지는 자바의 메모리 모델에 의해 규정된다.

## synchronized 키워드
* synchronized 키워드는 해당 메소드나 블록을 한번에 한 스레드씩 수행하도록 보장한다.
* blocking 사용
    * 특정 스레드가 해당 블록 전체에 락을 걸면, 해당 락에 접근하는 스레드들은 blocking 상태에 들어간다.
    * blocking 상태에 들어가면 아무 작업도 하지 못한 채 자원을 낭비한다. 또한 blocking 상태의 스레드를 ready 혹은 running 상태로 변경하기 위해 시스템의 자원을 사용해야 한다.
    * 따라서 성능 저하가 발생한다.
* 가시성 해결 : synchronized 블록에 진힙하기 전에 CPU캐시 메모리와 메인 메모리 값을 동기화하여 가시성을 해결한다.

## volatile
* 항상 가장 최근에 기록된 값을 읽는 것을 보장한다.
* 스레드 동기화의 기능 중 배타적실행(원자성)은 지원하지 않고, 가시성, 스레드간 통신만 지원한다.

[예시 : 잘못된 코드 - 동기화가 필요하다.]
```java
public class Counter {
    private volatile int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```
* 증가 연산자에서 문제가 생긴다. 코드 상으로는 하나의 연산이지만 실제로는 count 필드에 두번 접근한다. 먼저 값을 일고, 그런 다음 1 증가한 새로운 값을 저장하는 것이다. 만약 두 번째 스레드가 이 두 접근 사이를 비집고 들어와 값을 읽어가면 첫 번째 스레드와 똑같은 값을 돌려 받게 된다.

## java.util.concurrent
* 동시성 기능 지원
    * 고수준 편의 기능 제공 : 멀티스레드 프로그래밍 작업을 단순화
    * 저수준 요소 제공 : 고수준 개념을 직접 구현할 수 있도록 지원

## java.util.concurrent.atomic 패키지의 AtomicLong
* java.util.concurrent.atomic 패키지 : 락 없이도(lock-free; 락-프리) 스레드 안전한 프로그래밍을 지원하는 클래스들이 담겨 있다.
* 가시성(스레드 간 통신) 뿐만 아니라 배타적 실행(원자성)까지 지원한다.

volatile 키워드에서 증가연산자에서 발생했던 문제점을 해결할 수 있다. volatile에서 언급했던 코드를 AtomicLong을 이용하면 다음과 같이 수정하여, 배타적 실행을 가능하게 할 수 있다.
```java
public class Counter {
    private AtomicLong count = new AtomicLong();
    public void increment() {
        count.getAndIncrement();
    }
    
    public long getCount() {
        return count.get();
    }
}
```
성능도 synchronized로 처리했을 때 보다 우수하다. 그 이유는 non-blocking이 가능하므로 blocking 방식인 synchronized 보다 성능상 이점이 있기 때문이다.

## 데이터 레이스와 데드락 등 문제들에 대한 해결책
* 가장 좋은 방법은 가변 데이터를 공유하지 않는 것.
    * 불변 데이터만 공유하거나 아무것도 공유하지 않도록 한다.
    * 가변 데이터는 단일 스레드에서만 사용하는 것이 좋다.
* 멀티 스레드 환경에서 한 스레드가 데이터를 수정한 후에 다른 스레드에 공유할 때는 해당 객체에서 공유하는 부분만 동기화해도 된다.

## 참고
* Package java.util.concurrent.atomic : https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html
* atomic과 CAS 알고리즘 : https://steady-coding.tistory.com/568

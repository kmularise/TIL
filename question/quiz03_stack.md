## 스택이란 무엇일까?
스택이란 나중에 들어온 요소가 먼저 나오는 선형 자료구조이다. 즉 LIFO(last-in first-out, 후입선출)을 만족한다. 여기서 선형 자료구조란 데이터들 간의 앞뒤 관계가 1:1의 선형관계로 되어 있는 자료구조이다.  

스택을 구현하려면 일반적으로 다음과 같은 메소드를 구현해야 한다.
* push() : 데이터 삽입
* pop() : 가장 위쪽에 있는 데이터 즉, 가장 나중에 넣은 데이터 꺼내서 반환
* peek() : 가장 위쪽에 있는 데이터 반환
* size() : 스택에 있는 데이터의 개수
* isEmpty() : 스택이 비어있는지 여부

## 자바 메모리 영역 중 스택에도 스택 자료구조가 활용된다.
그렇다면 


그렇다면 스택은 컴퓨터 어떤 부분에서 사용될까?
1. 스택 영역에서의 함수 호출 과정
2. 알고리즘 DFS, 재귀함수
3. 

# Java 에서 제공하는 java.util.Stack 클래스는 디자인이 잘못되어, 보통의 엔터프라이즈 환경에서는 잘 사용하지 않는다. 왜 그럴까?

## 엔터프라이즈 환경에서 java.util.Stack 클래스를 잘 사용하지 않는 이유
Java 에서 제공하는 java.util.Stack 클래스는 실제 서비스 환경에서는 잘 사용하지 않는다고 한다. Java 11버전의 소스코드를 살펴보면서 그 이유를 생각해 보았다.

우선 java.util.Stack은 클래스이며 Deque이나 Queue와 같은 다른 자료구조처럼 인터페이스가 아니고 클래스로 되어 있고, Vector 클래스를 상속한다. Stack 클래스를 사용하지 않는 대부분의 이유가 java.util.Stack의 이러한 구조적인 문제로 발생한다.

첫번째 문제점은 스택에서 구현하지 않아도 되는 기능도 사용할 수 있다는 점이다. Stack은 Vector를 상속받기 때문에, Vector의 모든 메소드를 사용할 수 있다. 이는 의도하지 않은 버그나 성능저하를 일으킬 수 있다.

초보 개발자가 java.util.Stack에서 사용할 수 있는 메소드를 찾다가, 다음과 같이 첫번째 요소를 지우는 방법을 적용시켰다고 하자.
```java
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < 10000 ; i++) {
        stack.push(i);
    }
    for (int i = 0; i < 6000; i++) {
        stack.remove(0);
    }
```
잘 작동하기는 하나 속도 측면에서 비효율적이다. 일단 remove(int index)의 경우, Vector에서 구현된 메소드를 사용하게 되는데, 이는 스택에서 필요한 기능은 아니다. 삭제가 빈번하게 일어나는 경우, 연결리스트를 구현체로 이용한다면 좀 더 빠른 속도로 같은 작업을 처리할 수 있을 것이다. 

```java
    Deque<Integer> list = new LinkedList<>();
    for (int i = 0; i < 10000 ; i++) {
        list.add(i);
    }
    for (int i = 0; i < 6000; i++) {
        list.removeFirst();
    }
```
Stack이 인터페이스였다면, remove(int index)와 같은 불필요한 메소드는 아예 사용하지 못하게끔 방지할 수 있고, 비즈니스 로직에 맞춰서 구현체도 선택할 수 있었을 것이다.

두번째 문제점은 상위클래스 Vector의 구현에 Stack의 동작이 의존한다는 점이다.
그 증거로 java.util.Stack의 메소드가 Thread safe한지 바로 알아보기 어려운데 그 이유는 Thread safe인지 나타내는 부분이 Vector 구현부에 의존하기 때문이다.
```java
    public E push(E item) {
        addElement(item);

        return item;
    }
```
다음은 Stack의 메소드 중 push() 메소드이다. 언뜻 보면 이 메소드가 Thread safe하지 않은 것처럼 보인다. 하지만 addElement라는 Vector에서 구현된 메소드를 보면 
```java
    public synchronized void addElement(E obj) {
        modCount++;
        add(obj, elementData, elementCount);
    }
```
synchronized를 써서 메소드 블록에 쓰레드 1개만 접근할 수 있기 때문에 Thread safe함을 알 수 있다. Vector라는 내부 구조를 살펴봐야 Thread safe함을 알 수 있기 때문에 캡슐화가 되지 않는다. 만약에 Vector의 addElement 메소드가 synschronized 처리가 되어 있지 않았다면, Stack의 push 메소드도 Thread safe 하지 않았을 것이다. 또한 만약 Vector에서 addElement의 구현이 바뀌게 되면, Stack 클래스 또한 영향을 받게 된다. Stack 클래스를 사용하기 위해서는 Vector 내부 구조까지 봐야하는 문제가 생기게 되는 것이다.

## 그럼에도 불구하고 java.util.Stack이 라이브러리에 계속 남아있는 이유는?
Vector는 ArrayList와 동일한 구조를 가지고 배열의 크기가 늘어나고, 줄어듬에 따라 자동으로 크기가 조절이 된다. 이는 Stack의 LIFO를 고려한다면, Vector에 속해서는 안된다. 삽입과그렇지만 자바의 하위 호환성을 위해서 상속관계를 계속 유지하고 있다.

## Stack 클래스 사용 대신 어떤 방법을 써야할까?
자바 공식 API에 의하면 다음과 같이 나와 있다.
```
A more complete and consistent set of LIFO stack operations is provided by the Deque interface and its implementations, which should be used in preference to this class. For example:

   Deque<Integer> stack = new ArrayDeque<Integer>();
```
즉, 좀 더 완전하고 일관된 Stack의 LIFO 연산 집합을 Deque 인터페이스와 그 구현체들에서 제공하고 있으니, Stack 클래스보다는 Deque과 Deque 구현체들을 사용하라고 되어 있다.

덱(Deque)도 선형 자료구조로 맨 앞과 맨 뒤의 데이터의 삽입과 삭제가 가능한 선형 자료구조이다. 즉, 자료구조 스택의 기능을 확장한 자료구조이다. API에서 제안한 방식을 채택하면, 사용자가 비즈니스 로직에 맞게 구현방식을 선택할 수 있고, 구현체가 클래스가 아닌 인터페이스를 상속받아서 구현체 말고 다른 클래스의 내부구조를 살펴보지 않아도 되기 때문에 캡슐화가 보장된다는 장점이 있다. 



첫번째로는 Stack의 각 메소드가 Thread safe한지 알아보기 어렵기 때문이다. Stac






첫번째 이유는 java.util.Stack은 Thread safe 하지 않기 때문이다. - Thread safe하다. 다만 Vector를 상속받아 알아보기 어려웠을 뿐
두번째 이유는 java.util.Stack은 클래스로 되어 있어 다른 클래스가 Stack을 상속 시 캡슐화와 확장가능성이 충족되지 않기 때문이다.
세번째 이유은 Stack은 삽입과 삭제가 많이 일어날때 활용하기 좋은 자료구조인데, Vector 클래스를 보면, 배열을 활용한다. 구현 방법을 선택할 수 없다는 것이 문제점이다. 배열은 삽입과 삭제를 할 때 O(n)이라는 시간 복잡도를 가진다. 
Stack의 기능을 고려하지 않은 채 Vector가 구현되었다고 생각한다. Stack을 쓰면 사용자가 구현 방식을 선택할 수 없다. 

그렇다면 Stack 대신에 Java 라이브러리에서 어떤 라이브러리를 사용해야 할까?
첫번째로는 Deque 

각각의 이유에 대해서 자세히 살펴보고자 한다.
우선, java.util.Stack 클래스가 Thread safe하지 않다는 점에 대해서는 



Queue나 Deque과 같은 자료구조는 인터페이스로 되어 있다.
하지만 Java에서 제공하는 java.util.Stack 클래스로 되어 있어 메소드 선언부와 구현부가 존재한다.



1. Thread safe 하지 않기 때문이다.
2. 인터페이스가 아니라 클래스라서 상속시킬 때 문제가 발생할 수 있다.
스택이란 무엇인가요?
Java 에서 제공하는 java.util.Stack 클래스는 디자인이 잘못되어, 보통의 엔터프라이즈 환경에서는 잘 사용하지 않는데요. 왜 그럴까요?


## 참고자료
* 자바의 신 v2 pg608~611

https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Stack.html



https://johngrib.github.io/wiki/jvm-stack/#fn:2-5-2

https://velog.io/@jeong11/Java-OOP-callstack

https://www.jetbrains.com/help/idea/starting-the-debugger-session.html#productivity-tips


[추가적으로 궁금해서 찾아본 것]
자바의 스택/ 스택 프레임
https://sanghoonly.tistory.com/62

자바 스택 프레임
https://johngrib.github.io/wiki/jvm-stack/#fn:2-5-2
오라클 8 공식 문서
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html

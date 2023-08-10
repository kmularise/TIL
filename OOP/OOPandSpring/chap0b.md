# 0B. 자바 8 람다와 인터페이스 스펙 변화

## 람다 도입 이유
* 멀티 코어 프로세서들이 등장하면서 병렬화 프로그래밍에 대한 필요성이 생기기 시작했음
```
빅데이터 지원 -> 병렬화 강화 -> 컬렉션 강화 -> 스트림 강화
-> 람다 도입 -> 인터페이스 명세 변경 -> 함수형 인터페이스 도입
```

## 람다란 무엇인가?
* 코드 블록 : 메소드의 인자나 반환값으로 사용
* 코드 블록을 위해, 다시 메소드를 사용하기 위해 익명 객체를 만들지 않아도 된다.

[기존 방식 코드 블록 사용 - 별도의 클래스와 객체, 메소드 생성]
```java
public class B001 {

    public static void main(String[] args) {
        MyTest mt=new MyTest();
        Runnable r=mt;
        r.run();
    }
}

class MyTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello Lambda");
    }
}
```
[기존 방식 코드 블록 사용 - 익명 객체 생성]
```java
public class B002 {

    public static void main(String[] args) {
        
        // 익명 객체 생성
        Runnable r =new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda 2");
            }
        };

        r.run();

    }
}
```
[새로운 방식의 코드 블록 사용 - 람다]
```java
public class B003 {

    public static void main(String[] args) {

        Runnable r=() -> {
            System.out.println("Hello lambda 3");
        };
        r.run();

    }
}
```
* 로직이 단 한줄로 표기되는 경우 {} 생략 가능
```java
Runnable r =()-> System.out.println("lambda 4");
```

## 함수형 인터페이스
* 함수형 인터페이스 : 추상 메소드를 하나만 갖고 있는 인터페이스
```java
public class BOO4 {

    public static void main(String[] args) {

        MyFunctionInterface m1= a-> a*a;

        int b=m1.runSomething(5);
        System.out.println(b);

    }
}

@FunctionalInterface
interface MyFunctionInterface {
    public abstract int runSomething(int count);
}
```
### 메소드 호출 인자로 람다 사용
* 람다식을 함수형 인터페이스 참조변수에 저장해서 사용 가능
* 람다식을 메소드의 인자로도 사용 가능

### 메소드 반환값으로 람다 사용

## 자바 8 API에서 제공하는 함수형 인터페이스
| 함수형 인터페이스 | 추상 메소드 |	용도 |
| ----- | ------- | ------|
| Runnable | void run() |	실행할 수 있는 인터페이스 |
| Supplier<T> | T get() | 제공할 수 있는 인터페이스 |
| Consumer | void accept(T t) |	소비(출력)할수 있는 인터페이스 |
| Function<T, R> | R apply(T t) 입력을 받아서 출력할 수 있는 인터페이스 |
| Predeicate<T> | Boolean test(T t) | 입력을 받아 참/거짓을 단정 인터페이스 |
| UnaryOperator<T> | T apply(T t) |	단항(Unary)연산을 할 수 있는 인터페이스 |

## 컬렉션 스트림에서 람다 사용
* How가 아닌 What만을 지정, 함수형 프로그래밍 장점인 선언적 프로그래밍 사용
* 의사소통 내용 자체가 그대로 코드로 구현되는 것이 장점

## 메소드 레퍼런스와 생성자 레퍼런스
* 메소드 레퍼런스
| 메소드 레퍼런스 유형 | 람다식의 인자 | 예제 |
| ----- | ------ | -------- |
| 클래스::정적메소드 | 정적 메소드의 인자가 된다. | Math::sqrt<br>num -> Math.sqrt(num)
| 인스턴스::인스턴스메소드 | 인스턴스 메소드의 인자가 된다. | Syste.out::println<br>sqrtNum -> System.out.println(sqrtNum)
| 클래스::인스턴스메소드 | 첫번쨰 인자는 인스턴스가 되고 그 다음 인자(들)는 인스턴스 메소드의 인스턴스 메소드의 인자(들)가 된다. | Integer::compareTo<br>(a, b) -> a.compareTo(b) |

* 생성자 레퍼런스
    * 기본 생성자 외의 다른 생성자가 있는 경우라면 그에 맞는 함수형 인터페이스 참조 변수를 사용해야 한다.

## 인터페이스의 디폴트 메소드와 정적 메소드
* 자바 8에서는 인터페이스가 가질 수 있는 멤버는 다음과 같다.
    * 정적 상수
    * 추상 인스턴스 메소드
    * 구체 인스턴스 메소드 - 디폴트 메소드
    * (구체) 정적 메소드
* 추상 인스턴스 메소드가 한개이면 구체 인스턴스 메소드와 정적 메소드의 개수와 관계 없이 함수형 인터페이스가 될 수 있다.

[예시] Function<T, R> : T : 함수에 대한 입력 유형, R : 함수 결과 유형
| 메소드 | 설명 | 메소드 유형 |
| ----- | ------ | ------ |
| R apply(T t) | 주어진 T 타입 매개변수 t에 주어진 로직을 적용해 R 타입 반환 | 추상 인스턴스 메소드 |
| default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) | 이 함수를 먼저 입력에 적용한 다음 그 after 함수를 결과에 적용하는 구성된 함수 반환 | 디폴트 메소드 - 구체 인스턴스 메소드 |
| default <V> Function<V,R> compose(Function<? super V, ? extends T> before)  | 먼저 before 함수를 입력에 적용한 다음 이 함수를 결과에 적용하는 구성된 함 수 반환 | 디폴트 메소드 - 구체 인스턴스 메소드 |
| static <T> Function<T, T> indentity() | 항상 입력 인수를 반환하는 함수 반환 | 정적 메소드 - 구체 정적 메소드 |

# Java 8 추가&변경
* Lambda(람다) 표현식
* Functional(함수형) 인터페이스
* Stream(스트림)
* Optional(옵셔널)
* 인터페이스의 기본 메소드(Default method)
* 날짜 관련 클래스들 추가
* 병렬 배열 정렬
* StringJoiner 추가
______________________________________
## Lambda(람다) 표현식
* 익명 클래스를 사용하면 가독성이 떨어지고 불편하다는 단점이 있다.
* 이를 보완하기 위해 람다 표현식이 만들어짐
* 람다 표현식은 인터페이스에 메소드가 하나인 것들만 적용 가능하다.<br>

    [예시]
    1. java.lang.Runnable -> 가장 많이 사용
    2. java.util.Compartor -> 빈번히 사용
    3. java.io.FileFilter
    4. java.util.concurrent.Callable
    5. java.security.PrivilegedAction
    6. java.nio.file.PathMatcher
    7. java.lang.reflect.InvocationHandler  
    <br>
* 람다 표현식은 익명 클래스로 전환이 가능하다.

### 기능적 인터페이스(Functional interface)
* 기능적 인터페이스 : 하나의 메소드만 선언
* 기능적 인터페이스에 두 개 이상의 메소드 선언을 방지하기 위해 @FunctionalInterface 사용

### 정리
* 메소드가 하나만 존재하는 인터페이스는 @FunctionalInteface로 선언할 수 있으며, 이 인터페이스를 람다 표현식으로 처리할 수 있다.
* (매개 변수 목록) -> 처리식으로 람다를 표현하며, 처리식이 한 줄 이상일 때에는 처리식을 중괄호로 묶을 수 있다.
_____________________________________
## Functional(함수형) 인터페이스 - java.util.function 패키지
### Java 8에서 제공하는 주요 Functional 인터페이스
* Predicate
* Supplier
* Consumer
* Function
* UnaryOperator
* BinaryOperator

### Predicate
test()라는 메소드가 있으며, 두 객체를 비교할 때 사용하고 boolean을 리턴한다. 추가로 and(), negate(), or()이라는 default 메소드가 구현되어 있으며, isEqual()이라는 static 메소드도 존재한다.


### Supplier
get() 메소드가 있으며, 리턴값은 generic으로 선언된 타입을 리턴한다. 다른 인터페이스들과는 다르게 추가적인 메소드는 선언되어 있지 않다.


### Consumer
accept()라는 매개 변수를 하나 갖는 메소드가 있으며, 리턴 값이 없다. 그래서 출력을 할 때처럼 작업을 수행하고 결과를 받을 일이 없을 때 사용한다.
<br>추가로 andThen()이라는 default 메소드가 구현되어 있는데, 순차적인 작업을 할 때 유용하게 사용될 수 있다.


### Function
apply() 라는 하나의 매개 변수를 갖는 메소드가 있으며, 리턴값도 존재한다. 이 인터페이스는 Function<T, R>로 정의되어 있어, Generic 타입을 두개 갖고 있다. 앞에 있는 T는 입력 타입, 뒤에 있는 R은 리턴 타입을 의미한다. 즉, 변환을 할 필요가 있을 때 이 인터페이스를 사용한다.


### UnaryOperator: A unary operator from T -> T
apply()라는 하나의 매개 변수를 갖는 메소드가 있으며, 리턴값도 존재한다. 단, 한 가지 타입에 대하여 결과도 같은 타입일 경우 사용한다.


### BinaryOperator: A binary operator from (T, T) -> T
apply()라는 두개의 매개 변수를 갖는 메소드가 있으며, 리턴 값도 존재한다. 단, 한 가지 타입에 대하여 결과도 같은 타입일 경우 사용한다.
____________________________________

## Stream(스트림)
* 연속되는 요소들의 흐름
* 배열, 콜렉션 등에서 만들어질 수 있다.

### 스트림의 구조
```java
list.stream() //스트림 생성
    .filter(x -> x > 10) //중개 연산
    .count() // 종단 연산
```
* 스트림 생성 : stream() 메소드를 호출하면 Stream 타입 리턴
* 중개 연산: 데이터를 가공할 때 사용되며 연산 결과로 Stream 타입을 리턴. 따라서 여러 개의 중간 연산을 연결할 수 있다.
* 종단 연산: 스트림 처리를 마무리하기 위해서 사용되며, 숫자값을 리턴하거나 목록형 데이터를 리턴
> 중개 연산이 반드시 있어야 하는 것은 아니다.

### 스트림 장점
* 일련의 데이터를 연속적으로 가공하는데 유용
    * 내부적으로 수행하므로 중간과정이 밖으로 드러나지 않음
        * 외부에 변수 등이 만들어지지 않음
    * 배열, 콜렉션, I/O 등을 동일한 프로세스로 가공
    * 함수형 프로그래밍을 위한 다양한 기능 제공
    * 원본을 수정하지 않음
    * 멀티쓰레딩에서 병렬처리 가능

### 메소드 참조

* 메소드 참조의 종류
| 종류 | 예 |
| ------- | -------------- |
| static 메소드 참조 | ContainingClass:::staticMethodName |
| 특정 객체의 인스턴스 메소드 참조 | containingObject ::: instanceMethodName |
| 특정 유형의 임의의 객체에 대한 인스턴스 메소드 참조 | ContainingType::methodName |
| 생성자 참조 | ClassName::new |
________________________
## Optional(옵셔널)
* 객체를 편리하게 처리하기 위해 만든 클래스
* null 처리를 간편하게 하기 위해 만들어짐
* Optional 클래스에 값을 잘못 넣으면 NoSuchElementException이 발생할 수 있음

### Optional 객체 생성 방법
* empty() : 데이터가 없는 Optional 객체 생성
* ofNullable() : null이 추가될 수 있는 상황
* of() : 반드시 데이터가 들어갈 수 있는 상황

### Optional 데이터 조회 방법
* get() : 데이터가 없을 경우 null 리턴
* orElse() : 값이 없을 경우 기본값 지정
* orElseGet() : orElse()와 같으나 Supplier<T> 인터페이스 활용
* orElseThrow() : 데이터가 없을 경우 예외 발생, Supplier<T> 인터페이스 활용

_______________________
## Default mthod
* default 키워드가 있으면 인터페이스에서 메소드를 구현할 수 있다.
* default 메소드를 만든 이유 : 하위 호환성 때문
______________________
## 날짜 관련 클래스 추가
### 값 유지

| 버전 | 패키지 | 설명 |
| ------- | -------------- | -------------- |
| 예전 버전 | java.util.Date<br>java.util.Calendar | Data 클래스는 날짜 계산을 할 수 없다. Calender 클래스는 불변 객체가 아니므로 연산 시 객체 자체가 변경되었다. | 
| Java 8 | java.time.ZonedDateTime<br>java.time.LocalDate 등 | ZonedDateTime과 LocalDate 등은 불변 객체이다.<br>모든 클래스가 연산용의 메소드를 갖고 있으며, 연산 시 새로운 불변 객체를 돌려 준다.<br>그리고 쓰레드에 안전하다. |

### 변경

| 버전 | 패키지 | 설명 |
| ------- | -------------- | -------------- |
| 예전 버전 | java.text.format.SimpleDateFormat | SimpleDateFormat는 쓰레드 안전하지도 않고 느리다. |
| Java 8 | java.time.format.DateTimeFormatter | DateTimeFormatter는 쓰레드 안전하며 빠르다. |

### 시간대
| 버전 | 패키지 | 설명 |
| ------- | -------------- | -------------- |
| 예전 버전 | java.util.TimeZone | "Asia/Seoul" 이나 "+09:00" 같은 정보를 가진다. |

### 속성 관련
| 버전 | 패키지 | 설명 |
| ------- | -------------- | -------------- |
| 예전 버전 | java.util.Calendar | Calendar.YEAR<br>Calendar.MONTH<br>Calendar.Date(또는 Calender.DAY_OF_MONTH) 등 이들은 정수(int)이다. |
| Java 8 | java.time.temporal.ChronoField<br>(java.time.temporal.TemporalField) | ChronoField.YEAR<br>ChronoField.MONTH_OF_YEAR<br>ChronoField.DAY_OF_MONTH<br>등이 enum 타입이다. |
| Java 8 | java.time.temporal.ChronoUnit<br>(java.time.temporal.TemporalUnit) | ChronoUnit.YEARS(연수) <br>ChronoUnit.MONTHS(개월)<br>ChronoUnit.DAYS(일)<br>등이 enum 타입이다. |

________________________________

## 병렬 배열 정렬(Parallel array sorting)
* Arrays 클래스의 parallelSort() 정렬 메소드 추가
    * 내부적으로 Fork-Join 사용
* sort() VS parallelSort()
    * 5000개 정도부터 parallelSort() 성능이 더 빨라짐

## StringJoiner 추가
* delimiter, prefix, suffix가 있을 경우 문자열 처리가 용이하다.
```java
    private void joinString(String[] strings) {
        StringJoiner joiner = new StringJoiner(".", "[", "]");
        for (String word : strings) {
            joiner.add(word);
        }
        System.out.println("joiner = " + joiner);
    }
```
[결과]
```
joiner = [Kim.Eui.Jin]
```
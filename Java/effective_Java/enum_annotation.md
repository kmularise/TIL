# 열거 타입과 어노테이션
자바에는 특수한 목적의 참조 타입이 두가지 있는데, 하나는 열거타입이고 다른 하나는 어노테이션이다.

# item 34
## 정수 열거 패턴(int enum pattern)의 단점
* 타입 안전을 보장할 방법이 없으며 표현력도 좋지 않다.
* 다른 종류를 의미하는 값을 보내어 동등 연산자로 비교하더라도 컴파일러는 아무런 경고 메시지를 출력하지 않는다.
* 정수 열거 패턴은 평범한 상수를 나열한 것 뿐이라 컴파일하면 그 값이 클라이언트 파일에 그대로 새겨진다. 따라서 상수의 값이 바뀌면 클라이언트도 반드시 다시 컴파일해야 한다.
* 문자열로 출력하기가 어렵다.

## 정수 열거 패턴의 변형, 문자열 상수 이용
* 하드코딩한 문자열에 오타가 있어도 컴파일러는 확인할 길이 없어 런타임 버그가 생길 수 있다.
* 문자열 비교에 따른 성능 저하도 있다.

## 열거 타입 (enum type) 이용
* 자바의 열거 타입은 C, C++, C#과 같은 다른 언어의 열거 타입과는 다르다.
* 자바의 열거타입은 완전한 형태의 클래스로 단순한 정숫값일 뿐인 다른 언어의 열거타입보다 훨씬 강력한다.
* 열거 타입은 밖에서 접근할 수 있는 생성자를 제공하지 않아서 사실상 final
* 클라이언트가 인스턴스를 직접 생성하거나 확장할 수 없으니 열거 타입 선언으로 만들어진 인스턴스들은 딱 하나씩만 존재함이 보장된다.
* 컴파일타임 타입 안정성 제공
* Object 메소드 구현, Comparable, Serializable 구현, 직렬화 형태가 웬만큼 변형을 가해도 문제없이 동작
* 열거 타입에서 상수를 하나 제거해도 제거한 상수를 참조하지 않는 클라이언트에서는 아무 영향이 없다.
* item 15 : 클라이언트에 노출해야 할 합당할 이유가 없다면 private 또는 package-private으로 선언하라가 적용된다.
* 하나의 메소드가 상수별도 다 다르게 동작 - 이런 열거 타입에서는 switch 문 대신 상수별 메소드 구현
* 전략 열거 타입 패턴 : 필드를 enum으로 두고 내부에 enum class 도입
* switch문은 열거 타입의 상수별 동작을 구현하는데 적합하지 않지만, 기존 열거 타입에 상수별 동작을 혼합해 넣을 때는 switch 문이 좋은 선택이 될 수 있다.
* 필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면 항상 열거타입을 사용하자.
* 열거 타입에 정의된 상수개수가 영원히 고정 불변일 필요는 없다. 열거타입은 나중에 상수가 추가돼도 바이너리 수준에서 호환되도록 설계되었다.

### enum source code
https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html

# item 35 : ordinal 메소드 대신 인스턴스 필드 사용
## ordinal 사용의 문제점
* 상수 선언 순서를 바꾸면 값이 바뀐다.
* 값을 중간에 비워둘 수 없다.
* 대부분 프로그래머는 ordinal을 사용할 일이 없다. ordinal은 EnumSet과 EnumMap과 같이 열거 타입 기반의 범용 자료구조에 사용될 목적으로 설계되었다고 한다.

## 해결책은 연거 타입 상수에 연결된 값은 인스턴스 필드에 저장하기
* 인스턴스 필드에 저장하자.

# item 36 : 비트 필드 대신 EnumSet
## 비트 필드
* 비트 필드를 사용하면 비트별 연산을 사용해 집합 연산을 효율적으로 수행할 수 있다.
* 그러나 비트 필드는 정수 열거 상수의 단점을 그대로 지니고, 비트 필드 값이 그대로 출력되면 해석하기 어렵다.
* 비트 필드 하나에 녹아 있는 모든 원소를 순회하기 어렵다.
* 최대 몇 비트가 필요한지 미리 예측해서 적절한 타입(int, long)을 선택해야 한다.
    * API를 수정하지 않고는 비트수를 늘릴 수 없기 때문이다.

## EnumSet
* Set 인터페이스를 완벽히 구현
* 타입 안전
* 다른 Set 구현체와 함께 사용할 수 있다.
* EnumSet의 내부는 비트 벡터로 구현되어 비트를 효율척으로 처리할 수 있는 산술연산을 써서 구현해서 비트를 직접 다룰 때 흔히 곁은 오류에서 해방된다.
* 불변 EnumSet이 없다. (~java11)
* 비트 64개 이하면 RegularEnumSet, 초과하면 JumboEnumSet으로 가게 된다.
```java
    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClassCastException(elementType + " not an enum");

        if (universe.length <= 64)
            return new RegularEnumSet<>(elementType, universe);
        else
            return new JumboEnumSet<>(elementType, universe);
    }
```
https://aegis1920.github.io/wiki/effective-java-item-36.html

## Collections - Set 불변 관련 
unmodifiableSet(Set<? extends T> s)
Returns an unmodifiable view of the specified set.
https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html


# item 37 : ordinal 인덱싱 대신 EnumMap 사용

# item 38 : 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라

# item 41 : 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
## 마커 인터페이스 
* ex) Serializable, Closeable, Clonable

## 마커 어노테이션
* ex) @Test
https://ryumodrn.tistory.com/100
https://dahye-jeong.gitbook.io/java/java/effective_java/2021-06-14-use-marker-interfaces-to-define-types


### Serializable에 대한 고찰
* Spring 사용할 때에는 언제 이용해야 하는가
https://velog.io/@nathan29849/Spring-Serializable
https://techblog.woowahan.com/2550/


## 참고
enum은 컴파일러가 따로 추가해주는 메소드가 있어서 Javadoc에서 보지 못하는 경우가 있다.
컴파일러는 enum을 생성할 때, 특별한 메소드를 자동으로 추가해준다.
예를 들어, 선언된 enum의 값들을 담은 배열을 반환하는 values 메소드를 추가해준다.
이 메소드는 주로 enum type들을 순회할 때 사용된다.

리플렉션을 통해 확인할 수 있다. 
https://stackoverflow.com/questions/13659217/where-is-the-documentation-for-the-values-method-of-enum

## Enum을 담는 자료구조 : EnumSet 또는 EnumMap을 사용해라!

## EnumMap

Javadoc에 따르면 

"when the map is created. Enum maps are represented internally as arrays. This representation is extremely compact and efficient." 

-> hashmap 은 key를 bucket에 저장하고각 bucket이 linked list를 참조 하고 있음. (linkedlist에는 hash(key)가 같은 element가 들어감) 그런데 enummap 의 경우 key로 사용할 값이 제한되어 있으므로, 그 갯수만큼 길이를 가진 array를 선언하고. 해당 index에 값을 넣으면 됨. 

 ## EnumSet

Javadoc에 따르면 

"when the set is created. Enum sets are represented internally as bit vectors."

-> hashset은 hashmap 과 같은데 map의 value가 있다 없다를 표현하는 지시자 같은 값이 들어감. enumset은 값이 있다 없다만 표시하면 되니까 enummap 처럼 array로 구현하지 않고 10101011 같은 bitvector로 구현이 가능.



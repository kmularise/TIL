# item 26 : 로 타입(raw type)은 사용하지 말라.

## 예시를 통한 용어 정리
* 로 타입 : ```List```
* 제네릭 타입 : ```List<E>```
* 매개변수화 타입 : ```List<String>```
* 타입 매개변수 : ```E```
* 실제 타입 매개변수 : ```String```
* 한정적 타입 매개변수 : ```List<E extends Number>```
* 비한정적 와일드카드 타입 : ```Class<?>```
* 한정적 와일드카드 타입 : ```Class<? extends Annotation>```

## 매개변수화 타입을 사용해야 하는 이유
* 런타임이 아닌 컴파일 타임에 문제를 찾을 수 있다. (안정성)
* 제네릭을 활용하면 이 정보가 주석이 아닌 타입 선언 자체에 녹아든다.(표현력)
```java
List<Integer> numbers = new ArrayList<>(); //뭘 담는지 한눈에 알 수 있다.
```
* '로 타입'을 사용하면 안정성과 표현력을 잃는다.
```java
List numbers = new ArrayList(); //뭘 담는지 한눈에 알아보기 어렵다.
```
* 그럼에도 불구하고 자바가 '로 타입'을 지원하는 이유
    * 하위 호환성 때문이다.
    * 컴파일되면 다 없어진다.
        ```
            INVOKEVIRTUAL com/effective/demo/item26/Box.get ()Ljava/lang/Object;
            CHECKCAST java/lang/Integer
            INVOKEVIRTUAL java/lang/Integer.intValue ()I
            BIPUSH 100
        ```
    * 마이그레이션 호환성을 위해 로 타입을 지원하고 제네릭 구현에는 소거방식을 사용하기로 했다. (item 28)
* List와 List<Object>의 차이
    * List는 타입 안정성과 표현력이 없고 List<Object>는 안정성과 표현력이 있다.
* Set과 Set<?>의 차이
    * Set은 안정성이 깨진다. Set<?> 어떤 한종류를 다루는 Set은 다 되고, 안전하다.
```
Set<?> temp = new HashSet<>();//null 밖에 못들어감
Set mySet = new HashSet();//1, "string" 등등 다 들어가서 타입 안정성 보장 안됨.
```
* 예외는 다음과 같다.
    * .class
    ```java
    List<Integer>.class //이런 클래스는 없다. 
    ```
    * instanceof
    ```java
    List<String> stringList = new ArrayList<>();
    stringList instanceof List<String> //쓸수는 있으나 어차피 <String> 소거되서 stringList instanceof List<String>
    ```

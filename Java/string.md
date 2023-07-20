# String
## 불변 객체
* 불변 객체란? 객체가 생성된 이후로는 상태를 변경할 수 없는 객체
* 장점 : 
    * 상태를 변경할 수 없으므로 Thread safe
    * 객체의 신뢰성을 높일 수 있다.
* 단점 :
    * 코드가 길어진다
* 불변 객체에 대한 오해 : 객체 생성 비용은 과대평가되고 있으며, 이는 불변 객체가 주는 효율성으로 충분히 상쇄할 수 있다. 이러한 효율성에는 가비지 컬렉션으로 인한 오버헤드 감소, 가변 객체로 인한 오류가 발생하지 않도록 보호하는 코드 제거가 있다.
* 불변 객체를 만드는 방법 : 
    1. Setter 메소드를 제공하지 않는다.
    2. 인스턴스 변수를 final과 private으로 선언한다.
    3. 메소드 오버라이딩 금지
    4. defensive copy : 인스턴스 변수가 가변 객체의 참조값을 포함한다면, 가변 객체의 값을 수정하는 메소드를 제공하지 말고, 가변 객체의 참조값의 원본을 내보내지 말고, 복사본을 내보내야 한다.

## String 클래스
* String 클래스의 선언부
```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence
```
    * public final : 어디서든 접근 가능, 클래스 확장 불가
    * implements java.io.Serializable, Comparable<String>, CharSequence :
        * Serializable : 해당 객체를 파일로 저장하거나 다른 서버에 전송 가능한 상태가 된다.
        * Comparable : 객체의 순서를 처리
        * CharSequence : 문자열을 다루기 위한 클래스임을 명시
* 디코딩은 일반적으로 암호화되어 있거나 컴퓨터가 이해할 수 있는 값들을 알아보기 쉽게 변환하는 것

## String 문자열을 byte로 변환하기
### UCS(Unicode Character Set)
![Alt text](image/image.png)
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/charset/Charset.html
* 요즘에는 UTF-16 많이 사용

## null
객체가 null이라는 것은 객체가 아무런 초기화가 되어 있지 않으며, 클래스에 선언되어 있는 어떤 메소드 사용할 수 없음을 의미

## String "==" 과 equals()
```java
String text = "Check value";
String text2 = "Check value";
boolean isSame = (text == text2);
```
* 위의 코드에서 isSame은 true로 나온다.
* 그 이유는 자바에 Constant Pool이라는 것이 존재하기 때문이다. 자바에서는 객체들을 재사용하기 위해서 Constant Pool이라는 것이 만들어져 있고, String의 경우 동일한 값을 갖는 객체가 있으면, 이미 만든 객체를 재사용한다.

## String valueOf() vs String toString()
null인 객체가 들어왔을 때
* valueOf() : null 문자열 리턴 
* toString() : NullPointerException 발생

## String VS StringBuffer VS StringBuilder
### String
* String은 immutable한 객체다. 한 번 만들어지면 더 이상 그 값을 바꿀 수 없다.
* 계속 하나의 String을 만들어 계속 더하는 작업을 한다면, 기존 String 객체는 더 이상 사용할 수 없고 Garbage Collection의 대상이 된다.
### StringBuffer, StringBuilder
* StringBuffer와 StringBuilder는 문자열을 더하더라도 새로운 객체를 생성하지 않는다.
* StringBuffer : Thread safe
* StringBuilder : Thread safe 하지 않음
* 여러 쓰레드에서 인스턴스 변수에 동시에 접근하는 일이 있을 경우 StringBuffer를 사용해야 한다.
* JDK 5 이상에서는 String 더하기 연산을 할 경우, 컴파일할 때 자동으로 해당 연산을 StringBuilder로 변환해 준다.


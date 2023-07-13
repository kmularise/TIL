# Java 7에서의 변화
* Java 7 변화는 JSR 336이라는 문서에 정리

## Java 7 대표 변경사항 
* 숫자 표시 방법 보완
* switch 문에서 String 사용
* 제네릭 생성자에 타입 명시하지 않아도 가능
* 예외 처리 시 다중 처리 가능

## Java 7 추가사항
* Fork/Join
* NIO2
* Files

### 숫자 표시 방법 보완
```java
int decVal = 1106; //10진법
int octVal = 02112; //8진법
int hexVal = 0x452; //16진법
int binaryVal = 0b10001010010; //2진법 : Java 7 추가
```
```java
int million = 1_000_000;
```
### switch 문에서 String 사용

### 제네릭 생성자에 타입 명시하지 않아도 가능
```java
HashMap<String, Integer> map = new HashMap<>();
```

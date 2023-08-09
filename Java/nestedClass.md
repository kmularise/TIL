# 클래스 안의 클래스 - 내부 클래스
* 클래스 안의 클래스 : Nested 클래스, inner class
* 다른 클래스 안에 선언되는 클래스
## Nested 클래스의 분류
* 크게 네 종류가 있다.
    * 멤버 인스턴스 내부 클래스
    * 정적 내부 클래스
    * 메소드 안에 정의된 클래스
    * 익명 클래스 : 클래스의 선언과 객체의 생성을 동시에 하는 이름 없는 클래스(일회용)
```java
class Outer{
    int iv = 0; //인스턴스 변수
    static int cv = 0; //클래스 변수
    
    void myMethod() {
        int iv = 0; //지역 변수
    }
}
```

```java
class Outer{
    class InstanceInner () // 인스턴스 내부 클래스
    static class StaticInner () // 스태틱 내부 클래스
    
    void myMethod() {
        class LocalInner() // 지역 내부 클래스 - 메소드 안에 정의된 클래스
    } 
}
```
## Nested 클래스를 사용하는 이유
* 한 곳에서만 사용되는 클래스를 논리적으로 묶어서 처리할 필요가 있을 때
* 캡슐화가 필요할 때 - 외부/내부 클래스 간의 관계가 긴말할 때 사용
* 소스의 가독성과 유지보수성을 높이고 싶을 때 - 과하게 사용되면 클래스 비대화

## Nested 클래스의 분류
![Alt text](image/image-1.png)
## 익명 클래스의 장점
* 클래스 생성을 줄이기 때문에 메모리를 덜 사용한다.
## 코드 예시
[code](./code/innerClass/) 

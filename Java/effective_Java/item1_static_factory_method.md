# item1 생성자 대신 정적 팩토리 메소드를 고려하라
* public 생성자 VS 정적 팩토리 메소드 
> 정적 팩토리 메소드는 추상화 팩토리 패턴이 아니다.
> 정적 팩토리 메소드는 팩토리 메소드 패턴이 아니다.

## 정적 팩토리 메소드의 장점
### 1. 이름을 가질 수 있다.
```java
public class Student{
    private int score;
    private int number;
    private String name;

    private Student(int score, int number, String name) {
        this.score = score;
        this.number = number;
        this.name = name;
    }
    //생성자는 같은 시그니처를 가진 생성자는 만들지 못하지만 정적 팩토리 메소드는 이름을 달리 할 수 있어 같은 시그니처를 가진 다른 메소드를 만들 수 있다.
    public static withNumber(String name, int number) {
        return new Student(50, number, name);
    }

    public static withScore(String name, int score) {
        return new Student(score, 1, name);
    }
}
```
### 2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
* 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 재활용한다.
* 객체가 자주 요청되는 상황이라면 성능을 상당히 끌어올려 준다.

### 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
* 인터페이스를 사용해서 반환할 객체의 클래스를 자유롭게 선택이 가능하다. -> 유연성
* 자바 8 이후부터 인터페이스에 정적 메소드를 선얼할 수 있게 되었다.
    * 자바 8부터는 인터페이스가 정적 메소드를 가질 수 없다는 제한이 풀렸기 때문에 인스턴스화 불가 동반 클래스를 둘 이유가 없다.

## 정적 팩토리 메소드의 단점
### 1. 정적 팩토리 메소드만 제공하면 하위클래스를 만들 수 없다.
* private 생성자를 가지기 때문에 정적 팩토리 메소드만 제공하면 하위 클래스를 만들 수 없다.
* 그렇지만 상속보다 조합(compositon)을 사용할 수 있다느 점에선 오히려 장점이 될 수 있다.

### 2. 정적 팩토리 메소드는 프로그래머가 찾기 어렵다.
* java docs나 개발자가 알아보기 어렵다.
* 따라서 이를 위해 명명 컨벤션을 지키면 좋다.
    * type : getType과 newType의 간결한 버전
    ```java
    List<Complaint> litany = Collections.list(legacyLitany);
    ```
<!-- https://deveric.tistory.com/123 -->
# Optional(옵셔널)
* 객체를 편리하게 처리하기 위해 만든 클래스
* null 처리를 간편하게 하기 위해 만들어짐
* Optional 클래스에 값을 잘못 넣으면 NoSuchElementException이 발생할 수 있음

## Optional orElse VS Optional orElseGet
| 메소드 | 설명 |
| ----- | ------ |
| T orElse(T other) | 값이 존재하면 값을 반환하고, 값이 존재하지 않으면 other 반환 |
| T orElseGet(Supplier<? extends T> ohter) | 값이 존재하면 값을 반환하고, 그렇지 않으면 other을 호출하고 호출 결과를 반환 |

기본적인 설명은 이렇다. 

orElse()의 인자에 함수가 들어가면 함수를 무조건 실행하지만, orElseGet은 lazy evaluation(지연 연산)을 하기 때문에 연산이 필요한 경우에만 함수를 실행시킨다.


[Optional 클래스 구현부 일부]
```java
    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return value != null ? value : supplier.get(); // lazy evaluation 가능
    }
```

[main 메소드 실행시키면 orElse() 메소드에서만 필요하지 않은 경우에도 함수가 호출됨]
```java
public class OptionalTest {
    public static void main(String[] args) {
        String name1 = Optional.ofNullable("not empty")
                .orElse(getRandomName());
        System.out.println("name1 = " + name1);
        String name2 = Optional.ofNullable("not empty")
                .orElseGet(OptionalTest::getRandomName);
        System.out.println("name2 = " + name2);
    }
    public static String getSample() {
        return null;
    }
    public static String getRandomName() {
        System.out.println("running!!");
        return "euijin";
    }
}
```


[정리]
* 기본 객체가 이미 생성되어 있거나 직접 접근 가능하다면 : orElse() 메소드 사용
* 나머지 경우 (특히 함수를 이용한 값을 반환하는 경우): orElseGet()

## 참고 자료
https://www.baeldung.com/java-optional-or-else-vs-or-else-get

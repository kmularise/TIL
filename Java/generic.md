# 제네릭(Generic)
* 클래스 내부에서 지정하는 것이 아닌 외부에서 사용자에 의해 지정되는 것을 의미
```java
public class CastingGenericDto<T> implements Serializable {
    private T object;
    public void setObject(T obj) {
        this.object = obj;
    }

    public T getObject() {
        return object;
    }
}
```
## 제네릭의 장점
* 제네릭은 사용하면 잘못된 타입이 들어오는 것을 컴파일 단계에서 방지할 수 있다.
* 관리하기가 편하다. 클래스 외부에서 타입을 지정해주기 때문에 타입을 체크하고 변환해줄 필요가 없기 때문이다.
* 코드의 재사용성이 높아진다.
## 제네릭 이름 짓기
| 타입  | 설명 |
| --------- | ------------- |
| \<T> | Type |
| \<E> | Element |
| \<K> | Key |
| \<V> | Value |
| \<N>	| Number |
| \<S>, \<U>, \<V> | 두번째, 세번째 네번째 선언된 타입 |

## 상속과 제네릭
상속을 이용해서 제네릭에 사용할 타입을 제한할 수 있다.

## 메소드를 제네릭하게 선언하기
예시)
``` java
public <T extends Car> void boundedGenericMethod(WildcardGeneric<T> c, T addValue)
```
```java
public <S, T extends Car> void multiGenericMethod(WildcardGeneric<T> c, T addVaule, S another)
```
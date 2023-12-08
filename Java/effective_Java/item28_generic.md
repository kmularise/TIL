# 아이템 28. 배열보다는 리스트를 사용하라. 핵심 정리: 배열과 제네릭은 잘 어울리지 않는다.
* 배열은 공변 (covariant), 제네릭은 불공변
```java
//공변 
Object[] anything = new String[10];
anything[0] = 1; //각각 보면 문제 없는데 합치고 보면 이상하다.

//불공변 - 상위타입, 하위타입이 의미가 없다.
List<String> names = new ArrayList<>();
List<Object> objects = names; //컴파일 에러 발생
```
* 배열은 실체화(reify) 되지만, 제네릭은 실체화 되지 않는다. (소거)
    * 실체화 : 런타임에도 타입이 유지되는지의 여부
        * 배열 : 런타임에도 String배열
        * List : 컴파일 후에는 String 정보가 소거된다. - 하위 버전 호환성 때문에
new Generic<타입>[배열] 은 컴파일 할 수 없다.
제네릭 소거: 원소의 타입을 컴파일 타임에만 검사하며 런타임에는 알 수 없다.
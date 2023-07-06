# 어노테이션
## 어노테이션의 사용
* 컴파일러에게 정보를 알려주거나
* 컴파일할 때와 설치 시의 작업을 지정
* 실행할 때 별도 처리가 필요할 때

## 미리 정해져 있는 어노테이션
* @Override
* @Deprecated
* @Supress Warnings

## 어노테이션 선언
### 메타 어노테이션
어노테이션을 선언할 때 사용
* @Target
* @Retention
* @Documented
* @Inherited

### @Target
```java
@Target(ElementType.METHOD)
```
| 요소 타입  | 대상 |
| --------- | ------------- |
| CONSTRUCTOR | 생성자 선언 시 |
| FIELD  | enum 상수를 포함한 필드(field) 값 선언 시 |
| LOCAL_VARIABLE | 지역 변수 선언 시 |
| METHOD | 메소드 선언 시 |
| PACKAGE | 패키지 선언 시 |
| PARAMETER | 매개 변수 선언 시 |
| TYPE | 클래스, 인터페이스, enum 등 선언 시 |

### @Retention
```java
@Retention(RetentionPolicy.RUNTIME)
```
| 요소 타입 | 대상 |
| ----- | ------------- |
| SOURCE | 어노테이션 정보가 컴파일 시 사라짐 |
| CLASS | 클래스 파일에 있는 어노테이션 정보가 컴파일러에 의해서 참조 가능함. 하지만 가상 머신(Virtual Machine)에서는 사라짐
| RUNTIME | 실행 시 어노테이션 정보가 가상 머신에 의해서 참조 가능 |


https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/annotation/RetentionPolicy.html#RUNTIME
### @Documented
해당 어노테이션에 대한 정보가 Javadocs(API) 문서에 포함된다는 것을 선언

### @Inherited
모든 자식 클래스에서 부모 클래스의 어노테이션을 사용 가능하다는 것을 선언

### @interface
어노테이션을 선언할 때 사용

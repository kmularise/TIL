# Error와 Exception
## 공통점
* Error와 Exception은 Throwable 클래스의 하위클래스이고 비정상적인 상황이 발생했음을 가리킨다.
* Throwable 클래스와 그 하위클래스들의 인스턴스만 JVM에 던져질(throw) 수 있고, catch 구문에 의해 잡힐 수 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/8e8c796d-151e-43fb-bbe6-2e1eaa79b9c3)

## Error (오류)
* Error는 시스템이 종료되어야 할 수준의 상황과 같이 수습할 수 없는 심각한 문제
* 개발자가 미리 예측하여 방지할 수 없다.

## Exception (예외)
* Exception은 애플리케이션이 처리할 수 있는 비정상적인 조건이다.
    * Exception은 try-catch 문에 의해 복구될 수 있고, 런타임과 컴파일 때 발생할 수 있다.
* 개발자가 구현한 로직에서 발생한 실수나 사용자의 영향에 의해 발생한다.
* Checked Exception 과 Unchecked Exception 으로 나뉜다.
* Checked Exception은 메소드나 생성자에서 명시적으로 throws로 예외를 던지거나, try-catch 문으로 예외를 처리하는지 컴파일 시점에 정적으로 확인한다.
* Unchecked Exception(Runtime Exception)은 JVM이 런타임 시에 던지는 Exception 하위 클래스이다.
    * Unchecked Exception(Runtime Exception)은 컴파일 시점에 예외를 처리하는지 확인하지 않는다. 컴파일 시점에 예외가 발생하는지 여부를 판단할 수 없다.



## 참고 자료
* https://www.baeldung.com/java-errors-vs-exceptions
* https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Exception.html
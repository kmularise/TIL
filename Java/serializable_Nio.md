# Serializable
## Serialiable 사용
* 생성한 객체를 파일로 저장하거나, 저장한 객체를 읽을 때
* 객체를 다른 서버로 보내거나, 다른 서버에서 생성한 객체를 받을 때
* Serializable 인터페이스를 구현하면 JVM에서 해당 객체는 저장하거나 다른 서버로 전송할 수 있도록 해준다.
* Serializable 인터페이스를 구현한 후에는 다음과 같이 serialVersionUID 값을 지정해주는 것을 권장한다.
    * 별도로 지정하지 않으면, 자바 소스가 컴파일 될 때 자동으로 생성
    ```java
    static final serialVersionUID = 1L;
    ```
    * serialVersionUID : 해당 객체의 버전을 명시
    * 각 서버가 쉽게 해당 객체가 같은지 다른지를 확인할 수 있도록 하기 위해서 serialVersionUID로 관리해주어야 한다. 즉, 클래스 이름이 같더라도 이 ID가 다르면 다른 클래스라고 인식한다.
    * 같은 UID라고 할지라도, 변수의 개수나 타입 등이 다르면 이 경우도 다른 클래스로 인식한다.
* 객체에 Serializable을 구현하지 않은 경우, Serializable 되어 있지 않다는 NotSerializableException이 발생한다.
* 변수가 추가되는 등 Serializable 객체의 형태가 변경되면 컴파일 시 serialVersionUID가 다시 생성된다.
    * serialVersionUID가 달라서 InvalidClassException이 발생한다.
* Serializable을 구현해 놓은 상황에서 serialVersionUID를 명시적으로 지정하면 변수가 변경되더라도 예외는 발생하지 않는다. 하지만, 만약 Serializable한 객체의 내용이 바뀌었는데도 아무런 예외가 발생하지 않으면 운영상황에서 데이터가 꼬일 수 있기 때문에 권장하는 방법은 아니다.

## transient와 Serializable
* transient 예약어를 사용하여 선언한 변수 : Serializable 대상에서 제외
* 해당 객체를 생성한 JVM에서는 transient 예약어를 사용하여 선언한 변수를 사용할 수 있다.
* 보안상 중요한 변수나 저장해야할 필요가 없는 변수에 대해 transient를 사용할 수 있다.


# 자바 NIO
* JDK 1.4부터 NIO 추가 - 속도 때문
* NIO는 채널(Channel)과 버퍼(Buffer)를 사용
* NIO는 파일을 읽고 쓸 때 뿐만 아니라 파일 복사를 하거나, 네트워크로 데이터를 주고 받을 때에도 사용할 수 있다.

## NIO의 Buffer 클래스
### 버퍼 상태 및 속성 확인 메소드
| 리턴 타입 | 메소드 | 설명 |
| --------- | ------------- | ------- |
| int | capacity() | 버퍼에 담을 수 있는 크기 리턴 |
| int | limit() | 버퍼에서 읽거나 쓸 수 없는 첫 위치 리턴 |
| int | position() | 현재 버퍼의 위치 리턴 |
```
0 <= position <= limit <= 크기(capacity)
```

### 위치를 변경하는 메소드 
| 리턴 타입 | 메소드 | 설명 |
| --------- | ------------- | --------- |
| Buffer | flip() | limit 값을 현재 position으로 지정한 후, position을 0(가장 앞)으로 이동 |
| Buffer | mark() | 현재 position을 mark |
| Buffer | reset() | 버퍼의 position을 mark 한 곳으로 이동 |
| Buffer | rewind() | 현재 버퍼의 position을 0으로 이동 |
| int | remaining() | limit-position 결과를 리턴 |
| boolean | hasRemaining() | position와 limit 값에 차이가 있을 경우 true 리턴 |
| Buffer | clear() | 버퍼를 지우고 현재 position을 0으로 이동하며, limit 값을 버퍼의 크기로 변경 |

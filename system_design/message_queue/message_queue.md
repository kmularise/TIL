# 메시지 큐
## 정의

메시지의 무손실을 보장하고 비동기 통신을 지원하는 컴포넌트
* 무손실(durability) : 메시지 큐에 일단 보관된 메시지는 소비자가 꺼낼 때까지 안전히 보관된다는 특성
* 메시지의 버퍼 역할을 하며, 비동기적으로 전송한다.
* 메시지 큐의 기본 아키텍처 :
    * 생산자 또는 발행자(producer/publishser)라고 불리는 입력 서비스가 메시지를 만들어 메시지 큐에 발행(publish)한다.
    * 큐에는 보통 소비자 혹은 구독자(consumer/subscriber)라 불리는 서비스 혹은 서버가 연결되어 있는데, 메시지를 받아 그에 맞는 동작을 수행하는 역할을 한다.

## 장점
* 메시지 큐를 이용하면 서비스 또는 서버 간 결합이 느슨해져서, 규모 확장성이 보장되어야 하는 안정적 애플리케이션을 구성하기 좋다.
* 시스템 컴포넌트를 분리할 수 있고, 각기 독립적으로 확장되도록 하기 좋다.
* 생산자는 소비자 프로세스가 다운되어 있어도 메시지를 발생할 수 있고, 소비자는 생산자 서비스가 가용한 상태가 아니더라도 메시지를 수신할 수 있다.

## 사용 예시
시간이 오래 걸릴 수 있는 프로세스, 비동기적으로 처리하면 편하다.
* 웹 서버는 작업을 메시지큐에 넣는다.
* 작업 처리 프로세스들은 작업을 메시지 큐에서 꺼내 비동기적으로 완료한다.
* 생산자와 소비자 서비스 규모는 각기 독립적으로 확장될 수 있다.
    * 큐의 크기가 커지면 더 많은 작업 프로세스를 추가하여 처리 시간 감축
    * 큐가 거의 비어 있는 상태라면, 작업 프로세스 수를 줄인다.

## 참고자료
* 가상 면접 사례로 배우는 대규모 시스템 설계
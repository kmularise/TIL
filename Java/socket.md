# 소켓 프로그래밍

## 관련 CS 지식
* OSI 7계층
* TCP vs UDP
* 포트
* IP

## Socket 클래스
* Socket 클래스는 데이터를 보내는 쪽(보통 클라이언트)에서 객체를 생성하여 사용한다.
* 데이터를 받는 족(보통 서버)에서 클라이언트 요청을 받으면, 요청에 대한 Socket 객체를 생성하여 데이터를 처리한다.
* 원격에 있는 장비와의 연결 상태 보관

### ServerSocket 생성자
* backlog : 최대 대기 개수
* bindAddr : 특정 주소에만 접근이 가능하도록 지정
* ServerSocket() : 서버 소켓 객체만 생성, 이 생성자 제외한 나머지 생성자는 객체가 생성되자 마자 연결을 대기할 수 있는 상태가 된다.

### ServerSockeet 메소드
| 리턴 타입 | 메소드 | 설명 |
| -------- | -------- | -------- |
| Socket | accept() | 새로운 소켓 연결을 기다리고, 연결이 되면 Socket 객체를 리턴 |
| void | close() | 소켓 연결을 종료 , close() 메소드 처리를 하지 않고, JVM이 계속 동작중이라면, 해당 포트는 동작하는 서버나 PC에서 다른 프로그램이 사용할 수 없다.|

### Socket 생성자
| 생성자 | 설명 |
| -------- | -------- |
| 1. Socket() | 소켓 객체만 생성 |
| 2. Socket(Proxy proxy) | 프록시 관련 설정과 함께 소켓 객체만 생성 |
| 3. Socket(SocketImpl impl) | 사용자가 지정한 SocketImpl 객체를 사용하여 소켓 객체만 생성 |
| 4. Socket(InetAddress address, int port) | 소켓 객체 생성 후 address와 port를 사용하는 서버에 연결 |
| 5. Socket(InetAddress address, int port, InetAddress localAddr, int localPort) | 소켓 객체 생성 후 host와 port를 사용하는 서버에 연결하며, 지정된 localAddr와 localPort에 접속 |
| 6. Socket(String host, int port) | 소켓 객체 생성 후 host와 port를 사용하는 서버에 연결 |
| 7. Socket(String host, int port, InetAddresss localAddr, int localPort) | 소켓 객체 생성 후 host와 port를 사용하는 서버에 연결하며, 지정된 localAddr와 localPort에 접속 |
* 4., 5., 6., 7. : 객체 생성과 함께 지정된 서버에 접속
* close() : 소켓 닫기
* Timeout 관련 메소드 - 소켓 연결에 문제가 있을 경우 끊어줌

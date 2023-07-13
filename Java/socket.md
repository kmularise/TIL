# 소켓 프로그래밍

## 관련 개념
* OSI 7계층
* TCP vs UDP
* 포트
* IP
* 웹서버
* HTTP 메소드

## Socket 클래스
* Socket 클래스는 데이터를 보내는 쪽(보통 클라이언트)에서 객체를 생성하여 사용한다.
* 데이터를 받는 족(보통 서버)에서 클라이언트 요청을 받으면, 요청에 대한 Socket 객체를 생성하여 데이터를 처리한다.
* 원격에 있는 장비와의 연결 상태 보관

### ServerSocket 생성자 - TCP 서버
* backlog : 최대 대기 개수
* bindAddr : 특정 주소에만 접근이 가능하도록 지정
* ServerSocket() : 서버 소켓 객체만 생성, 이 생성자 제외한 나머지 생성자는 객체가 생성되자 마자 연결을 대기할 수 있는 상태가 된다.

### ServerSockeet 메소드 
| 리턴 타입 | 메소드 | 설명 |
| -------- | -------- | -------- |
| Socket | accept() | 새로운 소켓 연결을 기다리고, 연결이 되면 Socket 객체를 리턴 |
| void | close() | 소켓 연결을 종료 , close() 메소드 처리를 하지 않고, JVM이 계속 동작중이라면, 해당 포트는 동작하는 서버나 PC에서 다른 프로그램이 사용할 수 없다.|

### Socket 생성자 - TCP 클라이언트
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

## Datagram 클래스 - UDP 
* UDP는 TCP와 달리 데이터가 제대로 전달되었다는 보장을 하지 않는다.
* TCP와 다르게 클래스 하나에서 보내는 역할과 받는 역할을 모두 수행할 수 있다.

### DatagramSocket 생성자
| 생성자 | 설명 |
| -------- | -------- |
| DatagramSocket() | 소켓 객체 생성 후 사용 가능한 포트로 대기 |
| DatagramSocket(DatagramSocketImpl impl) | 사용자가 지정한 SocketImpl 객체를 사용하여 소켓 객체만 생성 | 
| DatagramSocket(int port) | 소켓 객체 생성 후 지정된 port로 대기 |
| DatagramSocket(int port, InetAddress address ) | 소켓 객체 생성 후 address와 port를 사용하는 서버에 연결 |
| DatagramSocket(SocketAddress address) | 소켓 객체 생성 후 address에 지정된 서버로 연결 |
* 소켓을 더 이상 사용할 필요가 없을 때 close() 호출

### 메소드
| 리턴 타입 | 메소드 | 설명 |
| -------- | -------- | -------- |
| void | receive(DatagramPacket packet) | 메소드 호출 시 요청을 대기하고, 만약 데이터를 받았을 때에는 packet 객체에 데이터를 저장 |
| void | send(DatagramPacket packet) | packet 객체에 있는 데이터 전송 |

### DatagramPacket
| 생성자 | 설명 |
| -------- | -------- |
| DatagramPacket(byte[] buf, int length) | length의 크기를 갖는 데이터를 받기 위한 객체 생성 |
| DatagramPacket(byte[] buf, int length, InetAddresss address, int port) | 지정된 address와 port로 데이터를 전송하기 위한 객체 생성 |
| DatagramPacket(byte[] buf, int offset, int length) | 버퍼의 offset이 할당되어 있는 데이터를 전송하기 위한 객체를 생성 |
| DatagramPacket(byte[] buf, int offset, int length, InetAddress addresss, int port) | 버퍼의 offset이 할당되어 있고, 지정된 address와 port로 데이터를 전송하기 위한 객체 생성 |
| DatagramPacket(byte[] buf, int offset, int length, SocketAddress address) | 버퍼의 offset이 할당되어 있고, 지정된 소켓 address로 데이터를 전송하기 위한 객체 생성 |
* byte 배열 : 전송되는 데이터
* offset : byte 배열의 첫 위치
* length : 데이터 크기

## 자바에서 웹페이지 요청
* Apache의 Http Components
https://hc.apache.org/
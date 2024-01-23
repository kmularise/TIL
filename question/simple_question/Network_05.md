# HTTP/HTTPS
## 멱등성이란?

멱등성이란 어떤 연산을 여러 번 수행했을 때의 결과가 첫 번째 수행할 때 결과와 동일하게 유지되는 성질을 말한다.

요청 메서드는 해당 메서드와의 복수의 동일한 요청의 서버에 대한 의도된 효과가 단일 요청 의 효과와 동일한 경우, 멱등성으로 간주된다. GET, HEAD, OPTION, TRACE, PUT, DELETE 및 안전 요청 메소드는 멱등하다.


## HTTP와 TCP 차이
* TCP
    * 두 개의 호스트를 연결하고 데이터 스트림을 교환하게 해주는 중요한 네트워크 프로토콜
    * TCP는 데이터와 패킷이 보내진 순서대로 전달하는 것을 보장해준다.
    * 트랜스포트 계층
    * 혼잡 제어 : 컴퓨터 네트워크에서 네트워크의 혼잡 상태를 관리하고 제어하는 메커니즘
* HTTP((Hyper Text Transfer Protocol)
    * HTML 문서와 같은 리소스들을 가져올 수 있도록 해주는 프로토콜
    * 클라이언트-서버 프로토콜
    * 애플리케이션 계층

참고자료 : [mdn HTTP](https://developer.mozilla.org/ko/docs/Web/HTTP/Overview), [mdn TCP](https://developer.mozilla.org/ko/docs/Glossary/TCP)

## HTTPS를 사용할 때, 공인 인증서를 발급 받는 과정
1. 키 생성 : 서버에서 공개 키와 개인키를 생성한다. 
2. 인증서 발급 요청 : 서버가 CA에 공개키를 저장하는 인증서의 발급을 요청한다.
3. CA는 CA의 이름, 서버의 공개 키, 서버의 정보 등을 기반으로 인증서를 생성하고 CA의 개인키로 암호화하여 서버에게 제공한다.
4. 서버는 브라우저에게 암호화된 인증서를 제공한다.
5. 브라우저는 CA의 공개키를 미리 다운받고 갖고 있어, 암호화된 인증서를 복호화한다.
6. 암호화된 인증서를 복호화하여 얻은 서버의 공개키로 세션키를 공유한다.

### (참고) HTTPS 연결과정
1. 브라우저(클라이언트)가 서버로 최초 연결 시도를 한다.
2. 서버는 공개키(엄밀히는 인증서)를 브라우저에게 넘겨준다.
3. 브라우저는 인증서의 유효성을 검사하고 세션키를 발급한다.
4. 브라우저는 세션키를 보관하며 추가로 서버의 공개키로 세션키를 암호화하여 서버로 전송한다.
5. 서버는 개인키로 암호화된 세션키를 복호화하여 세션키를 얻는다.
6. 클라이언트와 서버는 동일한 세션키를 공유하게 되어 데이터를 전달할 때 세션키로 암호화와 복호화를 진행한다.

참고자료 : [HTTP와 HTTPS](https://mangkyu.tistory.com/98)
## CA란?
HTTPS 웹사이트에 SSL/TLS 인증서를 발급해주는 독립된 인증기관이다.

## HTTP와 HTTPS 차이
* HTTP(Hyper Text Transfer Protocol)
    * HTML과 같은 하이퍼미디어 문서를 전송하기 위한 애플리케이션 계층 프로토콜
    * 클라이언트-서버 모델
    * 애플리케이션 계층
    * 80 포트 사용

* HTTPS (HyperText Transfer Protocol Secure)
    * HTTP 프로토콜의  암호화된 버전이다. 
    * TLS(Transport Layer Security)를 사용하여 클라이언트와 서버 간의 모든 통신을 암호화한다.
    * 443 포트 사용
    * 대칭키 암호화와 비대칭키 암호화 사용, 인증서 사용
참고자료 : [mdn HTTP](https://developer.mozilla.org/ko/docs/Web/HTTP), [HTTP와 HTTPS](https://mangkyu.tistory.com/98)

## Rest API란?
* REST API는 REST(REpresentational State Transfer) 아키텍처 스타일의 디자인 원칙을 준수하는 API이다.
### 특징
* 균일한 인터페이스 : 요청이 어디에서 오는지와 무관하게, 동일한 리소스에 대한 모든 API 요청은 동일하게 보여야 한다. URL 자원 식별, 표현을 통한 자원 조작, Self-descriptive messages, HATEOAS 구조를 가진다.
* Stateless(무상태성) : 각 요청에서 요청의 처리에 필요한 모든 정보를 포함해야 한다. REST API는 서버측 세션을 필요로 하지 않는다. 서버 애플리케이션은 클라이언트 요청과 관련된 데이터를 저장할 수 없다.
* 클라이언트-서버 디커플링 : 클라이언트와 서버 애플리케이션은 서로 간에 완전히 독립적이어야 한다. 클라이언트 애플리케이션이 알아야 하는 유일한 정보는 요청된 리소스의 URI이며, 이는 다른 방법으로 서버 애플리케이션과 상호작용할 수 없다.
* 캐싱 가능성 : 리소스를 클라이언트 또는 서버측에서 캐싱할 수 있어야 한다.
* 계층 구조 아키텍처 : REST API에서는 호출과 응답이 서로 다른 계층을 통과한다.  REST API는 엔드 애플리케이션 또는 중개자와 통신하는지 여부를 클라이언트나 서버가 알 수 없도록 설계되어야 한다.

### 균일한 인터페이스(Uniform-Interface) 하위 항목
[**URL 자원 식별**]
* identification of resources
* 자원은 URL로 식별되어야 한다.

[**표현을 통한 자원조작**]
* manipulation of resources through representations
* URL과 GET, DELETE 등 HTTP 표준 메소드 등을 통해 자원을 조회, 삭제 등 작업을 설명할 수 있는 정보가 담겨야 한다.

[**Self-descriptive messages**]
* HTTP Header에 타입을 명시하고 각 메시지(자원)들은 MIME types에 맞춰 표현되어야 한다.
    * 예를 들어 .json를 반환한다면 application/json으로 명시해주어야 한다.
    * 예를 들어 json 타입의 데이터를 보낼 때 헤더의 'Content-Type' = 'application/json'을 명시해야 한다.
* MIME types
    * 문서, 파일 등의 특성과 형식을 나타내는 표준
    * IETF의 RFC6838에 정의 및 표준화되어 있다.
        ex) 'font/ttf', 'text/plain', 'text/csv' 등을 말한다.

[**HATEOAS 구조**]
* HATEOAS(Hypermedia as the Engine of Application State)
* 하이퍼링크에 따라 다른 페이지를 보여줘야 하며 데이터마다 어떤 URL에서 원했는지 명시해주어야 한다.
* 보통은 href, links, link, url 속성 중 하나에 해당 데이터의 URL을 담아서 표기해야 한다.


참고자료 : [IBM Restful API](https://www.ibm.com/kr-ko/topics/rest-apis)

## Persistent connectio(Keep-Alive)n과 Non-Persistent connection 차이
* `persistent connection` : 요청이 처리된 이후에도 TCP connection을 close 하지 않고 유지하는 경우
    * keep-alive: keep-alive면, 연결은 지속되고 끊기지 않으며, 동일한 서버에 대한 후속 요청을 수행할 수 있다.
* `non-persistent connection` : 요청이 처리된 이후에 TCP connection이 종료되는 경우


참고자료 : [mdn Connection](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Connection)

# TCP
## 패킷의 순서가 섞이면 어떻게 처리할까?

TCP는 시퀀스 번호와 ACK를 통해 올바른 순서의 패킷이 왔는지 확인할 수 있고, 순서가 맞지 않거나 누락된 패킷을 감지하면 재전송을 요청할 수 있고 시퀀스 번호를 통해 데이터를 올바른 순서로 재조합할 수 있다.

* 시퀀스 번호 할당 : TCP(Transmission Control Protocol)는 각 패킷에 시퀀스 번호를 할당한다. 이 시퀀스 번호는 전송되는 데이터의 순서를 나타내며, 수신자는 이 번호를 사용하여 데이터를 올바른 순서로 재조합한다. 

* 재전송 요청 : 수신자는 순서가 맞지 않거나 누락된 패킷을 감지하면 재전송을 요청할 수 있다. 이를 통해 손실된 패킷이나 순서가 어긋난 패킷을 다시 전송받아 데이터의 무결성과 순서를 보장한다.

* ACK : TCP는 전송된 패킷에 대한 확인 응답 (ACK)을 사용하여 패킷이 성공적으로 수신되었는지 확인한다. 수신자는 올바른 순서로 받은 패킷에 대해 ACK를 보내며, 송신자는 ACK를 통해 어떤 패킷이 성공적으로 도착했는지 알 수 있다.

## 3 way hand shake와 4 way hand shake
### 3 way hand shake (연결 과정)
* TCP 통신을 이용하여 데이터를 전송하기 위해 네트워크 연결을 설정(Connection Establish)하는 과정이다.
* 양쪽 모두 데이터를 전송할 준비가 되었다는 것을 보장하고, 실제로 데이터 전달이 시작하기 전에 한 쪽이 다른 쪽이 준비되었다는 것을 알 수 있도록 한다.

![image](https://github.com/kmularise/TIL/assets/106499310/3e9c64d9-28ac-4966-aa0e-19fa2579d094)

1. SYN(Synchronize) 단계: 클라이언트는 서버에 연결을 시작하고자 함을 알리기 위해 SYN 패킷을 보낸다.

2. SYN-ACK(Synchronize-Acknowledgment) 단계: 서버는 클라이언트의 SYN 패킷을 받고, 연결을 수락한다는 의미로 SYN-ACK 패킷을 클라이언트에게 보낸다.

3. ACK(Acknowledgment) 단계: 클라이언트는 서버의 SYN-ACK 패킷을 받고, 최종적으로 ACK 패킷을 서버에 보내 연결 과정을 완료한다.

### 4-way handshaking (연결 해제 과정)
* TCP의 연결을 해제(Connection Termination)하는 과정
![image](https://github.com/kmularise/TIL/assets/106499310/b976fbc4-53a0-4e60-977f-26e9e1a57fe1)

1. FIN(Finish) 단계 - 클라이언트에서 서버로: 클라이언트가 연결을 종료하고자 할 때, FIN 패킷을 서버에 보내 연결 종료 의사를 알린다.

2. ACK(Acknowledgment) 단계 - 서버에서 클라이언트로: 서버는 클라이언트의 FIN 패킷을 받고, ACK 패킷을 보내 이를 확인한다. 서버는 아직 보낼 데이터가 있을 수 있으므로, 데이터 전송을 마칠 때까지 기다린다.

3. FIN 단계 - 서버에서 클라이언트로: 서버가 모든 데이터 전송을 완료하면, 서버도 FIN 패킷을 클라이언트에게 보내 연결 종료 의사를 표시한다.

4. ACK 단계 - 클라이언트에서 서버로: 클라이언트는 서버의 FIN 패킷을 받고, 최종적으로 ACK 패킷을 보내 이를 확인한다.

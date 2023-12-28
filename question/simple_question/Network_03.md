# 로드 밸런서/로드밸런싱


## 로드 밸런서와 로드 밸런싱의 차이에 대해서 설명해주세요.
* 로드 밸런서
<br> 서버에 가해지는 부하(=로드)를 분산(=밸런싱) 해주는 장치입니다. 클라이언트와 서버풀 사이에 위치하며, 한대의 서버로 부하가 집중하지 않도록 트래픽을 관리합니다.

* 로드 밸런싱
<br> 서버에 가해지는 부하를 여러 대의 서버로 분산해서 처리하는 방식입니다. 서버를 여러대로 증설하는 Scale-out 방식을 사용하게 된다면 로드밸런싱이 필요합니다.

> Scale-up : 서버 자체의 성능을 확장하는 것
> <br>Scale-out : 기존 서버와 동일하거나 낮은 성능의 서버를 여러대로 증설하는 것

[가비아](https://m.post.naver.com/viewer/postView.naver?volumeNo=27046347&memberNo=2521903)
[AWS 페이지](https://aws.amazon.com/ko/what-is/load-balancing/#:~:text=%EB%A1%9C%EB%93%9C%20%EB%B0%B8%EB%9F%B0%EC%8B%B1%EC%9D%80%20%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98%EC%9D%84,%EC%82%AC%EC%9A%A9%EC%9E%90%EC%97%90%EA%B2%8C%20%EB%B0%98%ED%99%98%ED%95%B4%EC%95%BC%20%ED%95%A9%EB%8B%88%EB%8B%A4.)


## 로드 밸런싱의 작동 방식에 대해 설명해주세요.
* 요청과정
    1. 유저가 로드밸런서로 서비스를 요청한다.
    2. 로드밸런서는 NAT를 적용하여 IP/MAC 주소를 변조하고 실제 서버에 요청한다.
* 응답과정
    1. 서버가 로드밸런서로 받은 요청에 대한 응답을 전달한다.
    2. 로드밸런서는 NAT를 적용하여 출발지 IP 주소를 로드밸런서의 IP주소로 변조하고 유저에게 전달한다.
https://deveric.tistory.com/91


## 로드 밸런싱의 등장 배경에 대해 설명해주세요.
트래픽 증가에 대한 해결방법으로 Scale up 방식과 Scale out 방식이 있습니다. Sacle up 방식은 서버 자체 성능을 업그레이드해야 하는데, 이는 하드웨어 특성 상 한계가 존재합니다. 따라서 트래픽이 매우 커지게 된다면, Scale out 방식을 사용해야 합니다. 여러대의 서버에 트래픽이 균등하게 분산되어야 하므로 로드 밸런싱이 필요하게 되었습니다.

> Scale-up : 서버 자체의 하드웨어 성능을 업그레이드 하는 것
> <br>Scale-out : 하나의 서버가 아닌 다수의 서버를 사용하는 것


## L4와 L7 로드 밸런서 차이를 설명해주세요.
| | L4 로드밸런서 | L7 로드밸런서 |
| --- | --- | --- |
| 네트워크 계층 | Layer 4 전송계층(Transport layer) | Layer 7 응용계층(Application layer) |
| 특징 | TCP/UDP 포트 정보를 바탕으로 한다. | TCP/UDP 정보 뿐만 아니라 HTTP의 URI, FTP의 파일명, 쿠키 정보 등을 바탕으로 한다. |
| 장점 | - 데이터 안을 들여다보지 않고 패킷 레벨에서만 로드를 분산하기 때문에 속드가 빨르고 효율이 높다.<br>- 데이터의 내용을 복호화할 필요가 없기에 안전하다.<br>- L7 로드밸런서보다 가격이 저렴하다. | - 상위 계층에서 부하(로드)를 분산하기 때문에 훨씬 더 섬세한 라우팅이 가능하다.<br>- 캐싱 기능을 제공한다.<br> - 비정상적인 트래픽을 사전에 필터링할 수 있어 서비스 안정성이 높다.
| 단점 | - 패킷의 내용을 살펴볼 수 없기 때문에 섬세한 라우팅이 불가능하다.<br>- 사용자의 IP가 수시로 바뀌는 경우라면 연속적인 서비스 제공이 어렵다. |- 패킷의 내용을 복호화해야 하기 때문에 더 높은 비용이 든다.<br>- 클라이언트가 로드밸런서와 인증서를 공유해야 하기 때문에 공격자가 로드밸런서를 통해서 클라이언트 데이터에 접근할 보안 상의 위험하다. |

<!-- ## 로드밸런싱 알고리즘 -->


# Nginx
https://willseungh0.tistory.com/137

## Nginx를 사용하신 이유가 있나요?
우선 로드밸런싱을 하기 위해 Nginx를 사용했습니다. 리눅스 운영체제 하에서는 웹서버로 로드밸런싱을 할 때, 보통 Nginx랑 Apache를 사용합니다. 저는 그 중 Nginx를 선택했고, 그 이유는 Nginx는 이벤트 기반으로 요청을 비동기로 처리하기 때문에, 한 스레드가 여러 요청을 처리할 수 있어 CPU를 효율적으로 사용할 수 있고, 메모리 또한 적게 사용할 수 있습니다. 또한 요청 당 스레드를 생성하는 Apache에 비해 대규모 트래픽을 수용할 수 있기 때문입니다.

<!-- * 실제 서버 IP 주소 숨기기 위해서
* 캐싱 -->
https://sorjfkrh5078.tistory.com/289

## Nginx의 핵심 동작 원리 키워드가 무엇일까요?
* 이벤트 기반(Event-Driven) 비동기 처리 방식
* 멀티 프로세스 싱글 스레드 방식
    * 기본적으로는 멀티 프로세스 싱글 스레드 방식으로 동작하나 이용하던 스레드가 blocked될 경우 다른 스레드를 사용할 수 있게끔 스레드 풀을 설정할 수 있다.
 
https://www.nginx.com/blog/inside-nginx-how-we-designed-for-performance-scale/
https://www.nginx.com/blog/inside-nginx-how-we-designed-for-performance-scale/
https://www.nginx.com/blog/thread-pools-boost-performance-9x/


# 보안


## TLS과 SSL이 나오게 된 배경이 무엇일까요?
SSL/TLS는 전송 계층(Transport Layer)에서 보안을 제공하는 프로토콜입니다. 클라이언트와 서버가 통신을 할 때 SSL/TLS를 통해 제 3자가 메시지를 읽거나 변조하지 못하도록 합니다. 공격자가 서버를 모방하여 사용자 정보를 가로채는 네트워크 상 인터셉터를 방지할 수 있습니다. 


# 네트워크 공격


## CSRF 공격에 대해 설명해주세요
* CSRF(Cross-Site Request Forgery) 공격
    * 사용자가 자신의 의지와 무관하게 공격자가 의도한 행동을 하여 특정 웹페이지를 보안에 취약하게 하거나, 수정 삭제 등의 작업을 하게 만드는 공격 방법입니다.
<img src = https://velog.velcdn.com/images%2Fgwanuuoo%2Fpost%2F7f926efe-4b52-49d9-8dfd-78b26c0c1bb1%2FScreen%20Shot%202021-06-27%20at%202.15.28%20PM.png>

* XSS 공격과 차이
    * XSS 공격은 사용자가 웹사이트를 신용하여 악성 스크립트가 실행된다면, CSRF 공격은 반대로 특정 웹사이트가 사용자의 브라우저를 신용하여 발생하는 공격입니다.


## XSS 공격에 대해 설명해주세요
* XSS(Cross Site Scripting, 사이트간 스크립팅) 공격
    * 공격자가 웹 페이지에 악성 스크립트를 삽입하는 공격방법입니다. 이는 웹 애플리케이션이 사용자로부터 입력 받은 값을 제대로 검사하지 않고 사용할 경우 나타납니다. 주로 다른 웹사이트와 정보를 교환하는 식으로 작동하므로 사이트 간 스크립팅이라고 합니다.

CSRF and XSS are different in several ways. First, CSRF relies on the user's browser to send a request to the target site, while XSS relies on the user's browser to execute code from the attacker's site. Second, CSRF does not require the attacker to compromise the target site, while XSS does. Third, CSRF does not affect the user's browser directly, while XSS does. Fourth, CSRF can be prevented by validating the origin and integrity of the requests, while XSS can be prevented by sanitizing and escaping the input and output of the web application.

## CORS란 무엇일까요?
CORS(Cross-Origin Resource Sharing)는 서버가 웹 브라우저에서 리소스를 로드할 때 다른 오리진을 통한 로드를 제한하는 HTTP 헤더 기반 메커니즘입니다. CORS는 브라우저가 실서버가 실제 요청을 허용할지 확인하기 위해 서버에 사전 요청을 보내는 메커니즘을 사용합니다. 이러한 사전 요청에서 실제 요청에서 사용될 HTTP 메소드와 헤더를 나타내는 헤더를 보냅니다.

보안상의 이유로, 브라우저는 스크립트에 의해 시작된 cross-origin HTTP 요청을 제한합니다. 이는 동일한 오리진에서만 요청이 가능함을 의미합니다. 하지만, 다른 오리진의 응답이 올바른 CORS 헤더를 포함하고 있다면, 그 오리진으로부터 리소스 요청이 가능합니다.
<img src =https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS/cors_principle.png width = 500>

> 오리진 : 프로토콜과 호스트 이름, 포트의 조합

https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS

# 브라우저에 www.google.com을 입력했을 때 일어나는 일에 대해 설명해주세요

1. 사용자가 웹브라우저 검색창에 www.google.com 입력
2. 웹브라우저는 캐싱된 DNS 기록들을 통해 해당 도메인주소와 대응하는 IP주소를 확인
    * 캐싱된 기록이 없을 경우, 다음 단계로 넘어갑니다.
3. 웹브라우저가 HTTP를 사용하여 DNS에게 입력된 도메인 주소를 요청
4. DNS가 웹브라우저에게 찾는 사이트의 IP주소를 응답
    * ISP(Internet Service Provider)의 DNS서버가 호스팅하고 있는 서버의 IP주소를 찾기 위해 DNS query를 보냅니다.
5. 웹브라우저가 웹서버에게 IP주소를 이용하여 html문서를 요청
6. 웹 애플리케이션 서버(WAS)와 데이터베이스에서 웹페이지 작업을 처리
7. 위의 작업처리 결과를 웹서버로 전송
8. 웹서버는 웹브라우저에게 html 문서결과를 응답
9. 웹브라우저는 화면에 웹페이지 내용물 출력
<!-- https://velog.io/@eassy/www.google.com%EC%9D%84-%EC%A3%BC%EC%86%8C%EC%B0%BD%EC%97%90%EC%84%9C-%EC%9E%85%EB%A0%A5%ED%95%98%EB%A9%B4-%EC%9D%BC%EC%96%B4%EB%82%98%EB%8A%94-%EC%9D%BC

https://velog.io/@tnehd1998/%EC%A3%BC%EC%86%8C%EC%B0%BD%EC%97%90-www.google.com%EC%9D%84-%EC%9E%85%EB%A0%A5%ED%96%88%EC%9D%84-%EB%95%8C-%EC%9D%BC%EC%96%B4%EB%82%98%EB%8A%94-%EA%B3%BC%EC%A0%95 -->

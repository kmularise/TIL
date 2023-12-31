# 식별과 인증 어떻게 다를까?

# 웹 브라우저를 식별할 수 있는 요소들

## 1. HTTP 헤더
사용자에 대한 정보를 전달하는 HTTP 헤더에는 크게 7가지가 있다.
| 헤더 이름 | 헤더 타입	| 설명 |
| --------- | --------- | --------- |
| From | 요청 | 사용자의 이메일 주소 |
| User-Agent | 요청 | 사용자의 브라우저 |
| Referer | 요청 | 사용자가 현재 링크를 타고 온 근원 페이지 |
| Authorization | 요청 | 사용자의 이름과 비밀번호(뒤에서 다룸, 발전된 헤더) |
| Client-ip | 확인(요청) | 클라이언트이 IP주소 |
| X-Forwarded-For | 확인(요청) | 클라이언트의 IP주소 |
| Cookie | 확인(요청) |서버가 생성한 ID 라벨 |

각각의 헤더에 대해 살펴보자.
### From
From 헤더는 사용자 이메일 주소를 포함한다. 이상적으로는 각 사용자가 서로 다른 이메일 주소를 가지므로 From 헤더로 사용자를 식별할 수 있다. **그러나 악의적인 서버가 이메일 주소들을 모아서 스팸 매일을 발송하는 문제 때문에 From 헤더를 보내는 웹 브라우저는 많지 않다고 한다.**

### User-Agent
사용자가 쓰고 있는 웹 브라우저의 이름과 버전 정보, 어떤 경우에는 운영체제에 대한 정보까지 포함하여 서버에게 알려준다.

구체적으로 다음과 같이 구성된다. 
```
User-Agent: <product> / <product-version> <comment>


<product>: product 식별자
<product-version>: product 버전 번호
<comment>: 추가적인 product의 정보
```
크롬 브라우저에서의 예시는 다음과 같다.
```
Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36
```

이는 특정 웹 브라우저에서 제대로 동작하도록 그것들의 속성에 맞추어 콘텐츠를 최적화하는 데 유용할 수 있지만, User-Agent 자체로는 특정 사용자를 식별하는 데는 크게 도움되지 않는다.

### Referer
사용자가 현재 페이지로 유입하게 한 웹페이지의 URL을 가르킨다. Referer헤더 자체만으로 사용자를 식별할 순 없지만, 사용자가 이전에 어떤 페이지에 방문했었는지 알려준다.

From, User-Agent, Referer는 그 자체로는 웹 브라우저를 식별하기에는 부족한 정보를 가진다.

### 2. 클라이언트 IP 주소
클라이언트의 IP 주소를 사용하는 방식은 사용자가 확실한 IP 주소를 갖고있고, IP 주소가 잘 바뀌지 않고, 웹 서버가 요청마다 클라이언트의 IP 주소를 알 수 있을 때 웹 브라우저를 식별할 수 있다. 클라이언트의 IP 주소는 보통 HTTP 헤더에 없지만 웹 서버는 HTTP 요청을 보내는 TCP 커넥션의 반대쪽 IP를 알아낼 수 있다.

그러나 클라이언트 IP 주소 자체만으로는 웹 브라우저를 식별하는데 한계가 있다. 그 이유는 다음과 같다.

* 동일한 IP 주소 환경에서 여러 브라우저가 요청을 보낼 경우, 클라이언트를 식별할 수 없다.
* 많은 ISP(인터넷 서비스 제공 사업자)는 사용자가 인터넷에 접속하면 IP를 할당한다. 시간에 따라 IP 주소가 변하게 되는데 웹 브라우저 식별에 어려움이 있을 수 있다.
* NAT는 클라이언트의 실제 IP 주소를 방화벽 뒤에 숨기고, 클라이언트의 실제 IP 주소를 내부에서 사용하는 방화벽 IP 주소로 변환하여 실제 IP 주소를 방화벽 뒤로 숨긴다. 따라서 서버가 알 수 있는 IP주소는 방화벽 IP주소로 실제 웹 브라우저의 IP와는 다르다.
* HTTP 프록시와 게이트웨이는 원래 서버에 새로운 TCP를 연결한다. 이때, 웹 서버는 클라이언트의 IP 주소 대신 프록시의 IP주소를 본다. 일부 프록시는 클라이언트의 IP 주소를 보존하기 위해 Client-Ip나 X-Forward-For HTTP 같은 헤더를 사용하지만 모든 프록시가 이런식으로 동작하진 않는다.

```
NAT (Network Address Translation) : 네트워크 주소 변환기
1. 공공망과 연결되는 사용자들의 고유한 사설망을 공격자들로부터 보호하기 위해 사용
2. 인터넷의 공인 IP주소를 절약하고자 하는 목적으로 사용
```
## 쿠키
쿠키는 서버가 사용자의 웹 브라우저에 전송하는 작은 데이터 조각이다. 웹 브라우저는 쿠키들을 저장해 놓았다가, 동일한 서버에 재요청 시 저장된 데이터를 함께 전송한다. 쿠키도 웹브라우저를 식별할 때 사용될 수 있다. 

하지만 쿠키는 쉽게 조작될 수 있다. 웹 브라우저의 자체적인 개발도구를 사용하면 쿠키 값을 변경해서 쿠키를 서버에 전송할 수 있기 때문이다. 

## 사용자 로그인
IP 주소로 사용자를 식별하려는 수동적인 방식보다, 웹 서버는 사용자 이름과 비밀번호로 인증할 것을 요구해서 사용자에게 명시적으로 식별 요청 한다.

그러나 사용자는 매번 로그인할 때마다 아이디와 비밀번호를 기억해야 하고, 방문자수 측정을 인증을 하지 않는 사용자도 포함해서 측정하는 상황에서는 사용자 로그인을 웹 브라우저 식별에 사용할 수 없다.

## 브라우저 지문 
브라우저 지문은 웹사이트에서 브라우저와 운영체제의 고유 기능을 수집하고 결합하여 특정 브라우저(더 나아가 특정 사용자)를 식별하는 관행이다. 다음과 같은 요소들이 포함될 수 있다.

* 브라우저 버전
* 시간대 및 기본 언어
* 시스템에서 사용할 수 있는 비디오 또는 오디오 코덱 세트
* 시스템에 설치된 글꼴
* 브라우저 설정 상태
* 컴퓨터의 디스플레이 크기 및 해상도

웹사이트는 디바이스에서 JavaScript 및 CSS 를 실행하여 이와 같은 정보를 검색할 수 있으며, 이 데이터를 결합하여 브라우저에 대한 고유한 지문을 생성할 수 있다. 또한 이를 통해 웹에서 사용자를 추적할 수 있다.

언뜻 보면, 웹 브라우저를 식별할 수 있는 좋은 방법으로 보인다. 그러나 브라우저 지문은 사용자가 추적을 원치 않을 때도 만료기한 없이 추적될 수 있어 개인정보보호를 침해할 수 있다. 악성적인 온라인 추적의 한 유형이다.

특히 브라우저 지문을 얻는 방법 중 하나인 캔버스 지문(Canvas Fingerprinting)은 HTML에서 캔버스 요소를 이용하여 브라우저 지문을 생성한다. 캔버스 지문은 컴퓨터에 아무것도 로드하지 않으므로 컴퓨터나 장치에 저장되지 않고 다른 곳에 저장되어 있어 사용자가 데이터를 삭제할 수 없다.

## 방문자수를 측정하려면 어떤 식별방법을 사용해야 할까?
쿠키를 웹 브라우저 식별방법에 사용할 경우, 웹 브라우저가 쿠키를 쉽게 조작할 수 있다. 

## 느낀점

## 참고자료
*  HTTP 완벽가이드 11장
* https://www.mozilla.org/ko/firefox/features/block-fingerprinting/
* https://re-date.com/page-81/page-84-2/
* https://www.moonkorea.dev/Javascript-canvas-api%EB%A1%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-%ED%8E%B8%EC%A7%91%ED%95%98%EA%B8%B0

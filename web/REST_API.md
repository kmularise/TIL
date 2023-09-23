# REST API
* RESTful한 API, 일련의 특징과 규칙 등을 지키는 API, REST 아키텍쳐 스타일을 따르는 API
* Roy Thomas Fielding이 쓴 논문에서 처음으로 등장한 개념
    * [논문 URL] (https://www.ics.uci.edu/~fielding/pubs/dissertation/fielding_dissertation.pdf)

## REST API의 특징

### 1. Uniform-Interface
API에서 자원들은 각각의 독립적인 인터페이스를 가지며 각각의 자원들이 URL 자원 식별, 표현을 통한 자원 조작, Self-descriptive messages, HATEOAS 구조를 가지는 것을 말한다.

독립적인 인터페이스라는 것은 서로 종속적이지 않은 인터페이스를 말한다.
예를 들어 웹페이지를 변경했다고 웹 브라우저를 업데이트하는 일은 없어야 하고, HTTP 명세나 HTML 명세가 변경되어도 웹페이지는 잘 작동해야 한다.

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

### 2. Stateless
* 이 규칙은 HTTP 자체가 Stateless 이기 때문에 HTTP를 이용하는 것만으로도 만족된다.
* 이는 REST API를 제공해주는 서버는 세션(session)을 해당 서버 쪽에 유지하지 않는다는 의미이다.

### 3. Cacheable
* HTTP는 원래 캐싱이 된다. 
    * 아무런 설정을 하지 않으면 자동적으로 캐싱이 된다.
    * 웹페이지를 새로 고침하면 304가 뜨면서 원래 있던 js와 css 이미지 등을 불러오는 것을 볼 수 있다.
        * 이는 HTTP 메소드 중 GET에 한정되며 'Cache-Control:max-age=100'(100초) 이런 식으로 한정된 시간을 정할 수 있으며 캐싱된 데이터가 유효한지 판단하기 위해 'Last-modified'와 'Etag'라는 헤더값을 쓴다. 'Etag'는 전달되는 값에 태그를 붙여 캐싱되는 자원인지를 확인해주는 것이다.

> HTTP 헤더의 Cache-control = no-store 로 하게 되면 캐싱이 안된다. 기본적으로 cache-control = public 으로 되어 있기 때문에 캐싱이 되며 HTTP header를 기반으로 캐싱 컨트롤을 하는 게 중요하다.

### 4. Client-Server 구조
클라이언트와 서버가 서로 독립적인 구조를 가져야 한다.
* 이는 HTTP를 통해 가능한 구조이다.
* 서버에서 HTTP 표준만 지킨다면 웹에서는 그에 따른 화면이 잘 나타나게 된다.
* 서버는 API를 제공하고 그 API에 맞는 비즈니스 로직을 처리하면 된다.
* 클라이언트에서도 HTTP로 받는 로직만 잘 처리하면 된다.

### 5. Layered System
계층구조로 나눠져 있는 아키텍처를 뜻한다. WEB 기반 서비스이면 보통 이러한 시스템을 구축한다.
<img width="757" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/3789441e-2712-476d-a3b0-eb4ae3794d08">

## REST API의 URI 규칙
'표현을 통한 자원 조작'을 기반으로 URI 규칙이 만들어졌다.
자원을 표기하는 URI의 아래 6가지 규칙을 적용해야 한다.
1. 동작은 HTTP 메소드로만 해야 하고 URI에 해당 내용이 들어가면 안된다. 수정은 put, 삭제는 delete, 추가는 post, 조회는 get을 이용해야 한다.
2. .jpg, .png 등 확장자는 표시하지 말아야 한다.
3. 동사가 아닌 명사로만 표기해야 한다. 
4. 계층적인 내용을 담고 있어야 한다.
5. 대문자가 아닌 소문자로만 사용하며 너무 길 경우에 바를 써야 할 경우 '_'가 아닌 '-'를 사용한다.
6. HTTP 응답 상태코드를 적재적소에 활용한다. 성공시에는 200, 리다이렉트를 301 등..

<!-- ## REST
분산 하이퍼미디어 시스템(예: 웹)을 위한 아키텍쳐 스타일

### 아키텍쳐 스타일
제약조건의 집합

### REST를 구성하는 스타일
* clinet-server
* stateless
* cache
* **uniform interface**
* layered system
* code-on-demand (optional)


## REpresentational State Transfer
>  a way of providing interoperability between computer systems on the Internet

역사

WEB(1991)
Q : 어떻게 인터넷에서 정보를 공유할 것인가?
A : 정보들을 하이퍼텍스트로 연결한다.
표현 형식 : HTML
식별자 : URI
전송방법  : HTTP -->


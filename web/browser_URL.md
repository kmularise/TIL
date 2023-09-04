# www.naver.com을 주소창에 치면 무슨 일이 일어날까?
브라우저에서 주소창에 URL 입력 시 어떤 일이 일어날까?

![image](https://github.com/kmularise/TIL/assets/106499310/76c742c0-d469-42df-816d-f83642c17cbd)

* 리다이렉트, 캐싱, DNS, IP 라우팅, TCP 연결 구축을 거쳐 요청, 응답이 일어나는 TTFB(Time to First Byte)가 시작된다.
* 이후 컨텐츠를 다운받게 되고 브라우저 렌더링 과정을 거쳐 네이버라는 화면이 나타나게 된다.
> TTFB(Time to First Byte) 참고 정도로만 알아두기
## 리다이렉트
* 리다이렉트가 있다면 리다이렉트를 진행하고 없다면 그대로 해당 요청에 대한 과정이 진행된다.

## 캐싱
* 해당 요청이 캐싱이 가능한지 파악한다. 캐싱이 이미 된 요청이라면 캐싱된 값을 반환하며 캐싱이 되지 않은 새로운 요청이라면 그 다음 단계로 넘어간다.
* 캐싱은 요청된 값의 결과값을 저장하고 그 값을 다시 요청하면 다시 제공하는 기술이다. 브라우저 캐시와 공유 캐시로 나뉜다.
    * 브라우저 캐시 : 쿠키, 로컬 스토리지 등을 포함한 캐시, 개인 캐시라고도 한다.
        * 사용자가 HTTP를 통해 다운로드하는 모든 문서를 브라우저 자체가 보유
        * 304 : 요청된 리소스를 재전송할 필요가 없음
            * 캐시된 자원으로부터의 암묵적인 리다이렉션
    * 공유 캐시 : 클라이언트와 서버 사이에 있으며 사용자 간에 공유할 수 있는 응답을 저장할 수 있다.
        * ex) 서버 앞단의 프록시 서버가 캐싱을 하는 것

<img width="1192" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/ea522119-31e9-4051-8571-bd81e55fbd95">

![image](https://github.com/kmularise/TIL/assets/106499310/422cd996-5c8d-4921-9cb4-0f2547c6da63)

## DNS(Domain Name System)
DNS는 계층적인 도메인 구조와 분산된 데이터베이스를 이용해서 FQDN을 인터넷 프로토콜인 IP로 바꿔주는 시스템이다. 리졸버와 네임서버 등으로 이루어져 있다.
* 리졸버 : DNS 관련 요청을 네임서버로 전달하고 해당 응답값을 클라이언트에게 전달
* 네임서버 : 도메인을 IP로 변환하는 네임서버
* FQDN(Fully Qualified Domain Name)
    * 호스트와 도메인이 합쳐진 완전한 도메인 이름

ex) www.naver.com
<br>www = host 주소 또는 Third level 도메인, sub 도메인이라고 불림
<br>naver = second level 도메인
<br>com = top level 도메인

<img width="935" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/682e49b9-e7b2-4ce0-8395-f8f0a02dc7e1">

예를 들어 www.naver.com에 DNS 쿼리가 오면 오른 쪽부터 역순으로 [Root DNS] -> [.com DNS] -> [.naver DNS] -> [.www DNS] 과정을 거쳐 주소를 찾아 IP 주소를 매핑한다.

### DNS 캐싱
미리 해당 도메인이름을 요청했다면 로컬 PC에 자동적으로 저장된다. 브라우저 캐싱과 OS 캐싱이 있다.

## IP 라우팅 & ARP
해당 IP를 기반으로 라우팅, ARP 과정을 거쳐 실제 서버를 찾는다.

## TCP 연결 구축
TCP 3 Way-Handshake 및 SSL 연결(HTTPS 이용하는 경우)등을 통해 연결을 설정한다. 이후 요청을 보낸후 해당 요청 서버로부터 응답을 받는다.

> TCP 연결은 HTTP/2까지 일어난다. HTTP/3는 TCP 연결이 아닌 QUIC 연결이 일어난다.

## 콘텐츠 다운로드
요청한 컨텐츠를 서버로부터 다운받는다.

## 브라우저 렌더링
받은 데이터를 바탕으로 부라우저 엔진이 브라우저 렌더링 과정을 거쳐 화면을 만든다.

## 참고자료
https://developer.mozilla.org/ko/docs/Web/HTTP/Status/304
https://developer.mozilla.org/en-US/docs/Web/HTTP/Caching


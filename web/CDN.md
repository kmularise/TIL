# CDN
* CDN : Content Delivery Network
* 전 세계에 분산되어 있는 서버 네트워크
* CDN은 사용자가 리소스를 다운로드 할 수있는 대체 서버 노드를 제공하여 작동
* 이한 노드는 전 세계에 퍼져 있기 때문에 지연 시간 감소로 컨텐츠의 빠른 응답과 다운로드 시간을 가능하게 한다.
* 1대의 CDN 서버가 장애가 나도 다른 CDN 서버로 재연결하기 때문에 안정성을 보장한다.

## 목적
* CDN 노드의 목적은 사이트의 정적 컨텐츠 (이미지, CSS / JS 파일, 구성 요소)를 캐시하는 것
* 이러한 캐시기능은 프록시와 유사하다.
* 해외에서 요청이 들어왔을 때, 이전에 CDN 노드에 방문한 기록이 있을 경우, 해당 노드에서 바로 처리하여 보여준다.
* 이것을 가능하게 하는 기술 : GSLB

## GSLB(Global Server Load Balancing)
* DNS의 발전된 형태 (DNS : 도메인 이름을 IP 주소로 변환)

### DNS와 GSLB의 차이
[재해 복구]
* DNS는 서버의 상태를 알 수 없어서 서비스를 이용하지 못하는 유저가 생길 수 있다.
* GSLB는 서버의 상태를 모니터링하고 실패한 서버의 IP는 응답에서 제외하므로, 유저는 서비스를 계속 이용할 수 있다.

[로드밸런싱]
* DNS는 라운드 로빈 방식을 사용하기 때문에 정교한 로드 밸런싱이 어렵다.
* GSLB는  서버의 로드를 모니터링 하기 때문에 로드가 적은 서버의 IP를 반환하는 식으로 정교한 로드밸런싱을 할 수 있다.

[레이턴시 기반]
* DNS는 라운드 로빈 방식을 사용하여 유저는 네트워크상에서 멀리 떨어진 위치의 서버로 연결될 수도 있다.
* GSLB는 각 지역별로 서버에 대한 레이턴시(latency) 정보를 가지고 있기 때문에 유저가 접근을 하면, 유저의 지역으로 부터 가까운(더 작은 레이턴시를 가지는) 서버로 연결된다.

[위치기반 서비스]
* DNS에서 유저는 Round Robin하게 서버로 연결
* GSLB는 유저의 지역정보를 기반으로, 해당 지역을 서비스하는 서버로 연결 

### GSLB 주요 기술 요소
* Healthcheck
![image](https://github.com/kmularise/TIL/assets/106499310/198c958b-accf-434a-8f20-1a18689a7f8e)
* GSLB는 등록된 호스트들에 대해서 주기적으로 health check를 수행한다.
* 호스트가 실패할 경우 DNS 응답에서 해당 호스트를 제거한다.
* 실패한 호스트로의 접근을 막기 때문에 서버의 가용성을 높일 수 있다.
* 그림에서는 등록된 서버 1.1.1.1과 2.2.2.2를 주기적으로 모니터링 하면서 상태를 확인한다.

## 참고자료
https://brownbears.tistory.com/408
https://www.joinc.co.kr/w/man/12/GSLB
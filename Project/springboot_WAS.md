## WAS와 SpringBoot 내장 WAS(Servlet Contatiner) 비교
* 웹 서버는 정적인 컨텐츠만 제공하기 때문에 클라이언트의 요구에 유연하게 대처할 수 없다.
* 다양한 클라이언트의 요구에 유연하게 대처하기 위해 DB와 연결해 데이터를 주고받거나 데이터 조작을 하여 동적인 페이지를 생성해 응답하기 위해 WAS를 사용하고 있다.

## 스프링부트에 내장된 WAS(Web Application Server) 비교
Spring Boot 웹 애플리케이션은 내장된 WAS를 갖고 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/f0ffdc9c-225c-49cf-bedd-e50b1a0901fd)

* servlet statck apllication : Tomcat, Jetty, Undertow 
* reactive stack application : Netty, Tomcat, Jetty, Undertow
* 보통 내장된 서블릿 컨테이너가 있는 jar로 실행되거나 컨테이너 내부의 war 파일로 실행되는데, 서블릿 컨테이가 내장된 jar를 중점으로 둘 것이다.

## 몇가지 지표
<img width="553" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/68ce272e-3773-435a-831e-5ec9c7c56772">

* 메모리 사용량은 Tomcat, Jetty, Undertow는 비슷했고, Jetty가 가장 적은 메모리 사용량을 가졌다.
* Tomcat, Jetty, Undertow의 성능이 비슷하나, Undertow가 확실히 가장 빠르고, Jetty가 살짝 느렸다.
* 기준을 뭘로 두냐에 따라 어떤 WAS(서블릿 컨테이너)를 사용할지가 달라지고, 정교하게 하기 위해선 JMeter, Gatling 같은 툴을 사용하면 좋다.

## Tomcat VS Jetty
### Tomcat의 장점
* 모든 플랫폼에서 Tomcat을 설치하고 구성하는 것이 매우 쉽다.
* WAS의 거의 모든 기능을 제공하면서 가볍다.
* 기능을 확장할 수 있는 다양한 사용자 정의 옵션이 있다.
* 오랫동안 사용되어온 제품이라 매우 안정적
* 잘 문서화되어 있어 배우기 쉽다.
* 간단한 웹 애플리케이션 배포 옵션을 제공
* 자바 엔터프라이즈를 지원
* 사용자에게 추가적인 보안 수준을 제공
### Tomcat의 단점
* 메모리 누수 측면에서 몇 가지 문제가 있고, 메모리 설정에 문제가 발생할 수 있다.
* 클러스터 지원이 충분하지 않다.
* 이해하기 쉬운 오류 메시지를 제공하지 않아서 문제를 해결하는 동안 각 로그파일을 확인해야 한다.

### Jetty의 장점
* 유연성이 있어 다른 프레임워크에 일부로 적용될 수 있다.
* 클라우드 작업 지원
* Jetty는 사용자 친화적이며 Tomcat보다 더 나은 인터페이스 보유
* Tomcat에 비해 동시 사용자를 처리하는데 더 좋다.
* 필요한 지식과 기술이 거의 없기 때문에 사용자가 쉽게 사용할 수 있다.
### Jetty 단점
* Jetty에는 시작 시간 문제가 있다.
## 참고자료
https://docs.spring.io/spring-boot/docs/2.1.17.RELEASE/reference/html/howto-embedded-web-servers.html
https://wildeveloperetrain.tistory.com/105
https://zepinos.tistory.com/50
https://www.baeldung.com/spring-boot-servlet-containers
https://kchanguk.tistory.com/2
https://kchanguk.tistory.com/2
https://cloudinfrastructureservices.co.uk/tomcat-vs-jetty-whats-the-difference/
https://medium.com/@ijayakantha/boost-microservices-performance-with-undertow-server-e1165504df99
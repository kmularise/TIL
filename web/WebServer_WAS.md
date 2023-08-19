# 웹 서버(Web Server), 웹 애플리케이션 서버(WAS)

![webServer](https://github.com/kmularise/TIL/assets/106499310/b175960f-12d1-4a4a-85ef-2b73d2e9af47)


* HTTP 기반으로 동작
* 정적 리소스 제공, 기타 부가기능
* 정적(파일) HTML, CSS, JS, 이미지, 영상
* 예) NGINX, APACHE


# 웹 애플리케이션 서버(WAS - Web Application Server)
* HTTP 기반으로 동작
* 웹 서버 기능 포함(정적 리소스 제공 가능)
* 프로그램 코드를 실행해서 애플리케이션 로직 수행 : 
    * 동적 HTML, HTTP API(JSON)
    * 서블릿, JSP, 스프링 MVC
* 예) 톰캣(Tomcat) Jetty, Undertow

## 웹 서버 VS 웹 애플리케이션 서버(WAS)
* 웹 서버는 정적 리소스(파일), WAS는 애플리케이션 로직
* 사실은 둘의 용어도 경계가 모호함
    * 웹 서버도 프로그램을 실행하는 기능을 포함하기도 한다.
    * 웹 애플리케이션 서버도 웹 서버의 기능을 제공함
* 자바는 서블릿 컨테이너 기능을 제공하면 WAS
    * 그러나 서블릿 없이 자바코드를 실행하는 서버 프레임워크도 있음
* **WAS는 애플리케이션 코드를 실행하는데 더 특화**

# 1. 웹 시스템 구성 - WAS, DB
* WAS, DB 만으로 시스템 구성 가능
* WAS는 정적 리소스, 애플리케이션 로직 모두 제공 가능

[문제점]
* WAS가 너무 많은 역할을 담당, 서버 과부하 우려
* 가장 비싼 애플리케이션 로직이 정적 리소스 때문에 수행이 어려울 수 있음
* WAS 장애 시 오류 화면도 노출 불가능

![image](https://github.com/kmularise/TIL/assets/106499310/e67c5a09-1da4-4b67-b8fa-04f1b33c37ed)

# 2. 웹 시스템 구성 - WEB, WAS, DB
* 정적 리소스는 웹 서버가 처리
* 웹 서버는 애플리케이션 로직 같은 동적인 처리가 필요하면 WAS에 요청을 위임
* WAS는 중요한 애플리케이션 로직 처리 전담

![image](https://github.com/kmularise/TIL/assets/106499310/87559b8b-ad3c-4974-95ac-6b9015858d94)

* 효율적인 리소스 관리
    * 정적 리소스가 많이 사용되면 Web 서버 증설
    * 애플리케이션 리소스가 많이 사용되면 WAS 증설

![image](https://github.com/kmularise/TIL/assets/106499310/9b1d6bb5-bf5f-4095-9a67-9d14cd1497d3)

* 정적 리소스만 제공하는 웹 서버는 잘 죽지 않음
* 애플리케이션 로직이 동작하는 WAS 서버는 잘 죽음
* WAS, DB 장애 시 WEB 서버가 오류 화면 제공 가능

![image](https://github.com/kmularise/TIL/assets/106499310/352311b0-804d-4baf-9db8-cb19ddb357bd)
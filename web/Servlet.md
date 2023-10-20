# 서블릿

## 서블릿이란?
* 클라이언트의 요청을 처리하고, 그 결과를 다시 클라이언트에게 전송하는 Servlet 클래스의 구현 규칙을 지킨 자바 프로그래밍 기술
> A servlet is a Java programming language class that is used to extend the capabilities of servers that host applications accessed by means of a request-response programming model. 

* 서블릿은 모든 유형의 요청을 처리하고 그 결과를 전송할 수 있으나, 서블릿은 보통 웹을 구현하기 위해 사용되는 기술이다. 웹을 구현하기 위해 HTTP 프로토콜에 특화된 서블릿 클래스를 제공하고 있다.
> Although servlets can respond to any type of request, they are commonly used to extend the applications hosted by web servers. For such applications, Java Servlet technology defines HTTP-specific servlet classes.

<!-- ## 서블릿의 역할

## 서블릿 동작 방식

## 서블릿 객체 -->

## 서블릿 생명주기
1. init : 서블릿 인스턴스 생성
    * 클라이언트의 요청이 들어오면 컨테이너는 해당 서블릿이 메모리에 있는지 확인하고, 없을 경우 init()메소드를 호출하여 적재한다.
    * init() 메소드는 처음 한번만 실행되기 때문에, 서블릿의 스레드에서 공통적으로 사용해야하는 것이 있다면 오버라이딩하여 구현하면 된다.
    * 실행 중 서블릿이 변경될 경우, 기존 서블릿을 파괴하고 init()을 통해 새로운 내용을 다시 메모리에 적재한다.

2. service : 실제 기능 수행
    * 클라이언트의 요청에 따라서 service()메소드를 통해 요청에 대한 응답이 doGet()가 doPost()로 분기된다.
    * 이 때, 가장 먼저 처리하는 과정으로 생성된 HttpServletRequest, HttpServletResponse에 의해 request와 response객체가 제공된다.

3. destroy : 서블릿 인스턴스 삭제
    * 컨테이너가 서블릿에 종료 요청을 하면 destroy() 메소드가 호출된다.
    * 종료시에 처리해야하는 작업들은 destroy()메소드를 오버라이딩하여 구현하면 된다.
    * 보통 서블릿 컨테이너가 종료되는 시점에 호출하며, 특정 servlet 로드/언로드 시에도 사용된다.

## 참고자료
* [JavaEE 튜토리얼](https://docs.oracle.com/javaee/5/tutorial/doc/bnafe.html)
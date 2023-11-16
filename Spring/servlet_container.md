## 웹서버(Web Server)
* HTTP 프로토콜 지원
* 정적 리소스만 지원 (이미지, HTML 파일), 동적인 리소스는 처리하기 어려워서, 웹 서버에서는 처리할 수 없으므로 서블릿 컨테이너로 보내준다.
* 예시 : Apache, Nginx

![image](https://github.com/kmularise/TIL/assets/106499310/363763b9-7def-4797-861a-45902481b280)

## 서블릿 컨테이너(Servlet Container)
* 서블릿 컨테이너 = 웹 컨테이너(Web Container), 컨테이너(Container)
* JSP/Servlet API를 지원
* 서블릿 컨테이너는 서블릿의 생명주기를 관리하는 역할을 수행한다. 
    * URL을 특정 서블릿에 매핑하고 URL 요청한 클라이언트가 적절한 접근 권한을 가지고 있는지 확인한다.
* 예시 : Apache Tomcat, Jetty, WildFly
* 웹서버 안에서 동작한다. (예시 : 톰캣은 Nginx 안에서 작동)
* 정적 콘텐츠를 생성하고 응답을 반환한다.

## 서블릿, 서블릿 필터, 서블릿 리스너, 스프링 컨테이너(IOC 컨테이너)

![image](https://github.com/kmularise/TIL/assets/106499310/7ea095ff-8e4a-43c4-a26d-3018849417fe)

## ServletContext vs. ApplicationContext
### ServletContext
* 서블릿 컨테이너가 시작되면 웹 애플리케이션을 배포하고 로드한다.
* 웹 애플리케이션이 로드되면 서블릿 컨테이너는 ServletContext를 한번 생성하여 서버의 메모리에 보관한다.
* 그 다음, 서블릿 컨테이너는 init() 메소드를 호출하여 모든 필터, 서블릿과 리스너를 초기화하고 로드한다.
* 서블릿 컨테이너가 모든 초기화 단계를 완료하면 ServletContextListener의 contextInitialized()가 호출된다.

### ApplicationContext
* ApplicationContext는 스프링 컨테이너를 나타내며 앞서 언급한 빈을 인스턴스화, 구성 및 어셈블하는 역할을 담당한다.
* Spring Boot는 다른 초기화 순서를 따른다.
* 서블릿 컨테이너의 라이프사이클에 연결하는 대신, Spring Boot는 Spring Boot를 부트스트랩하고 내장된 서블릿 컨테이너를 사용하기 위해 Spring 설정을 활용한다. 
* 필터 및 서블릿 선언은 스프링 설정에서 탐지되고 서블릿 컨테이너에 등록된다.


```java
public abstract class SpringBootServletInitializer implements WebApplicationInitializer {
@Override
public void onStartup(ServletContext servletContext) throws ServletException {
    // Logger initialization is deferred in case an ordered
    // LogServletContextInitializer is being used
    this.logger = LogFactory.getLog(getClass());
    WebApplicationContext rootApplicationContext = createRootApplicationContext(servletContext);
    if (rootApplicationContext != null) {
    servletContext.addListener(new SpringBootContextLoaderListener(rootApplicationContext, servletContext));
    }
    else {
    this.logger.debug("No ContextLoaderListener registered, as createRootApplicationContext() did not "
        + "return an application context");
    }
    }
}
```

## 자바 웹 및 스프링 개요
### Apache 웹 서버 
정적 콘텐츠를 효율적으로 제공 
### 톰캣:
* 동적 콘텐츠를 제공하며 정적 콘텐츠도 처리할 수 있지만 효율은 낮다.
* 서블릿 컨테이너로 작동한다.
* 세 가지 주요 구성 요소: 필터, 서블릿 및 리스너
    * 톰캣(서블릿 컨테이너)에 요청이 들어오면 서블릿 필터에서 가장 먼저 처리된다.
    * 서블릿이 요청을 처리하고 응답을 생성한다.
### 스프링 웹 애플리케이션
* 두 가지 유형의 컨테이너가 있으며 각각은 다르게 설정되고 초기화된다.
    * IOC 컨테이너 :
        * 빈들을 인스턴스화하고 구성 및 조립하는 역할을 한다.
        * 애플리케이션 당 하나의 ApplicationContext가 있다.
    * MVC 컨테이너 :
        * 각 DispatcherServlet은 자체 WebApplicationContext를 가지며 최상위 WebApplicationContext에서 이미 정의된 모든 빈을 상속한다.
        * 이렇게 상속된 빈들은 서블릿의 특정한 범위 내에서 오버라이드될 수 있으며 특정 서블릿 인스턴스에서 새로운 스코프 범위를 정의할 수 있다.
        * DispatcherServlet은 모든 요청을 처리하고 적절한 채널로 보낸다.


## 서블릿 컨테이너, 톰캣
### 서블릿 컨테이너 생명 주기
* 애플리케이션에 서블릿 리스너를 등록하여 애플리케이션이 시작되거나 종료될때 알릴 수 있다.
* 이를 통해 서블릿은 이러한 이벤트를 수신해서 이벤트가 발생할 때 특정 동작을 수행할 수 있다.
* 컨테이너 이벤트를 기반으로 동작하는 리스너를 만들려면 ServletContextListener 인터페이스를 구현한 클래스를 개발해야 한다.
* 구현해야 할 메소드는 contextInitialized 및 contextDestroyed 이며 두 메소드들은 서블릿 컨테이너가 초기화되거나 종료될 때 자동으로 호출된다.
* 리스너를 컨테이너에 등록하려면 다음과 같은 기술 중 하나를 사용할 수 있다.
1. @WebListener 어노테이션 활용
2. web.xml에 리스너 등록
3. ServletContext에 정의된 addListener 메소드 사용


```java
@WebListener
public class StartupShutdownListener implements ServletContextListener {

public void contextInitialized(ServletContextEvent event) {
    System.out.println("Servlet startup...");
    System.out.println(event.getServletContext().getServerInfo());
    System.out.println(System.currentTimeMillis());
    sendEmail("Servlet context has initialized");
}

public void contextDestroyed(ServletContextEvent event) {
    System.out.println("Servlet shutdown...");
    System.out.println(event.getServletContext().getServerInfo());
    System.out.println(System.currentTimeMillis());
    sendEmail("Servlet context has been destroyed...");
}

// notify
private void sendEmail() { }
```

## 서블릿 컨텍스트
* 서블릿 컨테이너와 상호작용(예: MIME type 파일 얻기, 요청 디스패치, 로크 파일 쓰기)하기 위해 서블릿이 사용할 메소드를 정의한다.
* 웹 애플리케이션 당, JVM 당 하나의 컨텍스트가 있다. 웹 애플리케이션은 서버의 URL namespace의 특정 하위 집합에 설치된 서블릿과 컨텐츠의 컬렉션이다.

## 톰캣은 요청을 어떻게 처리하는가? How Tomcat hanldes requests


## 서블릿
* 서블릿은 자바 요청에 응답하는 클래스다. 주로 HTTP 웹 요청을 처리한다.
* 서블릿은 사용할 수 있는 상태가 되기 위해서는 서블릿 컨테이너에 배포되어야 한다.
* 서블릿은 메소드의 구현부에서 처리를 수행한 다음 클라이언트에 응답을 반환한다. <!-- 완성해야함 -->

## 서블릿 생명주기
1. 요청이 서블릿 컨테이너에서 감지되면 서블릿 클래스는 클래스 로더를 통해 로드된다.
    * 서블릿 컨테이너는 서블릿 생성자를 호출한다.
    * 서블릿 생성자는 매개변수가 없는 생성자(기본 생성자)를 가지고 있어야 한다.
2. 서블릿 컨테이너는 서블릿을 초기화하고 서블릿은 사용할 수 있는 상태가 된다.
3. init 메소드는  javax.servlet.Servletinterface에 속해 있고, 서블릿 컨테이너에 의해 호출된다.
4. 1,2,3 단계가 완료되면 service() 메소드가 호출된다. 그 이후로는 서블릿 인스턴스가 요청을 처리해야 할 때마다 service() 메소드가 호출된다. service() 메소드를 구현하는 것은 선택사항이다.  
5. 마지막으로 컨테이너는 소멸 메소드(destroy())를 호출하여 인스턴스를 제거한다. 이 시점에서 서블릿은 더 이상 필요하지 않은 메모리나 스레드 등을 정리한다.

```java
public interface Servlet {
    public void init(ServletConfig config) throws ServletException;

    public void service(ServletRequest req, ServletResponse res)throws ServletException, IOException;

    public void destroy();

    public String getServletInfo();

    public ServletConfig getServletConfig();
}
```

## Filter
필터는 자원(서블릿 또는 정적 컨텐츠)으로의 요청이나 해당 자원에서의 응답에 대한 필터링 작업을 수행하는 객체이다.
* 필터는 주로 요청 전처리 및 응답 후처리 역할을 하는데 사용된다.
* 필터는 여러 서블릿이나 Java EE 웹 컴포넌트에서 공통 기능이 필요한 경우 주로 사용된다. 이러한 기능에는 인증, 로깅 및 암호화가 포함될 수 있다.
    * 인증 필터(Authentication Filters)
    * 로깅 및 감사 필터(Logging and Auditing Filters)
    * Data compression Filters
    * Encryption Filters
    * Tokenizing Filters
    * Filters that trigger resource access events
    * XSL/T filters
    * Mime-type chain Filter
* 웹 필터는 주어진 URL을 방문할 때 요청을 전처리하고 특정 기능을 호출하는데 유용하다.
* 특정 URL에 직접 서블릿을 호출하는 대신 동일한 URL 패턴을 가진 모든 필터가 서블릿 이전에 호출된다.
* 필터는 javax.servlet.Filter 인터페이스를 구현해야 한다. 이 인터페이스에 포함된 메소드로는 init, destroy 및 doFilter가 있다.
    * init 및 destroy 메소드 : 컨테이너에 의해 호출되며 필터의 초기화 및 소멸 작업을 수행한다.
    * doFilter 메소드 : 필터 클래스의 작업을 구현하는 데 사용되며 실제 필터링 작업을 수행한다.

### 필터 생명 주기
1. 필터가 서블릿 컨테이너에 의해 생성된다.
    * 생성자 호출
    * init() 메소드 호출
2. doFilter() : 각 요청/응답마다 호출
3. 서블릿 컨테이너가 destroy()가 호출된다. 

### 필터 체인
필터는 필터 체인을 사용하여 체인 내의 다음 필터를 호출한다. 호출 중인 필터가 마지막 필터인 경우 체인 끝에 있는 리소스를 호출한다.
* 필터의 실행 순서는 web.xml 설정에 기반한다.
* 모든 필터의 대상 리소스는 하나의 스레드에서 실행된다.
* 모든 필터는 동일한 요청 객체를 공유한다.

## 서블릿 리스너
서블릿에서 서블릿 속성이 추가, 제거 또는 업데이트될 때 동작을 수행하는 작업은 리스너를 통해 구현된다.

리스너는 일반적으로 다음과 같은 경우에 사용된다.
1. 응용 프로그램 시작 시 데이터/설정을 로드하려는 경우
2. 이벤트 발생 시 데이터베이스 연결을 열고 닫으려는 경우
3. 응용 프로그램 종료 시 특정 동작을 수행하려는 경우

## Spring MVC(모델-뷰-컨트롤러 MVC)
* Spring MVC(모델-뷰-컨트롤러(MVC) 디자인 패턴을 기반으로 한 가장 인기 있는 Java 웹 프레임워크 중 하나이다.
* Spring Boot은 기본적으로 Tomcat, Jetty, 그리고 Undertow 서블릿 컨테이너를 지원하며, 각각의 서버 레벨 커스터마이제이션을 구현하기 위해 커스터마이제이션 훅을 제공한다.
* Spring MVC의 핵심 요소는 Dispatcher Servlet으로, 이는 모든 요청을 처리하고 적절한 채널로 디스패치하는 주요 서블릿이다. Dispatcher Servlet을 사용하면 Spring MVC는 웹 응용 프로그램의 모든 요청을 처리하는 진입점을 제공하는 Front Controller 패턴을 따른다.
* 표준 서블릿 리스너를 사용하여 Spring 애플리케이션 컨텍스트를 부트스트랩하고 종료한다. 애플리케이션이 시작될 때 어떤 요청이든 발생하기 전에 애플리케이션 컨텍스트가 생성되고 DispatcherServlet에 주입되며, 애플리케이션이 종료될 때 Spring 컨텍스트가 원활하게 닫힌다.

## Spring MVC 인터셉터
* HandlerInterceptor는 적절한 HandlerAdapter가 핸들러를 실행시키기 전에 호출된다.
* 이 메커니즘은 인가와 같은 전역적인 사전 처리에 사용될 수 있고 지역 또는 테마 변경과 같은 일반 핸들러 동작을 위해 사용할 수 있다. 주요 목적은 반복적인 핸들러 코드를 팩토링하는 것이다.


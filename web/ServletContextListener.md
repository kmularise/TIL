# ServletContextListner 구현
서블릿은 다양한 시점에서 발생하는 이벤트와 이벤트를 처리하기 위한 인터페이스를 정의하고 있다. 이벤트와 이벤트 처리 인터페이스를 이용하면 웹 애플리케이션에 필요로 하는 데이터의 초기화나 요청 처리 등을 추적할 수 있게 된다.
ServletContextListner는 이벤트를 처리할 수 있는 인터페이스 중 하나이다.

## ServletContextListner를 이용한 이벤트 처리
* 웹 컨테이너는 웹 애플리케이션(컨텍스트)이 시작되거나 종료되는 시점에 특정 클래스의 메소드를 실행할 수 있는 기능을 제공
* 이 기능을 사용하면 웹 애플리케이션을 실행할 때 필요한 초기화 작업이나 웹 애플리케이션이 종료된 후 사용된 자원을 반환하는 등의 작업 수행
* 웹 애플리케이션이 시작되고 종료될 때 특정한 기능을 실행하려면
    1. javax.servletContextListener 인터페이스를 구현한 클래스 작성
    2. web.xml 파일에 1번에서 작성한 클래스 등록

### javax.servlet.ServletContextListener 인터페이스
웹 애플리케이션이 시작되거나 종료될 때 호출할 메소드를 정의한 인터페이스
* contextInitialized() : 웹 애플리케이션을 초기화할 때 호출한다.
* contextDestroyed() : 웹 애플리케이션을 종료할 때 호출한다.
ServeletContextListener 인터페이스에 정의된 두 메소드는 모두 파라미터로 javax.servlet.ServletContextEvent 타입의 객체를 전달받는다.
### ServletContextEvent
* getServletContext() : 웹 애플리케이션의 컨텍스트를 구할 수 있다. web.xml 파일에 설정된 컨텍스트 초기화 파라미터를 구할 수 있다.

### 리스너의 실행 순서
한 개 이상의 리스너가 등록된 경우, contextInitialized() 메소드는 등록된 순서대로 실행되고 contextDestroyed() 메소드는 등록된 반대 순서대로 실행된다.

### 리스너에서의 익셉션 처리
catch 블록에서 Exception을 발생시키는 이유는 contextInitialized() 메소드 정의에 throws가 없기 때문이다. 이 메소드는 발생시킬 수 있는 checked Exception을 지정하고 있지 않으므로 익셉션을 발생시키려면 RuntimeException이나 그 하위 타입 Exception을 발생시켜야 한다.

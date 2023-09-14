# 빈 스코프
빈 스코프 : 빈이 존재할 수 있는 범위

스프링은 다음과 같은 다양한 스코프를 지원한다.
* 싱글톤(Singleton) : 기본 스코프, 스프링 컨테이너의 시작부터 종료까지 유지되는 가장 넓은 범위의 스코프
* 프로토타입(Prototype) : 스프링 컨테이너가 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프
* 웹 관련 스코프
    * request : 웹 요청이 들어오고 나갈 때까지 유지되는 스코프
    * session : 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
    * application : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프
    * websocket : 웹 소켓과 동일한 생명주기를 가지는 스코프

## 싱글톤 스코프

![image](https://github.com/kmularise/TIL/assets/106499310/19d5223a-5cde-41f2-a273-36e80cddfbb1)

* 싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 빈을 반환한다.
* 싱글톤 빈은 컨테이너 생성 시점에 같이 생성되고 초기화된다.

## 프로토타입 스코프

![image](https://github.com/kmularise/TIL/assets/106499310/7bc1e22e-3881-4f7d-9faa-f803c086c2a9)

* 프로토타입의 빈을 스프링 컨테이너에 요청하면 스프링 컨테이너는 프로토타입의 빈을 생성하고, 필요한 의존관계를 주입한다.
* 프로토타입 빈은 스프링 컨테이너에서 **빈을 조회할 때** 생성되고 초기화 메서드도 실행된다.
* 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다.
    * 클라이언트에게 빈을 반환한 이후에는 생성된 프로토타입 빈을 관리하지 않는다.
* 프로토타입 빈을 관리할 책임은 클라이언트에게 있다.
* 따라서 @PreDestory와 같은 종료 콜백 메서드가 호출되지 않는다.

## 용도
* 코드에서 new로 오브젝트를 생성하는 것을 대신하기 위해 사용
* 대부분의 경우 new로 오브젝트를 생성하거나 팩토리를 이용해서 오브젝트를 생성하면 된다. 하지만 컨테이너의 DI 기능을 사용해야 하고 싶은 경우는 프로토타입 스코프 빈을 사용한다.
* 매번 새로운 오브젝트가 필요하면서 DI를 통해 다른 빈을 사용해야할 경우 프로토타입 빈이 적절한 선택이다.

## 웹 스코프
* 웹 스코프는 웹 환경에서만 동작한다.
* 웹 스코프는 스프링이 해당 스코프의 종료 시점까지 관리한다. 따라서 종료 메소드가 호출된다.

[종류]
request(요청 스코프) : HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리된다.
session(세션 스코프) : HTTP Session과 동일한 생명주기를 가지는 스코프
application(애플리케이션 스코프) : 서블릿 컨텍스트(ServletContext)와 동일한 생명주기를 가지는 스코프
websocket : 웹 소켓과 동일한 생명주기를 가지는 스코프

* 스프링 빈 등록 시 웹 스코프를 그대로 주입받으면 오류가 발생한다.
    * 싱글톤 빈은 스프링 컨테이너 생성 시 함께 생성되어서 라이프 사이클을 같이하지만, 웹 스코프의 경우 싱글톤 빈이 생성되는 시점에는 아직 생성되지 않았기 때문이다.
* 이를 해결하기 위해 프록시 객체를 사용한다.
    * 스프링 컨테이너에 가짜 프록시 객체를 등록한다. 즉, 가짜프록시 객체로 의존성 주입이 일어난다.
    * 가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.
    * 가짜 프록시 객체는 원본 클래스를 상속받아서 만들어졌기 때문에 이 객체를 사용하는 클라이언트 입장에서는 원본인지 아닌지도 모르게 동일하게 사용할 수 있다.

## 용도
* request : 요청 스코프 빈의 주요 용도는 애플리케이션 코드에서 생성한 정보를 프레임워크 레벨의 서비스나 인터셉터 등에 전달하는 것이다.
* session : 세션 스코프를 이용하면 HTTP 세션에 저장되는 정보를 모든 계층에서 안전하게 이용할 수 있다.
    * HTTP 세션은 사용자별로 만들어지고 브라우저를 닫거나 세션 타임이 종료될 때까지 유지되기 때문에 로그인 정보나 사용자별 선택옵션 등을 저장해두기에 유용하다. 하지만 세션을 직접 이용하는 건 매우 번거롭고, 웹 환경 정보에 접근할 수 있는 계층에서만 가능한 작업이다. 하지만 세션 스코프를 이용하면 HTTP 세션에 저장되는 정보를 모든 계층에서 안전하게 이용할 수 있다.
* application :
    * 싱글톤 스코프와 비슷한 존재 범위를 갖는다.
    * 웹 애플리케이션과 애플리케이션 컨텍스트의 존재 범위가 다른 경우가 있는 경우 사용한다.
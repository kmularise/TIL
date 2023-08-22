* 운영서버에 모든 요청 정보를 로그에 다 남기면 성능저하가 발생할 수 있다. 개발 단계에서만 적용하자.

## HttpServletRequest 역할
서블릿은 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다. 그리고 그 결과를 HttpServletRequest 객체에 담아서 제공한다.

* START LINE 
    * HTTP 메소드
    * URL
    * 쿼리 스트링
    * 스키마, 프로토콜 
* 헤더
    * 헤더 조회
* 바디
    * form 파라미터 형식 조회 
    * message body 데이터 직접 조회

HttpServletRequest 객체는 추가로 여러가지 부가기능도 함께 제공한다.
### 임시 저장소 기능
* 해당 HTTP 요청이 시작부터 끝날 때 까지 유지되는 임시 저장소 기능 
    * 저장: request.setAttribute(name, value)
    * 조회: request.getAttribute(name)
### 세션 관리 기능
* request.getSession(create: true)

## HTTP 요청 데이터 - 개요
HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법을 알아보자.

주로 다음 3가지 방법을 사용한다. 
* GET - 쿼리 파라미터
    * /url?username=hello&age=20
    * 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
    * 예) 검색, 필터, 페이징등에서 많이 사용하는 방식
* POST - HTML Form
    * content-type: application/x-www-form-urlencoded
    * 메시지 바디에 쿼리 파리미터 형식으로 전달 username=hello&age=20 
    * 예) 회원 가입, 상품 주문, HTML Form 사용
* HTTP message body에 데이터를 직접 담아서 요청
    * HTTP API에서 주로 사용, JSON, XML, TEXT
    * 데이터 형식은 주로 JSON 사용 
    * POST, PUT, PATCH

## 참고 - 요청 파라미터
> content-type은 HTTP 메시지 바디의 데이터 형식을 지정한다.
> GET URL 쿼리 파라미터 형식으로 클라이언트에서 서버로 데이터를 전달할 때는 HTTP 메시지 바디를
사용하지 않기 때문에 content-type이 없다.
> POST HTML Form 형식으로 데이터를 전달하면 HTTP 메시지 바디에 해당 데이터를 포함해서 보내기
때문에 바디에 포함된 데이터가 어떤 형식인지 content-type을 꼭 지정해야 한다. 
> 이렇게 폼으로 데이터를 전송하는 형식을 application/x-www-form-urlencoded 라 한다.

## HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
* HTTP message body에 데이터를 직접 담아서 요청
* HTTP API에서 주로 사용, JSON, XML, TEXT
* 데이터 형식은 주로 JSON 사용
* POST, PUT, PATCH

## HTTP 응답 메시지 생성
* HTTP 응답코드 지정 
* 헤더 생성
* 바디 생성

### 편의 기능 제공
Content-Type, 쿠키, Redirect

## 템플릿 엔진으로
지금까지 서블릿과 자바 코드만으로 HTML을 만들어보았다. 서블릿 덕분에 동적으로 원하는 HTML을 마음껏 만들 수 있다. 정적인 HTML 문서라면 화면이 계속 달라지는 회원의 저장 결과라던가, 회원 목록 같은 동적인 HTML을 만드는 일은 불가능 할 것이다.
그런데, 코드에서 보듯이 이것은 매우 복잡하고 비효율 적이다. 자바 코드로 HTML을 만들어 내는 것 보다 차라리 HTML 문서에 동적으로 변경해야 하는 부분만 자바 코드를 넣을 수 있다면 더 편리할 것이다. 이것이 바로 템플릿 엔진이 나온 이유이다. 템플릿 엔진을 사용하면 HTML 문서에서 필요한 곳만 코드를 적용해서 동적으로 변경할 수 있다.
템플릿 엔진에는 JSP, Thymeleaf, Freemarker, Velocity등이 있다. 다음 시간에는 JSP로 동일한 작업을 진행해보자.
> 참고
> JSP는 성능과 기능면에서 다른 템플릿 엔진과의 경쟁에서 밀리면서, 점점 사장되어 가는 추세이다. 템플릿
엔진들은 각각 장단점이 있는데, 강의에서는 JSP는 앞부분에서 잠깐 다루고, 스프링과 잘 통합되는 Thymeleaf를 사용한다.

## 서블릿과 JSP의 한계
서블릿으로 개발할 때는 뷰(View)화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했다.
JSP를 사용한 덕분에 뷰를 생성하는 HTML 작업을 깔끔하게 가져가고, 중간중간 동적으로 변경이 필요한 부분에만 자바 코드를 적용했다. 그런데 이렇게 해도 해결되지 않는 몇가지 고민이 남는다.

회원 저장 JSP를 보자. 코드의 상위 절반은 회원을 저장하기 위한 비즈니스 로직이고, 나머지 하위 절반만 결과를 HTML로 보여주기 위한 뷰 영역이다. 회원 목록의 경우에도 마찬가지다.
코드를 잘 보면, JAVA 코드, 데이터를 조회하는 리포지토리 등등 다양한 코드가 모두 JSP에 노출되어 있다. JSP가 너무 많은 역할을 한다.

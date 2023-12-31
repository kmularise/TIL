# 필터(filter) VS 인터셉터(interceptor)

![image](https://github.com/kmularise/TIL/assets/106499310/64ba1178-95ec-47da-935f-3b6a7fdb5ed4)

## 필터(Filter)

![image](https://github.com/kmularise/TIL/assets/106499310/78be89f9-3429-4530-935d-9b209eef9e99)


* 디스패처 서블릿(Dispatcher Servlet)에 요청이 전달되기 전/후에 url 패턴에 맞는 모든 요청에 대해 부가작업을 처리할 수 있는 기능을 제공
* 디스패처 서블릿은 스프링의 가장 앞단에 존재하는 프론트 컨트롤러이므로, 필터는 스프링 범위 밖에서 처리가 된다.
* 스프링 컨테이너가 아닌 톰캣과 같은 웹 컨테이너(서블릿 컨테이너)에 의해 관리가 된다. (단, 스프링 빈으로 등록은 된다.)
* 디스패처 서블릿 전/후에 처리

## 인터셉터(Interceptor)
* 인터셉터(Interceptor)는 J2EE 표준 스펙인 필터(Filter)와 달리 Spring이 제공하는 기술
* 디스패처 서블릿(Dispatcher Servlet)이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공
* 웹 컨테이너(서블릿 컨테이너)에서 동작하는 필터와 달리 인터셉터는 스프링 컨텍스트에서 동작

![image](https://github.com/kmularise/TIL/assets/106499310/947e6cdf-a1b6-43f7-9648-9ce74aeeeead)
* 디스패처 서블릿은 핸들러 매핑을 통해 적절한 컨트롤러를 찾도록 요청하는데, 그 결과로 실행 체인(HandlerExecutionChain)을 돌려준다.
* 이 실행 체인은 1개 이상의 인터셉터가 등록되어 있다면 순차적으로 인터셉터들을 거쳐 컨트롤러가 실행되도록 하고, 인터셉터가 없다면 바로 컨트롤러를 실행한다.

### 인터셉터(Interceptor)와 AOP의 비교
* 인터셉터 대신에 컨트롤러들에 적용할 부가기능을 어드바이스로 만들어 AOP를 적용할 수도 있다.
* 다음과 같은 이유들로 컨트롤러의 호출 과정에 적용되는 부가기능들은 인터셉터를 사용하는 편이 낫다.
    1. 컨트롤러는 타입과 실행 메소드가 모두 제각각이라 포인트컷(적용할 메소드 선별)의 작성이 어렵다.
    2. 컨트롤러는 파라미터나 리턴 값이 일정하지 않다.
    3. AOP에서는 HttpServletRequest/Response를 객체를 얻기 어렵지만 인터셉터에서는 파라미터로 넘어온다.


## 필터 VS 인터셉터
| 대상 | 필터(Filter) | 인터셉터(Interceptor) | 
| ------- | ------- | ------- |
| 관리되는 컨테이너 | 서블릿 컨테이너 | 스프링 컨테이너 |
| 스프링의 예외처리 여부 | X | O |
| Request/Response 객체 조작 가능 여부 | O | X |
| 용도 | - 공통된 보안 및 인증/인가 관련 작업<br>- 모든 요청에 대한 로깅 또는 감사<br>- 이미지/데이터 압축 및 문자열 인코딩<br>- Spring과 분리되어야 하는 기능 | - 세부적인 보안 및 인증/인가 공통 작업<br>- API 호출에 대한 로깅 또는 감사<br>- Contoller로 넘겨주는 정보(데이터)의 가공|

<!-- https://mangkyu.tistory.com/173
https://mangkyu.tistory.com/18 -->


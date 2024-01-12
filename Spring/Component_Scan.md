# 컴포넌트 스캔
스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다.
`@ComponentScan`은 `@Component`가 붙은 모든 클래스를 스프링 빈으로 등록한다.


## 컴포넌트 스캔과 Spring Boot의 `@SpringApplication`
Spring Boot를 사용하면 스프링 부트의 대표 시작 정보인 `@SpringBootApplication` 에 `@ComponentScan` 이 포함되어 있다.

## `@ComponentScan` 동작 방식
1. Configuration 클래스 및 Annotation에 사용하는 설정들을 파싱한다. 
2. basePackage 밑의 모든 .class 자원을 불러와서 component 후보인지 확인하여 BeanDefinition (빈 생성을 위한 정의)을 만든다. 
3. 생성된 빈 정의를 바탕으로 빈을 생성하고 의존성있는 빈들을 주입한다.

## `@ComponentScan` VS `@Import`
* `@ComponentScan` : 등록될 빈의 후보로 있는 클래스들의 패키지를 나타낸다. 일반적으로 개발하는 중에 빈 클래스를 계속 추가하는 애플리케이션 코드 내의 빈을 등록하는데 사용하기 적합하다.
* `@Import` : 등록할 빈을 나타낸다. 등록할 빈의 후보가 명확하게 사전 정의된 경우에 쓰는 것이 좋다. 주로 설정 클래스(`@Configuration` 클래스)를 로드할 때 사용한다.

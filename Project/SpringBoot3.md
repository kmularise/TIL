# Spring Boot 3 업그레이드 특징
* Spring Boot 2.7.x 는 2023년 11월까지 지원 예정
    * 보안 등 지원을 해주지 않기 때문에 안정성을 위해서는 Spring Boot 3을 써야 한다.
* 지원하는 최소 자바 버전을 Java 17로 올렸다.
*  Logback 및 Log4j2 날짜 및 시간의 기본값이 ISO-8601 표준을 따른다.
* 보안상 이슈로 /some/greeting과 /some/greeting/이 더 이상 일치하지 않는다.
* Spring Framework 6.0을 필요로 한다.
    * HTTP API 에러 처리를 위한 RFC 7807 스펙을 지원한다.
    https://docs.spring.io/spring-framework/docs/6.0.0-RC1/reference/html/web.html#mvc-ann-rest-exceptions

## 참고자료
https://github.com/spring-projects/spring-batch/releases/tag/v5.0.0
https://revf.tistory.com/260
https://docs.spring.io/spring-framework/docs/6.0.0-RC1/reference/html/web.html#mvc-ann-rest-exceptions
https://github.com/spring-projects/spring-framework/wiki/What's-New-in-Spring-Framework-6.x
https://endoflife.date/spring-boot
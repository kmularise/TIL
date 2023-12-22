# Spring Boot AutoConfiguration(자동 구성)
Spring과 다른 Spring Boot의 주요 특징은 autoconfiguration(자동 구성)이다.

 개발자의 입장에서 사용할 라이브러리의 의존성만 추가하게 되면 autoconfigure를 통해 해당 라이브러리에 대한 구성(필요한 빈 설정과 생성)이 자동으로 이루어지기 진다. 따라서 Spring 처럼 수동으로 빈 등록 및 설정을 하지 않아도 된다.


## Spring Boot AutoConfiguration 흐름
1. 애플리케이션이 실행되면 @SpringBootAplication의 @EnableAutoConfiguration 어노테이션으로 인해 자동 구성을 위한 AutoConfigurationImportSelect 클래스의 내부 메소드가 동작하게 된다.
2. META-INF/spring/org.springframework.autoconfigure.AutoConfiguration.imports 파일에 등록된 AutoConfiguration 클래스 정보를 읽어 오고 spring.factories에서는 자동 구성에 사용될 필터 정보를 읽어온다.
3. spring.factories에서 읽어온 필터를 통해 AutoConfiguration.imports 파일에 등록된 AutoConfiguration 클래스를 필터링한다.
4. 필터링을 통해 실제 적용될 AutoConfiguration 클래스들만 남게 된다.

## Spring Boot 2.7 이후 AutoConfiguration

Spring Boot 2.7은 auto-configurations을 등록하기 위해 새로운 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 파일을 도입했으며, 동시에 spring.factories에서의 등록과의 하위 호환성을 유지했다. 이 릴리스로 인해, org.springframework.boot.autoconfigure.EnableAutoConfiguration 키를 사용하여 spring.factories에서 자동 구성을 등록하는 지원이 제거되고, 대신 imports 파일 사용이 권장된다. 다른 키들 아래의 spring.factories 내 다른 항목들은 영향을 받지 않는다.

> Spring Boot 2.7 introduced a new META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports file for registering auto-configurations, while maintaining backwards compatibility with registration in spring.factories. With this release, support for registering auto-configurations in spring.factories using the org.springframework.boot.autoconfigure.EnableAutoConfiguration key has been removed in favor of the imports file. Other entries in spring.factories under other keys are unaffected.

https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#auto-configuration-files
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#changes-to-auto-configuration
https://wildeveloperetrain.tistory.com/292
<!-- https://wildeveloperetrain.tistory.com/292 -->
<!-- https://yozm.wishket.com/magazine/detail/2115/ -->
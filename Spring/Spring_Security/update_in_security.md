# Spring Security 업데이트 사항
## WebSecurityConfigureAdapter Deprecated
* SpringSecurity에서는 사용자가 컴포넌트 기반 보안 구성으로 전환하도록 권장하기 때문에 WebSecurityConfigurerAdapter을 더이상 사용하지 않는다.

## withDefaultPasswordEncoder() Deprecated
* 이 메소드를 production 을 위한 사용에는 안전하지 않다.
* 하지만 demo 버전이나 시작 버전으로는 수용될 수 있다.
* production 목적으로는 비밀번호가 외부에서 encoding되어야 한다.
* withDefaultPasswordEncoder()에 대한 지원이 제거될 계획은 없지만, production 목적으로는 안전하지 않기에 depreated 되었다.
* 대안으로는 다음과 같이 password를 외부에서 encoding 하는 방법이 있다.
```java
@Bean
public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.builder().username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
}

```
## Lambda DSL
* 람다식을 이용하여 Http 보안을 설정할 수 있다.
[람다식 이용한 코드]
```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/blog/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
            )
            .rememberMe(withDefaults());
    }
}
```

[그렇지 않은 코드]
```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/blog/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .rememberMe();
    }
}
```

## 
* [Deprecated List (spring-security-docs 6.1.3 API)](https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html)
* [User.withDefaultPasswordEncoder() is deprecated](https://stackoverflow.com/questions/49847791/java-spring-security-user-withdefaultpasswordencoder-is-deprecated)
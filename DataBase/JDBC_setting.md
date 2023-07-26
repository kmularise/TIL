# 데이터베이스 프로그래밍
* 자바에서는 JDBC API를 이용해서 데이터베이스 프로그래밍을 한다.

## 데이터베이스 기초
* [데이터베이스 기초 참고](./database_basic.md)

## 인텔리제이 설정 시 주의
### 이미 사용하고 있는 포트가 있는지 확인하자.
* 톰캣 깔려 있는 경로에서 직접 터미널에서 톰캣을 8080포트로 실행시키고 나서 인텔리제이 상에서 톰캣을 같은 포트로 실행시키려고 하니까 원래 열려 있던 포트 기준으로 html 페이지가 띄워졌다.
* 포트 관리가 중요함을 느꼈다.

### db 드라이버 로딩 안되는 문제

```java
try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//        Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
```
드라이버를 로딩하는데서 java.lang.ClassNotFoundException: com.mysql.jdbc.Driver 이런 오류가 발생했다. 
프로젝트 구조 -> 문제로 들어가서 아래 그람과 같이 하면 아주 간단하게 해결됐다.
![Alt text](<스크린샷 2023-07-26 오후 4.57.28.png>)

https://honsal.blogspot.com/2016/02/intellij-idea-jdbc-driver.html 자료를 참고했다.


## 참고자료
https://wisdom-and-record.tistory.com/61
https://leirbag.tistory.com/80

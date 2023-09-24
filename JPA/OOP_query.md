
동적 쿼리 QueryDSL

## QueryDSL
* 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
* JPQL 빌더 역할
* 컴파일 시점에 문법 오류를 찾을 수 있다.
* 동적쿼리 작성이 편리하다.
* 단순하고 쉽다.
* 실무 사용 권장

## JDBC 직접 사용, SpringJdbcTemplate 등
* JPA를 사용하면서 JDBC 커넥션을 직접 사용하거나, 스프링 JdbcTemplate, 마이바티스 등을 함께 사용 가능
* 단 영속성 컨텍스트를 적절한 시점에 강제로 플러시 필요
ex) JPA를 우회해서 SQL을 실행하기 직전에 영속성 컨텍스트 수동 플러시

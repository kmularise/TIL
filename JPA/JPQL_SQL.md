# JPQL과 SQL

## JPQL
Java EE에서, Java Persistence API(JPA)는 관계형 데이터베이스에 접근하기 위한 표준 API로, 일반 자바 객체(POJO)를 관계형 데이터로 매핑하는 객체/관계 매핑(ORM)을 관리하는 간단하고 효율적인 방법을 제공한다.

엔티티는 일반적으로 데이터베이스의 단일 관계형 테이블과 연결되어 있다.

Java Persistence Query Language(JPQL)은 자바 애플리케이션이 엔티티들을 다루기 위해서는 엔티티 인스턴스에 접근하고 탐색하기 위해 설계되었다.

JPQL을 사용하면 SQL/JDBC와는 달리 데이터베이스 테이블에 직접 쿼리하는 대신, 기초 데이터베이스 테이블에 매핑된 JPA 엔티티들에 대해 쿼리를 정의함으로써, 비즈니스 로직 레이어로부터 데이터베이스 세부 정보를 숨기는 추상화 레이어를 다룬다.

JPQL이 JPA 엔티티에 대한 쿼리를 작성하는 유일한 옵션이 아니며, 일부 상황에서 네이티브 SQL 쿼리를 사용하는 것이 더 편리하다.

## JPQL과 SQL 유사점
* 데이터베이스 데이터에 접근하고 조작하는 데 사용
* 비절차적 문장(nonprocedural statements)으로 인터프리터에 의해 인식된다.

## JPQL과 SQL 차이점
* JPQL이 JPA 엔티티들을 다루는 반면, SQL은 직접적으로 관계형 데이터를 다룬다.
    * JPQL을 사용하면 SQL/JDBC와는 달리 JDBC API를 자바 코드에서 사용할 필요가 없다. 컨테이너가 이 모든 작업을 뒷단에서 대신 처리해준다.
* JPQL 쿼리는 컴파일 타임에 타입 체크를 받을 수 있어, 쿼리에 오류가 있을 경우 미리 발견하기 쉽다. SQL 쿼리는 일반적으로 문자열로 처리되어 런타임에만 오류를 발견할 수 있다.
https://www.oracle.com/technical-resources/articles/vasiliev-jpql.html


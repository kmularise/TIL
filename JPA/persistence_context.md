# 영속성 컨텍스트

## 엔티티 매니저 팩토리와 엔티티 매니저
![image](https://github.com/kmularise/TIL/assets/106499310/e287e99d-6489-4ef6-b370-d7ff587d0db1)

## 영속성 컨테스트란?
* 엔티티를 영구 저장하는 환경
* 영속성 컨텍스트는 논리적인 개념
* 엔티티 매니저를 통해서 영속성 컨텍스트에 접근

![image](https://github.com/kmularise/TIL/assets/106499310/a0ada38f-8e90-42fd-a05d-567530fc3285)

```
Java SE(Standard Endition, J2SE)

* 가장 보편적으로 쓰이는 자바 API집합체
* 예전에는 J2SE로 불렸으나 버전 6.0이후에 Java SE로 변경
* 이전에는 썬마이크로시스템즈에서 관리했으나 지금은 JCP주도하에 개발
* 일반 자바 프로그램 개발을 위한 용도로 사용되며 스윙이나 AWT와 같은 GUI방식의 기본 기능이 포함
```

```
Java EE(Enterprise Edition, J2EE)
* 자바를 이용한 서버측 개발을 위한 플랫폼
* 표준 플랫폼인 Java SE를 사용하는 서버를 위한 플랫폼
* 전사적 차원(대규모의 동시 접속과 유지가 가능한 다양한 시스템의 연동 네트워크 기반 총칭)에서 필요로 하는 도구로 EJB, JSP, Servlet, JNDI 같은 기능을 지원하며 WAS(Web Application Server)를 이용한 프로그램 개발 시 사용된다. 
```

## 엔티티의 생명주기

![image](https://github.com/kmularise/TIL/assets/106499310/b5a4237d-20a6-41ba-9657-08fa8f309a32)

* 비영속(new/transient)
* 영속(managed)
* 준영속 (detached)
* 삭제(removed)
```java
//비영속
Member member = new Member();
member.setId(100L);
member.setName("HelloJPA");

//영속
em.persist(member);
//준영속 - 회원 엔티티를 영속성 컨텍스트에서 분리
em.detach(member);
//삭제 - 객체를 상태한 상태 - delete 쿼리가 commit 시점에 나간다.
em.remove(member);
```

## 영속 컨텍스트의 이정
### 1차 캐시
* 1차 캐시에 있는 경우 
    1. 1차 캐시에서 조회
* 1차 캐시에 없는 경우
    1. DB 조회
    2. 1차 캐시에 저장 및 반환
-> 엔티티 매니저는 DB 트랜잭션 단위로 만들고, DB 트랜잭션이 끝나면 종료시킨다. 
-> 사용자의 요청이 하나 들어와서 작업이 끝나면 영속성 컨텍스트를 지우고, 1차캐시도 날아간다. 따라서 성능적 이점은 매우 짧은 순간 동안만 있다.
-> 애플리케이션 전체에서 공유하는 캐시는 JPA Hibernate에서 2차캐시다.
-> 성능적 이점보다는 객체지향적 코드를 작성할 때 등 방식이 주는 이점이 있다.

### 영속 엔티티의 동일성 보장
반복 가능한 읽기(REPEATABLE READ) 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공한다.
### 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
* 엔티티 등록 
```java
em.persist(memberA);// 1차 캐시에 저장, INSERT SQL 생성해서 쓰기 지연 SQL 저장소에 쌓아둔다.
trasaction.commit(); // 쓰기 지연 SQL 저장소에 있던 SQL 쿼리들이 flush가 되면서 실제 DB에 반영된다. 
```
### 변경 감지(Dirty checking)
* 엔티티 수정
```java
//영속 엔티티 조회
Member memberA = em.find(Member.class, "memberA");

//영속 엔티티 데이터 수정
memberA.setUsername("hi");
memberA.setAge(10);

transcation.commit(); // flush() 호출하면서 엔티티와 값을 읽은 영속성 컨텍스트 시작 이후 최초 시점의 스냅샷을 비교
// 엔티티 값과 스냅샷이 다르면 update SQL를 쓰기 자연 SQL 저장소에 쌓아둔다.
// update 쿼리를 DB에 반영하고 commit()이 일어난다. 
```

### 지연 로딩(Lazy Loading)

## 플러시
* 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
* 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
    * 영속성 컨텍스트를 비우진 않는다.
* 트랜잭션이라는 작업 단위가 중요하다. -> 커밋 직전에만 동기화하면 된다.

### 플러시가 일어나면 발생하는 일
* 변경 감지(Dirty Checking)
* 수정된 엔티티를 쓰기 지연 SQL 저장소에 등록
* 쓰기 지연 SQL 저장소의 쿼리(등록, 수정, 삭제 쿼리)를 데이터베이스에 전송
* 1차 캐시가 지워지진 않는다.

### 영속성 컨텍스트를 플러시하는 방법
* em.flush() - 직접 호출
* 트랜잭션 커밋 - 플러시 자동 호출
* JPQL 쿼리 실행 - 플러시 자동 호출


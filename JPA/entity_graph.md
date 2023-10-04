# Entity Graph
*  엔티티들은 서로 연관되어 있는 경우 이 관계는 그래프로 표현이 가능하다.
* Entity Graph는 JPA가 어떤 엔티티를 불러올 때 이 엔티티와 관계된 엔티티를 불러올 것인지에 대한 정보를 제공한다.
* @EntityGraph는 Data JPA에서 fetch join을 어노테이션으로 사용할 수 있도록 만들어 준 기능

```java
public interface MemberRepository extends JpaRepository<Member,Long> ,MemberRepositoryCustom{

    @Override //기본 적으로 findAll 을 제공하기 때문에 Override 하여 재정의 후 사용 
    @EntityGraph(attributePaths = {"team"}) // DataJpa 에서 fetch 조인을 하기 위한 설정
    List<Member> findAll();

}
```

## Entity Graph 와 fetch join의 차이점
*  Entity Graph의 경우 fetchType을 eager로 변환하는 방식으로 outer left join을 수행하여 데이터를 가져온다.
* fetch join의 경우 따로 outer join으로 명시하지 않는 경우 inner join을 수행한다.
* Entity Graph를 이용하면 fetch join의 단점(1:N 컬렉션 조인 시 하나만 조인할 수 있는 제약, distinct 사용)을 피할 수 있다.

## @EntityGraph동작 방식
* 기본적으로 fetchType.Lazy, fetchType.Eager는 static정보로 runtime에 변경할 수 없다.
* EntityGraph는 이를 변경할 수 있게 하는 기능으로 fetchType.Lazy로 설정해놓은 경우에 fetchType.Eager로 사용할 수 있다.
* 이 때문에 fetch join에서 1:N연관관계로 조회 할경우 1개의 컬렉션까지밖에 최대 같이 조회 가능한 부분이나, distinct가 필요한 단점을 극복할 수 있다.
* 다만 1:N 연관관계 컬렉션을 같이 조회 해 오는 경우 paging은 fetch join과 동일하게 수행할 수 없다.

## @EntityGraph의 타입  
FETCH 가 default 이다.
* FETCH: @EntityGraph 에 명시한 attribute 는 EAGER 로 패치한다. 나머지는 LAZY 로 패치한다.
* LOAD: @EntityGraph 에 명시한 attribute 는 EAGER 로 패치한다. 나머지는 Entity 에 명시한 fetch type을 따른다.
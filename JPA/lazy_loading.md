# 지연로딩
지연 로딩 적용 시 엔티티를 상속한 프록시라는 가짜 객체에서 엔티티 정보를 가져온다.
```java
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
```
```java
Member meber = em.find(Member.class, 1L);
Team team = member.getTeam();
team.getName(); // 실제 team을 사용하는 시점에 초기화(DB 조회)
```

## 프록시와 즉시 로딩 주의
* 가급적 지연 로딩만 사용(특히 실무에서)
* 즉시 로딩을 적용하면 예상하지 못한 SQL 발생
* 즉시 로딩은 JPQL에서 N+1 문제를 일으킨다.
* @ManyToOne, @OneToOne은 기본이 즉시 로딩 -> LAZY로 설정
* @OneToMany, @ManyToMany는 기본이 지연 로딩

## 지연로딩 활용
* 모든 연관관계에서 지연 로딩 사용
* JPQL fetch 조인, 엔티티 그래프, Batch Size 조절

# fetch join
* 연관된 엔티티나 컬렉션을 SQL 한 번에 함께 조회하는 기능
* JPQL에서 성능 최적화를 위해 제공
* SQL join 종류가 아니다.


## fetch join 도입
* 지연로딩으로 인한 N + 1 문제 발생을 해결하기 위해 도입
> ex) N + 1 문제 예시 : SQL 1번으로 N명의 Member를 조회하였는데, 한번 SQL을 실행해서 조회된 결과 수만큼 N번 Member 엔티티 안에 연관관계로 있는 Order 엔티티까지 추가로 조회 SQL을 실행하는 것

## fecth join 사용 시 주의할 점
* 카테시안 곱(Cartesian Product)이 발생 하여 중복이 생길 수 있다.
    * JPQL DISTINCT 추가
        1. SQL에 DISTINCT를 추가
        2. 애플리케이션에서 엔티티 중복 제거
![image](https://github.com/jhl221123/TIL/assets/106499310/cb54d08c-7e43-4f18-8674-89a8a6e92c93)

## fetch join 한계
* 패치 조인 대상에게 별칭(as) 부여 불가능
    * SELECT, WHERE, 서브 쿼리에 페치 조인 대상을 사용할 수 없다.
    * 제약을 걸어둔 이유는 일관성 때문이다. 별칭을 사용할 경우 fetch join 결과는 연관된 모든 엔티티가 있을 것이라 가정하고 사용해야 하는데 이를 어기게 된다.
* 둘 이상의 컬렉션을 fetch할 수 없다.
* 컬렉션을 fetch join 하면 페이징 API를 사용할 수 없다.
    * 단일 연관 필드(일대일, 다대일)는 페치 조인을 사용해도 페이징 API를 사용할 수 있다.
    * 하지만 일대다 관계에서는 페치 조인 후 페이징 API를 사용하면 경고 로그를 남기고 메모리에서 페이징 처리를 한다. 데이터가 많으면 성능 이슈와 메모리 초과 예외가 발생할 수 있어서 위험하다. 

<!-- ## 참고자료
* https://loosie.tistory.com/750
* https://medium.com/sjk5766/fetch-join-%ED%8A%B9%EC%A7%95-%EB%B0%8F-%EB%8B%A8%EC%A0%90-75095d1ede21 -->

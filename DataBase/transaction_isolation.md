# 트랜잭션 격리 수준
위에서 아래로 갈수록 격리 수준이 높아진다.
* READ UNCOMMITTED
* READ COMMITTED
* REPEATABLE READ
* SERIALIZABLE

## READ UNCOMMITTED
READ UNCOMMITTED 격리수준에서는 어떤 트랜잭션의 변경내용이 커밋이나 롤백과 상관없이 다른 트랜잭션에서 보여진다.

## READ COMMITTED
어떤 트랜잭션의 변경 내용이 커밋 되어야만 다른 트랜잭션에서 조회할 수 있다.
* NON-REPETABLE READ 부정합 문제가 발생할 수 있다.
    * 하나의 트랜잭션내에서 똑같은 SELECT를 수행했을 경우 항상 같은 결과를 반환해야 한다는 REPEATABLE READ 정합성에 어긋나는 것이다.
    * 일반적인 웹 어플리케이션에서는 크게 문제되지 않지만, 작업이 금전적인 처리와 연결되어 있다면 문제가 발생할 수 있다

## REPETABLE READ
* 트랜잭션이 시작되기 전에 커밋된 내용에 대해서만 조회할 수 있다.
 자신의 트랜잭션 번호보다 낮은 트랜잭션 번호에서 변경된(또는 커밋된) 것만 보게 되는 것이다.
* 이 격리수준에서는 NON-REPETABLE READ 부정합이 발생하지 않는다.

## SERIALIZABLE
* 가장 단순하고 가장 엄격한 격리수준이다.
* 격리수준이 SERIALIZABLE일 경우 읽기 작업에도 공유 잠금을 설정하게 되고, 동시에 다른 트랜잭션에서 이 레코드를 변경하지 못하게 된다.
* 동시처리 능력이 다른 격리수준보다 떨어지고, 성능저하가 발생하게 된다.


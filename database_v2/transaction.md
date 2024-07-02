# 트랜잭션
## ACID
* 데이터베이스 트랜잭션이 안전하게 수행됨을 보장하기 위한 성질

### Atomicity (원자성)
* 모두 성공하거나 모두 실패해야 한다.
* 트랜잭션은 논리적으로 쪼개질 수 없는 작업 단위이기 때문에 내부의 SQL 문들이 모두 성공해야 한다.
* 중간에 SQL 문이 실패하면 지금까지의 작업을 모두 취소하여  롤백(rollback)한다.
* commit 실행 시 DB에 영구적으로 저장하는 것은 DBMS가 담당하는 부분이다.
* rollback 실행 시 이전 상태로 되돌리는 것도 DBMS가 담당하는 부분이다.
* 개발자는 언제 commit 하거나 rollback 할지를 챙겨야 한다.

### Consistency (일관성)
* 트랜잭션은 DB상태를 consistent 상태에서 또다른 consistent 상태로 바꿔줘야 한다.
* constraints, trigger 등을 통해 DB에 정의된 rules을 트랜잭션이 위반했다면 rollback 해야 한다.
* transaction이 DB에 정의된 rule을 위반했는지는 DBMS가 commit하기 전에 확인하고 알려준다.
* 그 외에 애플리케이션 관점에서 트랜잭션이 consistent하게 동작하는지는 개발자가 고려해야 한다.

### isolation (격리성)
* 여러 트랜잭션들이 동시에 실행될 때도 혼자 실행되는 것처럼 동작하게 만든다.
* DBMS는 여러 종류의 격리 수준(isolation level)을 제공한다.
* 개발자는 isolation level 중에 어떤 level로 transaction을 동작시킬지 설정할 수 있다.
* concurrency control의 주된 목표가 isolation이다.

### durability (영속성)
* commit된 트랜잭션은 DB에 영구적으로 저장한다.
* 즉 DB 시스템에 문제(power fail 또는 DB crash)가 생겨도 commit된 트랜잭션은 DB에 남아 있는다.
* '영구적으로 저장한다'라고 할 때는 일반적으로 비휘발성 메모리에 저장함을 의미한다.
* 기본적으로 트랜잭션의 영속성은 DBMS가 보장한다.


### Dura

# 트랜잭션 격리수준

## schedule
- 여러 트랜잭션들이 동시에 실행될 때 각 트랜잭션에 속한 operation들의 실행 순서
- 각 트랜잭션 내의 operation들의 순서는 바뀌지 않는다.
- serial schedule : 트랜잭션들이 겹치지 않고 한번에 하나씩 실행되는 스케줄
- nonserial schedule : 트랜잭션들이 겹쳐서 실행되는 스케줄

### serial schedule 성능
- 한번에 하나의 트랜잭션만 실행되기 때문에 좋은 성능을 낼 수 없고 현실적으로 사용할 수 없다.


### nonserial schedule 성능
- 트랜잭션들이 겹쳐서 실행되기 때문에 동시성이 높아져서 같은 시간 동안 더 많은 트랜잭션들을 처리할 수 있다.
- 트랜잭션들이 어떤 형태로 겹쳐서 실행되는지에 따라 의도하지 않은 결과가 나올 수 있다.

## conflict of two operations
세가지 조건을 모두 만족하면 conflict

1. 서로 다른 트랜잭션 소속
2. 같은 데이터에 접근
3. 최소 하나는 write operation

`conflict operation은 순서가 바뀌면 결과도 바뀐다.`

### conflict equivalent
두 조건을 모두 만족하면 conflict equivalent
1. 두 schedule은 같은 트랜잭션들을 가진다.
2. 어떤 conflicting operations의 순서도 양쪽 schedule은 모두 동일하다.

### conflict serializable
serial schedule과 conflict equivalent일 때

## 생각해볼 점
* 성능 때문에 여러 트랜잭션들을 겹쳐서 실행할 수 있으면 좋다. (nonserial schedule)
* 하지만 의도하지 않은 결과가 나올 수 있다.
&#8594; 해결책 : conflict serializable한 nonserial schedule을 허용한다.

## RDMS 구현
여러 트랜잭션을 동시에 실행되도 schedule이 conflict serializable 하도록 보장하는 프로토콜을 적용한다.


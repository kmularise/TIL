# 격리 수준에서 발생할 수 있는 이상현상

## Dirty-Read
* 하나의 트랜잭션이 다른 트랜잭션이 아직 커밋하지 않은 변경사항을 읽을 때 발생한다.

1. 트랜잭션 A가 데이터베이스의 어떤 데이터를 수정한다.
2. 트랜잭션 B가 수정된 데이터를 읽는다. 하지만 트랜잭션 A는 아직 커밋(commit)을 하지 않은 상태이다.
트랜잭션 A가 롤백(rollback)되어 데이터베이스의 수정이 취소되면, 트랜잭션 B가 읽은 데이터는 더 이상 유효하지 않은 데이터, 즉 "dirty data"가 된다.
| | Dirty-read |
| --- | --- |
| Read Uncommitted | 발생 가능성 있음 |
| Read Committed | 발생하지 않음 |
| Repeatable Read | 발생하지 않음 |
| Serializable 발생하지 않음 | 발생하지 않음 |
## Non-Repeatable Read(Fuzzy Read)
* 하나의 트랜잭션이 두 번 이상 동일한 데이터를 읽을 때, 그 사이에 다른 트랜잭션이 해당 데이터를 수정하거나 삭제하여 결과가 달라진다.

1. 트랜잭션 A가 어떤 데이터를 읽는다.
2. 트랜잭션 B가 그 데이터를 수정하고 커밋한다.
3. 트랜잭션 A가 같은 데이터를 다시 읽을 때, 트랜잭션 B의 수정된 결과를 읽게 된다.

| | Non-Repeatable Read |
| --- | --- |
| Read Uncommitted | 발생 가능성 있음 |
| Read Committed | 발생 가능성 있음 |
| Repeatable Read | 발생하지 않음 |
| Serializable 발생하지 않음 | 발생하지 않음 |

## Phantom Read
* 트랜잭션이 동일한 쿼리를 여러 번 실행할 때, 다른 트랜잭션이 그 사이에 데이터를 삽입하거나 삭제하여 결과 집합이 달라지는 상황이다.
1. 트랜잭션 A가 특정 조건을 만족하는 행들의 집합을 읽는다.
2. 트랜잭션 B가 새로운 행을 삽입하거나 기존 행을 삭제하여 트랜잭션 A가 읽은 조건을 만족하는 행의 집합을 변경한다.
3. 트랜잭션 A가 동일한 조건으로 다시 쿼리를 실행하면, 처음 읽을 때와 다른 행들이 추가되거나 제거된 것을 확인하게 된다.

| | Phantom Read |
| --- | --- |
| Read Uncommitted | 발생 가능성 있음 |
| Read Committed | 발생 가능성 있음 |
| Repeatable Read | 발생 가능성 있음 |
| Serializable 발생하지 않음 | 발생하지 않음 |

### MVCC와 Phantom Read
* MySQL의 InnoDB 스토리지 엔진 처럼 MVCC(Multi-Version Concurrency Control)를 사용하는 경우 Phantom Read를 방지할 수 있습니다. 
* 트랜잭션이 시작될 때 스냅샷을 생성하여, 스냅샷을 통해 트랜잭션 동안 일관된 데이터를 읽을 수 있도록 한다.

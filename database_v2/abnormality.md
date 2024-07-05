# 격리 수준에서 발생할 수 있는 이상현상

## Dirty-Read
1. 트랜잭션 A가 데이터베이스의 어떤 데이터를 수정합니다.
2. 트랜잭션 B가 수정된 데이터를 읽는다. 하지만 트랜잭션 A는 아직 커밋(commit)을 하지 않은 상태이다.
트랜잭션 A가 롤백(rollback)되어 데이터베이스의 수정이 취소되면, 트랜잭션 B가 읽은 데이터는 더 이상 유효하지 않은 데이터, 즉 "dirty data"가 된다.
| | Dirty-read |
| --- | --- |
| Read Uncommitted | 발생 가능성 있음 |
| Read Committed | 발생하지 않음 |
| Repeatable Read | 발생하지 않음 |
| Serializable 발생하지 않음 | 발생하지 않음 |
## Non-Repeatable Read(Fuzzy Read)
* 같은 데이터를 한 Transaction 에서 읽었음에도 불구하고 값이 달라지는 현상

1. 트랜잭션 A가 어떤 데이터를 읽습니다.
2. 트랜잭션 B가 그 데이터를 수정하고 커밋합니다.
3. 트랜잭션 A가 같은 데이터를 다시 읽을 때, 트랜잭션 B의 수정된 결과를 읽게 됩니다.

| | Non-Repeatable Read |
| --- | --- |
| Read Uncommitted | 발생 가능성 있음 |
| Read Committed | 발생 가능성 있음 |
| Repeatable Read | 발생하지 않음 |
| Serializable 발생하지 않음 | 발생하지 않음 |

## Phantom Read
* 한 개의 Transaction 에서 같은 조건으로 2번 읽었는데 2 번의 결과가 다른 현상. 없던 데이터가 생기는 현상
1. 트랜잭션 A가 특정 조건을 만족하는 행들의 집합을 읽습니다.
2. 트랜잭션 B가 새로운 행을 삽입하거나 기존 행을 삭제하여 트랜잭션 A가 읽은 조건을 만족하는 행의 집합을 변경합니다.
3. 트랜잭션 A가 동일한 조건으로 다시 쿼리를 실행하면, 처음 읽을 때와 다른 행들이 추가되거나 제거된 것을 확인하게 됩니다. 이를 "팬텀(phantom) read"라고 합니다.

| | Phantom Read |
| --- | --- |
| Read Uncommitted | 발생 가능성 있음 |
| Read Committed | 발생 가능성 있음 |
| Repeatable Read | 발생 가능성 있음 |
| Serializable 발생하지 않음 | 발생하지 않음 |

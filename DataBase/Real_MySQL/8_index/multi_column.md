# 다중 칼럼(Multi-column) 인덱스
* 두 개 이상의 칼럼으로 구성된 인덱스를 다중 칼럼 인덱스(복합 칼럼 인덱스)
* 먼저 있는 컬럼의 정렬 순서가 나중에 있는 컬럼의 정렬 순서가 우선이다.

## 예시
```sql
SELECT * FROM tbl_name
  WHERE hash_col=MD5(CONCAT(val1,val2))
  AND col1=val1 AND col2=val2;
```

```sql
CREATE TABLE test (
    id         INT NOT NULL,
    last_name  CHAR(30) NOT NULL,
    first_name CHAR(30) NOT NULL,
    PRIMARY KEY (id),
    INDEX name (last_name,first_name)
);
```
* name 인덱스는 last_name 및 first_name 열에 대한 인덱스이다.
* 이 인덱스는 last_name 및 first_name 값의 알려진 범위에 대한 값을 지정하는 쿼리에서 조회에 사용될 수 있다.
* 또한 last_name 값만 지정하는 쿼리에도 사용될 수 있다. 왜냐하면 해당 열은 인덱스의 왼쪽 최우선 접두사이기 때문이다. 따라서, name 인덱스는 다음 쿼리에서 조회에 사용된다.

```sql
SELECT * FROM test WHERE last_name='Jones';

SELECT * FROM test
  WHERE last_name='Jones' AND first_name='John';

SELECT * FROM test
  WHERE last_name='Jones'
  AND (first_name='John' OR first_name='Jon');

SELECT * FROM test
  WHERE last_name='Jones'
  AND first_name >='M' AND first_name < 'N';
```
그러나 다음 쿼리에서는 name 인덱스가 조회에 사용되지 않는다.

```sql
SELECT * FROM test WHERE first_name='John';

SELECT * FROM test
  WHERE last_name='Jones' OR first_name='John';
```
다음과 같은 SELECT 문을 발행한다고 가정해 보겠다.

```sql
SELECT * FROM tbl_name
  WHERE col1=val1 AND col2=val2;
```
col1과 col2에 대한 복수 열 인덱스가 존재하면, 적절한 행을 직접 가져올 수 있다.
col1과 col2에 대한 별도의 단일 열 인덱스가 존재한다면, 옵티마이저는 인덱스 병합 최적화(Index Merge optimization)를 시도하거나, 어느 인덱스가 더 많은 행을 제외하는지 결정하여 그 인덱스를 사용하여 행을 가져오려고 시도한다.

테이블에 복수 열 인덱스가 있는 경우, 인덱스의 어떤 왼쪽 최우선 접두사도 옵티마이저가 행을 찾는 데 사용될 수 있다. 예를 들어, (col1, col2, col3)에 대한 세 열 인덱스가 있다면, (col1), (col1, col2), (col1, col2, col3)에 대한 인덱스 검색 기능이 있다.

MySQL은 열이 인덱스의 왼쪽 최우선 접두사를 형성하지 않는 경우 인덱스를 사용하여 조회를 수행할 수 없다. 다음과 같은 SELECT 문을 가정해 보겠다.

```sql
SELECT * FROM tbl_name WHERE col1=val1;
SELECT * FROM tbl_name WHERE col1=val1 AND col2=val2;

SELECT * FROM tbl_name WHERE col2=val2;
SELECT * FROM tbl_name WHERE col2=val2 AND col3=val3;
```
(col1, col2, col3)에 대한 인덱스가 존재한다면, 첫 두 쿼리만 인덱스를 사용한다. 세 번째 및 네 번째 쿼리는 인덱스된 열을 포함하지만, (col2)와 (col2, col3)가 (col1, col2, col3)의 왼쪽 최우선 접두사가 아니기 때문에 조회에 인덱스를 사용하지 않는다.

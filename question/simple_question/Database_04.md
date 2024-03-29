# 데이터 액세스
## 랜덤 I/O와 순차 I/O에 대해 설명해주세요.
[공통점]
* 하드 디스크 드라이브의 플래터(원판)을 돌려서 읽어야 할 데이터가 저장된 위치로 디스크 헤더(disk arm)를 이동시킨 다음 데이터를 읽는 것
* 파일에 쓰기를 실행하면, 반드시 동기화 (fsync, flush 작업)이 필요하다.
[차이점]
* 랜덤 I/O
    * 디스크 헤더를 데이터의 개수만큼 움직여야 한다.
    * 여러 번 쓰기 또는 읽기를 요청해서 I/O 작업 부하가 크다.
    * 인덱스 레인지 스캔에 사용된다.
* 순차 I/O
    * 디스크 헤더를 한번만 움직인다.
    * 캐시 메모리가 장착된 RAID 컨트롤러를 사용해 순차 I/O를 효율적으로 처리할 수 있게 한다.
    * 풀 테이블 스캔에 사용된다.

> 쿼리 튜닝에 있어 랜덤 I/O를 줄이는 것이 중요하다.

# 인덱스
## 인덱스에 대해 설명해주세요.
인덱스는 컬럼의 값과 해당 레코드가 저장된 주소를 키와 값의 쌍으로 삼아 주어진 순서로 미리 정렬해 저장해 두고 원하는 결과를 최대한 빠르게 찾아갈 수 있도록 한다.

<img src = https://media.geeksforgeeks.org/wp-content/uploads/20230620131119/Structure-of-an-Index-in-Database_1-(1).webp width = 300>
<img src = https://media.licdn.com/dms/image/D5612AQEz-DtX6237cw/article-cover_image-shrink_720_1280/0/1677819422468?e=2147483647&v=beta&t=_F-qLQSEL0kbykzbqb-6A0n0m1NtVGT2aOVcedv4TH4 width = 400>


## 인덱스는 많을수록 좋을까요, 적을수록 좋을까요?
DBMS에서 인덱스는 데이터의 저장(INSERT, UPDATE, DELETE) 성능을 희생하고 그 대신 데이터 읽기 속도를 높이는 기능입니다. 읽기 작업이 빈번하고 저장 작업이 비교적 적은 테이블이라면 인덱스가 성능을 높여주고, 그렇지 않다면 인덱스를 걸지 않는 것이 좋습니다.

## Clustered Index에 대해 설명해주세요
* 클러스터링 인덱스란 프라이머리 키와 값이 비슷한 레코드끼리 묶어서 저장하는 것
    * 프라이머리 키가 변경되면 그 레코드의 물리적인 저장 위치가 변경된다.
* InnoDB와 같이 항상 클러스터링 인덱스로 저장되는 테이블은 프라이머리 키 기반의 검색이 매우 빠르다. 대신 레코드의 저장이나 프라이머리 키 변경이 상대적으로 느리다.
> InnoDB 스토리지 엔진에서만 지원
## Clustered Index를 명시적으로 설정하지 않으면 어떻게 될까요?
InnoDB에서는 명시적으로 Clustered Index를 설정하지 않더라도 InnoDB는 그 값을 생성한다. 그렇지만 사용자에게 보이지 않기 때문에 사용자가 접근하는 것이 불가능하다.

## Nonclustered Index에 대해 설명해주세요
* 레코드의 원본은 정렬되지 않고, 인덱스 페이지만 정렬된다.
* 인덱스를 생성할 때 데이터 페이지는 그냥 둔 상태에서 별도의 인덱스 페이지를 따로 만들기 때문에 용량을 더 차지한다.

## B+Tree와 B-Tree의 차이점에 대해 설명해주세요
* B-tree : 균형 트리로 루트로부터 리프까지의 거리가 일정한 트리 구조
* B+tree : B+Tree 는 리프 노드를 제외하고 값을 담아두지 않기 때문에 하나의 블록에 더많은 Key 들을 담아 둘 수 있다.
* 풀 스캔시 B+Tree는 리프 노드에 데이터가 모두 있기 때문에 한번의 선형 탐색만 하면 되기 때문에 B-Tree 에 비해 빠르다 는 장점이 있다.

| 구분 | B-Tree | B+Tree |
| --- | --- | --- |
| 데이터 저장 | 모든 노드는 데이터 저장 가능 | 리프노드에만 데이터 저장 가능 |
| 트리의 높이 |높음 | 낮음(한 노드 당 Key 를 많이 담을 수 있다.) |
|풀 스캔시, 검색 속도 | 모든 노드 탐색 | 리프 노드에서 선형 탐색 |
| 키 중복 | 없음 | 있음(리프 노드에 모든 데이터가 있기 때문에) |
| 검색 | 자주 access 되는 노드를 루트 노드에 가까이 배치하여 루트 노드에서 가까울 경우, 브랜치 노드에도 데이터가 존재하기 때문에 빠름 | 리프 노드까지 가야 데이터 존재 |
| 더블 링크드 리스트 | 없음 | 리프 노드끼리 링크드 리스트로 연결되어있음 | 


[참고자료](https://velog.io/@emplam27/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-B-Tree)
## 인덱스의 종류에 대해서 설명해주세요
[역할별 구분]
* 프라이머리 키
    * 레코드를 대표하는 컬럼의 값으로 만들어진 인덱스를 의미한다.
    * 해당 레코드를 식별할 수 있는 값이기 때문에 식별자라고도 부른다.
    * Null을 허용하지 않으며 중복을 허용하지 않는다.
* 보조 키(세컨더리 인덱스)
    * 프라이머리 키를 제외한 나머지 모든 인덱스는 세컨더리 인덱스로 분류한다.
    * 유니크 인덱스는 프라이머리 키와 비슷하고 프라이머리 키를 대체해 사용할 수 있어 대체 키라고도 부른다.
# 로그
## [Binary 로그](https://dev.mysql.com/doc/refman/8.0/en/binary-log.html)는 언제 사용될까요?
binary log는 레플리케이션과 데이터 복구 작업에 사용된다. 

레플리케이션에서 복제 대상 데이터베이스(master DB)의 binary log는 복제할 데이터베이스(slave DB)에 전송될 데이터 변경사항에 관한 기록을 제공한다. 복제 대상 데이터베이스(master DB)는 binary log가 포함된 정보를 복제할 데이터베이스(slave DB)에 전송하고 복제할 데이터베이스(slave DB)에서는 복제 대상 데이터베이스(master DB)와 같은 데이터 변경 사항이 이루어지도록 해당 트랜잭션을 재현한다.

특정 데이터 복구 작업은 binary log을 사용한다. 백업이 복원된 후, 백업이 이루어진 시점 이후에 기록된 바이너리 로그의 이벤트들이 다시 실행된다. 이 이벤트들은 데이터베이스를 백업 시점에서 최신 상태로 만든다.


>**binary log**
><br>데이터베이스 변경 사항 관련된 이벤트를 포함한다. 이러한 이벤트에는 테이블 생성 작업 또는 테이블 데이터 변경이 있다. row-based logging을 사용하는 경우는 제외된다.

>**(레플리케이션)replication**
>한국말로 번역하면 복제라는 뜻으로 Master 서버에 저장되는 데이터들을 Slave 서버에 자동적으로 복제되도록 만드는 것


# [트랜잭션](https://en.wikipedia.org/wiki/Database_transaction)
하나의 거래를 안전하게 처리하도록 보장해주는 것

## 트랜잭션 4가지 특성[(ACID)](https://en.wikipedia.org/wiki/ACID)에 대해서 설명해주세요
* 원자성(Atomicity)
    * 트랜잭션 내에서 실행한 작업들은 하나의 작업인 것처럼 모두 성공하거나 모두 실패해야 한다.
* 일관성(Consistency)
    * 모든 트랜잭션은 일관성 있는 데이터베이스 상태를 유지해야 한다.
* 격리성(Isolation)
    * 동시에 실행되는 트랜잭션들이 서로에게 영향을 미치지 않도록 격리한다.
    * 동시성 제어를 위해 트랜잭션 격리 수준을 선택할 수 있다.
* 지속성(Durability)
    * 트랜잭션을 성공적으로 끝내면 그 결과가 항상 기록되어야 한다.
    * 중간에 시스템에 문제가 발생해도 데이터베이스 로그 등을 사용해서 성공한 트랜잭션 내용을 복구해야 한다.
    * 이는 완료된 트랜잭션은 비휘발성 메모리에 기록됨을 의미한다.
## TCL(Transaction Control Language)에 대해 설명해주세요.
* 트랜잭션을 제어할때 사용한다.
* 논리적인 작업 단위를 묶어 DML에 의해 조작된 결과를 트랜잭션 별로 제어한다.
* commit과 rollback이 있다.
>- commit : 트랜잭션의 모든 변경사항을 데이터베이스에 영구적으로 저장
><br>- rollback : 트랜잭션이 시작된 이후에 발생한 모든 변경사항을 취소하고, 트랜잭션 시작 전의 상태로 되돌림
## [트랜잭션](https://en.wikipedia.org/wiki/Database_transaction)에 대해 설명해주세요.
* 데이터베이스의 상태를 변화시키기 위해 수행하는 작업의 단위
* 하나의 거래를 안전하게 처리하도록 보장해주는 것

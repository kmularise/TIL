# 데이터베이스의 데이터 조회 및 수정 방법
## 디스크 읽기 방식
* SSD는 기존 하드 디스크 드라이브에서 데이터 저장용 플래터(원판)을 제거하고 그 대신 플래시 메모리를 장착하고 있다.
* SSD는 빠르고, 전원이 공급되지 않아도 데이터가 삭제되지 않는다.
* SSD는 기존 하드 디스크보다 랜덤 I/O가 빠르다.
    * 데이터베이스 서버에서 순차 I/O 작업은 비중이 크지 않고, 랜덤 I/O를 통해 작은 데이터를 읽고 쓰는 작업이 대부분이므로 SSD는 DBMS 스토리지에 최적이다.

## 랜덤 I/O와 순차 I/O
* I/O : 하드 디스크 드라이브의 플래터(원판)을 돌려서 읽어야 할 데이터가 저장된 위치로 디스크 헤더(disk arm)을 이동시킨 다음 데이터를 읽는다.
* 순차 I/O : 여러 개의 데이터를 입력할 때, 디스크 헤더를 한번만 움직인다.
* 랜덤 I/O : 여러 개의 데이터를 입력할 때, 디스크 헤더를 한번만 움직인다.
-> 디스크 헤더를 움직이는 시간은 디스크에 데이터를 읽고 쓰는데 걸리는 시간에서 가장 많이 차지하고, 병목이 되는 부분이다.
-> 여러 번 쓰기 또는 읽기를 요청하는 랜덤 I/O 작업이 작업 부하가 훨씬 더 크다.

## 쿼리 튜닝
* 일반적으로 쿼리를 튜닝하는 것은 랜덤 I/O 자체를 줄여주는 것이 목적이다.
    * 랜덤 I/O를 줄여 쿼리를 처리하는 데 꼭 필요한 데이터만 읽도록 쿼리를 개선하는 것을 의미한다.
* 인덱스 레인지 스캔 (Index Range scan)은 데이터를 읽기 위해 주로 랜덤 I/O를 이용하고 풀 테이블 스캔(Full Table scan)은 순차 I/O를 사용한다.

## 클러스터링 인덱스
### 클러스터링 인덱스를 활용한 스캔 : 순차 I/O
* 클러스터링 익데스(PK)를 이용해 레인지 스캔을 하게 되면, 순차 I/O 방식으로 동작한다.
* 클러스터링 인덱스는 메모리 상에 저장된 데이터 페이지의 논리적인 위치와 물리적으로 저장된 위치가 동일한 순서로 정렬된다.
* 클러스터링 인데스를 통해 조회하더라도, 순서대로 조회하는 것이 아니라 띄엄띄엄 조회하게 된다면 랜덤 I/O 방식으로 동작한다.

## 세컨더리 인덱스
### 세컨더리 인덱스를 활용한 스캔 : 랜덤 I/O
* 데이터 페이지의 위치와 물리적인 위치가 동일한 순서가 아니다.
* 세컨더리 인덱스는 물리적인 저장위치를 저장하는 것이 아니라 클러스터링 인덱스의 위치를 저장하고 있다.
* 세컨더리 인덱스를 이용해 조회하는 명령은 여러 곳에 분산된 데이터를 조회하게 되므로, 랜덤 I/O 방식으로 동작한다.
* 수정하는 작업 또한 인덱스를 활용하므로 여러 데이터를 동시에 수정하는 작업도 랜덤 I/O 방식으로 작동한다.
# 인덱스

## 풀스캔(full scan) vs 인덱스 설정
* 인덱스가 걸려있으면 full scan보다 더 빨리 찾을 수 있다.
* O(log N) (B-tree based index)

## 인데스를 사용하는 이유
조건을 만족하는 튜플(들)을 빠르게 조회하기 위해서이다. 빠르게 정렬(order by)하거나 그룹핑(group by)하기 위해서이다.

## 유니크 인덱스(unique index)
유니크 인덱스가 설정되어 있으면 인덱스 값이 동일한 행들을 여러개 갖는 것은 허용되지 않는다. 이를 통해 데이터 무결성을 지킬 수 있다.

## multicolumn index, composite index(다중 컬럼 인덱스)
인덱스를 생성할 때 여러 개의 컬럼을 사용해 인덱스를 만드는 것이다. 인덱스를 만들 때 컬럼의 순서는 cardinality(기수성)이 높은 순서대로 설정할 때 성능이 더 좋다.


## primary key와 인덱스
RDB에서 primary key 지정 시 index가 자동 생성된다.

## B-tree based index
이진 탐색을 이용한다. 사용할 수 있는 인덱스가 여러개인 경우, optimizer가 알아서 적절하게 인덱스를 선택한다.

## 인덱스 단점 
* table에 write할 때마다 인덱스도 변경이 발생한다.
* 추가적인 저장 공간을 차지한다.
-> 따라서 불필요한 인덱스는 만들지 않는 것이 좋다.

## 커버링 인덱스(Covering index)
* 조회하는 attribute(s)를 인덱스가 모두 cover할 때
* 조회 성능이 더 빠르다.

# 캐시

## 정의
값비싼 연산 결과 또는 자주 참조되는 데이터를 메모리 안에 두고, 뒤이은 요청이 보다 빨리 처리될 수 있도록 하는 저장소

## 효과
* 응답시간(latency) 개선
* 애플리케이션의 성능은 데이터베이스의 호출 빈도수에 영향을 받는다. -> 캐시는 데이터베이스 호출 빈도수를 줄일 수 있다.
* 데이터베이스 부하를 줄일 수 있다.

## 캐시 계층
* 데이터가 잠시 보관되는 곳
* 데이터베이스보다 훨씬 빠르다.
* 성능 개선, 데이터베이스 부하를 줄일 수 있다.
* 캐시 계층 규모를 독립적으로 확장시키는 것이 가능해짐

## 캐시 사용시 유의할 점
### 캐시가 바람직한 상황은?
- 데이터 갱신은 자주 일어나지 않지만 참조가 빈번하게 일어나는 경우
### 캐시에 두기 적합한 데이터 종류
- 영속적으로 보관할 데이터를 캐시에 두는 것은 바람직하지 않다.
- 중요 데이터는 지속적 저장소(persistent data store)에 두어야 한다.
### 캐시 데이터 만료 정책
* 만료된 데이터는 캐시에서 삭제된다.
* 만료기한이 너무 짧으면 데이터베이스 호출을 자주하게 되고, 만료기한이 너무 길면 원본과 차이가 날 가능성이 높아진다.
### 일관성 유지
* 일관성 : 데이터 저장소의 원본과 캐시 내의 사본이 동일한지 여부
### 장애 대처
* 캐시 서버를 한 대만 두는 경우, 해당 서버가 단일 장애 지점(SPOF, Single Point of Failure)이 되어버릴 가능성이 있다.
* SPOF를 피하려면 여러 지역에 거쳐 캐시 서버를 분산시켜야 한다.
### 캐시 메모리 크기
* 캐시 메모리가 너무 작으면 액세스 패턴에 따라 데이터가 너무 자주 캐시에서 밀려나버려(eviction) 캐시의 성능이 떨어진다. 
* 캐시 메모리 과할당(overprovision)을 통해 캐시에 보관될 데이터가 갑자기 늘어났을 때 막을 수 있다.
### 데이터 방출(eviction) 정책
* 캐시가 찼을 때 추가로 캐시에 데이터를 넣어야 할 경우 기존 데이터를 내보내야 한다. -> 캐시 데이터 방출 정책
* LRU(Least Recently Used) : 마지막으로 사용된 시점이 가장 오래된 데이터를 내보내는 정책, LFU, FIFO


## 참고자료
* 가상 면접 사례로 배우는 대규모 시스템 설계

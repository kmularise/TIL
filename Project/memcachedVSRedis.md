# memcached VS Redis

|   | Memcached | Redis |
| ---- | ---- | ---- |
| 밀리 초 미안의 대기시간 | O | O |
| 데이터 파티셔닝 | O | O |
| 자료구조 지원 | X | O |
| 멀티 스레드 | O | X |
| 스냅샷 | X | O |
| replication | X | O |
| 트랜잭션 | X | O |
| Pub/Sub | X | O |
| 루아 스크립트 | X | O |
| 위치 정보 지원 | X | O | 

### 밀리초 미만의 대기 시간 (Sub-millisecond latency)
Redis와 Memcached는 모두 밀리초 미만의 응답 시간을 지원한다. 데이터를 메모리에 저장함으로써 디스크 기반 데이터베이스보다 더 빠르게 데이터를 읽을 수 있다.

### 데이터 파티셔닝
Redis와 Memcached 모두 여러 노드에 데이터를 분할할 수 있다. 이를 통해 수요가 증가할 때 더 많은 데이터를 더 효과적으로 처리할 수 있도록 확장할 수 있다. 

### 자료구조 지원
문자열 외에도 Redis는  list, set, sorted set, hashes, bit arrays 및 hyperloglogs를 지원합니다. 애플리케이션은 이러한 자료구조를 사용하여 다양한 사용 사례를 지원할 수 있다. 예를 들어 Redis Sorted Set를 사용하면 순위별로 정렬된 플레이어 목록을 유지하는 게임 리더보드를 쉽게 구현할 수 있다.

### 멀티스레드
Memcached는 멀티스레드이므로 멀티 코어를 사용할 수 있습니다. 즉, 컴퓨팅 용량을 확장하면 더 많은 작업을 처리할 수 있습다.

### 스냅샷
Redis를 사용하면 특정 시점 스냅샷을 통해 데이터를 디스크에 보관할 수 있다. 이는 복구에 사용할 수 있다.

### Replication(복제)
Redis를 사용하면 Redis 기본 복제본을 여러 개 생성할 수 있다. 이를 통해 데이터베이스 읽기를 확장하고 가용성이 높은 클러스터를 보유할 수 있다.

### 트랜잭션
Redis는 명령 그룹을 격리된 원자성 작업으로 실행할 수 있는 트랜잭션을 지원한다.

### Pub/Sub
Redis는 채팅, 실시간 댓글 스트림, 소셜 미디어 피드 및 서버 상호 통신에 사용할 수 있는 패턴 일치를 통해 Pub/Sub 메시징을 지원한다.

### 루아 스크립트
Redis를 사용하면 트랜잭션 Lua 스크립트를 실행할 수 있다. 스크립트는 성능을 향상하고 애플리케이션을 단순화하는 데 도움이 될 수 있다.

### 위치 정보 지원
Redis에는 대규모 실시간 위치 정보 처리 작업을 위해 제작된 명령어가 있다. 두 요소(예: 사람 또는 장소) 사이의 거리를 찾고, 특정 지점의 거리 내에 있는 모든 요소를 찾는 등의 작업을 수행할 수 있다.

## 참고자료
https://aws.amazon.com/ko/elasticache/redis-vs-memcached/
* 데이터 파티셔닝
* 채팅 관련 자료
https://aws.amazon.com/blogs/database/how-to-build-a-chat-application-with-amazon-elasticache-for-redis/
* Redis 위치정보
https://aws.amazon.com/blogs/database/amazon-elasticache-utilizing-redis-geospatial-capabilities/

# 분산 락
* 서로 다른 프로세스가 상호배타적(mutually exclusive)인 방식으로 공유자원을 처리하는 환경에서 유용한 기술

## 필요성

![image](https://github.com/kmularise/TIL/assets/106499310/3bc3736e-053c-415a-8aae-0a8e5f9ab130)

* 여러 요청들이 한 자원에 대해서 공유할 때, 각 분산 DB의 동기화가 여러 요청의 동기화 속도를 못 따라 가는 상황이 발생한다.
* 이에 대해 데이터 정합성은 깨지게 되고, 데이터 동시성 문제가 발생하게 된다.
* 예를 들어, 위와 같이 한 번에 여러 구매 요청이 들어왔을 경우 수량이라는 자원을 동시에 사용할 경우 여러 수량의 커밋되거나 롤백되는 수량의 동기화가 다른 서버가 따라가지 못해서 정합성이 깨지고, 동시성 문제가 발생할 수 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/2ae7ca18-d8a8-4263-a5d0-ce30e3e666ee)

* 단적인 예를 들어, 공유 자원인 수량을 레디스에 올려놓고 분산락(Distributed Lock)을 활용해서 데이터 동시성 문제를 해결할 수 있다.
* 각 요청마다 락을 점유하고 데이터를 업데이트하기 때문에 각 서버는 각 DB의 동기화를 기다리지 않아도 되며, 동시성 문제도 해결할 수 있다.

## 분산 락 구현 방법
### MySQL 이용
* 용도 : 분산락의 사용량이 많지 않을 때 이용
* 장점 
    * 인프라 구축과 유지보수에 대한 비용이 발생하지 않는다.
    * MySQL에서 제공하는 USER-LEVEL LOCK은 락에 이름을 지정할 수 있기에 LOCK 이름을 이용하여 애플리케이션 단에서 제어 가능
* 단점
    * 락은 휘발성 데이터에 가깝기에 영구적인 데이터를 저장하는 용도로 쓰이는 MySQL은 적합하지 않을 수 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/fdb7d20e-7bb6-4a2a-b135-0316864e2006)

### Redis 이용
보통 다음과 같은 이유로 Redisson을 사용한다.
* Lettuce
    * 스핀락(Spin Lock)을 사용
    * 스핀락(Spin Lock)은 Lock이 없는 프로세스는 Lock을 획득하기 위해 무한루프를 돌게되는데, 이 때 CPU를 낭비하게 되기 때문에 성능 저하로 이어질 수 있다.
* Redisson
    * pubsub 기능 사용
    * pubsub이란 마치 인터럽트처럼, Lock 획득을 기다리는 클라이언트에게 Lock 획득할 수 있다는 신호를 보내주는 것

## 참고자료
https://redis.io/docs/manual/patterns/distributed-locks/
* MySQL 이용한 분산락
    * https://techblog.woowahan.com/2631/
    * https://dev.mysql.com/doc/refman/5.7/en/locking-functions.html
    * https://medium.com/@htyesilyurt/distributed-lock-with-redisson-rlock-and-spring-boot-redis-pub-sub-86d51fd83e8b
* Redis Pub / Sub
    * https://inpa.tistory.com/entry/REDIS-%F0%9F%93%9A-PUBSUB-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C-%EC%B1%84%ED%8C%85-%EA%B5%AC%EB%8F%85-%EC%95%8C%EB%A6%BC
* Lettuce VS Redisson
    * https://redisson.org/lettuce-replacement-why-redisson-is-the-best-lettuce-alternative.html

<!-- https://wildeveloperetrain.tistory.com/280 -->
<!-- https://serverwizard.tistory.com/281 -->
<!-- https://velog.io/@znftm97/%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0-V3-%EB%B6%84%EC%82%B0-DB-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-%EB%B6%84%EC%82%B0-%EB%9D%BDDistributed-Lock-%ED%99%9C%EC%9A%A9 -->
<!-- https://velog.io/@hgs-study/redisson-distributed-lock -->
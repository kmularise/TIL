# @Transactional
스프링의 @Transactional은 AOP (관점 지향 프로그래밍, Aspect-Oriented Programming) 기반으로 트랜잭션을 적용할 수 있게 도와준다.

## @Transactional과 AOP
* @Transactional 어노테이션이 붙으면 트랜잭션이 관리 대상이 되고 스프링은 해당 타겟을 포인트 컷의 대상으로 자동 등록한다.
* 선언적 트랜잭션 : AOP를 이용해 코드 외부에서 트랜잭션의 기능을 부여해주고 속성을 지정할 수 있게 해주는 것

## @Transactional 롤백 처리
스프링의 선언적 트랜잭션 안에서 예외가 발생했을 때, 해당 예외가 unchecked Exception (런타임 예외)이라면 자동적으로 롤백이 발생한다. 하지만 checked exception 이라면 롤백이 되지 않는다.

## @Transactional 동작방식
1. @Transactional을 붙이면 내부적으로 커넥션을 가져와서 자동 커밋 모드를 비활성화하고 트랜잭션을 시작한다.

2. 해당 커넥션을 한 스레드에서 공유할 수 있도록 스레드 로컬(Thread Local) 변수에 저장하는 트랜잭션 동기화(Transaction Synchronization)를 진행한다.

<!-- https://mangkyu.tistory.com/154 -->
<!-- https://mangkyu.tistory.com/169 -->
<!-- https://mangkyu.tistory.com/170 -->
<!-- https://mangkyu.tistory.com/312 -->
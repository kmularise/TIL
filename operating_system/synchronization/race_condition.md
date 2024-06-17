# race condition

- 공유 데이터(shared data)의 동시 접근(concurrent access)은 데이터의 불일치 문제(inconsistency)를 발생시킬 수 있다.
- 일관성(concistency) 유지를 위해서는 협력 프로세스(coopertating process) 간의 실행 순서(orderly exectuion)를 정해주는 메커니즘 필요

<br>
<br>

## Race condition

- 여러 프로세스들이 동시에 공유 데이터를 접근하는 상황
- 데이터의 최종 연산 결과는 마지막에 그 데이터를 다룬 프로세스에 따라 달라짐

→ race condition을 막기 위해서는 concurrent process는 동기화(synchronize)되어야 한다.

## The Critical-Section Problem

- n개의 프로세스가 공유 데이터를 동시에 사용하기를 원하는 경우
- 각 프로세스의 code segment에는 공유 데이터를 접근하는 코드인 critical section이 존재
- Problem
    - 하나의 프로세스가 critical section에 있을 대 다른 모든 프로세스는 critical section에 들어갈 수 없어야 한다.

## 프로그램적 해결법의 충족 조건

### Mutual Exclusion (상호 배제)

- 프로세스 Pi가 critical section 부분을 수행 중이면 다른 모든 프로세스들은 그들의 critical section에 들어가면 안 된다.

### Progress(진행)

- 아무도 critical section에 있지 않은 상태에서 critical section에 들어가고자 하는 프로세스가 있으면 critical section에 들어가게 해주어야 한다.

### Bounded Waiting(유한 대기)

- 프로세스가 critical section에 들어가려고 요청한 후부터 그 요청이 허용될 때까지 다른 프로세스들이 critical section에 들어가게 해주어야 한다.
- 가정
    - 모든 프로세스의 수행 속도는 0보다 크다
    - 프로세스들 간의 상대적인 수행 속도는 가정하지 않는다.

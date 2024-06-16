# 스케줄링 알고리즘

## FCFS(First-Come-First-Served)
* convoy effect: 큐에서 오래 기다린다.

<br>
<br>

## SJF(Shortest-Job-First)
- 주어진 프로세스 들에 대해 최소 평균 대기 시간(minimum average waiting time)을 보장한다.
- 문제점 :
    - Starvation : 우선순위가 낮은 프로세스는 영원히 실행되지 않을 수도 있다.
    - CPU 사용 시간을 미리 알 수 없다.

<br>
<br>

### 1. 비선점(Nonpreemptive)
일단 CPU를 잡으면 이번 CPU burst가 완료될 때까지 CPU를 선점(preemption) 당하지 않는다.

### 2. 선점(Preemptive)
- 현재 수행 중인 프로세스의 남은 burst time 보다 더 짧은 CPU burst time을 가지는 새로운 프로세스가 도착하면 CPU를 빼앗긴다.
- 이 방법을 Shortest-Remaining-Time-First(SRTF) 이라고도 부른다.

<br>
<br>

## Priority Scheduling
- 선점과 비선점 스케줄링이 있다.
- SJF는 일종의 priority scheduling이다.
- 문제점 : 기아(starvation) 발생
    → 해결책 : Aging - 시간이 지나면 프로세스의 우선순위를 높여 준다.

<br>
<br>

## 라운드 로빈(Round Robin, RR)
* 각 프로세스는 동일한 크기의 할당 시간(time quantum)을 가진다. 
* 할당 시간이 지나면 프로세스는 선점(preempted)당하고 ready queue의 제일 뒤에 가서 다시 줄을 선다.
* n개의 프로세스가  ready queue에 있고 할당 시간이 q time unit인 경우 각 프로세스는 최대 q  time unit 단위로 CPU 시간의 1/n을 얻는다.
→ 어떤 프로세스도 (n-1)q time unit 이상 기다리지 않는다.
- 실행
    - q가 커지면 ⇒ FCFS
    - q가 작아지면 ⇒ 컨텍스트 스위칭 오버헤드가 커진다.

<br>
<br>

## Multilevel Queue
![image](https://github.com/kmularise/TIL/assets/106499310/f2cccdf8-de0b-4f1c-bbca-d4c48b8cfa2a)

- Ready queue를 여러 개로 분할
    - foreground(interactive)
    - background(batch - no human interaction)
- 각 큐는 독립적인 스케줄링 알고리즘을 가짐
    - [foreground 프로세스](../process/process-0-kind.md) - RR
    - [background 프로세스]((../process/process-0-kind.md)) - FCFS
- 큐에 대한 스케줄링이 필요
    - Fixed priority scheduling
        - foreground 먼저 그 다음에 background
        - starvation 가능성 있음
    - Time slice
        - 각 큐에 CPU time을 적절한 비율로 할당
        - ex) foreground에 RR로 80% 할당, background에 FCFS로 20% 할당

<br>
<br>

## Mutilevel Feedback Queue
![image](https://github.com/kmularise/TIL/assets/106499310/b4fd8699-d99d-401f-b322-c64e71440cb5)
- 프로세스가 다른 큐로 이동 가능
- 에이징(aging)을 이와 같은 방식으로 구현할 수 있다.
- Multilevel-feedback-queue scheduler를 정의하는 파라미터들
    - Queue의 수
    - 각 큐의 scheduling algorithm
    - Process를 상위 큐로 보내는 기준
    - Process를 하위 큐로 내쫓는 기준
    - 프로세스가 CPU 서비스를 받으려 할 때 들어갈 큐를 결정하는 기준

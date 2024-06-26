# 프로세스 상태
프로세스 상태는 운영체제마다 차이가 조금씩 있다. 기본적인 상태도로는 다음과 같은 상태도가 있다.

## 프로세스 상태 : Five-State
![image](https://github.com/kmularise/TIL/assets/106499310/bc236461-66bf-4828-a8b9-1751911e3001)

* new (생성 상태)
    * 프로세스가 생성 중인 상태
    * 이제 막 메모리에 적재되어 PCB를 할당 받은 상태
    * 준비가 완료되었다면 준비 상태가 된다.
* ready (준비 상태)
    * 당장이라도 CPU를 할당 받아 실행할 수 있지만 자신의 차례가 아니기에 기다리는 상태
    * 자신의 차례가 된다면 실행 상태가 된다. (=dispatch)

* running (실행 상태)
    * CPU를 할당 받아 실행 중인 상태
    * CPU 할당 시간이 지나면, 즉, 타이머 인터럽트가 발생하면 준비 상태가 된다.
    * 실행 도중 입출력 장치를 사용하면 입출력 작업이 끝날때까지, 즉, 입출력 완료 인터럽트를 받을 때까지 대기상태가 된다. 
* waiting (대기 상태)
    * 프로세스가 특정 자원이나 이벤트를 기다리는 상태
        ex) 프로세스가 실행 도중 입출력 장치를 사용하는 경우
    * 기다리는 작업이 끝나면 준비 상태로 된다.
        ex) 입출력 작업이 끝나면 (입출력 완료 인터럽트를 받으면) 준비 상태로 된다.
* terminated (종료 상태)
    * 프로세스가 종료된 상태
    * PCB, 프로세스의 메모리 영역 정리

## 프로세스 상태 : Seven-State
![image](https://github.com/kmularise/TIL/assets/106499310/45f3f1e4-e890-487f-8a25-ffa5debf67f3)
- new
- ready
- running
- blocked(wait, sleep)
    - 프로세스가 CPU를 할당 받아도 당장 실행할 수 없는 상태
    - 프로세스 자신이 요청한 이벤트(예: I/O)가 즉시 만족되지 않아 이를 기다리는 상태
    - wait : 지정한 프로세스의 자식 프로세스를 포함해서 프로세스들의 작업이 끝나기를 기다리는 상태
    - sleep : 프로세스를 정해준 시간만큼 잠시 멈추는 것
- terminated
- suspended : 프로세스의 중지 상태
    - 외부적인 이유로 프로세스의 수행이 정지된 상태
    - 프로세스는 통째로  디스크에  swap out된다.
    - (예) 사용자가 프로그램을 일시 정지시킨 경우 (break key)
        
        시스템이 여러 이유로 프로세스를 잠시 중단시킴
        
        (메모리에 너무 많은 프로세스가 올라와 있을 때)
        
        cf ) Blocked : 자신이 요청한 event가 만족되어 Ready
        
        Suspended: 외부에서 resume해 주어야 Active


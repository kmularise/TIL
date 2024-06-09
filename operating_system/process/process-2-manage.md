# 운영체제의 프로세스 관리방법
프로세스는 실행되기 위해서 CPU를 필요로 한다. 하지만 CPU의 자원은 한정되어 있기에, 프로세스는 일정시간동안 CPU를 점유하고 있다가 타이머 인터럽트가 발생하면 프로세스는 다음 프로세스에게 CPU 차례를 양보한다. 이렇게 운영체제는 프로세스를 관리해야 하는데, 이 때 사용하는 자료구조가 PCB이다.  
 
<br>

## PCB(process control block, 프로세스 제어블록)
* 프로세스 관련 정보를 저장하는 자료
* 프로세스 생성 시 커널 영역에 생성되고, 프로그램 종료 시에 폐기 된다.
* 운영체제가 각 프로세스를 관리하기 위해 프로세스당 유지하는 정보

![image](https://github.com/kmularise/TIL/assets/106499310/a0648715-e4e3-48af-ae9c-3eb356867628)


- 운영체제가 각 프로세스를 관리하기 위해 프로세스당 유지하는 정보
- 다음의 구성 요소를 가진다 (구조체로 유지)
    1. OS가 관리상 사용하는 정보
        - Process state, Process ID
        - scheduling information, priority
    2. CPU 수행 관련 하드웨어 값
        - Program counter, registers
    3. 메모리 관련
        - code, data, stack의 위치 정보
    4. 파일 관련
        - Open file descriptors…


## 컨텍스트 스위칭(context switching)
* CPU를 한 프로세스에서 다른 프로세스로 넘겨주는 과정
- CPU가 다른 프로세스에게 넘어갈 때 운영체제는 다음을 수행
    - CPU를 내어주는 프로세스 상태를 그 프로세스의 PCB에 저장
    - CPU를 새롭게 얻는 프로세스의 상태를 PCB에서 읽어옴
* 시스템 콜이나 인터럽트 발생 시 반드시 컨텍스트 스위칭이 일어나는 것은 아니다.
<br>

![context_switch drawio](https://github.com/kmularise/TIL/assets/106499310/6741eb06-40aa-4bea-aa15-8f0741efeb35)
<br>

* (1)의 경우에도 CPU 수행 정보 등 컨텍스트의 일부를 PCB에 저장해야 한다.
* 그러나 문맥교환을 하는 (2)의 경우 그 부담이 훨씬 크다.
    * ex) cache memory flush : 프로세스가 사용하던 캐시 내역을 지워줘야 한다.

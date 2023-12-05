## Mass-Storage
* 비휘발성, 2차 저장 장치
* HDD(Hard Disk Drive) 또는 NVM(Non-Volatile Memory, SSD, 플래시 메모리)
* magnetic tapes(백업용), 광학 디스크, 클라우드 스토리지
    * RAID 시스템 적용

## Disk를 사용하는 이유
* 메모리의 휘발성 : 파일 시스템을 구축해야 한다.
* 프로그램 실행을 위한 메모리 공간 부족 : swap 영역이 있어야 한다.

## Disk Structure 

<img width="557" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/6a10fe85-1510-4119-9e0c-8c3e65a9e3bf">

- logical block
    - 디스크의 외부에서 보는 디스크의 단위 정보 저장 공간들
    - 주소를 가진 1차원 배열처럼 취급한다. (Logical Block Addressing, LBA)
    - 정보를 전송하는 최소 단위 : 운영체제는 파일/디렉토리를 블록 단위로 읽고 쓴다.
- Sector
    - HDD의 최소 저장 단위이다.
    - Logical block이 물리적인 디스크에 매핑된 위치이다.
    - Sector 0은 최외곽 실린더의 첫 트랙에 있는 첫번째 섹터로 logical block에서 0번 인덱스에 해당하며 어떤 파일 시스템을 사용하더라도 부팅관련 정보를 저장하는 공간이다.

![image](https://github.com/kmularise/TIL/assets/106499310/6351ab81-749f-4cf6-bcbc-c8842ede5561)


## Disk Management

### Physical formatting 
- 디스크 컨트롤러가 읽고 쓸수 있는 섹터 단위로 디스크 공간을 나누는 과정을 의미한다.
- 각 섹터는 "header + 실제 data(보통 512byte) + trailier"로 구성된다.
- header, tailer는 데이터에 대한 메타데이터이며 디스크 컨트롤러가 직접 접근 및 운영한다. 이 공간에는 sector number, ECC(error-correcting code)등의 정보가 저장된다.

### Partitioning
- 물리 디스크를 하나 이상의 실린더 그룹으로 나누는 과정이다. 논리적인 영역으로 구회한다.
- 운영체제 입장에서는 이 그룹을 각각을 독립적인 디스크로 인식하기 때문에 logical disk라고도 부른다.

### Logical formatting
- 파일 시스템을 설정한다.
- 어떤 방식으로 파일을 관리할 지 결정, 새로운 데이터를 쓸 준비하는 작업

### booting
- 컴퓨터 부팅 시 메모리에는 어떠한 정보도 저장되지 않은 빈공간이다. CPU는 디스크에 직접 접근하지 못하며 메모리만 접근 가능하다.
- 메모리 중 ROM에 있는 small bootstrap loader를 실행하면 Sector 0(boot block)을 로드해 실행하게 된다.
- sector 0에는 full bootstrap load program이 존재하며 이를 통해 디스크에 접근하고 OS를 메모리에 적재하게 된다.


## HDD 스케줄링

### access time의 구성
* Seek time : 헤드를 특정 실린더로 움직이는데 걸리는 시간, 가장 오래 걸린다.
* Rotational latency : 헤드가 원하는 섹터에 도달하기까지 걸리는 회전지연시간
* Transfer time : 실제 데이터의 전송 시간

### HDD 스케줄링 목표
* access time 최소화 : seek time이 가장 비중을 많이 차지하므로 결국 seek time을 최소화해야 한다. (seek time = seek distance)
* disk bandwitdh를 최대한 높여야 한다.

## HDD 스케줄링 알고리즘

다음과 같은 종류의 알고리즘이 있다.

- FCFS
- SCAN
- C-SCAN

그 밖에 SSTF, LOOK, C-LOOK 알고리즘도 있으나 Operating System Concepts 10v판에서는 빠졌기 때문에 여기서는 자세히는 다루지 않고 넘어가려고 한다.

### FCFS(First Come First Served)
* 모든 요청은 도착 순서대로 처리된다.
* 일반적으로 가장 빠른 방식을 제공하지 않는다.
<p>
    <img width="393" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/6f107913-35c7-4509-b21c-42d53eb449e5">
</p>
<p>
<em>head 움직임 횟수 : 640 cylinders</em>
</p>

### SCAN(elevator scheduling)
* disk arm은 디스크의 한쪽 끝에서 다른 쪽 끝으로 이동하면서, 각 실린더에 도달할 때마다 요청을 처리한다. 디스크의 다른 쪽 끝에 도달하면 헤드의 이동방향을 반대로 바꾸고 다시 이동하고 각 실린더에 도달할 때마다 요청을 처리하면서 다시 반대쪽 끝으로 이동한다.

* 실린더에 대한 요청이 균일하게 분포한다고 가정할 때, 헤드가 한쪽 끝에 도달해서 방향을 바꿀 때 헤드 바로 앞에 있는 요청은 상대적으로 적다. 이는 해당 실린더가 최근에 요청이 처리되었기 때문이다. 헤드 바로 앞에 큐에 요청이 도착하면 거의 즉시 처리된다. 반면 헤드 바로 뒤에 도착한 요청은 disk arm이 디스크의 끝까지 이동하여 방향을 바꾸고 돌아올 때까지 기다려야 한다.
<p>
<img width="418" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/041b5623-f156-419d-883b-4976c52e5bae">
</p>
<p>
<em>head 움직임 횟수 : 236 cylinders</em>
</p>

### C-SCAN(Circular SCAN)
* 더 균일한 대기시간을 제공하기 위해 SCAN 알고리즘을 변형했다.
* C-SCAN은 SCAN처럼 헤드를 디스크의 한 쪽 끝에서 다른 쪽 끝까지 이동시키면서 각 실린더의 요청들을 처리한다. 그러나 헤드가 다른 한쪽 끝에 도달하면 즉시 디스크의 시작 부분으로 돌아간다. 원형리스트와 같이 처리한다.
<p>
<img width="417" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/c31fd504-7c82-45a4-9b17-880727c21bf4">
</p>
<p>
<em>head 움직임 횟수 : 183 cylinders</em>
</p>

### HDD 스케줄링 알고리즘 결정
* SCAN, C-SCAN은 입출력이 많은 시스템에서 효율적이다.
    * starvation(기아) 문제를 일으킬 가능성이 적기 때문이다.
    * 그러나 헤드 바로 앞쪽에 무수히 많은 요청이 지속적으로 들어오는 경우와 같이 starvation 문제가 발생할 수 있다. 리눅스에서는 이를 방지하기 위해 오래된 요청을 고려하여 데드라인 스케줄러를 추가하였다.
* HDD 스케줄링 알고리즘은 필요할 경우 다른 알고리즘으로 쉽게 교체할 수 있도록 운영체제와 별도의 모듈로 작성되는 것이 바람직하다.

## Swap Area Management
* 가상 메모리 시스템에서 디스크를 메모리의 연장공간으로 사용한다.
* Swap 영역은 파일 시스템 내부에 둘 수도 있으나 일반적으로 별도 partition을 사용한다.

### Swap 영역이 일반 디스크와 다른 특성
* 공간 효율성보다는 속도 효율성을 우선시한다.
* 일반 파일보다 훨신 짧은 시간만 존재하고 자주 참조된다.

### 현대의 Swap 영역
* Swap 영역은 파일에 의해 백업되지 않는 ```anonymous memory```의 페이지에 대한 백업 저장소로만 사용된다. 여기에는 프로세스의 스택, 힙, 초기화되지 않은 데이터 영역에 할당된 메모리가 포함된다.

## RAID
* 여러개의 디스크를 묶어 사용하는 것이다.

### 디스크 처리 속도 향상
* 여러 디스크에 블록의 내용을 분산 저장한다.
* 병렬적으로 읽어온다. (interleaving, striping)

### 신뢰성 향상
* 동일 정보를 여러 디스크에 중복 저장함으로써 하나의 디스크가 고장 시 다른 디스크에서 데이터를 읽어올 수 있다.(mirroring, shadowing)
* 단순히 모든 정보를 중복 저장하는 것이 아니라 일부 디스크에 패리티(parity)를 저장해 공간의 효율성을 높일 수 있다.

<p>
<img width="355" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/409e0863-9f25-4576-87fa-5061908382d8">
</p>
<p>
<em>RAID levels</em>
</p>

<img width="364" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/4f81f880-ffdc-481f-96b0-caf148e4a30f">
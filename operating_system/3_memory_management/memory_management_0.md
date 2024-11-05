# 메모리 관리

## 논리적 주소와 물리적 주소

### Symbolic address
- 함수나 변수값을 메모리로 사용하는 것

### 논리적 주소(logical address, virtual address)
- 프로세스마다 독립적으로 가지는 주소 공간
- 각 프로세스마다 0번지부터 시작한다.
- CPU가 보는 주소

### 물리적 주소
- 메모리에 실제 올라가는 위치

-> 컴파일이 되면 Symbolic address가 논리적 주소로 변환이 되고, 이를 물리적 메모리 주소로 변환해서 처리하게 된다.

## 주소 바인딩(address binding)
- 한 주소 공간에서 다른 주소 공간으로 매핑하는 과정
- 어느 타이밍에 주소 바인딩이 이루어지는 지에 따라 compile time / load time / run time address binding 으로 나눈다.

![image](https://github.com/user-attachments/assets/dbe614aa-d79a-4e10-b001-a23503608c35)


### compile time address binding
- 컴파일 시간(compile time)에 물리 주소를 결정하는 것으로, 실행 파일에 물리 주소를 포함시킨다.
- 논리 주소와 물리 주소가 같다.
- 프로그램에 물리 주소가 포함 되어 있어 프로세스를 로드하는 것이 빠르지만, 생성된 주소 공간이 다른 프로세스에 의해 점유되어 있을 경우 충돌 할 수 있기 때문에 프로그램을 다시 컴파일 해야 하는 상황이 생긴다.
- 이러한 이유로 멀티 프로그래밍이 가능한 현대 OS 환경에서는 실효성이 떨어져 사용되지 않는 방법이다.

### load time address binding
- 로드 시간(load time)에 논리주소를 물리주소로 바인딩하는 하는 것
- loader에 의해 재배치 가능한(relocatable) 주소가 물리 주소로 변환된다.
    1. 컴파일러가 주소를 결정할 수 없는 경우 symbolic address를 relocatable address로 변경
    2. 이후 로더(loader)에 의해 relocatable address가 absolute address로 변환됨

> relocatable code : 컴파일러 또는 어셈블러에 의해 생성되는 코드로, 컴파일 시간에 알 수 없는 경우 생성된다. 컴파일러가 symbolic code를 relocatable code로 변환 한다. 재배치 가능한 상대 주소를 사용한다.

### run time address binding
- 실행 시간(run time)에 논리 주소를 물리주소로 바인딩하는 것
- 현대 OS는 대부분 이 방식을 사용
- 한 메모리 세그먼트에서 다른 메모리 세그먼트로 실행 중에 이동하는 것이 가능하려면 런타임까지 바인딩을 지연 시켜야한다.
- MMU(Memory Management Unit) 장치에 의해 논리 주소를 물리 주소로 변환한다.

## MMU(Memory Management Unit) 역할
- MMU는 CPU가 메모리에 접근하는 것을 관리하는 컴퓨터 하드웨어 부품이다.
- 가상 메모리 주소를 실제 메모리 주소로 변환하며, 메모리 보호, 캐시 관리, 버스 중재 등의 역할을 담당한다.

<img src="https://github.com/user-attachments/assets/42bfe415-7c71-4977-b63c-59e6b1f567eb" width=400>

- MMU는 페이지를 기본 단위로 가상 주소를 실제 물리 주소로 매핑 시키는 역할을 한다. 변화 과정에서, TLB와 페이지 테이블이 사용된다.
    * TLB : 캐시 역할. 자주 사용되는 페이지 저장
    * 페이지 테이블 : 물리 주소와 연관시킬 수 있는 페이지가 저장되어 있는 자료구조

![image](https://github.com/user-attachments/assets/da4be12c-e7a3-454b-b403-d6f1c74c15e1)

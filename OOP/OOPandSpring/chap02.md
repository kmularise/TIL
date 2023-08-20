# 02. 자바와 절차적/구조적 프로그래밍

## 자바 프로그램의 개발과 구동
| 현실 세계 | 가상 세계(자바 월드) | |
| -------- | --------- | ----- |
| 소프트웨어 개발 도구 | JDK - 자바 개발 도구 | JVM용 소프트웨어 개발 도구 |
| 운영체제 | JRE - 자바 실행 환경 | JVM용 OS |
| 하드웨어 - 물리적 컴퓨터 | JVM - 자바 가상 머신 | 가상의 컴퓨터 |

![image](https://github.com/kmularise/TIL/assets/106499310/c5bb5105-0dd5-4f2b-a092-713b1d1783da)

현실 세계에서 소프트웨어, 즉 프로그램은 개발자가 개발 도구를 이용해 개발하고 운영체제를 통해 물리적 컴퓨터인 하드웨어 상에서 구동된다. 자바 가상 세계에서도 자바 개발도구인 JDK를 이용해 개발된 프로그램은 JRE에 의해 가상의 컴퓨터인 JVM 상에서 구동된다. 
다만 배포되는 JDK, JRE, JVM은 편의를 위해 JDK가 JRE를 포함하고 다시 JRE는 JVM을 포함하는 형태로 배포된다.
자바가 이런 구조를 택한 이유는 작성한 프로그램을 각 플랫폼 용으로 배포되는 설치파일을 따로 준비해야 하는 불편함을 없애기 위해서다.
____________________________
## 프로그램이 메모리를 사용하는 방식
![image](https://github.com/kmularise/TIL/assets/106499310/45169167-07e6-4665-a082-5f766a0b5e76)
* 스태틱: 클래스 할당
* 스택: 메소드 호출 시 리턴 값, 함수 인자, 지역변수 할당
* 힙: 객체 할당
____________________________
## 스택 프레임
* JVM에서 쓰레드가 생성될 때, 해당 쓰레드를 위한 스택도 같이 생성된다.
* 스택 프레임은 메소드가 호출될 때마다 만들어지며, 메소드 상태 정보를 저장한다.

### main() 메소드가 실행되기 전 JVM에서 수행하는 전처리 작업들
* java.lang 패키지를 스태틱 영역(메소드 영역)에 배치한다.
* import된 패키지를 스태틱 영역에 배치한다.
* 프로그램 상 모든 클래스를 스태틱 영역에 배치한다.

### 블록 구문과 메모리: 블록 스택 프레임
* if문을 실행 시 if ~ else 블록 중 해당하는 블록의 스택 프레임이 생성된다.
* if문을 닫는 중괄호를 만나면 블록 스택 프레임 소멸한다. 따라서 소멸 후 if 문 블록 스택 프레임 안의 변수를 사용할 시 오류가 발생한다. 
* 블록 안에서 선언된 것은 밖에서 사용 불가하다.
* 외부 스택 프레임에서 내부 스택 프레임의 변수에 접근하는 것은 불가능하나 그 역은 가능하다.

```
클래스 내 필드 스코프 : 해당 클래스 안
메소드 내 변수 스코프 : 해당 메소드 안
```

### 메소드 호출과 스택 프레임 - 지역 변수의 경우
* 반환값을 저장할 변수 공간이 맨 아래, 그 다음으로 인자를 저장할 변수 공간, 마지막으로 메소드의 지역 변수가 자리 잡는다.
* Call By Value(값에 의한 호출)
    * 매개 변수의 값을 복사하여 함수의 인자로 전달한다. 이 때 서로 변돌의 변수 공간을 가지게 된다.
* 입력 값들(인자 리스트)과 반환값에 의해서만 메소드 사이에 값이 전달될 뿐 서로 내부의 지역 변수를 볼 수 없다.
* 자바에서는 지역 변수의 참조를 금지하고 있다. 따라서 C언어처럼 포인터로 인자를 넘겨서 다른 함수에서 지역변수를 수정할 수 없다.
__________________________________
## 전역 변수와 메모리
* 지역 변수 : 스택 프레임에 종속적
* 전역 변수 : 스택 프레임에 독립적, 공유 변수라고도 한다.

전역 변수를 변경하는 것은 좋지 않다. 여러 메소드에서 전역 변수의 값을 변경하기 시작하면 메모리 영역을 추적하지 않는 이상 전역 변수에 저장돼 있는 값을 파악하기 쉽지 않다.
_______________________________
## 멀티 쓰레드 / 멀티 프로세스
* 멀티 쓰레드 : 스택 영역을 쓰레드 개수만큼 분할해서 사용
* 멀티 프로세스 : 다수의 데이터 저장 영역을 갖는 구조

멀티 프로세스는 각 프로세스마다 데이터 저장 영역이 있고 각자 고유의 공간이므로 서로 참조할 수 없다. 멀티 쓰레드는 하나의 데이터 저장 영역을 사용하는데 스택 영역만 분할해서 사용하는 구조이다. 

멀티 프로세스는 하나의 프로세스가 다른 프로세스의 데이터 저장 영역을 절대 침범할 수 없는 메모리 구조이지만 메모리 사용량은 크다. 멀티 쓰레드는 하나의 데이터 저장 영역 안에서 스택 영역만 분할한 것이기에 하나의 쓰레드에서 다른 쓰레드의 스택 영역에는 접근할 수 없지만 스태틱 영역(메소드 영역)과 힙 영역은 공유해서 사용하는 구조다. 따라서 멀티 프로세스 대비 메모리를 적게 사용할 수 있는 구조다. 웹 프로그래밍 시 요청당 스레드(Servlet)가 요청당 프로세스(CGI)보다 더 효율적인 이유다.
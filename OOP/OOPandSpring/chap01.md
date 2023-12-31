# 01. 사람을 사랑한 기술
## 기계어에서 객체 지향 프로그래밍 언어로

| | 기계어 | 어셈블리어 | C언어 | 자바 |
| --------- | ------- | ------- | ------- | --------- |
| 개발자의 코딩 | 0과 1의 나열 | 일상 단어 사용 | 수학적 기호 사용 | 수학적 기호 사용 |
| 소스 파일 | 기종마다 하나씩 | 기종마다 하나씩 | 기종이 몇 개든 단 하나 | 기종이 몇 개든 단 하나 |
| 목적 파일(기계어) | 소스 그 자체 | 어셈블러로 소스를 번역해 생성 | 컴파일러로 소스를 번역해 생성 | 기종이 몇 개든 단 하나의 JVM용 기계어 생성 |
| 기계어 비교 | | 기계어와 1 : 1 대응하는 니모닉 | 기계어와 m : n 대응하는 수학적 기호 | 기계어와 m : n 대응하는 수학적 기호 |
| 비고 | | 기종별 어셈블러 필요 | 기종별 컴파일러 필요 |단 하나의 컴파일러만 필요 기종별 JRE 세팅 필요(한 번만 설치해주면 됨) |

### C언어 - One Source Multi Object Use Anywhere
![image](https://github.com/kmularise/TIL/assets/106499310/d927dfc8-b98d-45bf-97dd-8f8f59a5d05e)
* One Source : 하나의 소스 파일만 작성
* Multi Object : 기종마다 하나씩 기계어 목적 파일 생성
* Use Anywhere : 모든 컴퓨터에서 실행 가능
* 하나의 소스로 이기종 간에 이식성 확보

하나의 소스로 모든 컴퓨터에서 실행 가능하다. 하나의 소스를 가지고 기종별로 컴파일만 하면 해당 기종별로 목적 파일, 즉 기계어 코드가 만들어지는 것이다.

### 자바 - 진정한 객체 지향 언어, Write Once Use Anywhere
![image](https://github.com/kmularise/TIL/assets/106499310/69cb6216-d8b3-4407-97ad-d2ca3f52dd0b)
* 한 펀의 컴파일로 이기종 간 이식성 확보

## 스프링 프레임워크
* OOP 프레임 워크
* 스프링 프레임워크의 근원적 요소
    1. IoC/DI
    2. AOP
    3. PSA

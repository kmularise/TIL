# 07. 스프링 삼각형과 설정 정보

## POJO(Plain Old Java Object) : 스프링 삼각형
1. IoC/DI
2. AOP
3. PSA

## IoC/DI - 제어의 역전/의존성 주입
* IoC : Inversion of Control, 제어의 역전
* DI : Dependency Injection, 의존성 주입

### 스프링을 적용하지 안흔 기존 방식 - 의존 관계 직접 해결
![image](https://github.com/kmularise/TIL/assets/106499310/0e83994a-dbdf-49d4-9615-bcc6e508ae86)
```shell
의사코드

운전자가 자동차를 생산한다. 
자동차는 내부적으로 타이어를 생산한다. # 의존 관게가 일어나고 있는 부분, 의존성을 자체적으로 해결
```
* 자동차는 타이어에 의존한다.
* 운전자는 자동차에 의존한다고 봐도 된다.
* 자동차의 생성자 코드에서 tire 속성에 새로운 타이어를 생성해서 참조할 수 있게 해주었다.

### 스프링 없이 의존성 주입하기 1 - 생성자를 통한 의존성 주입
* 주입이란? 
    외부에서 의존 관계 설정
* 현실 세계의 표준 규격 준수 = 프로그래밍 세계의 인터페이스 구현
[예시]
![image](https://github.com/kmularise/TIL/assets/106499310/d0071918-a72f-49c0-b6ee-71e0f4dd7d0a)
```shell
의사코드

운전자가 타이어를 생산한다.
운전자가 자동차를 생산하면서 타이어를 장착한다.

자바로 표현 - 생성자 인자 이용
Tire tire = new KoreaTire();
Car car = new Car(tire); # 외부에서 생산된 타이어를 자동차에 장착하는 작업
```
* 의존성 주입을 적용할 경우 Car은 그저 Tire 인터페이스를 구현한 어떤 객체가 들어오기만 하면 정상적으로 작동하게 된다.
* 의존성 주입을 하면 확장성이 좋아진다.
    * 새로운 타이어 브랜드가 생겨도 각 타이어 브랜드들이 Tire 인터페이스만 구현한다면 Car.java 코드를 변경할 필요 없이 사용할 수 있고 컴파일할 필요도 없다.
    * 새로운 타이어 브랜드가 생겨도 각 타이어 브랜드 소스 코드 파일만 컴파일해서 배포하면 된다.
* 전략 패턴 응용
    * 전략 : Tire를 구현한 KoreaTire, AmericaTire
    * 컨텍스트 : Car의 getTireBrand() 메소드
    * 클라이언트 : Driver의 main 메소드

### 스프링 없이 의존성 주입하기 2 - 속성을 통한 의존성 주입 
[예시]
![image](https://github.com/kmularise/TIL/assets/106499310/c9e0f9e8-cba3-4a05-ba6f-953da636a9cd)

```shell
의사코드

운전자가 타이어를 생산한다.
운전자가 자동차를 생산한다.
운전자가 자동차에 타이어를 장착한다.

자바로 표현 - 속성 접근자 메소드 사용
Tire tire = new KoreaTire();
Car car = new Car();
car.setTire(tire);
```

### 스프링을 통한 의존성 주입 - XML 파일 사용, 속성  주입
* 자동차의 타이어 브랜드를 변경할 때 그 무엇도 재컴파일/재배포하지 않아도 XML 파일만 수정하면 프로그램의 실행 결과를 바꿀 수 있다.

### 스프링을 통한 의존성 주입 - @Autowired을 통한 의존성 주입

![image](https://github.com/kmularise/TIL/assets/106499310/977bd101-f919-4a8d-9e2d-0766c7ce47bf)

### 스프링을 통한 의존성 주입 - @Resource를 통한 속성 주입
| | @Autowired | @Resource |
| ----------- | ------ | ------ |
| 출처 | 스프링 프레임워크 | 표준 자바 |
| 소속 패키지 | org.springframework.beans.factory.annotation.Autowired | javax.annotation.Resource |
| 빈 검색 방식 | byType 먼저, 못찾으면 byName | byName 먼저, 못찾으면 byType |
| 특이사항 | @Qualifier(") 협업 | name 어트리뷰트 |
| byName 강제하기 | @Autowired<br>@Qualifier("tire1") | @Resource(name="tire1") |

### 의존 관계
* 변수에 값을 할당하는 모든 곳에 의존 관계가 생긴다. 즉, 대입 연산자(=)에 의해 변수에 값이 할당되는 순간 의존이 생긴다. 
* 변수가 지역 변수이건 속성이건, 할당되는 값이 리터럴이건 객체이건 의존은 발생한다.
* 의존 대상이 내부에 있을 수도 있고, 외부에 있을 수도 있다.
* DI는 외부에 있는 의존 대상을 주입하는 것을 말한다.
* 의존 대상을 구현하고 배치할 때 SOLID와 응집도는 높이고 결합도는 낮추라는 기본 원칙에 충실해야 한다.

## 2. AOP : Aspect-Oriented Programming
* AOP : 로직(code) 주입, 횡단 관심사(다수의 모듈에 공통적으로 나타나는 부분) 처리
* 코드 = 핵심 관심사 + 횡단 관심사 
    * 핵심 관심사 : 모듈 별로 다르다.
    * 횡단 관심사 : 모듈별로 반복되어 중복해서 나타나는 부분
![image](https://github.com/kmularise/TIL/assets/106499310/cc6c9731-d42b-4597-853d-36a3ccb87d81)
* 메소드에 로직(코드)을 주입할 수 있는 곳들
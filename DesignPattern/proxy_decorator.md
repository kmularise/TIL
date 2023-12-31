# 프록시 패턴

> 제어 흐름을 조정하기 위한 목적으로 중간에 대리자를 두는 패턴
* 프록시는 대리자, 대변인이라는 뜻을 가진 단어다. 대리자/대변인이라고 하면 다른 누군가를 대신해 그 역할을 수행하는 존재를 말한다.
* 프록시 패턴이 실제 서비스 메소드 반환값에 가감하지 않는다.
* 프록시 패턴은 제어의 흐름을 변경하거나 다른 로직을 수행하기 위해 사용한다.
* 개방 폐쇄 원칙(OCP)와 의존 역전 원칙(DIP) 적용

## 사용 예시
1. 보안(Security) : 프록시는 클라이언트가 작업을 수행할 수 있는 권한이 있는지 확인하고 검사 결과가 긍정적인 경우에만 요청을 대상으로 전달한다.
2. 캐싱(Caching) : 프록시가 내부 캐시를 유지하여 데이터가 캐시에 아직 존재하지 않는 경우에만 대상에서 작업이 실행되도록 한다.
3. 데이터 유효성 검사(Data validation) : 프록시가 입력을 대상으로 전달하기 전에 유효성을 검사한다.
4. 지연 초기화(Lazy initialization) : 대상의 생성 비용이 비싸다면 프록시는 그것을 필요로 할때까지 연기할 수 있다.
5. 로깅(Logging) : 프록시는 메소드 호출과 상대 매개 변수를 인터셉트하고 이를 기록한다.
6. 원격 객체(Remote objects) : 프록시는 원격 위치에 있는 객체를 가져와서 로컬처럼 보이게 할 수 있다.

# 데코레이터 패턴
* 프록시 패턴에서는 Wrapper Class와 Real Class의 관계가 컴파일타임에 정해진다.




## 프록시 패턴과 데코레이터 패턴 
### 공통점
1. 인터페이스를 구현
2. 조합을 이용한다.
### 차이점
1. Real Class와 Wrapper Class의 관계가 정해지는 시점
    * 프록시 패턴 : 실제 클래스(Real Class)와 실제 클래스를 감싸는 클래스(Wrapper Class)의 관계가 컴파일타임에 정해진다. 
    * 데코레이터 패턴 : 실제 클래스(Real Class)와 실제 클래스를 감싸는 클래스(Wrapper Class)의 관계가 런타임에 정해진다.
2. 목적
    * 프록시 패턴 : 실제 클래스(Real Class)의 접근에 대한 제어를 목적으로 한다.
    * 데코레이터 패턴 : 실제 클래스(Readl Class)의 기능에 다른 기능을 추가하는 목적으로 한다.
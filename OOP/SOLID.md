# 05. 객체 지향 설계 5원칙
## SOLID 다섯 가지 기본 원칙
1. SRP(Single Responsibility Principle) : 단일 책임 원칙
2. OCP(Open Closed Principle) : 개방 폐쇄 원칙
3. LSP(Liskov Substitution Principle) : 리스코프 치환 원칙
4. ISP(Interface Segregation Principle): 인터페이스 분리 원칙
5. DIP(Dependency Inversion Principle): 의존 역전 원칙

### SRP - 단일 책임 원칙
* 객체는 단 하나의 책임만 가져야 한다.
* 어떤 변경이 필요할 때 수정 대상이 명확해진다.

### OCP - 개방 폐쇄 원칙
* 클래스나 모듈의 확장에는 열려 있어야 하고 변경에는 닫혀 있어야 한다.
* 높은 응집도와 낮은 결합도가 특징이다.
[예시]

![image](https://github.com/kmularise/TIL/assets/106499310/54b65dc6-0e15-4958-b78d-0d5a67c11841)
* 확장에 열려 있다.
    * DB Connection의 새로운 기능을 추가한다면 외부 클라이언트에서 ConnectionMaker의 구현체(오브젝트)를 새로 만들고, UserDao의 생성자 파라미터에 넣어 의존관계를 설정하면 된다.
    * 이때, UserDao에 영향을 주지 않고도 기능을 확장할 수 있기에 확장에 열려있다고 하는 것이다.
* 변경에 닫혀 있다.
    * 기존 ConnectionMaker의 구현체를 변경해야 하는 상황을 가정하자. UserDao는 인터페이스를 사용하고 있기에, 구현체가 행동(메소드)를 어떻게 구현하고 있는지는 모른다. 즉, UserDao는 기존 구현체의 변경에 영향을 받지 않는다.
    따라서 UserDao는 관심사를 두지 않는 기능에 변경이 일어났을 때, UserDao는 자신의 변화가 불필요하게 일어나지 않으므로 변경에 닫혀 있다고 볼 수 있다.
# 06. 스프링이 사랑한 디자인 패턴

## 디자인 패턴
* 디자인 패턴은 소프트웨어 설계 시 특정 상황에서 자주 만나는 문제를 해결하기 위해 사용할 수 있는 재사용 가능한 솔루션
* 확장성 추구 방법
  * 클래스 상속
  * 오브젝트 합성(composition)
* 디자인 패턴의 핵심 의도, 패턴을 적용할 상황, 해결해야할 문제, 솔루션의 구조와 각 요소의 역할 기억해두기
![image](https://github.com/kmularise/TIL/assets/106499310/49111e57-d283-4c54-b282-baa6078c6dfe)
__________________________________
## 어댑터 패턴(Adapter Pattern)
> 호출 당하는 쪽의 메소드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 패턴
* 어댑터 패턴은 합성, 즉 객체를 속성으로 만들어서 참조하는 디자인 패턴이다. 
* 어댑터는 변환기라고 할 수 있다. 변환기의 역할은 서로 다른 두 인터페이스 사이에 통신이 가능하게 하는 것이다.
* 개방 폐쇄 원칙(OCP) 적용
* 실례
    * ODBC/JDBC 어댑터 패턴을 이용해 다양한 데이터베이스 시스템을 단일한 인터페이스로 조작할 수 있게 해준다.
    * JRE 또한 어댑터 패턴이라고 할 수 있다.

### 예시
![image](https://github.com/kmularise/TIL/assets/106499310/785a63cb-4674-49c5-8b9c-89c15acedf67)

다음은 오리를 표현한 것이다.
```java
interface Duck {
  public void quack();  // 오리는 꽉꽉 소리를 낸다
  public void fly();
}

class MallardDuck implements Duck {
  @Override
  public void quack() {
    System.out.println("Quack");
  }
  @Override
  public void fly() {
    System.out.println("I'm flying");
  }
}
```
다음은 칠면조를 표현한 것이다. 두 새의 인터페이스는 다르다.
```java
interface Turkey {
  public void gobble();   // 칠면조는 골골거리는 소리를 낸다
  public void fly();
}

class WildTurkey implements Turkey {
  @Override
  public void gobble() {
    System.out.println("Gobble gobble");
  }
  @Override
  public void fly() {
    System.out.println("I'm flying a short distance");
  }
}
```
다음은 어댑터이다.
```java
class TurkeyAdapter implements Duck {
  Turkey turkey;

  public TurkeyAdapter(Turkey turkey) {
    this.turkey = turkey;
  }
  @Override
  public void quack() {
    turkey.gobble();
  }
  @Override
  public void fly() {
    // 칠면조는 멀리 날지 못하므로 다섯 번 날아서 오리처럼 긴 거리를 날게 한다
    for (int i = 0; i < 5; i++) {
      turkey.fly();
    }
  }
}
```
________________________________
## 프록시 패턴(Proxy Pattern)
> 제어 흐름을 조정하기 위한 목적으로 중간에 대리자를 두는 패턴
* 프록시는 대리자, 대변인이라는 뜻을 가진 단어다. 대리자/대변인이라고 하면 다른 누군가를 대신해 그 역할을 수행하는 존재를 말한다.
* 프록시 패턴이 실제 서비스 메소드 반환값에 가감하지 않는다.
* 프록시 패턴은 제어의 흐름을 변경하거나 다른 로직을 수행하기 위해 사용한다.
* 개방 폐쇄 원칙(OCP)와 의존 역전 원칙(DIP) 적용
### 중요 포인트
* 대리자는 실제 서비스와 동일한 이름의 메소드를 구현한다. 이때 인터페이스를 사용한다.
* 대리자는 실제 서비스에 대한 참조 변수를 갖는다.(합성)
* 대리자는 실제 서비스의 같은 이름을 가진 메소드를 호출하고 그 값을 클라이언트에게 돌려준다.
* 대리자는 실제 서비스의 메소드 호출 전후에 별도의 로직을 수행할 수도 있다.

### 예시
![image](https://github.com/kmularise/TIL/assets/106499310/ba8b178c-90de-4baa-8dd3-66991cf7fe15)
![image](https://github.com/kmularise/TIL/assets/106499310/1a49e485-f348-43e7-8e64-f9daba45ebf4)
```java
public interface IService {
	String runSomething();
}
```
```java
public class Service implements IService {
	@Override
	public String runSomething() {
		return "서비스 짱!!!";
	}
}
```
```java
public class Proxy implements IService {
	IService service1;

	@Override
	public String runSomething() {
		System.out.println("호출에 대한 흐름 제어가 주목적, 반환 결과를 그대로 전달");
		service1 = new Service();
		return service1.runSomething();
	}
}
```
```java
public class Client {  	
    public static void main(String[] args) { 		
	//직접 호출하지 않고 프록시를 호출한다. 		
	IService proxy = new Proxy(); 		
	System.out.println(proxy.runSomething()); 	
	}
}
```
프록시 패턴의 경우 실제 서비스 객체가 가진 메소드와 동일한 이름의 메소드를 사용하는데, 이를 위해 인터페이스를 사용한다. 인터페이스를 사용하면 서비스 객체가 들어갈 자리에 대리자 객체를 대신 투입해 클라이언트 쪽에서는 실제 서비스 객체를 통해 메소드를 호출하고 반환값을 받는지, 대리자 객체를 통해 메소드를 호출하고 반환값을 받는지 전혀 모르게 처리할 수 있다.

________________________________

## 데코레이터 패턴
> 메소드 호출의 반환값에 변화를 주기 위해 중간에 장식자를 두는 패턴
* 데코레이터는 도장/도배업자를 의미한다. 데코레이터 패턴은 원본에 장식을 더하는 패턴이다.
* 데코레이터 패턴은 프록시 패턴과 구현 방법은 같다.
    * 다만 프록시 패턴은 클라이언트가 최종적으로 돌려 받는 반환값을 조작하지 않고 그대로 전달하는 반면 데코레이터 패턴은 클라이언트가 받는 반환값에 장식을 덧입힌다.

    | | |
    | ---- | ---- |
    | 프록시 패턴 | 제어의 흐름을 변경하거나 별도의 로직 처리를 목적으로 한다.<br>클라이언트가 받는 반환값을 특별한 경우가 아니면 변경하지 않는다.|
    | 데코레이터 패턴 | 클라이언트가 받는 반환값에 장식을 더한다. |
* 개방 폐쇄 원칙(OCP)와 의존 역전 원칙(DIP) 적용
* [상속의 해결책 조합과 데코레이터 패턴 참고](../../Java/effective_Java/item18_composition.md)
### 중요 포인트
* 장식자는 실제 서비스와 같은 이름의 메소드를 구현한다. 이때 인터페이스를 사용한다.
* 장식자는 실제 서비스에 대한 참조 변수를 갖는다. (합성)
* 장식자는 실제 서비스의 동일한 이름을 가진 메소드를 호출하고, 그 반환값에 장식을 더해 클라이언트에게 돌려준다.
* 장식자는 실제 서비스의 메소드 호출 전후에 별도의 로직을 수행할 수도 있다.

### 예시
* 프록시 패턴과 다른 부분
```java
public class Decorator implements IService {
	IService service1;

	@Override
	public String runSomething() {
		System.out.println("호출에 대한 장식 주목적, 클라이언트에게 반환 결과에 장식을 더하여 전달");
		service1 = new Service();
		return "정말" + service1.runSomething();
	}
}
```
__________________________
## 싱글턴 패턴(Singleton Pattern)
> 클래스의 인스턴스, 즉 객체를 하나만 만들어 사용하는 패턴
* 싱글턴 패턴은 오직 인스턴스를 하나만 만들고 그것을 계속해서 재사용한다.
    * 커넥션 풀, 스레드 풀, 디바이스 설정 객체 등과 같은 경우 인스턴스를 여러개 만들게 되면 불필요한 자원을 사용하게 되고, 프로그램이 예상치 못한 결과를 낳을 수 있다.
* 싱글턴 패턴을 구현하려면 객체 생성을 위한 new에 제약을 걸어야 하고, 만들어진 단일 객체를 반환할 수 있는 메소드가 필요하다.
    * new를 실행할 수 없도록 생성자에 private 접근 제어자를 지정한다.
    * 유일한 단일 객체를 반환할 수 있는 정적 메소드가 필요하다.
    * 유일한 단일 객체를 참조할 정적 참조 변수가 필요하다.

### 중요 포인트
* private 생성자를 갖는다.
* 단일 객체 참조 변수를 정적 속성으로 갖는다.
* 단일 객체 참조 변수가 참조하는 단일 객체를 반환하는 getInstance() 정적 메소드를 갖는다.
* 단일 객체는 쓰기 가능한 속성을 갖지 않는 것이 정석이다.

### 예시
```java
public class Singleton {
    static Singleton singletonObject; //정적 참조 변수 - 단일 객체 저장
    private Singleton() {}; //private 생성자 - new를 통해 객체를 생성할 수 없도록 지정
    
    //객체 반환 정적 메서드
    public static Singleton getInstance(){
        if(singletonObject == null){
            singletonObject = new Singleton();
        }
        return singletonObject;
    }
}
```

[참조 변수들이 하나의 단일 객체를 참조]

![image](https://github.com/kmularise/TIL/assets/106499310/bdcff899-8016-41df-9e30-7610364e0ab6)

단일 객체인 경우 공유 객체로 사용되기 때문에 속성을 갖지 않게 하는 것이 정석이다. 단일 객체가 속성을 갖게 되면 하나의 참조 변수가 변경한 단일 객체의 속성이 다른 참조 변수에 영향을 미칙 때문이다. 이는 전역/공유 변수를 가능한 한 사용하지 말라는 지침과 일맥상통한다.

다만 읽기 전용 속성을 갖는 것은 문제가 되지 않는다. 이와 더불어 단일 객체가 다른 단일 객체에 대한 참조를 속성으로 가진 것 또한 문제가 되지 않는다. 이는 스프링의 싱글턴 빈이 가져야 할 제약조건이기도 하다.

_______________________________
## 템플릿 메소드 패턴(Template Method Pattern)
> 상위 클래스의 견본 메소드에서 하위 클래스가 오버라이딩한 메소드를 호출하는 패턴
* 상속을 통해 상위클래스의 기능을 확장할 때 사용
* 변하지 않는 기능은 상위클래스에 만들어두고 자주 변경되며 확장할 기능은 하위클래스에서 만들도록 한다.
* 상위 클래스에 공통 로직을 수행하는 템플릿 메소드와 하위 클래스에 오버라이딩을 강제하는 추상 메소드 또는 선택적으로 오버라이딩할 수 있는 훅(Hook) 메소드를 두는 패턴
* 상속을 통해 동일한 부분(중복)은 상위 클래스로, 달라지는 부분만 하위클래스로 분할
* 의존 역전 원칙 (DIP) 적용

| 템플릿 메소드 구성요소 | 설명 |
| --- | --- |
| 템플릿 메소드 | 공통 로직을 수행, 로직 중에 하위 클래스에서 오버라이딩한 추상 메소드/훅 메소드 호출
| 템플릿 메소드에서 호출하는 추상 메소드 | 하위 클래스 오버라이딩 필수 |
| 템플릿 메소드에서 호출하는 훅(Hook, 갈고리) 메소드 | 하위 클래스 오버라이딩 선택 |

### 예시
![image](https://github.com/kmularise/TIL/assets/106499310/a7fc9cac-50f6-4df3-93d7-577697373a0a)

________________________________
## 팩토리 메소드 패턴(Factory Method Pattern)
> 오버라이드된 메소드가 객체를 반환하는 패턴
* 팩토리는 공장을 의미. 공장은 물건을 생산하는데 객체지향에서 팩토리는 객체를 생성한다.
* 팩토리 메소드 : 객체를 생성 반환하는 메소드, 하위 클래스에서 오브젝트 생성 방법과 클래스를 결정할 수 있도록 미리 정의해둔 메소드
* 팩토리 메소드 패턴 : 하위 클래스에서 팩토리 메소드를 오버라이딩해서 객체를 반환하게 하는 것
* 상위클래스 코드에서는 하위클래스에서 구현할 메소드를 호출해서 필요한 타입의 오브젝트를 가져와 사용한다.
  * 이 메소드는 주로 인터페이스 타입으로 오브젝트를 리턴하므로 하위클래스에서 정확히 어떤 클래스의 오브젝트를 만들어 리턴할지는 상위클래스에서 알지 못한다.
  * 하위클래스는 다양한 방법으로 오브젝트를 생성하는 메소드를 재정의할 수 있다.
* 의존 역전 원칙(DIP) 적용

### 예시
![image](https://github.com/kmularise/TIL/assets/106499310/495efda8-d621-4b14-942a-9937c8ec811e)

________________________________
## 전략 패턴(Strategy Pattern)
> 클라이언트가 전략을 생성해 전략을 실행할 컨텍스트에 주입하는 패턴
* 개방 폐쇄 원칙(OCP)과 의존 역전 원칙(DIP) 적용
* 컨텍스트에서 필요에 따라 변경이 필요한 독립적인 책임으로 분리 가능한 기능을 인터페이스를 통해 통째로 외부로 분리시키고, 이를 구체적인 구현한 기능 클래스를 필요에 따라 바꿔서 사용할 수 있게 한다.                                                                                                                                                                                                                                                                                                                                                                                        

### 전략 패턴을 구성하는 세 요소
* 전략 패턴을 가진 전략 객체
* 전략 객체를 사용하는 컨텍스트(전략 객체의 사용자/소비자)
* 전략 객체를 생성해 컨텍스트에 주입하는 클라이언트(제3자, 전략 객체의 공급자)
![image](https://github.com/kmularise/TIL/assets/106499310/90bca965-6594-4537-8838-7fb78215cc2e)

클라이언트는 다양한 전략 중 하나를 선택해 생성한 후 컨텍스트에 주입한다.

### 예시
![image](https://github.com/kmularise/TIL/assets/106499310/27c984ef-5d90-4042-97dd-cb7ede553932)

________________________________
## 템플릿 콜백 패턴(Template Callback Pattern - 견본/회신 패턴)
> 전략을 익명 내부 클래스로 구현한 전략 패턴
* 전략 패턴의 변형
* 스프링 모델 중 하나인 DI(의존성 주입)에서 사용하는 형태의 전략 패턴
* 전략을 익명 내부 클래스로 정의해서 사용
* 개방 폐쇄 원칙(OCP)과 의존 역전 원칙(DIP) 적용

### 예시
전략을 생성하는 코드를 컨텍스트 내부에서 처리하는 경우
```java
public class Soldier {
    void runContext(final String weaponSound){
        System.out.println("전투 시작");
        executeWeapon(weaponSound);
        System.out.println("전투 종료");
    }
    
    private Strategy executeWeapon(final String weaponSound){
        return new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println(weaponSound);
            }
        };
    }
}
```
__________________
## 그 밖의 디자인 패턴들
* 프론트 컨트롤러 패턴(Front Controller Pattern, 최전선 제어자 패턴)
* MVC 패턴(Model - View - Controller)
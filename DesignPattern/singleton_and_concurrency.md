# 싱글톤 패턴과 동시성 문제
## Lazy Initialization
```java
class Singleton {
    private static Singleton myInstance = null;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (myInstance == null) {
            myInstance = new Singleton();
        }

        return myInstance;
    }
}
```
* 멀티 스레드에서 동시성 문제가 발생한다.

## 동시성 해결방법
### 1. synchronized 키워드 추가
```java
class Singleton {
    private static Singleton myInstance = null;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (myInstance == null) {
            myInstance = new Singleton();
        }

        return myInstance;
    }
}
```
* 최초로 접근한 스레드가 메소드 호출을 종료할 때까지 다른 스레드가 접근하지 못하도록 락을 걸어준다.
* 싱글톤 메소드를 호출할 때마다 락이 걸려 성능 저하 발생

### 2. DCL(Double Checked Locking)
```java
class Singleton {
    private static Singleton myInstance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (myInstance == null) {
            synchronized (Singleton.class) {
                if (myInstance == null) {
                    myInstance = new Singleton();
                }
            }
        }

        return myInstance;
    }
}
```
* myInstance가 volatile 변수가 아니기 때문에 낮을 확률로 최초로 synchronized 블록을 진입한 스레드가 아닌 다른 스레드가 synchronized 블록에 진입하여 객체 생성이 두번 일어날 수 있다.
* DCL 방식에서 myInstance를 volatile 변수로 하면 위와 같은 visibility 문제를 해결할 수 있다.
* 가독성이 떨어진다.

### 3. static 초기화
```java
class Singleton {
    private static Singleton myInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return myInstance;
    }
}
```
* 클래스 로딩 시점에 미리 인스턴스를 생성하는 방식
* 나중에 사용되거나 사용되지도 않을 객체를 클래스 로딩 시점에 만들어두기에 불필요하게 메모리 자원을 선점할 수 있다.

### Lazy Holder 방식
```java
class Singleton {
    private Singleton() {}

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
}
```
* LazyHolder 라는 정적 중첩 클래스(static nested class)를 선언해서 사용하는 방식
* Singleton 클래스가 최초 클래스 로딩 단계에서 로드가 되더라도 LazyHolder 클래스에 대한 변수를 가지고 있지 않아 초기화가 되지 않는다.
* getInstance()가 호출될 때 LazyHolder 클래스가 로딩되며 인스턴스를 생성하게 된다.
* 클래스를 로드하고 초기화하는 단계에서는 thread safety가 보장되기에 별도의 synchronized, volatile 키워드 없이도 동시성 문제를 해결할 수 있다.

<!-- enum도 있다. - singleton -->


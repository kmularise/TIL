# Spring이 제공하는 Transaction 핵심 기술
1. 트랜잭션 동기화
    * 트랜잭션을 시작하기 위한 Connection 객체를 특별한 저장소에 보관해두고 필요할 때 꺼내쓸 수 있도록 하는 기술

2. 트랜잭션 추상화
    * 트랜잭션 기술의 공통점을 담은 트랜잭션 추상화 기술을 제공하고 있다.
    * 애플리케이션에 각 기술마다(JDBC, JPA, Hibernate 등) 종속적인 코드를 이용하지 않고도 일관되게 트랜잭션을 처리할 수 있도록 해주고 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/7eea670a-7b79-4b3f-9485-ee842a62c008)

3. AOP를 이용한 트랜잭션 로직 분리
    * 부가 기능에 해당하는 트랜잭션 코드 분리
    * 해당 로직을 클래스 밖으로 빼내서 별도의 모듈로 만드는 AOP(Aspect Oriented Programming, 관점 지향 프로그래밍)를 적용
    * 트랜잭션 어노테이션(@Transactional)을 지원

## 1. 트랜잭션 동기화

### 기존 코드

UserDao와 MessageDao는 내부에서 Connection 을 생성하지 않고 파라미터로 주입받아 사용한다. 트랜잭션을 사용하기 위해서는 트랜잭션을 구성하는 여러개의 쿼리가 동일한 커넥션에서 실행되어야 하기 때문이다.

```java
public class UserDao {

    public void saveUser(final Connection connection, final String name) {
        try {
            String sql = "INSERT INTO user(name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
```

```java
public class MessageDao {

    public void saveMessage(final Connection connection, final String message) {
        try {
            String sql = "INSERT INTO message(content) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message);
            preparedStatement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
```

* 서비스 레이어의 관심사는 비즈니스 로직이다.
* 하지만, 트랜잭션을 사용하기 위해서는 UserDao 와 MessageDao 에서 실행되는 쿼리가 동일한 커넥션을 사용해야 한다. 
* UserService에도 커넥션을 생성하고 있다. 
* DBC를 사용하면 따라오게 되는 try/catch/finally 구문도 생겼다.
* 서비스 계층에서 커넥션을 생성하고, 트랜잭션 경계를 설정하는 코드가 비즈니스 로직과 함께 있다. DAO 에서는 데이터 액세스 기술이 Service 레이어에 종속된다.
```java
public class UserService {

    // ...

    public void register(final String name) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);

            userDao.saveUser(connection, name);
            messageDao.saveMessage(connection, name + "님 가입을 환영합니다.");

            connection.commit();
        } catch (final SQLException e) {
            try {
                connection.rollback();
            } catch (final SQLException ignored) {
            }
        } finally {
            try {
                connection.close();
            } catch (final SQLException ignored) {
            }
        }
    }
}
```

### 트랜잭션 동기화를 이용한 해결 방법
* 트랜잭션 동기화를 통해  트랜잭션을 시작하기 위해 생성한 Connection 객체를 별도의 특별한 공간에 보관하고, 이 커넥션이 필요한 곳(DAO) 에서 커넥션을 꺼내 사용하도록 하였다.

```java
@Service
public class UserService {

    private final DataSource dataSource;
    private final UserDao userDao;
    private final MessageDao messageDao;

    public UserService(final DataSource dataSource, final UserDao userDao, final MessageDao messageDao) {
        this.dataSource = dataSource;
        this.userDao = userDao;
        this.messageDao = messageDao;
    }

    public void register(final String name) {
        TransactionSynchronizationManager.initSynchronization();
        // 트랜잭션 동기화 초기화

        Connection connection = DataSourceUtils.getConnection(dataSource);
        // 커넥션 획득

        try {
            connection.setAutoCommit(false);

            userDao.saveUser(name);
            messageDao.saveMessage(name + "님 가입을 환영합니다.");

            connection.commit();
        } catch (final SQLException e) {
            try {
                connection.rollback();
            } catch (final SQLException ignored) {
            }
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            // 커넥션 자원 해제
        }
    }
}
```

```java
@Repository
public class UserDao {

    private final DataSource dataSource;

    public UserDao(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveUser(final String name) {
        Connection connection = DataSourceUtils.getConnection(dataSource);

        try {
            String sql = "INSERT INTO user(name) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
```

개발자가 JDBC가 아닌 Hibernate와 같은 기술을 쓴다면 위의 JDBC 종속적인 트랜잭션 동기화 코드들은 문제를 유발하게 된다. 대표적으로 Hibernate에서는 Connection이 아닌 Session이라는 객체를 사용하기 때문이다. 이러한 기술 종속적인 문제를 해결하기 위해 Spring은 트랜잭션 관리 부분을 추상화한 기술을 제공하고 있다.

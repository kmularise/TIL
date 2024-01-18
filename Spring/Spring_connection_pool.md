# 커넥션 풀

<br>
<br>

![image](https://github.com/kmularise/TIL/assets/106499310/d805401e-32df-43ac-bf4e-5c76b1731e26)

## 데이터베이스 커넥션 획득 과정
1. 애플리케이션 로직은 DB 드라이버를 통해 커넥션을 조회한다.
2. DB 드라이버는 DB와 `TCP/IP` 커넥션을 연결한다. 이 과정에서 TCP 연결 수립을 위한 3 way handshake가 발생한다.
3. DB 드라이버는 `TCP/IP` 커넥션이 연결되면 ID, PW와 기타 부가정보를 DB에 전달한다.
4. DB는 ID, PW를 통해 내부 인증을 완료하고, 내부에 DB 세션을 생성한다.
5. DB는 커넥션 생성이 완료되었다는 응답을 보낸다.
6. DB 드라이버는 커넥션 객체를 생성해서 클아이언트에 반환한다.

<br>
<br>
<br>

### 문제점 
* 커넥션을 매번 새로 만드는 것은 과정도 복잡하고 시간이 많이 소모된다.
* DB 뿐만 아니라 애플리케이션 서버에서도 `TCP/IP` 커넥션을 새로 생성하기 위해 리소르를 사용해야 한다.
* 사용자가 애플리케이션을 사용할 때 SQL을 실행하는 시간 뿐만 아니라 커넥션을 새로 만드는 시간이 추가되어 응답속도가 느려진다.

<br>
<br>
<br>

## 해결방법 : 커넥션 풀
* 커넥션 풀이란?
    * 커넥션을 관리하는 풀
    * 커넥션을 미리 생성해두고 사용한다.
* 애플리케이션을 시작하는 시점에 커넥션 풀에서 커넥션을 미리 확보해서 풀에 보관한다.
* 커넥션 풀에 들어 있는 커넥션은 `TCP/IP`로 DB와 연결되어 있는 상태이기 때문에, 언제든지 SQL을 DB에 전달할 수 있다.
* 애플리케이션 로직에서 DB 드라이버를 통해서 새로운 커넥션을 획득하는 것이 아니라 커넥션 풀을 통해 이미 생성되어 있는 커넥션을 객체 참조로 가져다 쓰면 된다.
* 애플리케이션 로직은 커넥션 풀에서 받은 커넥션을 사용해서 SQL을 데이터베이스에 전달하고 그 결과를 받아서 처리한다.
* 커넥션을 모두 사용하고 나면 커넥션을 종료하는 것이 아니라 커넥션이 살아있는 상태로 커넥션 풀에 반환해야 한다.

<br>
<br>
<br>

### 커넥션 풀 장점
* 서버 당 최대 커넥션 수를 제한할 수 있다. DB에 무한정 연결이 생성되는 것을 막아주어서 DB를 보호하는 효과가 있다.

<br>
<br>
<br>

### 자주 사용되는 오픈소스 커넥션 풀
* 대표적인 커넥션 풀 오픈소스로 HikariCP가 있다.

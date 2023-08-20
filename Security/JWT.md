# 토큰 기반 인증 : JWT
* 클라이언트가 인증 정보를 저장하는 방식
    * 세션 불일치 문제로부터 자유로움
* 인증 정보가 토큰의 형태로 브라우저 로컬 스토리지 또는 쿠키에 저장된다.
* JWT의 경우 디지털 서명이 존재해 토큰의 내용이 위변조되었는지 서버 측에서 확인할 수 있다.

## JWT란?
* Json Web Token
* RFC 7519 에 명세되어 있는 국제 표준으로써, 통신 양자간의 정보를 JSON 형식을 사용하여 안전하게 전송하기 위한 방법
* 정보가 토큰 자체에 포함된 (Self-Contained) 클레임 (Claim) 기반 토큰
* 일반적으로 인증(Authentication)과 권한 부여(Authorization)에 사용
    1. 인증절차를 거쳐 서버에서 JWT 발급
    2. 클라이언트는 이를 잘 보관하고 있다가 요청을 보낼 때 서버에 JWT를 함께 제출하여 서버로부터 행위에 대해 권한을 부여 받을 수 있다.

## JWT 구조
* JWT는 헤더(Header),  페이로드 (Payload), 서명 (Signature) 세 부분으로 구성되어 있다.
* 각 구성요소는 점(.)으로 분리된다. 즉, JWT는 헤더.페이로드.서명의 형태를 갖는다.
* 가각의 구성요소는 JSON 형태로 표현된다. 다만, JSON의 경우 개행을  
[예시]
<img width="1211" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/9eb097b2-a4ac-4390-a968-86c6b3948a72">

### 1. 헤더(Header)
* 일반적으로 암호화 알고리즘 토큰의 유형 두가지 정보를 JSON 형태로 담고 있다.
```
{
  "alg": "HS256",
  "typ": "JWT"
}
```
* 토큰을 사용하는 측에서 토큰의 유형이 JWT 임을 확신할 수 있다면, typ 필드는 생략되어도 괜찮다.
* alg 에 넣어둔 암호화 알고리즘은 주로 HMAC SHA256 또는 RSA 가 사용된다. 이는 후술할 서명 (Signature) 에서 사용된다.

### 2. 페이로드(Payload)
* 페이로드는 사용자의 정보 혹은 데이터 속성 등을 나타내는 클레임(Claim) 이라는 정보 단위로 구성된다.
* 클레임 : 등록된 클레임 (Registered Claim), 공개 클레임 (Public Claim), 비공개 클레임 (Private Claim)
    1. 등록된 클레임 (Registered Claim) : JWT 사양에 이미 정의된 클레임
        * iss : Issuer. 토큰 발급자를 나타낸다.
        * sub : Subject. 토큰 제목을 나타낸다.
        * aud : Audience. 토큰 대상자를 나타낸다.
        * exp : Expiration Time. 토큰 만료 시각을 나타낸다. Numeric Date 형식으로 나타낸다.
        * nbf : Not Before. 토큰의 활성 시각을 나타낸다. 쉽게 말해, 이 시각 전에는 토큰이 유효하지 않다는 의미이다. Numeric Date 형식으로 나타낸다.
        * iat : Issued At. 토큰이 발급된 시각을 나타낸다. Numeric Date 형식으로 나타낸다. 이 값으로 토큰이 발급된지 얼마나 오래됐는지 확인할 수 있다.
        * jti : JWT ID. JWT 의 식별자를 나타낸다.
    2. 공개 클레임 (Public Claim)
        * JWT 를 사용하는 사람들에 의해 정의되는 클레임
        * 충돌 방지를 위해 URI 형태로 이름을 짓거나, [IANA JSON Web Token Claims Registry](https://www.iana.org/assignments/jwt/jwt.xhtml) 라는 곳에 직접 클레임을 등록해야한다.
        * 서버-클라이언트 사이의 단순 통신을 넘어 제 3자도 JWT 토큰을 사용할 때 충돌이 일어나지 않도록 합의된 클레임
        ```
        {
        "email": "sample@domain.com",
        "profile": "http://domain.com/image.png",
        "http://domain.com/xxx/yyy/is_admin": true
        }
        ```
        * 위처럼 등록된 공개 클레임인 email, profile 등을 사용할 수도 있고, http://domain.com/xxx/yyy/is_admin 처럼 URI 형태로도 사용할 수 있다.
    3. 비공개 클레임 (Private Claim) : 서버와 클라이언트 사이에서만 협의된 클레임
        * 공개 클레임과 충돌이 일어나지 않게 사용하면 된다.
        ```
        {
        "user_id": "123456790",
        "user_age": 25
        }
        ```
### 3. 서명(Signature)
* 특정 암호화 알고리즘을 사용하여, Base64 인코딩된 헤더와 Base64 인코딩된 페이로드 그리고 개인키를 이용하여 암호화한다. 
* 서명을 통해 서버는 헤더 혹은 페이로드가 누군가에 의해 변조되었는지 그 무결성을 검증하고 보장할 수 있다.

## 토큰 기반 인증 과정 
![image](https://github.com/kmularise/TIL/assets/106499310/804ccb30-897e-4c23-bf69-7ade58c81457)
### 발급
* 사용자가 성공적으로 로그인하면 JWT의 발행시점, 만료기한, Payload 등 정보를 설정하고 토큰을 발급한다.
### 검증
* 사용자가 가지고 있는 토큰을 HTTP의 Authorization 헤더에 실어 보낸다.
* Authorization 헤더를 수신한 서버는 토큰이 위변조 되었거나, 만료 시각이 지나지 않은지 확인한 이후 토큰에 담겨 있는 사용자 인증 정보를 확인해 사용자를 인가한다.

## 문제점
* 많은 네트워크 트래픽 사용 : 세션은 Cookie 헤더에 세션 아이디만 실어 보내서 트래픽을 적게 사용하는데, JWT는 사용자 인증 정보, 토큰 발급 시각, 만료시각, 토큰 ID 등 데이터 크기가 세션 ID에 비해 크므로 훨씬 많은 네트워크 트래픽을 사용한다.
* 보안 문제 :  이미 발행된 토큰에 대해 서버는 아무런 제어를 할 수 없다.
    * 세션의 경우, 모든 인증정보를 서버에서 관리한다. 세션 아이디가 해커에게 탈취된다고 해도, 서버 측에서 해당 세션을 무효 처리하면 된다.
    * 토큰의 경우, 클라이언트가 모든 인증 정보를 가지고 있다. 토큰이 한번 해커에게 탈취되면, 해당 토큰이 만료되기 전까지 서버에서 토큰을 무효화시킬 수 없다.
    * JWT의 Payload에 넣을 수 있는 데이터 종류 제한 : JWT 특성상 토큰에 실린 Payload는 별도로 암호화 되어 있지 않으므로, 누구나 내용을 확인할 수 있으므로 민감한 데이터는 Payload에 실을 수 없다.

## JWT를 직접 인코딩하고 디코딩할 수 있는 사이트
https://jwt.io/

## 참고자료

* JWT 토큰 
    1. https://auth0.com/docs/secure/tokens/json-web-tokens
    2. https://dev.to/thecodearcher/what-really-is-the-difference-between-session-and-token-based-authentication-2o39
https://datatracker.ietf.org/doc/html/rfc7519

# Bcrypt 암호화 알고리즘

## 암호화 방식 분류
![image](https://github.com/kmularise/TIL/assets/106499310/90f989b1-3be3-45c6-a01d-cabb8d9187c3)
* 양방향 : 복호화 가능
* 단방향 : 복호화 불가능

## salt
* 아주 작은 임의의 랜덤한 텍스트
* 해커의 Raninbow Table에 대비하기 위한 장치

> Rainbow Table : 해커가 해시함수에 입력값들을 전부 넣어 결과갑을 구해 표로 정리한 것
> Rainbow Table 대비 : 보호할 정보 + 개발자 임의 정보 >=해시함수==> (결과값)

### 예시
```
$2a$10$vI8aWBnW3fID.ZQ4/zo1G.q1lRps.9cGLcZEiGDMVr5yUP1KUOYTa
```
* '2a' : 사용된 Bcrypt 알고리즘 버전
* '10' : cost factor, key derivation 함수가 2^10번 반복 실행, 이 값이 커질수록 해시값을 구하는 속도가 느려진다.
* vI8aWBnW3fID.ZQ4/zo1G.q1lRps.9cGLcZEiGDMVr5yUP1KUOYTa' : salt(앞 22자리) + 암호화된 비밀번호값
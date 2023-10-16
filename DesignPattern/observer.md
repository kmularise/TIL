# 옵저버 패턴
* 어떤 객체의 상태가 변할 때 그와 연관된 객체들에게 알림을 보내는 디자인 패턴
    * 객체의 상태 변화를 관찰하는 관찰자들, 즉 옵저버들의 목록을 객체에 등록하여 상태 변화가 있을 때마다 메소드 등을 통해 객체가 직접 목록의 각 옵저버에게 통지하도록 하는 디자인 패턴
    * 1:1 관계 또는 1:N 관계
* 분산 이벤트 핸들링 시스템을 구현하는 데 사용
* 발행/구독 모델, pub/sub 모델로 알려져 있음

## 장점
* Subject의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다.
* 발행자의 코드를 변경하지 않고도 새 구독자 클래스를 도입할 수 있어 개방 폐쇄 원칙(OCP)Visit Website 준수한다.
* 런타임 시점에서에 발행자와 구독 알림 관계를 맺을 수 있다.
* 상태를 변경하는 객체(Subject)와 변경을 감지하는 객체(Observer)의 관계를 느슨하게 유지할 수 있다. (느슨한 결합)

## 단점
* 구독자는 알림 순서를 제어할수 없고, 무작위 순서로 알림을 받는다.
* 다수의 옵저버 객체를 등록 이후 해지하지 않는다면 메모리 누수가 발생할 수도 있다.

## 예시
<img width="831" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/48a7005d-2f55-46de-9189-8a533935bbb2">

* ISubject : 관찰 대상자를 정의하는 인터페이스
* ConcreteSubject : 관찰 당하는 대상자 / 발행자 / 게시자
    * Observer들을 컬렉션(List, Map, Set ..등)으로 조합(composition)하여 가지고 있다.
    * Subject의 역할은 관찰자인 Observer들을 내부 컬렉션에 등록/삭제 하는 메소드를 갖고 있다. (register, remove)
    * Subject가 상태를 변경하거나 어떤 동작을 실행할때, Observer 들에게 이벤트 알림(notify)을 발행한다.
* IObserver : 구독자들을 묶는 인터페이스 (다형성)
* Observer : 관찰자 / 구독자 / 알림 수신자.
    * Observer들은 Subject가 발행한 알림에 대해 현재 상태를 취득한다.
    * Subject의 업데이트에 대해 전후 정보를 처리한다.


<!-- https://velog.io/@octo__/%EC%98%B5%EC%A0%80%EB%B2%84-%ED%8C%A8%ED%84%B4Observer-Pattern#:~:text=%EC%98%B5%EC%84%9C%EB%B2%84%20%ED%8C%A8%ED%84%B4(observer%20pattern)%EC%9D%80,%ED%95%98%EB%8F%84%EB%A1%9D%20%ED%95%98%EB%8A%94%20%EB%94%94%EC%9E%90%EC%9D%B8%20%ED%8C%A8%ED%84%B4%EC%9D%B4%EB%8B%A4. -->
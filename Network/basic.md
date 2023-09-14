## Host
* 컴퓨터가 네트워크에 연결되었을 때 보통 호스트라고 한다.

## End-point(단말기)
* 네트워크 이용 주체
* ex) 클라이언트, 서버, Peer - P2P 통신
## Switch
네트워크 그 자체를 이루는 호스트(Infrastructure)
* 라우터 (L3 스위치의 일종)
* IPS
* Tab, Aggregation Switch

### Switch가 하는일
* 비유 : 
    * Switch : 교차로
        * Switching : 경로 선택
    * 이정표 : 라우팅 테이블(Routing Table)
    * 자동차: 패킷
    * 도로망: 인터넷
* IP 주소를 스위칭 : 네트워크 계층 - L3 스위칭
* MAC 주소 스위칭 : L2 스위칭 
* 포트번호 스위칭 : L4 스위칭
* HTTP 정보에 근거해서 스위칭 : L7 스위칭

비용을 고려해야 한다. - Matric

# L2

## NIC 
* NIC(Network Interface Card)는 흔히 LAN(Local Area Network) 카드이다.
* 유/무선 NIC가 있지만 굳이 구별하지 않고 NIC라고 할 때가 많다.
* NIC는 H/W이며 MAC주소를 갖는다.

> 네트워크 규모 LAN < MAN < WAN

## L2 Access Switch
* End-point와 직접 연결되는 스위치
* MAC 주소를 근거로 스위칭
* Link-up : End-point와 Access switch가 물리적으로 연결되었을 때
* Link-down : End-point와 Access switch가 물리적으로 연결이 해제 되었을 때
* Up-link: 네트워크가 상위 layer와 연결될 때

![link-up](https://github.com/kmularise/TIL/assets/106499310/267d97e9-5a7a-4e31-a411-f80a0652a7b8)

## L2 Distribution switch
* L2 Access 스위치를 위한 스위치
* VLAN 기능을 제공하는 것이 일반적

![image](https://github.com/kmularise/TIL/assets/106499310/102b6a58-b60c-495e-b14e-e7630a3d6fec)


## LAN과 WAN의 경계 그리고 Broadcast
### Broadcast
* 주소 -  방송 주소
* 효울이 떨어진다. - Broadcast 범위 최소화?
* Broadcast 주소라는 매우 특별한 주소가 존재한다. (MAC, IP 모두 존재)
* 논리적인 것인지 아니면 물리적인 것이지로 구분하는 것도 방법이다.
* 일단 MAN(Metropolitan Area Network)은 제외한다.

출발지 - 목적지
한 주소가 브로드캐스트를 하는 동안 나머지는 통신을 못한다.

LAN - Frame 

![image](https://github.com/kmularise/TIL/assets/106499310/d145d7de-a386-4532-b84a-0a6ebb54f656)
<img width="1176" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/23297d68-82bb-44f9-b19f-70f2828a9264">


### Unicast

# L3

## IP v4 주소
32bit

### 네트워크 ID, 호스트 ID
![image](https://github.com/kmularise/TIL/assets/106499310/70d0b963-cfb3-4968-8cf2-a388d387d592)

## L3 Packet
* L3 Packet - IP
* Header와 Payload로 나뉘며 이는 상대적인 분류
* 최대 크기는 MTU(Maximum Transmission Unit) (1500 bytes)

![image](https://github.com/kmularise/TIL/assets/106499310/5e003fb6-3637-4f72-884c-ecc3ff6a72c2)

### Packet
* 단위 데이터

## Encapsulation과 Decapsulation
![en_decapsulation](https://github.com/kmularise/TIL/assets/106499310/3e41acf9-15f2-44f4-9def-c88025c902c4)
* TCP Segment 위의 계층에서는 Stream

## 패킷의 생성, 전달, 소멸
![packet_send](https://github.com/kmularise/TIL/assets/106499310/2d8016d0-5f3e-4927-a85f-663debccba6a)


# blocking I/O VS non-blocking I/O

## I/O
* input/output, 데이터의 입출력

## I/O 종류
* network(socket)
* file
* pipe
* device

## socket
네트워크 통신은 socket을 통해 데이터가 입출력된다.

<img width="1821" alt="image" src="https://github.com/Yuijin-Labs/template_board/assets/106499310/f7ccd634-1286-4b06-a5a7-2687b3d6082f">

## backend server
네트워크 상의 요청자들과 각각 소켓을 열고 통신한다.

## blocking I/O
I/O 작업을 요청한 프로세스/스레드는 요청이 완료될 때까지 블락된다.

![image](https://github.com/kmularise/TIL/assets/106499310/5fc0ba31-c4c1-4d41-9c75-b58c5539a8d6)

1. 스레드에서 read blocking system call 호출을 한다.
2. read blocking system call을 호출한 스레드는 blocked 상태가 된다.
3. 커널 모드로 전환되고 커널에서는 read I/O 작업을 시작한다.
4. I/O 작업이 완료되면 커널이 응답을 받고 데이터를 kernel space에서 user space로 옮기게 된다.
5. 스레드는 blocked 상태에서 깨어나 나머지 작업을 진행하게 된다.

## Socket에서 blocked I/O란?
소켓마다 send_buffer, recv_buffer 두개의 버퍼가 있다.
소켓 S에서 소켓 A로 데이터를 보내려고 한다. 그때 recv_buffer는 데이터가 들어올 때까지 해당 read blocking system call을 한 스레드는 blocked 상태가 된다.

![image](https://github.com/kmularise/TIL/assets/106499310/c70b10ed-d50f-4e7f-96be-42fc9ed45d22)

## non-blocking I/O
프로세스/스레드를 blocked시키지 않고 요청에 대한 현재 상태를 즉시 리턴한다.
blocked되지 않고 즉시 리턴하기 때문에 스레드가 다른 작업을 수행할 수 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/8f08c061-243b-405f-8b96-12f325377aba)

1. 스레드에서 read non-blocking system call을 호출한다.
2. 유저모드에서 커널모드로 전환되고 커널모드에서 read I/O 작업을 시작한다.
3. 커널에서 아직 데이터가 준비되지 않았기 때문에, EAGAIN or EWOULDBLOCK 값을 리턴시킨다.
4. 스레드는 blocked 상태가 되지 않고 이어서 다른 코드를 실행할 수 있다.
5. I/O 작업이 완료되면 커널이 응답을 받고 커널은 데이터를 준비해 놓는다.
6. 스레드는 실행을 하다가 read non-blocking 시스템 콜을 호출한다.
7. 다시 유저모드에서 커널모드로 전환되고 이때는 데이터가 준비되어 있기 때문에 데이터를 kernel space에서 user space로 데이터를 전송한다.

## 소켓에서 non-blocking I/O란?
socket S에서 socket A로 데이터를 보내려고 한다. socket A에 recv_buffer에 데이터가 있는지 확인하기 위해 read를 호출한다. 만약 blocking I/O라면 blocked 상태가 되지만 지금은 non-blocking I/O 이기 때문에 데이터가 없다고 알려주고 read에 대한 시스템 콜은 바로 종료가 된다.

마찬가지로 socket S에서 write를 할 때도 blocking I/O였다면 send_buffer가 가득 찼을 경우 send_buffer에 공간이 생길 때까지 blocked 상태가 되지만 non-blocking I/O는 block이 되지 않고 적절한 에러 코드와 함께 write에 대한 system call은 바로 종료가 된다.


![image](https://github.com/kmularise/TIL/assets/106499310/edb7f3f3-aba4-41ae-a703-8fba49d5f4c9)

## I/O multiplexing
* 관심 있는 I/O 작업들을 동시에 모니터링하고 그 중 완료된 I/O작업들을 한번에 알려준다.
* 한번의 시스템콜로 여러 이벤트에 대해 한번에 처리하는 방식이다.
* 종류로는 select, poll, epoll, kqueue(mac OS), IOCP(window) 등이 있다.

![image](https://github.com/kmularise/TIL/assets/106499310/34f3ace0-3760-47a0-8995-588e95f2d79c)

1. 스레드는 I/O multiplexing 시스템콜을 이용해서 커널에 요청한다.
monitor 2 sockets non-blocking read -> 2개의 socket에 대해서 non-blocking mode로 읽으려고 하니까 새로운 들어오는 데이터가 있는지 알려줘! 라는 뜻.
2. 커널은 2개의 socket에 대해서 read I/O요청을 보낸다.
3. I/O multiplexing 시스템 콜을 한 스레드는 blocked 상태 또는 non-blocked 상태 모두 될 수 있다. (지금은 blocking mode로 동작한다고 가정하자)
4. blocked 상태가 되는 동안 비슷한 타이밍에 2개의 socket에 대한 데이터가 모두 들어왔다.
5. 커널에서 데이터가 있다는 것을 알려준다.
6. 스레드는 blocked 상태에서 깨어나고 순차적으로 2개의 소켓에 관한 데이터를 읽어온다.

# 파일
## I/O
### I/O의 사용
* 파일에 읽거나 저장할 일이 있을 때
* 다른 서버나 디바이스로 보보낼  일일이  있있을 때

### I/O 처리 
* java.io
* 초기 단계 자바
* 스트림 기반
* 스트림 : 끊기지 않고 연속적인 데이터

## NIO
* JDK 1.4부터
* 버퍼(Buffer)와 채널(Channel) 기반으로 데이터 처리

## NIO2
* Java 7부터

## 자바의 File과 Files 클래스
* java.io.File
	* 파일, 파일 경로 정보 포함
	* 정체가 불분명, 심볼릭 링크와 같은 유닉스 계열 파일에서 사용하는 몇몇 기능을 제대로 제공하지 못함
	* 객체를 생성하여 데이터 처리
* java.nio.file.Files
	* Java7, NIO2
	* 별도의 객체를 생성할 필요가 없음
## 파일 읽기 및 쓰기
* Reader close() : 작업중인 대상을 해제
* Writer close() : 쓰기 위해 열은 스트림을 해제한다.
* 예외가 발생하든 발생하지 않든 close()로 쓰기 객체나 읽기 객체를 닫아줘야 하기 때문에 finally 불록에서 호출한다.

### 파일 쓰기
* FileWriter, BufferedWriter 이용

### 파일 읽기
* FileReader, BufferedReader 이용
* Scanner 이용
	* Scanner 클래스는 텍스트 기반의 기본 자료형이나 문자열 데이터를 처리하기 위한 클래스다.
	* 정규표현식을 사용하여 데이터를 잘라 처리할 수도 있다.
## 참고
* Java 7 이상 버전이면 java.nio.file 패키지의 Files 클래스 사용 권장
## 실습
[코드](./code/io)

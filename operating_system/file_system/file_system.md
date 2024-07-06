# 파일 시스템
* 파일 시스템이 파일과 디렉터리를 보조기억장치에 할당하고 접근하는 방법
* 대표적인 파일 시스템의 종류(FAT 파일 시스템, 유닉스 파일 시스템)

## 파티셔닝
* 저장 장치의 논리적인 영역을 구획하는 작업

## 포매팅
* 파일 시스템을 설정
* 어떤 방식으로 파일을 관리할지 결정, 새로운 데이터를 쓸 준비하는 작업
* 파일 시스템에는 여러 종류가 있다.
* 파티션마다 다른 파일 시스템을 설정할 수도 있다.
-> 포매팅까지 완료하여 파일 시스템을 설정했다면 이제 파일과 디렉터리 생성이 가능해진다.

## 파일 할당 방법
* 포매팅까지 끝난 하드 디스크에 파일을 저장하기
* 운영체제는 파일/디렉터리를 블록 단위로 읽고 쓴다.
    * 즉, 하나의 파일이 보조기억장치에 저장될 때에는 여러 블록에 걸쳐 저장된다.
    * 하드 디스크의 가장 작은 저장 단위는 섹터이지만 보통 블록 단위로 읽고 쓴다.


### 연속 할당
* 보조기억장치 내 연속적인 블록에 파일 할당
* 연속 할당은 파일이 저장 장치 내에서 연속적인 공간을 차지하도록 블록을 할당하는 방법이다.
* 연속된 파일에 접근하기 위해 파일의 첫번째 블록 주소와 블록 단위의 길이만 알면 된다.
* 디렉터리 엔트리: 파일 이름, 첫번째 블록 주소, 블록 단위 길이 명시
* 구현이 단순하지만 외부단편화를 야기할 수 있다.

### 불연속 할당
* 오늘날 주로 사용되는 방식

### 불연속 할당 : 연결 할당
* 각 블록의 일부에 다음 블록의 주소를 저장하여 각 블록이 다음 블록을 가리키는 형태로 할당
* 파일을 이루는 데이터 블록을 연결리스트로 관리한다.
* 불연속 할당의 일종: 파일이 여러 블록에 흩어져 저장되어도 무방하다.
* 디렉터리 엔트리 : 파일이름, 첫번째 블록 주소, 블록 단위의 길이 또는 파일 이름, 첫번째 블록 주소, 마지막 블록 주소
* 단점:
    * 반드시 첫번째 블록부터 하나씩 읽어들어야 한다.
    * 오류 발생 시 해당 블록 이후 블록은 접근이 어렵다.

### 불연속 할당 : 색인 할당
* 파일의 모든 블록 주소를 색인 블록이라는 하나의 블록에 모아 관리하는 방식
* 파일 내 임의의 위치에 접근하기 용이
* 디렉터리 엔트리: 파일 이름 & 색인 블록 주소

## 파일 시스템 종류
* FAT 파일 시스템
    * 연결 할당 기반 파일 시스템
    * 연결 할당의 단점을 보완
    * 각 블록에 포함된 다음 블록 주소를 한데 모아 테이블로 관리

## 유닉스 파일 시스템
* 색인 할당 기반 파일 시스템
* 색인 블록 == i-node
    * 파일의 속성 정보와 15개의 블록,주소 저장 가능
    1. 블록 주소 중 12개에는 직접 블록 주소 저장
        * 직접 블록 : 파일 데이터가 저장된 블록
    2. 1.번으로 충분하지 않다면 13번째 주소에 단일 간접 블록 주소 저장
        * 단일 간접 블록 : 파일 데이터를 저장한 블록 주소가 저장된 블록
    3. 2번으로 충분하지 않다면 14번째 주소에 이중 간접 블록 주소 저장
    4. 3번으로 충분하지 않다면 15번째 주소에 삼중 간접 블록 주소 저장


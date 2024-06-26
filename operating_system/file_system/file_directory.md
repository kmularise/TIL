# 파일과 디렉터리
## 파일 시스템(file system)
* 파일과 디렉터리를 관리하는 운영체제 내의 프로그램
* 파일과 디렉터리를 다루어 주는 프로그램

## 파일
* 보조기억장치에 저장된 관련 정보의 집합
* 의미 있고 관련 있는 정보를 모은 논리적 단위
* 파일을 이루는 정보
* 파일을 실행하기 위한 정보 + 부가 정보 (= 속성, 메타 데이터)

### 파일의 속성
| 속성 이름 | 의미 |
| --- | --- |
| 유형 | 운영체제가 인지하는 파일의 종류를 나타낸다. 확장자로 유형을 나타낼 수 있다. |
| 크기 | 파일의 현재 크기와 허용 가능한 최대 크기를 나타낸다. |
| 보호 | 어떤 사용자 해당 파일을 읽고, 쓰고, 실행할 수 있는지를 나타낸다. |
| 생성 날짜 | 파일이 생성된 날짜를 나타낸다. |
| 마지막 접근 날짜 | 파일에 마지막으로 접근한 날짜를 나타낸다. |
| 마지막 수정 날짜 | 파일이 마지막으로 수정된 날짜를 나타낸다. |
| 생성자 | 파일을 생성한 사용자를 나타낸다. |
| 소유자 | 파일을 소유한 사용자를 나타낸다. |
| 위치 | 파일의 보조기억장치 상의 현재 위치를 나타낸다. |

### 파일 연산을 위한 시스템 호출
1. 파일 생성
2. 파일 삭제
3. 파일 열기
4. 파일 닫기
5. 파일 읽기
6. 파일 쓰기

## 디렉터리
* 여러 계층으로 파일 및 폴더를 관리하는 트리 구조 디렉터리
* 최상위 디렉터리(루트 디렉터리, /), 서브 디렉터리

### 경로
* 디렉터리를 이용해 파일/디렉터리의 위치, 나아가 이름까지 특정 지을 수 있는 정보
* 절대 경로와 상대 경로
    * 절대 경로 : 루트 디렉터리에서 자기 자신까지 이르는 고유한 경로
    * 상대 경로 : 현재 디렉터리에서 자기 자신까지 이르는 경로

### 디렉터리 연산을 위한 시스템 호출
1. 디렉터리 생성
2. 디렉터리 삭제
3. 디렉터리 열기
4. 디렉터리 닫기
5. 디렉터리 읽기

### 디렉터리 엔트리
* 많은 운영체제에서는 디렉터리를 '특별한 형태의 파일'로 간주한다. 즉, 디렉터리는 포함된 정보가 조금 특별한 파일이다.
* 파일의 내부에는 파일과 관련된 정보들이 있다면, 디렉터리 내부에는 해당 디렉터에 담겨 있는 대상과 관련된 정보들이 담겨 있다.
    * 이 정보는 보통 테이블(표) 형태로 구성된다.
        * 각 엔트리(행)에 담기는 정보
            * 디렉터리에 포함된 대상의 이름
            * 그 대상이 보조기억장치 내에 저장된 위치(를 유추할 수 있는 정보)
    

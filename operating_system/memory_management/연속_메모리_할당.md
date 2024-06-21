# 연속 메모리 할당
* 프로세스에 연속적인 메모리 공간 할당

## 스와핑
* 현재 사용되지 않는 프로세스들을 보조기억장치의 일부 영역(스왑 영역)으로 쫓아내고 그렇게 생긴 빈 공간에 새로운 프로세스를 적재한다.
* 프로세스들이 요구하는 메모리 공간 크기가 실제 메모리 크기보다 큰 상황에서도 스와핑을 통해 모든 프로세스들을 동시에 실행시킬 수 있다.




### 메모리 확인
mac 운영체제를 기준으로 다음과 같은 명령어를 터미널에 입력하면 가상 메모리 관련 통계를 볼 수 있다.

`vm_stat`

<br>

```bash
Mach Virtual Memory Statistics: (page size of 16384 bytes)
Pages free:                                2864.
Pages active:                             93556.
Pages inactive:                           91282.
Pages speculative:                         1457.
Pages throttled:                              0.
Pages wired down:                         82266.
Pages purgeable:                            346.
"Translation faults":                 212942013.
Pages copy-on-write:                    4555066.
Pages zero filled:                     57355585.
Pages reactivated:                     70242536.
Pages purged:                           5098272.
File-backed pages:                        64777.
Anonymous pages:                         121518.
Pages stored in compressor:              530451.
Pages occupied by compressor:            216805.
Decompressions:                        71198335.
Compressions:                          81862546.
Pageins:                               20527111.
Pageouts:                                253084.
Swapins:                                 428521.
Swapouts:                                601882.
```

여기서 페이지 크기는 16KB임을 알 수 있다.

<img width="973" alt="image" src="https://github.com/kmularise/TIL/assets/106499310/84a3d3cc-8160-4d4f-b8e8-70ed6431b2b9">

## 메모리 할당
* 프로세스는 메모리의 빈 공간에 적재되어야 한다. 
* 최초 적합, 최적 적합, 최악 적합

### 최초 적합(first-fit)
* 운영체제가 메모리 내의 빈 공간을 순서대로 검색하다가 적재할 수 있는 공간을 발견하면 그 공간에 프로세스를 배치하는 방식이다.
* 검색 최소화, 빠른 할당

### 최적 적합(best-fit)
* 운영체제가 빈 공간을 모두 검색해본 뒤, 적재 가능한 가장 작은 공간에 할당한다.

### 최악 적합(worst-fit)
* 운영체제가 빈 공간을 모두 검색해본 뒤, 적재 간으한 가장 큰 공간에 할당한다.

## 연속 메모리 할당의 문제점
1. 외부 단편화
2. 물리 메모리보다 큰 프로세스 실행 불가

## 외부 단편화(external fragmentation)
* 프로세스를 연속적으로 메모리에 할당하는 방식은 메모리를 효율적으로 사용하는 방법이 아니다.
* 외부 단편화라는 문제가 발생하기 때문이다.
* 외부 단편화 :
    * 프로세스들이 실행과 종료를 반복하며 메모리 사이 사이에 빈 공간이 발생한다.
    * 프로세스를 할당하기 어려울 만큼 작은 메모리 공간들로 인해 메모리가 낭비되는 현상이다.



## 외부 단편화 해결
### 1. 메모리 압축 (compaction)
* 여기저기 흩어져 있는 빈 공간들을 하나로 모으는 방식이다.
* 프로세스를 재배치시켜 흩어져 있는 작은 빈 공간들을 하나의 큰 빈 공간으로 만드는 방법이다.
→ 프로세스를 재배치시키는 과정에서 오버헤드가 있다. 
<br>
<br>

### 2. 가상 메모리 기법, 페이징

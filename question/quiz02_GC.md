# 가비지 컬렉터
## 1. java GC 에서 young 영역과 old 영역은 각각 무엇인가?
young 영역은 생성된지 얼마 안된 객체들이 저장되어 있는 영역이고, old 영역은 young 영역에서 오랫동안 살아남은 객체들이 저장되어 있는 영역이다. young 영역은 Eden 영역과 survivor 영역인 S0, S1영역으로 나뉜다.

Minor GC와 Major GC가 일어나는 과정을 살펴보면 young 영역과 old 영역에 대해 더 잘 이해할 수 있다. 
객체는 새로 생성되면 young 영역에 Eden 영역에 할당된다. 이렇게 새롭게 생성된 객체들이 Eden 영역에 할당되는 과정을 반복하다가 Eden 영역이 다 차서 할당할 공간이 없으면 Minor GC가 발생하고, 여기서 살아남은 객체들은 survivor 영역으로 이동한다. survivor 영역에 있는 객체들은 Minor GC가 일어날 때마다 age가 1씩 일어난다. 이러한 과정이 반복되다가 객체의 age가 특정 임계값에 도달하게 되면 old 영역으로 이동하게 된다.
old 영역이 꽉 차게 되면 Major GC가 일어나게 된다.
______________________________________________
## 2. 트래픽이 무지 많이 몰리는 이벤트가 예정되어있습니다. 이때, Young Generation과 Old Generation의 비율은 어떻게 설정해야 할까?


### 애플리케이션 운영에 있어 주요하게 설정해야 할 목표 : 처리율과 GC로 인한 최대 중단 시간
애플리케이션 운영에 있어 충족시켜야할 주요 목표는 두가지이다. 처리율과 GC로 인한 최대 중단 시간은 GC 튜닝에 있어 설정해야할 주요 목표이다. 처리율이 높으면, 사용자가 애플리케이션이 빠르게 동작한다고 느낀다. 또한 GC로 인한 중단 시간이 길어지면, 애플리케이션 실행이 지연되어 사용자가 불편함을 느끼기 때문이다. 따라서 높은 처리율과 낮은 GC로 인한 최대 중단 시간을 설정하는 것이 중요하다. 하지만 처리율과 GC로 인한 최대 중단 시간 사이에 상충관계가 발생할 수 있다. 처리율이 증가한다면 힙 영역의 크기를 키우면, 탐색해야할 메모리 크기가 커져 GC로 인한 중단 시간이 늘어나고, GC로 인한 최대 중단 시간을 줄어들면, GC가 자주 발생하게 되어 처리율이 떨어지게 된다. 따라서 처리율과 GC로 인한 최대 중단 시간에 대한 목표를 적절하게 설정하고 힙 영역을 튜닝하는 것이 중요하다. 
```
처리율 : 총 시간 대비 가비지 컬렉션에 소요되지 않은 시간의 비율
GC로 인한 최대 중단 시간 : GC의 stop the world 과정으로 인해 애플리케이션이 중단되는 최대 시간
``` 
<br>

### 트래픽이 많이 몰릴 시 Young Generation과 Old Generation의 비율 설정
트래픽이 많이 몰리는 이벤트가 예정되어 있다는 것은 수많은 요청이 발생한다. 따라서 수많은 Request 객체들과 새롭게 발생하는 데이터와 관련된 객체들이 새롭게 생성될 것이다. 이러한 객체들은 짧은 시간 내에 유효한 참조가 없는 unreachable 객체가 된다. 에덴 영역이 다 차서 Minor GC가 일어나는 시점에서는 이러한 객체들은 메모리 해제 대상이 된다. 평소와 동일하게 Young Generation과 Old Generation 비율이 동일하다면, 새로운 객체들이 많이 생성되어서 Young Generation이 다 차게 되는 빈도 수가 증가하고 Minor GC가 보다 빈번하게 발생할 것이다. 대규모 트래픽 발생으로 오래 살아남을 객체는 거의 생성되지 않으므로 Major GC는 평소와 비슷하게 발생할 것이다. GC로 인한 중단 시간은 대규모 트래픽 발생 전과 동일할 것이다. GC로 인한 중단 시간만 고려한다면, 힙 영역의 크기가 이전과 동일하다면, Young Generation의 비율을 낮춰야 한다. Young Generation 탐색 시간이 감소해 GC로 이한 중단 시간이 줄어들기 때문이다. 처리율과 GC로 인한 최대 중단 시간을 동시에 고려하면, 목표로 하는 처리율과 GC로 인한 최대 중단 시간을 설정하고 이를 둘다 만족시키는 Old Generation에 대한 Young Generation 비율로 설정해야 한다. 이러한 GC 튜닝은 다른 수단을 시도해보고 나서 시도하는 것이 좋다. 특히 G1GC의 경우 수정 없이 효율적으로 작동할 수 있는 기본값이 있기도 하고, GC 튜닝은 메모리 영역을 건드리기에 신경써야할 요소가 많기 때문이다.

______________________________________

## 참고자료

https://didrlgus.github.io/java/02-post/#%ED%8A%B8%EB%9E%98%ED%94%BD%EC%9D%B4-%EB%AC%B4%EC%A7%80%EB%A7%8E%EC%9D%B4-%EB%AA%B0%EB%A6%AC%EB%8A%94-%EC%9D%B4%EB%B2%A4%ED%8A%B8%EA%B0%80-%EC%98%88%EC%A0%95%EB%90%98%EC%96%B4%EC%9E%88%EC%96%B4-%ED%8A%B8%EB%9E%98%ED%94%BD%EC%9D%B4-%EB%A7%8E%EC%9D%84%EB%95%8C-young-gen%EA%B3%BC-old-gen-%EB%B9%84%EC%9C%A8%EC%9D%80-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B4-%EC%A2%8B%EC%9D%84%EA%B9%8C

https://sigridjin.medium.com/weekly-java-%ED%8A%B8%EB%9E%98%ED%94%BD%EC%9D%B4-%EB%A7%8E%EC%9D%B4-%EB%AA%B0%EB%A6%AC%EB%8A%94-%EC%9D%B4%EB%B2%A4%ED%8A%B8%EA%B0%80-%EC%98%88%EC%A0%95%EB%90%98%EC%96%B4-%EC%9E%88%EC%9D%84-%EB%95%8C-young-gen%EA%B3%BC-old-gen%EC%9D%98-%EB%B9%84%EC%9C%A8-%EA%B3%A0%EB%AF%BC%ED%95%98%EA%B8%B0-3adfeca388af

https://blog.gceasy.io/2022/03/04/garbage-collection-tuning-success-story-reducing-young-gen-size/

https://www.uber.com/en-KR/blog/jvm-tuning-garbage-collection/

https://codeahoy.com/2017/08/06/basics-of-java-garbage-collection/?uclick_id=feb3b912-8dab-48aa-b43c-59ac40dca721

https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/sizing.html

https://stackoverflow.com/questions/59993585/jvm-young-generation-and-old-generation-ratio-allocation
https://docs.oracle.com/javase/7/docs/technotes/guides/vm/G1.html

https://docs.oracle.com/cd/E26576_01/doc.312/e24936/tuning-java.htm#GSPTG00073

https://medium.com/@raupach/the-impact-of-garbage-collection-c5c268ebb0ff

https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/

https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/g1_gc.html#pauses

https://johngrib.github.io/wiki/java/gc/tuning-guide/

https://12bme.tistory.com/543

https://linux.systemv.pe.kr/2015/07/%EC%9C%A0%EC%9A%A9%ED%95%9C-jvm-%ED%94%8C%EB%9E%98%EA%B7%B8%EB%93%A4-part-6-throughput-collector/

https://xzio.tistory.com/1958

https://velog.io/@ddangle/Java-G1-GC-%EC%97%90-%EB%8C%80%ED%95%B4

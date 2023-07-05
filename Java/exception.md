## try-catch

- try-catch에서 예외가 발생하지 않을 경우
    - try 내에 있는 모든 문장이 실행되고 try-catch 문장 이후의 내용이 실행된다.
- try-catch에서 예외가 발생하는 경우
    - try  내에서 예외가 발생한 이후의 문장들은 실행되지 않는다.
    - catch 내에 있는 문장은 반드시 실행되고, try-catch 문장 이후의 내용이 실행된다.

- try 다음에 오는 catch 블록은 1개 이상 올 수 있다.
- 먼저 선언한 catch 블록의 예외 클래스가 다음에 선언한 catch 블록의 부모에 속하면, 자식에 속하는 catch 블록은 절대 실행될 일이 없으므로 컴파일이 되지 않는다.
- 하나의 try 블록에서 예외가 발생하면 그 예외와 관련이 있는 catch 블록을 찾아서 실행한다.
- catch 블록 중 발생한 예외와 관련있는 블록이 없으면, 예외가 발생되면서 해당 쓰레드는 끝난다.

## 예외의 종류 세가지

- checked exception
- error
- runtime exception 혹은 unchecked exception

### error (에러)

- Error와 Exception으로 끝나는 오류의 가장 큰 차이는 프로그램 안에서 발생했는지, 밖에서 발생했는지 여부
- 더 큰 차이는 프로그램이 멈추어 버리느냐 계속 실행할 수 있느냐의 차이
    - Error는 프로세스에 영향을 줌
    - Exception은 쓰레드에만 영향을 줌

### runtime exception (이하 런타임 예외)

런타임 예외는 예외가 발생할 것을 미리 감지하지 못했을 때 발생한다.

컴파일 시에 체크를 하지 않기 때문에 unchecked exception 이라고도 부른다.
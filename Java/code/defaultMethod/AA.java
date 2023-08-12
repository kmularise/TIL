package defaultMethod;

public interface AA {
    public int status = 0;
    default void hello() {
        System.out.println("status = " + status);
    }
}

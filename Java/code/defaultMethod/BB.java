package defaultMethod;
public interface BB extends AA{
    public int status = 3;
    default void hello() {
        System.out.println("status = " + status);
    }

}

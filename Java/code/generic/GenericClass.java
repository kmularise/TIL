package generic;

public class GenericClass <X> {
    private X x;
    private Object o;

    public <T> GenericClass(T t) {
        this.o = t;
        System.out.println("t.getClass().getName() = " + t.getClass().getName());
    }
    
    public void setValue(X x) {
        this.x = x;
        System.out.println("x.getClass().getName() = " + x.getClass().getName());
    }
}

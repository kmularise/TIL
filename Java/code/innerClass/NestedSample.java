package innerClass;

/**
 * Static nested 클래스 예시
 * */
public class NestedSample {
    public static void main(String[] args) {
        NestedSample sample = new NestedSample();
        sample.makeStaticNestedObject();
    }
    
    public void makeStaticNestedObject() {
        OuterOfStatic.StaticNested staticNested = new OuterOfStatic.StaticNested();
        staticNested.setValue(3);
        System.out.println("staticNested.getValue() = " + staticNested.getValue());
    }
}

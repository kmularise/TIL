package innerClass;

/**
 * Static nested 클래스 예시
 * */
public class OuterOfStatic {
    static class StaticNested{
        private int value = 0;
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

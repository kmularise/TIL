package innerClass;

/**
 * Inner 클래스 예시
 * */
public class OuterOfInner {
    class Inner {
        private int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

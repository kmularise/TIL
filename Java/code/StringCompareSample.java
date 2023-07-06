public class StringCompareSample {
    public static void main(String[] args) {
        checkCompare();
    }
    public static void checkCompare() {
        String text = "Check Value";
        String text2 = "Check Value";
        if (text == text2) {
            System.out.println("test==test2 result is same.");
        } else {
            System.out.println("text==text2 result is different");
        }
        if (text.equals(text2)) {
            System.out.println("text.equals(text2) result is same");
        }
        String text3 = "check value";
        if (text.equalsIgnoreCase(text3)) {
            System.out.println("text.equalsIgnoreCase(text3) result is same");
        }
    }
}

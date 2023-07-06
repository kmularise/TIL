package innerClass.practice;

public class MyPage {
    private static InputBox input;

    public static void main(String[] args) {
        MyPage myPage = new MyPage();
        myPage.setUI();
        myPage.pressKey();
    }
    public void setUI () {
        input = new InputBox();
        KeyEventListener listener = new KeyEventListener() {
            @Override
            public void onKeyDown() {
                System.out.println("Key Down");
            }

            @Override
            public void onKeyUp() {
                System.out.println("Key Up");
            }
        };
        input.setListener(listener);

    }
    public void pressKey() {
        input.listenerCalled(2);
        input.listenerCalled(4);
    }
}

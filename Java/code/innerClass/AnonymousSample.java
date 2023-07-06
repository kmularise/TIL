package innerClass;

public class AnonymousSample {
    public static void main(String[] args) {
        AnonymousSample sample = new AnonymousSample();
        sample.setButtonListener();
        sample.setButtonListenerAnonymous();
    }

    private void setButtonListener() {
        MagicButton button = new MagicButton();
        MagicButtonListener listener = new MagicButtonListener();
        button.setListener(listener);
        button.onClickProcess();
    }
    public void setButtonListenerAnonymous() {
        MagicButton button = new MagicButton();
        /**
         * 익명 클래스 이용
         */
        button.setListener(new EventListener() {
            @Override
            public void onClick() {
                System.out.println("Magic Button Clicked !!!");
            }
        });
        button.onClickProcess();
    }
}

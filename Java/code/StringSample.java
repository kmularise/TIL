import java.nio.charset.Charset;

public class StringSample {

    public static void main(String[] args) {
        StringSample sample = new StringSample();
        //sample.convert();
        sample.convertUTF16();
    }

    private void convertUTF16() {
        try {
            String korean = "한글";
            byte[] bytes = korean.getBytes("UTF-16");
            printByteArray(bytes);
            String korean2 = new String(bytes, "UTF-16");
            System.out.println(korean2);
            System.out.println(Charset.defaultCharset());
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void convert() {
        try {
            String korean = "한글";
            byte[] bytes = korean.getBytes();
            printByteArray(bytes);
            String korean2 = new String(bytes);
            System.out.println(korean2);
            System.out.println(Charset.defaultCharset());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printByteArray(byte[] bytes) {
        for (byte data : bytes) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
}

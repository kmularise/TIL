public class UseStringMethods {
    public static void main(String[] args) {
        String str = "The String class represents character strings";
        String findStr = "string";
        findString(str, findStr);
        findAnyCaseString(str, findStr);
        countChar(str, 's');
        printContainWords(str, "ss");
    }
    public static void findString(String str, String findStr) {
        System.out.println("string is appeared at " + str.indexOf(findStr));
    }
    public static void findAnyCaseString(String str, String findStr) {
        System.out.println("string is appeared at " + str.toLowerCase().indexOf(findStr));
    }

    public static void countChar(String str, char c) {
        int count = 0;
        for (char compared : str.toCharArray()) {
            if (compared == c) {
                count += 1;
            }
        }
        System.out.println("s count = " + count);
    }

    public static void printContainWords(String str, String findStr) {
        String[] words = str.split(" ");
        for (String word : words) {
            if (word.contains(findStr)) {
                System.out.println(word + " contains " + findStr);
            }
        }
    }
}

package thread;

import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        Thread tarzanSong = new Thread(new TarzanRun(10));
        tarzanSong
//                .run(); // 타잔 노래가 끝나야 입력에 응답 가능
            .start(); // ⭐ 입력 응답과 동시 진행 가능해짐

        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNext()) {
                var line = sc.nextLine();

                //  🔽 이곳에 다음의 코드들을 붙여넣을 것



                if (line.equalsIgnoreCase("quit")) break;
                System.out.println(line);
            }
        }
    }
}

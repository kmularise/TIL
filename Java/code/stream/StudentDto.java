package stream;

public class StudentDto {
    private String name;
    private int age;
    private int scoreMath;
    private int scoreEnglish;

    public StudentDto(String name, int age, int scoreMath, int scoreEnglish) {
        this.name = name;
        this.age = age;
        this.scoreMath = scoreMath;
        this.scoreEnglish = scoreEnglish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScoreMath() {
        return scoreMath;
    }

    public void setScoreMath(int scoreMath) {
        this.scoreMath = scoreMath;
    }

    public int getScoreEnglish() {
        return scoreEnglish;
    }

    public void setScoreEnglish(int scoreEnglish) {
        this.scoreEnglish = scoreEnglish;
    }
}

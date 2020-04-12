package pojo;

public class Student {

    private int number;
    private int score;

    public Student(int number, int score) {
        this.number = number;
        this.score = score;
    }

    public int getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student)obj;
        return this.number == student.number && this.score == student.score;
    }

}

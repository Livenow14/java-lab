import org.junit.jupiter.api.DisplayName;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@DisplayName("5장. 형식 맞추기")
public class Chapter5 {

    /**
     * 개념은_빈_행을_분리하라.
     */
/*    static class Person {
        private String name;
        public void changeName(String name) {
            this.name = name;}
        public void appendPrefix(String prefix) {
            this.name = prefix.concat(this.name);}
    }*/

    /**
     * 개념은_빈_행을_분리하라.
     * 수정_이후
     */
    static class Person2 {
        private String name;

        public void changeName(String name) {
            this.name = name;
        }

        public void appendPrefix(String prefix) {
            this.name = prefix.concat(this.name);
        }

    }

    /**
     * 세로_밀집도
     */
    static class Team {
        /**
         * 팀 이름
         */
        private String name;

        /**
         * 팀에 속한 멤버
         */
        private List<Member> members;
        private void addMember(Member member) {
            this.members.add(member);
        }
    }

    static class Member {
        private String name;
    }

    /**
     * 세로_밀집도
     * 수정_이후
     */
    static class Team2 {
        private String name;
        private List<Member> members;

        private void addMember(Member member) {
            this.members.add(member);
        }

    }

    static class Member2 {
        private String name;
    }

    /**
     * 수직_거리 - 변수_선언
     */
    private String readInputStream(String path) {
        try (InputStream in = new FileInputStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))
        ) {
            StringBuilder actual = new StringBuilder();
            while (br.ready()) {
                actual.append(br.readLine());
            }
            return actual.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("파일 예외");
    }

    /**
     * 수직_거리 - 인스턴스_변수
     */
    static class Car {
        private String name;
        private Long fuelEfficiency;
    }

    /**
     * 수직_거리 - 종속_함수
     */
    static class Calculator {
        private Calculator() {
        }

        public static int plus(int num1, int num2) {
            return plus(num1, num2, 0);
        }

        public static int plus(int num1, int num2, int num3) {
            return num1 + num2 + num3;
        }

    }

    /**
     * 수직_거리 - 개념적_유사성
     */
    static class Assert {
        private Assert() {
        }

        public static void isFalse(boolean condition) {
            isFalse(null, !condition);
        }

        public static void isFalse(String message, boolean condition) {
            isTrue(message, !condition);
        }

        public static void isTrue(boolean condition) {
            isTrue(condition);
        }

        public static void isTrue(String message, boolean condition) {
            if (!condition) {
                throw new IllegalStateException(message);
            }
        }

    }

    /**
     * 세로_순서
     */
    static class Assert2 {
        private Assert2() {
        }

        public static void isFalse(boolean condition) {
            isFalse(null, !condition);
        }

        public static void isFalse(String message, boolean condition) {
            isTrue(message, !condition);
        }

        public static void isTrue(boolean condition) {
            isTrue(condition);
        }

        public static void isTrue(String message, boolean condition) {
            if (!condition) {
                throw new IllegalStateException(message);
            }
        }

    }

    /**
     * 가로_형식_맞추기 - 가로_공백과_밀집도
     */
/*    static class Line {
        int count;
        int totalChars;

        public Line(int count, int totalChars) {
            this.count = count;
            this.totalChars = totalChars;
        }

        private void measureLine(String line) {
            count++;
            int lineSize = line.length();               //A.
            totalChars += lineSize;
            lineWidthHistogram.addLine(lineSize, count); //B.
            recordWidestLine(lineSize);
        }

    }*/

    static class Quadratic {
        public static double root(double a, double b, double c) {
            double determinant = determinant(a, b, c);
            return (-b + Math.sqrt(determinant)) / (2*a);                //A.
        }

        private static double determinant(double a, double b, double c) {
            return b*b - 4*a*c;         //B.
        }

    }

    /**
     * 가로_형식_맞추기 - 가로_정렬
     */
    static class Car2 {
        private String name;
        private Long fuelEfficiency;
        private Long length;
    }

    /**
     * 가로_형식_맞추기 - 들여쓰기
     */
/*
    public void changeName(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalStateException("name은 null이 될 수 없습니다.");
        }
        if (name.contains("!")) {
            throw new IllegalStateException("name에 !가 포함될 수 없습니다.");
        }
        this.name = name;
    }
*/

    /**
     * 가로_형식_맞추기 - 가짜_범위
     */
/*    public static void print() {
        while (dis.read(buf, 0, readBufferSize) != -1)
            ;
    }*/

}

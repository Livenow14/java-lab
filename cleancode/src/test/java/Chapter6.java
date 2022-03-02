import org.junit.jupiter.api.DisplayName;

import java.io.BufferedOutputStream;
import java.util.NoSuchElementException;

@DisplayName("6장. 객체와 자료 구조")
public class Chapter6 {

    /**
     * 자료_추상화-1.1
     */
    static class Point {
        public double x;
        public double y;
    }

    /**
     * 자료_추상화-1.2
     */
    interface Point2 {
        double getX();

        double getY();

        void setCartesian(double x, double y);

        double getR();

        double getTheta();

        void setPolar(double r, double theta);
    }

    /**
     * 자료_추상화-추상적인_개념으로표현-1.3
     */
    interface Vehicle {
        double getFuelTankCapacityInGallons();

        double getGallonsOfGasoline();
    }

    /**
     * 자료_추상화-추상적인_개념으로표현-1.4
     */
    interface Vehicle2 {
        double getPercentFuelRemaining();
    }

    /**
     * 자료/객체_비대칭-자료구조
     */
    static class Square {
        public Point topLeft;
        public double side;
    }

    static class Circle {
        public Point Circle;
        public double radius;
    }

    public class Geometry {
        public final double PI = 3.14159265;

        public double area(Object shape) {
            if (shape instanceof Square) {
                Square s = (Square) shape;
                return s.side * s.side;
            } else if (shape instanceof Circle) {
                Circle c = (Circle) shape;
                return PI * c.radius * c.radius;
            }
            throw new NoSuchElementException();
        }

    }

    /**
     * 자료/객체_비대칭-객체
     */
    interface Shape {
        double area();
    }

    static class Square2 implements Shape {
        private Point topLeft;
        private double side;

        @Override
        public double area() {
            return side * side;
        }

    }

    static class Circle2 implements Shape {
        public final double PI = 3.14159265;

        private double radius;
        private Point center;

        @Override
        public double area() {
            return PI * radius * radius;
        }

    }

    /**
     * 디미터_법칙
     */
/*    void someMethod() {
        final String outputDir = ctxt.getOptions().getScratchDir().getAbsoloutePath();
    }*/

    /**
     * 디미터_법칙-기차충돌
     */
/*    void someMethod2() {
        Options opts = ctxt.getOptions();
        File scratchDir = opts.getScratchDir();
        final String outputDir = scratchDir.getAbsolutePath();
    }*/

    /**
     * 디미터_법칙-구조체_감추기
     */
/*    void someMethod3() {
        BufferedOutputStream bos = ctxt.createScratchFileStream(classFiledName);
    }*/

    /**
     * 자료_전달_객체
     */
    static class Address {
        private String street;
        private String city;
        private String zip;

        public Address(String street, String city, String zip) {
            this.street = street;
            this.city = city;
            this.zip = zip;
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getZip() {
            return zip;
        }

    }

}

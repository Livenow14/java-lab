import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

@DisplayName("3장. 함수")
public class Chapter3 {

    @Test
    void 작게_만들어라() {
        //given
        Person person = new Person("김검프");
        String newName = "김프검";
        //when
        person.changeName(newName);
        //then
        assertThat(person.getName()).isEqualTo(newName);
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * 수정 전
         */
        public void changeName(String name) {
            if (!Objects.isNull(name)) {
                if (!name.contains("!")) {
                    this.name = name;
                }
            }
            throw new IllegalStateException("인수에 문제가 있습니다.");
        }

        /**
         * 수정 이후
         */
        public void changeName2(String name) {
            if (Objects.isNull(name)) {
                throw new IllegalStateException("name은 null이 될 수 없습니다.");
            }
            if (name.contains("!")) {
                throw new IllegalStateException("name에 !가 포함될 수 없습니다.");
            }
            this.name = name;
        }

        /**
         * if문, 반복문의블록은 1개로 제한한다.
         * 함수에서 들여쓰기 수준은 1단까지만 허용한다.
         */
        public void changeName3(String name) {
            validateName(name);
            this.name = name;
        }

        /**
         * 한_가지만_해라
         */
        public void changeName4(String name) {
            validateName(name);
            this.name = name;
        }

        /**
         * 함수_당_추상화_수준은_하나로
         */
        public void changeName5(String name) {
            validateName(name);
            this.name = name;
        }

        private void validateName(String name) {
            if (Objects.isNull(name)) {
                throw new IllegalStateException("name은 null이 될 수 없습니다.");
            }
            if (name.contains("!")) {
                throw new IllegalStateException("name에 !가 포함될 수 없습니다.");
            }
        }
    }

    /**
     * Switch문
     */
    enum Employee {
        COMMISSIONED,
        HOURLY
        ;
    }

   /* public Money calculatePay(Employee employee) {
        switch (employee) {
            case Employee.COMMISSIONED:
                return calculateComissionedPay(employee);
            case Employee.HOURLY:
                return calculateHourlyPay(employee);
            default:
                throw new InvalidEmployeeType(employee);
        }
    }

    *//**
     * Switch문_수정이후
     *//*
    enum EmployeeRecord {
        COMMISSIONED,
        HOURLY
        ;
    }

    public abstract class Employee {
        public abstract boolean isPayDay();
        public abstract Money calculatePay();
        public abstract void deliverPay(Money pay);
    }

    public interface EmployeeFactory {
        Employee makeEmployee(EmployeeRecord employeeRecord);
    }

    public class EmployeeFactoryImpl implements EmployeeFactory {
        @Override
        public Employee makeEmployee(EmployeeRecord employeeRecord) {
            switch (employeeRecord) {
                case EmployeeRecord.COMMISSIONED:
                    return new CommisionedEmployee(employeeRecord);
                case EmployeeRecord.HOURLY:
                    return new HourlyEmployee(employeeRecord);
                default:
                    throw new InvalidEmployeeType(employee);
            }
            return null;
        }
    }*/

}

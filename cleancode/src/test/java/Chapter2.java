import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DisplayName("2장. 의미 있는 이름")
public class Chapter2 {

    @Test
    void 의도를_분명히_밝혀라_1() {
        int d;  // 경과 시간(단위: 날짜)
    }

    @Test
    void 의도를_분명히_밝혀라_1_수정이후() {
        int elapsedTimeInDays;
        int daysSinceCreation;
        int daySinceModification;
        int fileAgeInDays;
    }

    @Test
    void 의도를_분명히_밝혀라_2() {
        List<int[]> actual = getThem();
        assertThat(actual).hasSize(1);
    }

    private List<int[]> getThem() {
        List<int[]> theList = Arrays.asList(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
        List<int[]> list1 = new ArrayList<>();
        for (int[] x : theList) {
            if (x[0] == 4) {
                list1.add(x);
            }
        }
        return list1;
    }

    @Test
    void 의도를_분명히_밝혀라_2_수정이후() {
        List<int[]> flaggedCells = getFlaggedCells();
        assertThat(flaggedCells).hasSize(1);
    }

    private static final int STATUS_VALUE = 0;
    private static final int FLAGGED = 4;

    private List<int[]> getFlaggedCells() {
        List<int[]> gameBoard = Arrays.asList(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
        List<int[]> flaggedCells = new ArrayList<>();

        for (int[] cell : gameBoard) {
            if (cell[STATUS_VALUE] == FLAGGED) {
                flaggedCells.add(cell);
            }
        }
        return flaggedCells;
    }

/*    private List<Cell> getFlaggedCellsWithClass() {
        List<int[]> gameBoard = Arrays.asList(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
        List<int[]> flaggedCells = new ArrayList<>();

        for (Cell cell : gameBoard) {
            if (cell.isFlagged()) {
                flaggedCells.add(cell);
            }
        }
        return flaggedCells;
    }*/


    @Test
    void 그릇된_정보를_피하라() {
        List<String> nameList = new ArrayList<>();
    }

    @Test
    void 그릇된_정보를_피하라_수정이후() {
        List<String> names = new ArrayList<>();
        List<String> nameGroup = new ArrayList<>();
        List<String> bunchOfAccounts = new ArrayList<>();
    }

    /**
     * 의미_있게_구분하라
     */
    private void copyChars(char[] a1, char[] a2) {
        for (int i = 0; i < a1.length; i++) {
            a2[i] = a1[i];
        }
    }

    /**
     * 의미_있게_구분하라_수정이후
     */
    private void copyChars2(char[] source, char[] destination) {
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    @Test
    void 발음하기_쉬운_이름을_사용하라() {
        LocalDateTime genymdms;
        LocalDateTime modymdms;
    }

    @Test
    void 발음하기_쉬운_이름을_사용하라_수정이후() {
        LocalDateTime generationTimestamp;
        LocalDateTime modificationTimestamp;
    }

    @Test
    void 검색하기_쉬운_이름을_사용하라() {
        List<Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> b = a.stream()
            .filter(it -> it == 4)
            .findAny();

        assertThat(b).isNotEmpty();
    }

    public static final int TARGET_NUMBER = 4;

    @Test
    void 검색하기_쉬운_이름을_사용하라_수정이후() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> selectedNumber = numbers.stream()
            .filter(it -> it == TARGET_NUMBER)
            .findAny();

        assertThat(selectedNumber).isNotEmpty();
    }

    @Test
    void 인코딩을_피하라() {
        // PhoneNumber phoneString;
    }

    @Test
    void 인코딩을_피하라_수정이후() {
        // PhoneNumber phoneNumber;
    }

    /**
     * 인코딩을 피하라
     */
    interface ShapeFactory {
    }

    static class ShapeFactoryImpl implements ShapeFactory {
    }

    static class CShapeFactory implements ShapeFactory {
    }

    @Test
    void 자신의_기억역을_자랑하지_마라() {
        int a = 1;
        int b = 2;

        b = a;
    }

    @Test
    void 자신의_기억역을_자랑하지_마라_수정이후() {
        int source = 1;
        int destination = 2;

        destination = source;
    }

    /**
     * 클래스 이름
     * Manager, Processor, Data, Info 등을 피하라
     */
    static class Customer {
    }

    static class WikiPage {
    }

    static class Account {
    }

    static class AddressParser {
    }

    /**
     * 메서드 이름
     */
    void postPayment() {
    }

    void deletePage() {
    }

    void save() {
    }

    String getAddress() {
        return "";
    }

    void setAddress() {
    }

    void isTrue() {
    }

    /**
     * 기발한 이름을 피하라
     */
    void deleteItems() {
    }

    /**
     * 한 개념에 한 단어를 사용하라
     * 말장난을 하지 마라
     * 아래와 같이 쓰지 않는 점을 유의!
     */
    int add(int num1, int num2) {
        return num1 + num2;
    }

    public static final List<Integer> INTEGERS = new ArrayList<>();

    void add(int target) {
        INTEGERS.add(target);
    }

    // 수정한다면
    void append(int target) {
        INTEGERS.add(target);
    }

    void insert(int target) {
        INTEGERS.add(target);
    }

    /**
     * 해법 영역에서 가져온 이름을 사용하라
     */
    static class AccountVisitor {
    }

    static class JobQueue {
    }

    /**
     * 문제 영역에서 가져온 이름을 사용하라
     */
    static class Seller {
    }

    /**
     * 의미 있는 맥락을 추가하라
     * 불필요한 맥락을 없애라
     */
    static class Delivery {
        private String addName;
        private String addrstreet;
        private String addrCity;
        private String addrState;
        private LocalDateTime startAt;
    }

    // 수정한다면
    static class Delivery2 {
        private Address address;
    }

    static class Address {
        private String name;
        private String street;
        private String city;
        private String state;
        private LocalDateTime startAt;
    }

}


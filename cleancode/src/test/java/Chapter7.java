import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DisplayName("7장. 오류 처리")
public class Chapter7 {

    /**
     * 오류_코드보다_예외를_사용하라
     */
/*    static class DeviceController {
        public void sendShutDown() {
            DeviceHandle handle = getHandle(DEV1);
            // 디바이스 상태를 점검한다.
            if (handle != DeviceHandle.INVALID) {
                // 레코드 필드에 디바이스 상태를 저장한다.
                retrieveDeviceRecord(handle);
                // 디바이스가 일시정지 상태가 아니라면 종료한다.
                if (record.getstatus() != DEVICE_SUSPENDED) {
                    pauseDevice(handle);
                    clearDeviceWorkQueue(handle);
                    closeDevice(handle);
                } else {
                    logger.log("Device suspended. Unable to shut down");
                }
            } else {
                logger.log("Invalid handle for: " + DEV1.toString());
            }
        }

    }*/

    /**
     * 오류_코드보다_예외를_사용하라_수정_후
     */
/*    static class DeviceController2 {
        public void sendShutDown() {
            try {
                tryToShutDown();
            } catch (DeviceShutDownError e) {
                logger.log(e);
            }
        }

        private void tryToShutDown() throws DeviceShutDownError {
            DeviceHandle handle = getHandle(DEV1);
            DeviceRecord record = retrieveDeviceRecord(handle);

            pauseDevice(handle);
            clearDeviceWorkQueue(handle);
            closeDevice(handle);
        }

        private DeviceHandle getHandle(DeviceId id) {
            ...
            throw new DeviceShutDownError("Invalid handle for: ") + id.toString());
            ...
        }

    }*/

    @Test
    void Try_Catch_Finally_문부터_작성하라() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> retrieveSection("Invalid - file"))
            .isInstanceOf(IllegalStateException.class);
    }

    public List<String> retrieveSection(String sectionName) {
        // 실제로 구현할 때까지 비어 있는 더미를 반환.
        return new ArrayList<>();
    }

    @Test
    void Try_Catch_Finally_문부터_작성하라_수정_후() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> retrieveSection2("Invalid - file"))
            .isInstanceOf(IllegalStateException.class);
    }

    public List<String> retrieveSection2(String sectionName) {
        try(FileInputStream stream = new FileInputStream(sectionName)) {
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return new ArrayList<>();
    }

/*    void 호출자를_고려해_예외_클래스를_정의하라() {
        ACMEPort port = new ACMEPort(12);
        try {
            port.open();
        } catch (DeviceResponseException e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        } catch (ATM1212UnlockedException e) {
            reportPortError(e);
            logger.log("Unlock exception", e);
        } finally {
        }
    }

    void 호출자를_고려해_예외_클래스를_정의하라-수정_후() {
        LocalPort port = new LocalPort(12);
        try {
            port.open();
        } catch (PortDeviceFailure e) {
            reportError(e);
            logger.log(e.getMessage(), e);
        } finally {

        }
    }

    static class LocalPort {
        private ACMEPort innerPort;

        public LocalPort(int portNumber) {
            this.innerPort = new ACMEPort(portNumber);
        }

        public void open() {
            try {
                innerPort.open();
            } catch (DeviceResponseException e) {
                throw new PortDeviceFailure(e);
            } catch (ATM1212UnlockedException e) {
                throw new PortDeviceFailure(e);
            }
        }
    }

    void 정상_흐름을_정의하라() {
        try {
            MealExpense expenses = expenseReportDAO.getMeals(employee.getId());
            total += expenses.getTotal();
        } catch (MealExpenseNotFound e) {
            total += getMealPerDiem();
        }
    }

    void 정상_흐름을_파악하라_수정_후() {
        MealExpense expenses = expenseReportDAO.getMeals(employee.getId());
        total += expense.getTotal();
    }

    public class PerDiemMealExpenses implements MealExpenses {
        public int getTotal() {
            // 기본값으로 일일 기본 식비를 반환한다.
        }
    }

    void null을_반환하지_마라_수정_후() {
        List<Employee> employees = getEmployees();
        for (Employee e = employees; ) {
            totalPay += e.getPay();
        }
    }

    public List<Employee> getEmployees() {
        if(..직원이 없다면 ..){
            return Collections.emptyList();
        }
    }*/
}

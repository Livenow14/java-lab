import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("8장. 경계")
public class Chapter8 {

    void 외부_코드_사용하기() {
        Map sensors = new HashMap();
        Sensor sensor = (Sensor)sensors.get("1");
    }

    void 외부_코드_사용하기_수정후1() {
        Map<String, Sensor> sensors = new HashMap<>();
        Sensor sensor = sensors.get("1");
    }

    private static class Sensor {
        private String name;

        public Sensor(String name) {
            this.name = name;
        }
    }

    /**
     * 외부 코드 사용하기 - 수정후2
     */
    private static class Sensors {
        private Map sensors = new HashMap();

        public Sensor getById(String id) {
            return (Sensor) sensors.get(id);
        }
    }

    /**
     * log4j 익히기
     */
    @Test
    void testLogCreate() {
        Logger logger = Logger.getLogger("로거");
        logger.info("hello");
    }

    @Test
    void testLogAddAppender() {
        Logger logger = Logger.getLogger("로거");
        ConsoleAppender appender = new ConsoleAppender();
        logger.addAppender(appender);
        logger.info("hello");
    }

    @Test
    void testLogAddAppender2() {
        Logger logger = Logger.getLogger("로거");
        logger.removeAllAppenders();
        logger.addAppender(new ConsoleAppender(
            new PatternLayout("%p %t %m%n"),
            ConsoleAppender.SYSTEM_OUT
        ));
        logger.info("hello");
    }

    @Test
    void testLogAddAppender3() {
        Logger logger = Logger.getLogger("로거");
        logger.removeAllAppenders();
        logger.addAppender(new ConsoleAppender(
            new PatternLayout("%p %t %m%n")
        ));
        logger.info("hello");
    }

    @Nested
    class LogTest {
        private Logger logger;

        //로거 초기화
        @BeforeEach
        public void init() {
            logger = Logger.getLogger("logger");
            logger.removeAllAppenders();
            Logger.getRootLogger().removeAllAppenders();
        }

        //기본 로거 사용
        @Test
        void basicLogger() {
            BasicConfigurator.configure();
            logger.info("basicLogger");
        }

        //Stream Appender 설정
        @Test
        void addAppenderWithStream() {
            logger.addAppender(new ConsoleAppender(
                new PatternLayout("%p %t %m%n"),
                ConsoleAppender.SYSTEM_OUT
            ));
            logger.info("addAppenderWithStream");
        }

        //단순 Appender 설정
        @Test
        void addAppenderWithoutStream() {
            logger.addAppender(new ConsoleAppender(
                new PatternLayout("%p %t %m%n")
            ));
            logger.info("addAppenderWithStream");
        }
    }

}

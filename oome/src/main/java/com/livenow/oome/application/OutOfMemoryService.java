package com.livenow.oome.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OutOfMemoryService {

    private static final List<Integer[]> lists = new ArrayList<>();

    public void javaHeapSpaceErrorWhenHeapFull() {
        log.info("할당 전");
        lists.add(new Integer[10000 * 10000]);
        log.info("할당 후");
    }

    public void javaHeapSpaceErrorWhenNotEnoughHeap() {
        log.info("인스턴스화 전");
        Integer[] integers = new Integer[10000 * 10000];
        log.info("인스턴스화 후");
    }

    @Deprecated
    public void gcOverheadLimitExceeded() {
        log.info("반복 전");
        Map m = new HashMap();
        m = System.getProperties();
        Random r = new Random();

        while (true) {
            m.put(r.nextInt(), "randomValue");
        }
    }

}

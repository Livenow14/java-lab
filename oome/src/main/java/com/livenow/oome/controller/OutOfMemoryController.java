package com.livenow.oome.controller;

import com.livenow.oome.application.OutOfMemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oome")
@RequiredArgsConstructor
public class OutOfMemoryController {

    private final OutOfMemoryService outOfMemoryService;

    /**
     * Xms: 128m
     * Xmx: 512m
     */
    @GetMapping("/java-heap-full-heap")
    public ResponseEntity<String> javaHeapSpaceExceptionWhenHeapFull() {
        outOfMemoryService.javaHeapSpaceErrorWhenHeapFull();
        return ResponseEntity.ok("ok");
    }

    /**
     * Xms: 128m
     * Xmx: 256m
     */
    @GetMapping("/java-heap-not-enough-heap")
    public ResponseEntity<String> javaHeapSpaceExceptionWhenNotEnoughHeap() {
        outOfMemoryService.javaHeapSpaceErrorWhenNotEnoughHeap();
        return ResponseEntity.ok("ok");
    }

     /**
      * cpu 사용률 98%까지 올라갈 수 잇어, 조심하길 바랍니다.
     * Xms: 128m
     * Xmx: 512m
     */
    @Deprecated
    @GetMapping("/gc-overhead-limit-exceeded")
    public ResponseEntity<String> gcOverheadLimitExceeded() {
        outOfMemoryService.gcOverheadLimitExceeded();
        return ResponseEntity.ok("ok");
    }

}

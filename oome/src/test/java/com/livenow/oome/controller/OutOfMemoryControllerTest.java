package com.livenow.oome.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("OOME 발생 케이스 테스트")
class OutOfMemoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * tasks.named('test') {
   *     useJUnitPlatform()
   *     minHeapSize = "128m"
   *     maxHeapSize = "512m"
   * }
   */
  @Test
  void java_Heap_Space_Exception_heap_area_full() throws Exception {
    //given
    //when
    //then
    for (int i = 0; i < 5; i++) {
      mockMvc.perform(get("/oome/java-heap-full-heap"))
          .andExpect(status().isOk());
    }
  }

  /**
   * tasks.named('test') {
   *     useJUnitPlatform()
   *     minHeapSize = "128m"
   *     maxHeapSize = "256m"
   * }
   */
  @Test
  void java_Heap_Space_Exception_not_enough_heap() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(get("/oome/java-heap-not-enough-heap"))
        .andExpect(status().isOk());
  }

  /**
   * cpu 사용률 98%까지 올라갈 수 잇어, 조심하길 바랍니다.
   * tasks.named('test') {
   *     useJUnitPlatform()
   *     minHeapSize = "32m"
   *     maxHeapSize = "32m"
   * }
   */
  @Disabled
  @Test
  void gc_overhead_limit_exceeded() throws Exception {
    //given
    //when
    //then
    mockMvc.perform(get("/oome/gc-overhead-limit-exceeded"))
        .andExpect(status().isOk());
  }

}

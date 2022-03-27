import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.uber.h3core.H3Core;
import com.uber.h3core.util.GeoCoord;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("H3 학습 테스트")
public class H3Test {

  /**
   * 석촌호수 인근 위도/경도, 해상도(높을수록 자세히 표현됨)
   */
  private static final double LAT = 37.506594;
  private static final double LNG = 127.097440;
  private static final int RESOLUTION = 12;
  private static H3Core h3Core;

  static {
    try {
      h3Core = H3Core.newInstance();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @Nested
  @DisplayName("Indexing functions")
  class Indexingfunctions {

    /**
     * 위/경도, 해상도를 통해 H3Address를 반환받는다.
     */
    @Test
    void getToH3() {
      //given
      //when
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      //then
      System.out.println("h3 = " + h3);
    }

    /**
     * 위/경도, 변경된 해상도를 통해 H3Address를 반환받는다.
     */
    @Test
    void getToH3ResolutionChange() {
      //given
      //when
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION - 2);
      //then
      System.out.println("h3 = " + h3);
    }

    /**
     * h3Address를 위/경도로 변경한다.
     */
    @Test
    void h3ToGeo() {
      //when
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      //when
      GeoCoord geoCoord = h3Core.h3ToGeo(h3);
      //then
      System.out.println(String.format("[%f, %f]", geoCoord.lat, geoCoord.lng));
    }

    /**
     * h3Address에 포함된 위/경도를 나열한다 (6각형이기에, 6개의 위/경도 나옴)
     */
    @Test
    void h3ToGeoBoundary() {
      //given
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      //when
      List<GeoCoord> geoCoords = h3Core.h3ToGeoBoundary(h3);
      //then
      for (GeoCoord geoCoord : geoCoords) {
        System.out.println(String.format("[%f %f]", geoCoord.lat, geoCoord.lng));
      }
    }

  }

  @Nested
  @DisplayName("Index inspection functions")
  class IndexInspectionFunctions {

    /**
     * h3Address의 해상도를 구한다.
     */
    @Test
    void h3GetResolution() {
      //given
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      //when
      int resolution = h3Core.h3GetResolution(h3);
      //then
      System.out.println("resolution = " + resolution);
    }

    /**
     * 유효한 h3Address인지 확인한다.
     */
    @Test
    void h3IsValid() {
      //given
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      //when
      boolean actual = h3Core.h3IsValid(h3);
      //then
      assertThat(actual).isTrue();
    }

    /**
     * 5각형 h3Address인지 확인한다.
     */
    @Test
    void h3IsPentagon() {
      //given
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      //when
      boolean actual = h3Core.h3IsPentagon(h3);
      //then
      assertThat(actual).isFalse();
    }
  }

  @Nested
  @DisplayName("Hierarchical grid functions")
  class HierarchicalGridFunctions {

    /**
     * 해당 h3Address를 포함하는 상위(더 넓은) resolution 단계의 h3Address를 반환합니다.
     */
    @Test
    void h3ToParent() {
      //given
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      int setUpResolution = RESOLUTION - 2;
      //when
      String parentH3 = h3Core.h3ToParentAddress(h3, setUpResolution);
      //then
      System.out.println(parentH3);
    }

    /**
     * 해당 h3Address를 포함하는 하위(더 좁은) resolution 단계의 h3Address들를 반환합니다.
     */
    @Test
    void h3ToChildren() {
      //given
      String h3 = geoToH3Address(LAT, LNG, RESOLUTION);
      int setUpResolution = RESOLUTION + 2;
      //when
      List<String> childrenH3s = h3Core.h3ToChildren(h3, setUpResolution);
      //then
      for (String childrenH3 : childrenH3s) {
        System.out.println(childrenH3);
      }
    }

    /**
     * 자정한 h3Address 컬렉션들이 제공하는 지역의 압축된 h3Address 들을 반환합니다.
     * 해당 지역의 동일한 resolution의 h3Address들이 지역에 들어맞게 여러 resolution의 h3Address로 압축되서 바뀜,
     * ex) polyFill(동일한 resoultion들의 모임) -> compactAddress(압축된 resoultion의 모임)
     *
     * 참고, polyfill시 설정한 resolution이 압축 후 가장 낮은 resolution으로 잡힘
     * ex) SeokchonLake_Compact_res12
     */
    @Test
    void compactAddress() {
      //given
      List<String> h3Addresses = polyFill(GeoJsonFixture.석촌호수(), RESOLUTION + 2);
      //when
      List<String> compactAddressGroup = h3Core.compactAddress(h3Addresses);
      //then
      for (String compactAddress : compactAddressGroup) {
        System.out.println(compactAddress);
      }
    }

    /**
     * 압축했던 H3Address를 다시 동일한 Resolution의 H3Address들로 변환 (polyfill시의 H3Address로 변환됨)
     */
    @Test
    void unCompactAddress() {
      //given
      List<String> compactAddressGroup = h3Core.compactAddress(polyFill(GeoJsonFixture.석촌호수(), RESOLUTION));
      //when
      List<String> unCompactAddressGroup = h3Core.uncompactAddress(compactAddressGroup, RESOLUTION);
      //then
      for (String unCompactAddress : unCompactAddressGroup) {
        System.out.println(unCompactAddress);
      }
    }
  }

  @Nested
  @DisplayName("Region functions")
  class RegionFunctions {

    /**
     * GeoJson으로 표현된 지역에 포함된 H3Address를 전부 반홥합니다.
     * 설정한 resolution에 따라 표현되지 않는 구역도 생길 수 있습니다 (기본 12로 잡음)
     * ex)SeokchonLake_PolyFill_res10.csv, SeokchonLake_PolyFill_res12.csv 첨고.
     */
    @Test
    void polyfill() {
      //given
      GeoJson geoJson = GeoJsonFixture.석촌호수();
      //when
      List<String> h3Addresses = polyFill(geoJson, RESOLUTION);
      //then
      for (String h3Address : h3Addresses) {
        System.out.println(h3Address);
      }
    }
  }

  private String geoToH3Address(double lat, double lng, int resolution) {
    String h3Address = h3Core.geoToH3Address(lat, lng, resolution);
    System.out.println("[Base h3Address] = " + h3Address);
    return h3Address;
  }

  /**
   * GeoJson으로 표현된 지역을 동일한 resoultion을 같는 h3Address로 표현
   */
  private List<String> polyFill(GeoJson geoJson, int resolution) {
    List<GeoCoord> geoCoords = geoJson.getGeoFencePoints()
        .stream()
        .map(doubles -> new GeoCoord(doubles.get(1), doubles.get(0)))
        .collect(Collectors.toList());
    return h3Core.polyfillAddress(geoCoords, null, resolution);
  }

}

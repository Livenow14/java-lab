import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

@DisplayName("JTS 학습 테스트")
public class JTSTest {

  private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();
  //삭천호수 서호 꼭지점 위도경도
  private static final Coordinate[] westSeokchonLakeCoordinates;
  //석촌호수 동호 꼭지점 위도경도
  private static final Coordinate[] eastSeokchonLakeCoordinates;

  static {
    westSeokchonLakeCoordinates = new Coordinate[]{
        new Coordinate(37.50981094938025, 127.09763288497925),
        new Coordinate(37.506593858353796, 127.09743976593018),
        new Coordinate(37.506440660084685, 127.09868431091309),
        new Coordinate(37.50914711655335, 127.10458517074584),
        new Coordinate(37.511461995167984, 127.10241794586182),
        new Coordinate(37.50981094938025, 127.0989418029785),
        new Coordinate(37.50981094938025, 127.09763288497925)
    };
    eastSeokchonLakeCoordinates = new Coordinate[]{
        new Coordinate(37.51069605063256, 127.1011734008789),
        new Coordinate(37.50821093202033, 127.10304021835326),
        new Coordinate(37.51204070360412, 127.10797548294067),
        new Coordinate(37.51357255721701, 127.10728883743286),
        new Coordinate(37.51069605063256, 127.1011734008789)
    };
  }

  @Test
  void intersectionTest() {
    //given
    Polygon westLakePolygon = GEOMETRY_FACTORY.createPolygon(westSeokchonLakeCoordinates);
    Polygon eastLakePolygon = GEOMETRY_FACTORY.createPolygon(eastSeokchonLakeCoordinates);
    //when
    Geometry intersection = westLakePolygon.intersection(eastLakePolygon);
    //then
    결과로_나온_위도와_경도를_출력한다(intersection);
  }

  @Test
  void differenceTest() {
    //given
    Polygon westLakePolygon = GEOMETRY_FACTORY.createPolygon(westSeokchonLakeCoordinates);
    Polygon eastLakePolygon = GEOMETRY_FACTORY.createPolygon(eastSeokchonLakeCoordinates);
    //when
    Geometry intersection = westLakePolygon.difference(eastLakePolygon);
    //then
    결과로_나온_위도와_경도를_출력한다(intersection);
  }

  @Test
  void symDifferenceTest() {
    //given
    Polygon westLakePolygon = GEOMETRY_FACTORY.createPolygon(westSeokchonLakeCoordinates);
    Polygon eastLakePolygon = GEOMETRY_FACTORY.createPolygon(eastSeokchonLakeCoordinates);
    //when
    Geometry intersection = westLakePolygon.symDifference(eastLakePolygon);
    //then
    결과로_나온_위도와_경도를_출력한다(intersection);
  }

  @Test
  void unionTest() {
    //given
    Polygon westLakePolygon = GEOMETRY_FACTORY.createPolygon(westSeokchonLakeCoordinates);
    Polygon eastLakePolygon = GEOMETRY_FACTORY.createPolygon(eastSeokchonLakeCoordinates);
    //when
    Geometry intersection = westLakePolygon.union(eastLakePolygon);
    //then
    결과로_나온_위도와_경도를_출력한다(intersection);
  }

  private void 결과로_나온_위도와_경도를_출력한다(Geometry intersection) {
    String collect = Arrays.stream(intersection.getCoordinates())
        .map(iter -> String.format("[%f, %f]", iter.y, iter.x))
        .collect(Collectors.joining(", \n"));
    System.out.println(collect);
  }
}

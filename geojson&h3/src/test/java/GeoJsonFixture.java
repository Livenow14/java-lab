import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoJsonFixture {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static GeoJson 석촌호수() {
    return toGeoJson(석촌호수_StringGeoJson());
  }

  public static GeoJson toGeoJson(String geoJson) {
    try {
      return OBJECT_MAPPER.readValue(geoJson, GeoJson.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("직렬화 오류");
    }
  }

  private static String 석촌호수_StringGeoJson() {
    return """
        {
            "type": "Polygon",
            "coordinates": [
                [
                   [127.097633, 37.509811],
                           [127.097440, 37.506594],
                           [127.098684, 37.506441],
                           [127.102913, 37.508380],
                           [127.103040, 37.508211],
                           [127.103757, 37.508767],
                           [127.104585, 37.509147],
                           [127.104443, 37.509299],
                           [127.107975, 37.512041],
                           [127.107289, 37.513573],
                           [127.102535, 37.511337],
                           [127.102418, 37.511462],
                           [127.098942, 37.509811],
                           [127.097633, 37.509811]
                ]
            ]
        }
        """;

  }
}

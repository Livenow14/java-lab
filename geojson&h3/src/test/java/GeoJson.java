import java.util.List;

public class GeoJson {

  private String type;
  private List<List<List<Double>>> coordinates;

  public GeoJson() {
  }

  public GeoJson(String type, List<List<List<Double>>> coordinates) {
    this.type = type;
    this.coordinates = coordinates;
  }

  public String getType() {
    return type;
  }

  public List<List<List<Double>>> getCoordinates() {
    return coordinates;
  }

  public List<List<Double>> getGeoFencePoints() {
    return coordinates.get(0);
  }
}

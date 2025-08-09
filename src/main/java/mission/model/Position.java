package mission.model;

public class Position {
    private int place_id;
    private double lat;
    private double lng;

    public Position(int place_id, double lat, double lng) {
        this.place_id = place_id;
        this.lat = lat;
        this.lng = lng;
    }

    public int getPlace_id() {
        return place_id;
    }
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }
}



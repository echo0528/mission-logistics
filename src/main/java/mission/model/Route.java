package mission.model;

public class Route {
    private int place_id_1;
    private int place_id_2;
    private String time;

    public Route(int id_1, int id_2, String time) {
        this.place_id_1 = id_1;
        this.place_id_2 = id_2;
        this.time = time;
    }

    public int getPlace_id_1() {
        return place_id_1;
    }

    public int getPlace_id_2() {
        return place_id_2;
    }

    public String getTime() {
        return time;
    }


}

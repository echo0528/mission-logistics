package mission.repository;

import mission.model.Place;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceRepository {
    private final Map<Integer, Place> placeMap = new HashMap<>();

    public PlaceRepository() {
        loadPlacesFromCSV();
    }

    private void loadPlacesFromCSV() {
        Path path = Paths.get("src/main/resources/place.csv");

        try{
            boolean header = true;
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {

                if(header){
                    header = false;
                    continue;
                }

                String[] parts = line.split(",");

                if(parts.length != 3) continue;

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String address = parts[2].trim();

                Place place = new Place(id, name, address);
                placeMap.put(id, place);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Integer findIdByName(String name) {
        for (Place place : placeMap.values()) {
            if (place.getName().equals(name)) return place.getId();
        }
        throw new IllegalArgumentException(
                String.format(name+ "을(를) 이름으로 갖는 장소는 존재하지 않습니다.")
        );
    }
}

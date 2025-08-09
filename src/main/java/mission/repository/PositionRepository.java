package mission.repository;

import mission.model.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionRepository {
    private final Map<Integer, Position> positionMap = new HashMap<>();

    public PositionRepository(){
        loadPositionsFromCSV();
    }

    private void loadPositionsFromCSV() {
        Path path = Paths.get("src/main/resources/position.csv");

        try{
            List<String> lines = Files.readAllLines(path);
            boolean header = true;
            for (String line : lines) {
                if(header){
                    header = false;
                    continue;
                }
                String[] parts = line.split(",");

                if(parts.length != 3) continue;

                int place_id = Integer.parseInt(parts[0].trim());
                double lat = Double.parseDouble(parts[1].trim());
                double lng = Double.parseDouble(parts[2].trim());

                Position position = new Position(place_id,lat,lng);
                positionMap.put(place_id, position);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    public Position findPositionById(int id) {
        Position position = positionMap.get(id);
        if(position == null){
            throw new IllegalArgumentException(id + "에 해당하는 위치 정보가 없습니다.");
        }
        return position;
    }
}


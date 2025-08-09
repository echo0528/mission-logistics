package mission.repository;

import mission.model.Route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteRepository {
    private final Map<Integer, Map<Integer, Integer>> routeMap = new HashMap<>();

    public RouteRepository() {
        loadRoutesFromCSV();
    }

    private void loadRoutesFromCSV() {
        Path path = Paths.get("src/main/resources/route.csv");

        try{
            List<String> lines = Files.readAllLines(path);
            boolean header = true;
            for (String line : lines) {
                if(header)  {
                    header = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length !=3) continue;

                int place_id_1 = Integer.parseInt(parts[0].trim());
                int place_id_2 = Integer.parseInt(parts[1].trim());
                String timeStr = parts[2].trim();

                int timeMinutes = parseTimeToMinutes(timeStr);

                routeMap.computeIfAbsent(place_id_1, k -> new HashMap<>());
                routeMap.computeIfAbsent(place_id_2, k -> new HashMap<>());

            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    private int parseTimeToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0].trim());
        int minutes = Integer.parseInt(parts[1].trim());
        return hours * 60 + minutes;
    }

    public Integer getTimeBetween(int place_id_1, int place_id_2) {
        Map<Integer,Integer> innerMap = routeMap.get(place_id_1);
        if (innerMap == null) return null;
        return innerMap.get(place_id_2);
    }

    public String formatMinutesToTimeString(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}

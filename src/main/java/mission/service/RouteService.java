package mission.service;

import mission.model.Position;
import mission.repository.PlaceRepository;
import mission.repository.PositionRepository;

public class RouteService {
    private final PlaceRepository placeRepository;
    private final PositionRepository positionRepository;

    public RouteService(PlaceRepository placeRepository, PositionRepository positionRepository) {
        this.placeRepository = placeRepository;
        this.positionRepository = positionRepository;
    }

    public String calculateTime(String fromName, String toName) {
        //name으로 id 찾기
        int fromId = placeRepository.findIdByName(fromName);
        int toId = placeRepository.findIdByName(toName);

        //id로 position 찾기
        Position a = positionRepository.findPositionById(fromId);
        Position b = positionRepository.findPositionById(toId);

        //거리 계산
        double km = calculateDistance(a.getLat(),a.getLng(),b.getLat(),b.getLng());

        //시간 정리
        double hours = km/60.0;
        int minutes = (int) Math.round(hours*60);

        return TimeMinute(minutes);
    }

    private double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double R = 6378;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lng2 - lng1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R*c;
    }

    private static String TimeMinute(int minutes) {
        int hours = minutes / 60;
        int min = minutes % 60;
        return String.format("%02d시간 %02d분", hours, min);
    }
}

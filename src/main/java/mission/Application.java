package mission;

import mission.repository.PlaceRepository;
import mission.repository.PositionRepository;
import mission.repository.RouteRepository;
import mission.service.RouteService;
import mission.view.InputView;
import mission.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PlaceRepository placeRepository = new PlaceRepository();
        PositionRepository positionRepository = new PositionRepository();

        RouteService routeService = new RouteService(placeRepository, positionRepository);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String from = inputView.getStartpointInput();
        String to = inputView.getEndpointInput();

        String result = routeService.calculateTime(from,to);
        outputView.printResult(result);
    }
}
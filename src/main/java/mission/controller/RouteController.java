package mission.controller;

import mission.service.RouteService;
import mission.view.InputView;
import mission.view.OutputView;

public class RouteController {
    private final RouteService routeService;
    private final InputView inputView;
    private final OutputView outputView;

    public RouteController(RouteService routeService, InputView inputView, OutputView outputView) {
        this.routeService = routeService;
        this.inputView = inputView;
        this.outputView = outputView;

    }

    public void run(){
        String from = inputView.getStartpointInput();
        String to = inputView.getEndpointInput();

        String result = routeService.calculateTime(from,to);
        outputView.printResult(result);
    }
}

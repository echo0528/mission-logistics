package mission.view;

import api.Console;
public class InputView {
    public String getStartpointInput(){
        System.out.println("출발지를 입력해주세요.");
        return Console.readLine();
    }

    public String getEndpointInput(){
        System.out.println("도착지를 입력해주세요.");
        return Console.readLine();
    }
}

package mission.view;

import api.Console;

public class OutputView {
    public void printResult(String result){
        System.out.println("이동시간은 "+ result +"으로 예측됩니다.");
    }

    public void printError(String error){
        System.out.println(error);
        System.out.println();
    }
}

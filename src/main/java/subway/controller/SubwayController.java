package subway.controller;

import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.List;

public class SubwayController {
    private final InputView inputView;
    private final OutputView outputView;
    private final SubwayService subwayService;

    public SubwayController(InputView inputView, OutputView outputView,
                            SubwayService subwayService){
        this.inputView=inputView;
        this.outputView=outputView;
        this.subwayService=subwayService;
    }

    public void run(){

        while(true){
            String start=inputView.start();
            if(start.equals("Q")) break;

            String func=inputView.lookUpPath();
            runFunction(func);
        }
    }

    private void runFunction(String s){
        if(s.equals("1")){

            String startStation=inputView.inputStartStation();
            String endStation=inputView.inputEndStation();

            List<String> path= subwayService.distancePath(startStation, endStation);
            List<Integer> info=subwayService.calculate(path);

            outputView.outputPath(info, path);
            return;
        }

        if(s.equals("2")){

            String startStation=inputView.inputStartStation();
            String endStation=inputView.inputEndStation();

            List<String> path=subwayService.timePath(startStation, endStation);
            List<Integer> info=subwayService.calculate(path);

            outputView.outputPath(info, path);
            return;
        }

        if(s.equals("B")) return;
    }
}

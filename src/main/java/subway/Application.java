package subway;

import subway.config.DataLoader;
import subway.controller.SubwayController;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        DataLoader dataLoader=new DataLoader();
        dataLoader.loadData();
        SubwayService subwayService = new SubwayService(new StationRepository(), new LineRepository());
        SubwayController subwayController=new SubwayController(inputView, outputView, subwayService);

        subwayController.run();

    }
}

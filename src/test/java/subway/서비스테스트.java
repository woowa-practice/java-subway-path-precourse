package subway;

import org.junit.jupiter.api.Test;
import subway.config.DataLoader;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.service.SubwayService;

public class 서비스테스트 {
    private SubwayService service;
    private StationRepository stationRepository;
    private LineRepository lineRepository;
    private DataLoader dataLoader;

    @Test
    void 서비스테스트(){
        stationRepository=new StationRepository();
        lineRepository=new LineRepository();
        dataLoader=new DataLoader();

        service=new SubwayService(stationRepository, lineRepository);

        service.distancePath("교대", "양재");
        System.out.println(service.calculate(service.distancePath("교대", "양재")));
        service.timePath("교대", "양재");
        System.out.println(service.calculate(service.timePath("교대", "양재")));
    }
}

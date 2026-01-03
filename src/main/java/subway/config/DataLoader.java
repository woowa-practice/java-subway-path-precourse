package subway.config;

import subway.domain.Line;
import subway.domain.Section;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class DataLoader {

    public void loadData(){
        Station kyodae=new Station("교대역"); StationRepository.addStation(kyodae);
        Station gangnam=new Station("강남역"); StationRepository.addStation(gangnam);
        Station yeoksam=new Station("역삼역"); StationRepository.addStation(yeoksam);
        Station namboo=new Station("남부터미널역"); StationRepository.addStation(namboo);
        Station yangjae=new Station("양재역"); StationRepository.addStation(yangjae);
        Station maebong=new Station("매봉역"); StationRepository.addStation(maebong);
        Station forest=new Station("양재시민의숲역"); StationRepository.addStation(forest);

        Line lineTwo=new Line("2호선");
        lineTwo.addSection(new Section(kyodae, gangnam, 2, 3));
        lineTwo.addSection(new Section(gangnam, yeoksam, 2, 3));
        Line lineThree=new Line("3호선");
        lineThree.addSection(new Section(kyodae, namboo, 3, 2));
        lineThree.addSection(new Section(namboo, yangjae, 6, 5));
        lineThree.addSection(new Section(yangjae, maebong, 1, 1));
        Line lineNew=new Line("신분당선");
        lineNew.addSection(new Section(gangnam, yangjae, 2, 8));
        lineNew.addSection(new Section(yangjae, forest, 10, 3));

        LineRepository.addLine(lineTwo);
        LineRepository.addLine(lineThree);
        LineRepository.addLine(lineNew);
    }
}

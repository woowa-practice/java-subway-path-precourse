package subway.service;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.config.DataLoader;
import subway.domain.Line;
import subway.domain.Section;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

public class SubwayService {
    private final StationRepository stationRepository;
    private final LineRepository lineRepository;

    public SubwayService(StationRepository stationRepository, LineRepository lineRepository){
        this.stationRepository=stationRepository;
        this.lineRepository=lineRepository;
    }

    public List<String> distancePath(String start, String end){
        WeightedMultigraph<String, DefaultWeightedEdge> graph=
                new WeightedMultigraph<>(DefaultWeightedEdge.class);
        distanceGraph(graph);

        DijkstraShortestPath dijkstra=new DijkstraShortestPath(graph);
        List<String> shortestPath=dijkstra.getPath(start, end).getVertexList();

        return shortestPath;
    }

    public List<String> timePath(String start, String end){
        WeightedMultigraph<String, DefaultWeightedEdge> graph=
                new WeightedMultigraph<>(DefaultWeightedEdge.class);
        timeGraph(graph);

        DijkstraShortestPath dijkstra=new DijkstraShortestPath(graph);
        List<String> shortestPath=dijkstra.getPath(start, end).getVertexList();

        return shortestPath;
    }

    public List<Integer> calculate(List<String> path){
        List<Integer> totalInfo=new ArrayList<>();
        Integer totalDistance=0;
        Integer totalTime=0;

        for(int i=0; i<path.size()-1; i++){
            String start=path.get(i);
            String end=path.get(i+1);

            Section section=findSection(start, end);

            totalDistance+=section.getDistance();
            totalTime+=section.getTime();
        }

        totalInfo.add(totalDistance);
        totalInfo.add(totalTime);
        return totalInfo;
    }

    private void prepareGraph(WeightedMultigraph<String, DefaultWeightedEdge> graph){

        List<Station> stationList=StationRepository.stations();

        for (Station station : stationList) {
            graph.addVertex(station.getName());
        }

    }

    private void distanceGraph(WeightedMultigraph<String, DefaultWeightedEdge> graph){
        prepareGraph(graph);

        List<Line> lineList=LineRepository.lines();

        graph.setEdgeWeight(graph.addEdge(
                lineList.get(0).getSections().get(0).getStartStation().getName(),
                lineList.get(0).getSections().get(0).getEndStation().getName()),
                lineList.get(0).getSections().get(0).getDistance());
        graph.setEdgeWeight(graph.addEdge(
                lineList.get(0).getSections().get(1).getStartStation().getName(),
                lineList.get(0).getSections().get(1).getEndStation().getName()),
                lineList.get(0).getSections().get(1).getDistance());

        graph.setEdgeWeight(graph.addEdge(
                lineList.get(1).getSections().get(0).getStartStation().getName(),
                lineList.get(1).getSections().get(0).getEndStation().getName()),
                lineList.get(1).getSections().get(0).getDistance());
        graph.setEdgeWeight(graph.addEdge(
                lineList.get(1).getSections().get(1).getStartStation().getName(),
                lineList.get(1).getSections().get(1).getEndStation().getName()),
                lineList.get(1).getSections().get(1).getDistance());
        graph.setEdgeWeight(graph.addEdge(
                lineList.get(1).getSections().get(2).getStartStation().getName(),
                lineList.get(1).getSections().get(2).getEndStation().getName()),
                lineList.get(1).getSections().get(2).getDistance());

        graph.setEdgeWeight(graph.addEdge(
                lineList.get(2).getSections().get(0).getStartStation().getName(),
                lineList.get(2).getSections().get(0).getEndStation().getName()),
                lineList.get(2).getSections().get(0).getDistance());
        graph.setEdgeWeight(graph.addEdge(
                lineList.get(2).getSections().get(1).getStartStation().getName(),
                lineList.get(2).getSections().get(1).getEndStation().getName()),
                lineList.get(2).getSections().get(1).getDistance());

    }

    private void timeGraph(WeightedMultigraph<String, DefaultWeightedEdge> graph){
        prepareGraph(graph);

        List<Line> lineList=LineRepository.lines();

        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(0).getSections().get(0).getStartStation().getName(),
                        lineList.get(0).getSections().get(0).getEndStation().getName()),
                lineList.get(0).getSections().get(0).getTime());
        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(0).getSections().get(1).getStartStation().getName(),
                        lineList.get(0).getSections().get(1).getEndStation().getName()),
                lineList.get(0).getSections().get(1).getTime());

        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(1).getSections().get(0).getStartStation().getName(),
                        lineList.get(1).getSections().get(0).getEndStation().getName()),
                lineList.get(1).getSections().get(0).getTime());
        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(1).getSections().get(1).getStartStation().getName(),
                        lineList.get(1).getSections().get(1).getEndStation().getName()),
                lineList.get(1).getSections().get(1).getTime());
        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(1).getSections().get(2).getStartStation().getName(),
                        lineList.get(1).getSections().get(2).getEndStation().getName()),
                lineList.get(1).getSections().get(2).getTime());

        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(2).getSections().get(0).getStartStation().getName(),
                        lineList.get(2).getSections().get(0).getEndStation().getName()),
                lineList.get(2).getSections().get(0).getTime());
        graph.setEdgeWeight(graph.addEdge(
                        lineList.get(2).getSections().get(1).getStartStation().getName(),
                        lineList.get(2).getSections().get(1).getEndStation().getName()),
                lineList.get(2).getSections().get(1).getTime());

    }

    private Section findSection(String startStation, String endStation){
        List<Line> lineList=LineRepository.lines();

        for (Line line : lineList) {
            for (Section section : line.getSections()) {
                String start=section.getStartStation().getName();
                String end=section.getEndStation().getName();

                if(start.equals(startStation)&&end.equals(endStation)){
                    return section;
                }

                if(start.equals(endStation)&&end.equals(startStation)){
                    return section;
                }
            }
        }

        throw new IllegalArgumentException("[ERROR] 구간을 찾을 수 없습니다.");
    }
}

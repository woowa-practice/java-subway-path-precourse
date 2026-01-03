package subway.domain;

public class Section {
    private final Station startStation;
    private final Station endStation;
    private final Integer distance;
    private final Integer time;

    public Section(Station startStation, Station endStation, Integer distance, Integer time){
        this.startStation=startStation;
        this.endStation=endStation;
        this.distance=distance;
        this.time=time;
    }

    public Station getStartStation(){
        return startStation;
    }

    public Station getEndStation(){
        return endStation;
    }

    public Integer getDistance(){
        return distance;
    }

    public Integer getTime(){
        return time;
    }
}

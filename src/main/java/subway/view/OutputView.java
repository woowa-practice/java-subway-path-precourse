package subway.view;

import java.util.List;

public class OutputView {

    public void outputPath(List<Integer> info, List<String> station){
        System.out.println("[INFO] ---\n" +
                "[INFO] 총 거리: "+info.get(0)+"km\n" +
                "[INFO] 총 소요 시간: "+info.get(1)+"분\n" +
                "[INFO] ---");
        for (String s : station) {
            System.out.println("[INFO] "+s);
        }
        System.out.println();
    }
}

package subway.view;

import java.util.List;

public class OutputView {

    public void outputPath(Integer path, Integer time, List<String> station){
        System.out.println("[INFO] ---\n" +
                "[INFO] 총 거리: "+path+"\n" +
                "[INFO] 총 소요 시간: "+time+"\n" +
                "[INFO] ---");
        for (String s : station) {
            System.out.println("[INFO]" +s);
        }
    }
}

package subway.view;

public class InputView {

    public void start(){
        System.out.println("## 메인 화면\n" +
                "1. 경로 조회\n" +
                "Q. 종료\n" +
                "\n" +
                "## 원하는 기능을 선택하세요.");
    }

    public void lookUpPath(){
        System.out.println("## 경로 기준\n" +
                "1. 최단 거리\n" +
                "2. 최소 시간 \n" +
                "B. 돌아가기\n" +
                "\n" +
                "## 원하는 기능을 선택하세요.");
    }

    public void inputStartStation(){
        System.out.println("## 출발역을 입력하세요.");
    }

    public void inputEndStation(){
        System.out.println("## 도착역을 입력하세요.");
    }
}

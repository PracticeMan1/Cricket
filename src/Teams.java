import java.util.Arrays;
import java.util.HashMap;

public class Teams {
    int wickets;
    int runs;
    int balls;
    String name;
    int matchPlayed;
    HashMap<Integer,HashMap<Float,String>> teamDetailedHistory = new HashMap<Integer,HashMap<Float,String>>();
    HashMap<Integer, int[]> matchHistory = new HashMap<Integer, int[]>();
    int matchesWon;
    int totalRuns;
    int totalBalls;
    public Teams(String name, int numberOfPlayers) {
        int[] Players = new int[numberOfPlayers];
        this.name = name;
        this.runs = 0;
        this.balls = 0;
        this.wickets = 0;

    }

    public void display_score() {
        System.out.println("-------------------------------------------------");
        System.out.println("TEAM: " + this.name);
        System.out.println("        BALLS PLAYED: " + this.balls);
        System.out.println("        RUNS SCORED: " +  this.runs);
        System.out.println("        WICKETS LOST: " + this.wickets);
        System.out.println("-------------------------------------------------");

    }

    public int getWickets() {
        return this.wickets;
    }

    public int getRuns() {
        return this.runs;
    }

    public int getBalls() {
        return this.balls;
    }

    public void reset(){
        int[] dataArr = {getRuns(),getBalls(),getWickets()};
        matchHistory.put(this.matchPlayed,dataArr );
        totalRuns+= runs;
        totalBalls+= balls;
        this.runs = 0;
        this.balls = 0;
        this.wickets = 0;
    }

    public int getTotalRuns(){
        return totalRuns;
    }
    public int getTotalBalls(){
        return totalBalls;
    }
}

import java.util.HashMap;

public class Player {
    String playerName;
    int playerPlayingStyle; // 0 --> Bowler || 1 --> Batter || 2 --> All Rounder
    int playerRun;
    int playerTotalRuns;

    Teams team;
    HashMap<Integer,int[]> playerPerformanceHistory;

    public Player(String name, int playerPlayingStyle){
        this.playerName = name;
        this.playerPlayingStyle = playerPlayingStyle;
    }

    public void saveAndReset(){
        this.playerTotalRuns += this.playerRun;
        this.playerRun = 0;
    }
    @Override
    public String toString() {
        return playerName;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Teams {
    String teamName;
    int matchesWon;
    int matchesPlayed;
    HashMap<Integer, Player> playersMap = new HashMap<>();
    ArrayList<Integer> bowlersIDs = new ArrayList<>();
    HashMap<Integer,int[]> teamMatchHistory = new HashMap<>();
    Scanner scan = new Scanner(System.in);


    public Teams(String name, int totalPlayers){
        this.teamName = name;
        this.createTeam(totalPlayers);
    }

    private void createTeam(int totalPlayers){
        for(int i = 1; i<= totalPlayers; i++){
            System.out.print("Enter Player"+ i+ " name: ");
            String name = scan.next();
            System.out.print("Enter Player"+i+ " playing Style: Batter (0) Bowler (1) All-Rounder(2) ");
            int playingStyle = scan.nextInt();
            if (playingStyle ==0 || playingStyle ==2){
                bowlersIDs.add(i);
            } else if (playingStyle <0 || playingStyle >2) {
                System.out.println("OUT OF BOUNDS");
            }
            Player p = new Player(name, playingStyle);
            this.playersMap.put(i,p);
        }
    }

    public Player getPlayerByID(int id){
        return playersMap.get(id);
    }
}

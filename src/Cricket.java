
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;


public class Cricket{
    private LinkedHashMap<Float,String> matchHistory = new LinkedHashMap<Float,String>();
    private static final char[] possiblePlay = {'0','1','2','3','4','5','6','W'};
    private byte maxPlayers= 11;
    private float max_over = 6f;
    private Teams battingTeam;
    private Teams bowlingTeam;
    private static Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    public void setMaxOver (int maxOver){
        this.max_over = maxOver;
    }
    public void setBattingTeam(Teams T){
        this.battingTeam = T;
    }
    public  void setBowlingTeam(Teams T){
        this.bowlingTeam = T;
    }

    public void setMaxPlayers(byte numberOfPlayers){
        this.maxPlayers = numberOfPlayers;
    }

    public void seriesMatch(int numberOfMatches,Teams T1, Teams T2){
        while(numberOfMatches>0){
            toss(T1,T2);
            T1.reset();
            T2.reset();
            startGame(battingTeam);
            numberOfMatches--;
        }
        seriesResult(T1,T2);
    }
    private void startGame(Teams T){
        float currentOver = 0f;
        match(T,currentOver);
    }
    private void toss(Teams T1, Teams T2){
        System.out.println("\n");
        System.out.println("Begin the TOSS: ");
        System.out.println("Choose Heads (0) or Tails (1)");
        int calledToss = scan.nextInt();
        Random rand = new Random();
        System.out.println("TOSSING COIN");
        int actualToss = rand.nextInt(0,2);
        int optedPlay;
        if (actualToss == calledToss) {
            System.out.println("You have WON the TOSS...");
            System.out.println("Choose to BAT (1) or BOWL (2) first....");
            optedPlay = scan.nextByte();
        }
        else{
            System.out.println("You have LOST the TOSS");
            optedPlay = rand.nextInt(1,3);
        }
        if (optedPlay == 1){
            setBattingTeam(T1);
            setBowlingTeam(T2);
        }
        else {
            setBowlingTeam(T1);
            setBattingTeam(T2);
        }
    }
    private void match(Teams T,float currentOver){

        if (currentOver == this.max_over){
            resetScore(T);
            return;
        }
        char x = Cricket.possiblePlay[rand.nextInt(0,8)];
        updateScore(T,currentOver,x);
    }

    private void updateScore(Teams T,float currentOver,char x) {
        if (T.balls % 6 == 0) {
            System.out.println("Over : " + currentOver);
        }
        System.out.println("\t\t"+(T.balls%6 + 1)+" : "+x);


        if (x == 'W') {
            T.wickets += 1;
            if (T.wickets == this.maxPlayers-1) {
                System.out.println("               ALL OUT !!!");
                resetScore(T);
                return;
            }
        } else {
            T.runs = T.runs + (x - '0');
        }

        T.balls++;
        currentOver = T.balls / 6 + (T.balls % 6) * 0.1f;

        // Adding Ball History;
        matchHistory.put(currentOver, String.valueOf(x));

//        Checking if Over is finished or not
        if (T.balls % 6 == 0) {
            System.out.println("=====================================");
            System.out.println("OVER FINISHED");
            System.out.println("Over : " + currentOver +" TEAM "+ T.name + "  Runs: " + T.runs + " Wickets: " + T.wickets);
            System.out.println("====================================");
        }

//        Checking if the chasing team has won or not...
        if(this.bowlingTeam.runs > this.battingTeam.runs ){
            this.bowlingTeam.display_score();
            bowlingTeam.teamDetailedHistory.put(bowlingTeam.matchPlayed++,matchHistory);
            System.out.println(bowlingTeam.name+" won the match by "+ (maxPlayers-1 - bowlingTeam.wickets)+" wickets");
            bowlingTeam.matchesWon++;
            battingTeam.reset();
            bowlingTeam.reset();
            return;
        }
//      Continue to game;
        match(T,currentOver);
    }

    private void resetScore(Teams T) {
        if (T.name.equals(battingTeam.name)) {
            this.battingTeam.display_score();
            battingTeam.teamDetailedHistory.put(battingTeam.matchPlayed++,matchHistory);
            startGame(bowlingTeam);
        }
        else{
            this.bowlingTeam.display_score();
            bowlingTeam.teamDetailedHistory.put(bowlingTeam.matchPlayed++,matchHistory);
            if (this.battingTeam.runs > this.bowlingTeam.runs) {
                System.out.println(battingTeam.name+" won the match by "+ (battingTeam.runs- bowlingTeam.runs)+" runs");
                battingTeam.matchesWon++;
            } else {
                System.out.println("DRAW");
            }
            battingTeam.reset();
            bowlingTeam.reset();
        }


    }
    private void seriesResult(Teams T1,Teams T2){
        System.out.println("\n==================================================");
        if (T1.matchesWon > T2.matchesWon){
            System.out.println("Team "+ T1.name+ " has won the Series with maximum win of "+ T1.matchesWon);
        } else if (T1.matchesWon < T2.matchesWon) {
            System.out.println("Team "+ T2.name+ " has won the Series with maximum win of "+ T2.matchesWon);
        } else {
            System.out.println(" Nobody WON the Series as both teams has equal Wins!!!");
        }
        System.out.println("===================================================");
    }
}




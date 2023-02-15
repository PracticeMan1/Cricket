import java.util.Random;
import java.util.Scanner;

public class MatchController {
    //    Declaring Class Variables
    private float maxOver = 6f; // Default Maximum Over that can be played are 6

    // ON FIELD
    private Teams battingTeam;
    private Teams bowlingTeam;

    Scanner scan = new Scanner(System.in); // Scanner class object


    // GETTERS AND SETTERS

    public void setMaxOver(int maxOver) {
        this.maxOver = maxOver;
    }

    public void seriesMatch(int numberOfMatches,Teams teamA, Teams teamB){
        while(numberOfMatches>0){
            System.out.println();
            toss(teamA, teamB);
            Game game = new Game();
            game.startGame(battingTeam,bowlingTeam,maxOver,maxPlayers);
            numberOfMatches--;
            System.out.println(
                    "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-* MATCH " +
                    "ENDED -*-**-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        }
        seriesResult(teamA, teamB);
    }

    private void toss(Teams teamA, Teams teamB) {
        System.out.println("Begin the TOSS: ");
        System.out.println("Choose Heads (0) or Tails (1)");
        int calledToss = scan.nextInt();
        Random rand = new Random();
        System.out.println("TOSSING COIN");

        // Randomly selecting the actual output of toss
        int actualToss = rand.nextInt(0, 2);
        int optedPlay;

        // If Won the Toss providing with option to BAT or BOWL first
        if (actualToss == calledToss) {
            System.out.println("You have WON the TOSS...");
            System.out.println("Choose to BAT (1) or BOWL (2) first....");
            optedPlay = scan.nextByte();
        }
        // Else Randomly selecting to Bat or Bowl First
        else {
            System.out.println("You have LOST the TOSS");
            optedPlay = rand.nextInt(1, 3);
            if (optedPlay == 2){
                System.out.println(teamB.teamName + " has chosen to Bat first!!..");
            }else System.out.println(teamB.teamName + " has chosen to Bowl first!!..");
        }

        // Setting Batting and Bowling team as per toss outcome
        if (optedPlay == 1) {
            setBattingTeam(teamA);
            setBowlingTeam(teamB);
        } else {
            setBowlingTeam(teamB);
            setBattingTeam(teamA);
        }
    }


    private void seriesResult(Teams teamA,Teams teamB){
        // Series Winner Check
        System.out.println("\n==================================================");
        if (teamA.matchesWon > teamB.matchesWon){
            System.out.println("Team " + teamA.teamName + " has won the Series with maximum win of " + teamA.matchesWon);
        } else if (teamA.matchesWon < teamB.matchesWon) {
            System.out.println("Team " + teamB.teamName + " has won the Series with maximum win of " + teamB.matchesWon);
        } else {
            System.out.println(" Nobody WON the Series as both teams has equal Wins!!!");
        }
        System.out.println("===================================================");
    }
}

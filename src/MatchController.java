import java.util.Random;
import java.util.Scanner;

public class MatchController {
    //    Declaring Class Variables
    private byte maxPlayers = 11; // Default Team Size is 11
    private float max_over = 6f; // Default Maximum Over that can be played are 6

    // ON FIELD
    private Teams battingTeam;
    private Teams bowlingTeam;



    // SCOREBOARD

    private int runs;
    private int balls;
    private int wickets;
    private int targetScore = Integer.MAX_VALUE;


    // Some Final Fields
    private static final Random rand = new Random(); // Random class object
    Scanner scan = new Scanner(System.in); // Scanner class object


    // GETTERS AND SETTERS

    public void setMaxOver(int maxOver) {
        this.max_over = maxOver;
    }

    public void setBattingTeam(Teams T) {
        this.battingTeam = T;
    }

    public void setBowlingTeam(Teams T) {
        this.bowlingTeam = T;
    }

    public void setMaxPlayers(byte numberOfPlayers) {
        this.maxPlayers = numberOfPlayers;
    }


    // MATCH FLOW :-
    // TOSS
    // GAME STARTS
    // OVER FINISHED
    // RESULTS

    public void seriesMatch(int numberOfMatches,Teams T1, Teams T2){
        while(numberOfMatches>0){
            System.out.println();
            targetScore = Integer.MAX_VALUE;
            toss(T1,T2);
            System.out.println("-------------------------------------------FIRST HALF-------------------------------------------");
            startGame(battingTeam,bowlingTeam);
            numberOfMatches--;
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*MATCH ENDED-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        }
        seriesResult(T1,T2);
    }

    private void toss(Teams T1, Teams T2) {
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
                System.out.println(T2.teamName+" has chosen to Bat first!!..");
            }else System.out.println(T2.teamName+ " has chosen to Bowl first!!..");
        }

        // Setting Batting and Bowling team as per toss outcome
        if (optedPlay == 1) {
            setBattingTeam(T1);
            setBowlingTeam(T2);
        } else {
            setBowlingTeam(T1);
            setBattingTeam(T2);
        }
    }

    private void startGame(Teams T1, Teams T2) {
        System.out.println();
        // Setting up the field for starting the game
        runs = 0;
        wickets = 0;
        balls = 0;
        float currentOver = 0f;
        int strikerID = 1;
        int nonStrikerID =2;
        int bowlerID = 1;
        if (T2.bowlersIDs.size()>1) {bowlerID = randomNewBowler(T2,bowlerID);}
        playBall(T1,T2, currentOver, strikerID, nonStrikerID, bowlerID);
    }

    private void playBall(Teams T1, Teams T2, float currentOver, int strikerID, int nonStrikerID, int bowlerID) {
        // check for Overs
        if (currentOver == this.max_over) {
            resetScoreboard(T1);
            return;
        }

        // Randomly generating the outcome on the ball
        int ballOutcome = randomBallOutcomeGenerator(T1, strikerID);

        // Updating Scoreboard
        updateScore(T1, T2, currentOver, ballOutcome, strikerID, nonStrikerID, bowlerID);
    }

    private int randomBallOutcomeGenerator(Teams T, int strikerID) {
        double number = Math.random();

        if (T.getPlayerByID(strikerID).playerPlayingStyle == 1) {
            // Probability Distribution for Bowler
            // 0 --> 15%  , 1 --> 35%, 2--> 25%, 3 --> 5%, 4 --> 5%, 5 --> 5%, 6 -->5%, W --> 10%
            if (number < 0.15) {
                return 0;
            } else if (number < 0.45) {
                return 1;
            } else if (number < 0.7) {
                return 2;
            } else if (number < 0.75) {
                return 3;
            } else if (number < 0.8) {
                return 4;
            } else if (number < 0.85) {
                return 5;
            } else if (number < 0.9) {
                return 6;
            } else {
                return 7;
            }
        } else {
            // Probability Distribution for Batsman and All-Rounder
            // 0 --> 5%  , 1 --> 20%, 2--> 15%, 3 --> 10%, 4 --> 20%, 5 --> 5%, 6 -->20%, W --> 5%
            if (number < 0.05) {
                return 0;
            } else if (number < 0.25) {
                return 1;
            } else if (number < 0.4) {
                return 2;
            } else if (number < 0.5) {
                return 3;
            } else if (number < 0.7) {
                return 4;
            } else if (number < 0.75) {
                return 5;
            } else if (number < 0.95) {
                return 6;
            } else {
                return 7;
            }

        }
    }


    private void updateScore(Teams T1, Teams T2, float currentOver, int ballOutcome, int strikerID, int nonStrikerID, int bowlerID) {

        // Showing Current Over
        if (balls % 6 == 0) {
            System.out.println("Over : " + currentOver);
        }

        // Showing Current Ball Outcome
        if (ballOutcome != 7) System.out.println("\t\t" + (balls % 6 + 1) + " : " + ballOutcome);
        else System.out.println("\t\t" + (balls % 6 + 1) + " : Wicket!!! "+T1.getPlayerByID(strikerID)+" is out.. by stunning bowling of  "+T2.getPlayerByID(bowlerID));

        // Updating MetaData

        // Updating Players and Match variables as per ball outcome

        // If its Wicket
        if (ballOutcome == 7) {
            wickets += 1;
            // Checking for ALL OUT
            if (wickets == this.maxPlayers - 1) {
                System.out.println("               ALL OUT !!!");
                resetScoreboard(T1);
                return;
            }
            strikerID = wickets + 2;
        }

        // Else update Runs
        else {
            runs = runs + ballOutcome;
            T1.getPlayerByID(strikerID).playerRun += ballOutcome;
        }

        // Updating balls and Overs

        balls++;
        currentOver = (float) balls / 6 + (balls % 6) * 0.1f;

        // Updating Striker and NonStriker
        if (ballOutcome % 2 != 0) {
            int temp = strikerID;
            strikerID = nonStrikerID;
            nonStrikerID = temp;
        }

        // Checking if Over is finished or not
        if (balls % 6 == 0) {
            System.out.println("=====================================");
            System.out.println("OVER SUMMARY");
            System.out.println("Over : " + currentOver + " TEAM " + T1.teamName + "  Runs: " + runs + " Wickets: " + wickets);
            System.out.println("ON STRIKE: " + T1.getPlayerByID(strikerID).playerName + " : " + T1.getPlayerByID(strikerID).playerRun);
            System.out.println("ON NONSTRIKE: " + T1.getPlayerByID(nonStrikerID).playerName + " : " + T1.getPlayerByID(nonStrikerID).playerRun);
            System.out.println("BOWLER : " + T2.getPlayerByID(bowlerID));
            System.out.println("====================================");
            System.out.println();
            bowlerID = randomNewBowler(T2, bowlerID);
        }



        // Checking if the chasing team has won or not...
        if (runs > targetScore) {
            displayScoreboard(bowlingTeam);
            System.out.println(bowlingTeam.teamName + " won the match by " + (maxPlayers - 1 - wickets) + " wickets");
            bowlingTeam.matchesWon++;
            return;
        }
//      Continue to game;
        playBall(T1, T2, currentOver, strikerID, nonStrikerID, bowlerID);
    }

    private int randomNewBowler(Teams T, int bowlerID) {
        int newBowler = bowlerID;
        if (T.bowlersIDs.size() > 2) {
            while (newBowler == bowlerID) {
                newBowler = T.bowlersIDs.get(rand.nextInt(T.bowlersIDs.size()));
            }
        } else {
            while (newBowler == bowlerID) {
                newBowler = rand.nextInt(1, maxPlayers + 1);
            }
        }
        return newBowler;
    }

    private void resetScoreboard(Teams T) {
        T.teamMatchHistory.put(T.matchesPlayed++,new int[]{runs,balls,wickets});
        // Checking if its HALF TIME or MATCH ENDING

        if (T.teamName.equals(battingTeam.teamName)) {
            displayScoreboard(battingTeam);
            targetScore = runs+1;
            System.out.println("TARGET SETTED: "+ targetScore);
            // Now Bowling team will play the match
            System.out.println("------------------------------------------------SECOND HALF-------------------------------------");
            startGame(bowlingTeam,battingTeam);
        } else {
            // All teams had played, NOW printing the RESULT
            displayScoreboard(bowlingTeam);

            if (runs == targetScore) {
                System.out.println("DRAW");
            } else {
                System.out.println(battingTeam.teamName + " won the match by " + (targetScore - runs) + " runs");
                battingTeam.matchesWon++;
            }
        }
    }

    public void displayScoreboard(Teams T1){
        // Display Batting Team Stats
        Player p;
        System.out.println("==================================SCORE BOARD=============================");
        System.out.println("Batting Team: "+T1.teamName);
        for (int i = 1;(i<=maxPlayers && i<= wickets+2);i++){
            p = T1.getPlayerByID(i);
            System.out.println(p.playerName+" R: "+p.playerRun);
            p.saveAndReset();
        }
        System.out.println("===========================================================================");
    }
    private void seriesResult(Teams T1,Teams T2){
        // Series Winner Check
        System.out.println("\n==================================================");
        if (T1.matchesWon > T2.matchesWon){
            System.out.println("Team "+ T1.teamName+ " has won the Series with maximum win of "+ T1.matchesWon);
        } else if (T1.matchesWon < T2.matchesWon) {
            System.out.println("Team "+ T2.teamName+ " has won the Series with maximum win of "+ T2.matchesWon);
        } else {
            System.out.println(" Nobody WON the Series as both teams has equal Wins!!!");
        }
        System.out.println("===================================================");
    }
}

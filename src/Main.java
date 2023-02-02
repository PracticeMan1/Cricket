import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("ENTER THE NUMBER OF MATCHES TO BE PLAYED IN THIS SERIES!! :  ");
        int matchesToPlay = scan.nextInt();
        System.out.print("ENTER THE NAME OF YOUR TEAM : ");
        String teamName1 = scan.next();
        System.out.print("ENTER THE NAME OF OPPONENT TEAM : ");
        String teamName2 = scan.next();
        System.out.print("ENTER THE NUMBER OF OVERS TO BE PLAYED : ");
        int maxOver = scan.nextInt();
        System.out.println("ENTER THE NUMBER OF PLAYERS IN TEAM : ");
        byte numberOfPlayers = scan.nextByte();

        Teams team1 = new Teams(teamName1, numberOfPlayers);
        Teams team2 = new Teams(teamName2, numberOfPlayers);
        Cricket game = new Cricket();

        game.setMaxPlayers(numberOfPlayers);
        game.setMaxOver(maxOver);
        game.seriesMatch(matchesToPlay,team1,team2);


        System.out.println("Team : "+team1.name+ " has total runs: "+team1.getTotalRuns()+" from "+ team1.matchPlayed+ " matches");

    }


}
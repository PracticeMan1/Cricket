import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("ENTER THE NUMBER OF MATCHES TO BE PLAYED IN THIS SERIES!! :  ");
        int matchesToPlay = scan.nextInt();
        System.out.print("ENTER THE NUMBER OF PLAYERS IN TEAM : ");
        byte numberOfPlayers = scan.nextByte();

        System.out.print("ENTER THE NAME OF YOUR TEAM : ");
        String teamName1 = scan.next();
        Teams team1 = new Teams(teamName1, numberOfPlayers);
        System.out.println();
        System.out.print("ENTER THE NAME OF OPPONENT TEAM : ");
        String teamName2 = scan.next();
        Teams team2 = new Teams(teamName2, numberOfPlayers);
        System.out.println();
        System.out.print("ENTER THE NUMBER OF OVERS TO BE PLAYED : ");
        int maxOver = scan.nextInt();


        MatchController game = new MatchController();

        game.setMaxPlayers(numberOfPlayers);
        game.setMaxOver(maxOver);
        game.seriesMatch(matchesToPlay,team1,team2);
//         Teams T1 = new Teams("Arsh",4);

    }
}
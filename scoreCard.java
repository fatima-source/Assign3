import java.util.*;
public class scoreCard {

    static final int overs = 20;
    static final int balls_per_over = 6;
    static final int maxBalls = overs * balls_per_over;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();


        System.out.println("Enter name of team1: ");
        String team1 = input.nextLine();
        System.out.println("Enter name of team2: ");
        String team2 = input.nextLine();


        int toss = random.nextInt(2);
        String Toss_Winner = (toss == 0) ? team1 : team2;
        String tossLosser = (toss == 0) ? team2 : team1;
        System.out.println(Toss_Winner + " won the toss and will bat first");
        String battingTeam = Toss_Winner;
        String bowlingTeam = tossLosser;


        String[] team1Players = {"Babar Azam", "Shaheen Afridi", "Hasan Ali", "Mohammad Rizwan", "Fakhar Zaman",
                "Shadab Khan", "Haris Rauf", "Aamer Yamin", "Saeed Ajmal", "Umar Gul", "Ehsan Adil","Aamer Yamin", "Saeed Ajmal", "Umar Gul", "Ehsan Adil"};
        String[] team2Players = {"Shoaib Malik", "Mohammad Hafeez", "Imad Wasim", "Junaid Khan", "Mohammad Amir",
                "Usman Qadir", "Danish Aziz", "Imran Khan", "Sarfaraz Ahmed", "Wahab Riaz", "Asad Shafiq",};


        int ScoreOfTeam1 = simulateInnings(battingTeam, team1Players, random);
        int ScoreOfTeam2 = simulateInnings(bowlingTeam, team2Players, random);


        System.out.println(team1 + " Score: " + ScoreOfTeam1);
        System.out.println(team2 + " Score: " + ScoreOfTeam2);

        if (ScoreOfTeam1 > ScoreOfTeam2) {
            System.out.println("Winner is " + team1);
        } else if (ScoreOfTeam2 > ScoreOfTeam1) {
            System.out.println("Winner is " + team2);
        } else {
            System.out.println("Match is tie!");
        }

        input.close();
    }

    public static int simulateInnings(String battingTeam, String[] players, Random random) {
        System.out.println("\n" + battingTeam + " is batting now.");

        int totalRuns = 0;
        int wickets = 0;
        int oversBowled = 0;
        int ballsBowled = 0;


        int[] runsScored = new int[players.length];
        int[] ballsFaced = new int[players.length];
        boolean[] isOut = new boolean[players.length];

        int currentBatsmanIndex = 0;


        while (ballsBowled < maxBalls && wickets < players.length - 1) {
            int runs = random.nextInt(7);
            boolean out = random.nextInt(10) < 1;

            if (out) {
                isOut[currentBatsmanIndex] = true;
                wickets++;
                System.out.println(players[currentBatsmanIndex] + " is out!");


                currentBatsmanIndex++;
            } else {
                runsScored[currentBatsmanIndex] += runs;
                totalRuns += runs;
                ballsFaced[currentBatsmanIndex]++;
            }

            ballsBowled++;
            if (ballsBowled % balls_per_over == 0) {
                oversBowled++;
            }

            System.out.println("Ball " + ballsBowled + ": " + runs + " runs scored");
        }

        System.out.println("\nInnings Summary for " + battingTeam + ":");
        System.out.println("Total Runs: " + totalRuns);
        System.out.println("Wickets Fallen: " + wickets);
        System.out.println("Overs Bowled: " + oversBowled + "." + (ballsBowled % balls_per_over));

        System.out.println("\nScore Card : ");
        System.out.println("Name            Runs  Balls  Strike Rate  Status");
        for (int i = 0; i < players.length; i++) {
            String name = String.format("%-20s", players[i]);
            String runs = String.format("%-5d", runsScored[i]);
            String balls = String.format("%-5d", ballsFaced[i]);
            String strikeRate = String.format("%-10.2f", (ballsFaced[i] > 0) ? (runsScored[i] / (double) ballsFaced[i]) * 100 : 0);
            String status = isOut[i] ? "Out" : "Not Out";
            
            System.out.println(name + runs + balls + strikeRate + status);
        }

        System.out.println("Total score: " + totalRuns + "/" + wickets + " in " + oversBowled + "." + (ballsBowled % balls_per_over) + " overs");
        return totalRuns;
    }
}


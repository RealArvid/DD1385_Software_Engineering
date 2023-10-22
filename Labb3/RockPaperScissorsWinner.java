import java.util.ArrayList;
import java.util.Arrays;

public class RockPaperScissorsWinner{
    private static ArrayList<String> hand = new ArrayList<>(Arrays.asList("ROCK", "PAPER", "SCISSORS"));

    public static String determineWinner(String gesture1, String gesture2){
        int idx1 = hand.indexOf(gesture1);
        int idx2 = hand.indexOf(gesture2);

        if(idx1 == idx2)
            return "draw";        
        if((idx1 + 1) % 3 == idx2)
            return "lose";
        else
            return "win";
    }

    public static void main(String[] args){
        String result = determineWinner("SCISSORS", "ROCK");
        System.out.println(result);
    }
}
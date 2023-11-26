import java.time.*;


public class Main {
    private static int N = 200000;

    private static long getTime(StringBuilder stringBuilder){
        Instant start = Instant.now();
        for(int i = 0; i < N; i++){
            stringBuilder.append("0");
        }
        Instant end = Instant.now();
        return Duration.between(start, end).getNano();
    }
    public static void main(String[] args){
        System.out.println(getTime(new StringBuilder()));
    }
}

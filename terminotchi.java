import java.util.concurrent.TimeUnit;

public class terminotchi{
    static int SLEEP_TIME = 1;
    public static void main(String arg[]){

        clearTerminal();
    }
    public static void clearTerminal() {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_TIME);
        } catch(InterruptedException e) {
            System.out.println("Error: InterruptedException");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
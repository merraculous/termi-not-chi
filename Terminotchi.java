import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;


public class Terminotchi{
    static int SLEEP_TIME = 1;
    public static void main(String arg[]){
        System.out.println(loadFile("./ASCII/logo.txt"));
        clearTerminal(10);
    }
    public static void clearTerminal(int sleepTime) {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch(InterruptedException e) {
            System.out.println("Error: InterruptedException");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static String loadFile(String file) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String currLine;
            while ((currLine = br.readLine()) != null) {
                fileContent.append(currLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
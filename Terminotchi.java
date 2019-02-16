import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;


public class Terminotchi{
    
    static int SLEEP_TIME = 1;
    static Creature pet = new Creature();
    
    public static void main(String arg[]){
        System.out.println("\n\n\n\n\n\n\n\n\n" + loadFile("./ASCII/logo.txt"));
        clearTerminal(1);
        printScreen();
        readInput();
    }
    
    public static void clearTerminal(int sleepTime) {
        holUp(sleepTime);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void holUp(int sleepTime) {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch(InterruptedException e) {
            System.out.println("Error: InterruptedException");
        }
    }

    public static void printScreen() {
        printMeters();
        printPet();
    }

    public static void printPet() {
        System.out.println( pet.getArt() );
    }

    public static void printMeters() {
        String hungryBoi = getMeterASCII(pet.getHunger());
        String happyBoi = getMeterASCII(pet.getHappiness());
        String hygene = getMeterASCII(pet.getDirtyBoi());
        String text = "\n    HUNGER " + hungryBoi.replace("\n", "") + "     HAPPINESS " + happyBoi.replace("\n", "") + "     DIRTY BOI " + hygene;
        System.out.println(text);
    }

    public static String getMeterASCII(Meter m) {
        int percent = m.get();
        percent = (percent/10) * 10;
        String file = "./ASCII/gauges/" + Integer.toString(percent) + ".txt";
        return loadFile(file);
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

    public static void readInput(){
        System.out.println("Please choose an action: feed, play, clean");
        String input = System.console().readLine();
        switch(input.toLowerCase()){
            case "feed":
                //Call feed function
                System.out.println("you fed your pet");
                break;
            case "play":
                //Call play function
                System.out.println("you played with your pet");
                break;
            case "clean":
                //Call clean function
                System.out.println("you cleaned your pet");
                break;
            case "quit":
                System.out.println("byee");
                break;
            default:
                System.out.println("Please enter a valid option");
                break;
        }
        
        

    }

}
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;


public class Terminotchi{
    
    static int SLEEP_TIME = 1;
    static Creature pet = new Creature();
    
    public static void main(String arg[]){
        System.out.println("\n\n\n\n\n\n\n\n\n" + loadFile("./ASCII/logo.txt"));
        //clearTerminal(1);
        int numSeconds = 0;

        // To make the egg hatch immediately
        int numMinutes = 9000;
        
        while(pet.isAlive()) {
            
            printScreen(2);
            numSeconds += 2;

            // Update stage if it's been 15 minutes
            if (numMinutes > 15) {
                pet.loadNextStage();
                numMinutes = 0;
            }
            
            // Update meters
            if(numSeconds > 1) {
                pet.getDirtyBoi().decrement();
                pet.getHappiness().decrement();
                pet.getHunger().decrement();
                numSeconds = 0;
                numMinutes++;
            }
            
            // take input
            
        }
        clearTerminal(1);
        System.out.println( loadFile("./yourPetDied.txt") );
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

    public static void printScreen(int wait) {
        clearTerminal(wait);
        printMeters();
        printPet();
    }

    public static void printPet() {
        System.out.println( pet.getArt() );
    }

    public static void printMeters() {
        String hungy = Integer.toString(pet.getHunger().get());
        String happ = Integer.toString(pet.getHappiness().get());
        String db = Integer.toString(pet.getDirtyBoi().get());

        String hungryBoi = getMeterASCII(pet.getHunger());
        String happyBoi = getMeterASCII(pet.getHappiness());
        String hygene = getMeterASCII(pet.getDirtyBoi());
        String text = "\n    HUNGER " + hungryBoi.replace("\n", " ") + hungy + "%     HAPPINESS " + happyBoi.replace("\n", " ") + happ + "%     DIRTY BOI " + hygene.replace("\n", " ") +" " + hungy + "%\n\n";
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
}
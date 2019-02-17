import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;
import java.util.Random;


public class Terminotchi{
    
    static Random rand = new Random();
    static int SLEEP_TIME = 1;
    static Creature pet = new Creature();
    static boolean medAsked = false;
    
    public static void main(String arg[]){
        clearTerminal(0);
        System.out.println("\n\n\n\n\n\n\n\n\n" + loadFile("./ASCII/logo.txt") + "\n\n\n\n\n\n\n\n\n" );
        
        //clearTerminal(1);
        int numSeconds = 0;
        int promptSeconds = 0;

        // To make the egg hatch immediately
        int numMinutes = 9000;
        clearTerminal(2);
        printPet();
        
        while(pet.isAlive()) {
            
            printScreen(3);

            numSeconds += 2;
            promptSeconds += 2;

            // Update stage if it's been 15 minutes
            if (numMinutes > 10) {
                pet.loadNextStage();

                

                numMinutes = 0;
            } else if(pet.isSick() && !medAsked){
                sickness();
            } else if (promptSeconds > 5) {
                // take input
                readInput();
                promptSeconds = 0;
            }
            
            // Update meters
            if(numSeconds > 1) {
                pet.getDirtyBoi().decrement();
                pet.getHappiness().decrement();
                pet.getHunger().decrement();
                numSeconds = 0;
                numMinutes++;
            }
            
            catchSickness();
            
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

    public static String loadRandomFile(String dir, int lim) {
        int n = rand.nextInt(lim);
        return loadFile(dir + Integer.toString(n) +".txt");
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
        String text = "\n    HUNGER " + hungryBoi.replace("\n", " ") + hungy + "%     HAPPINESS " + happyBoi.replace("\n", " ") + happ + "%     DIRTY BOI " + hygene.replace("\n", " ") +" " + db + "%\n\n";
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
            case "":
                if(pet.isBoiii()) {
                    System.out.println("you did nothing to care for your boiii");
                } else {
                    System.out.println("you did nothing to care for your pet");
                }
                break;
            case "feed":
                pet.feed();
                System.out.println(loadRandomFile("./ASCII/food/", 7));
                if(pet.isBoiii()) {
                    System.out.println("you feed your boiii");
                } else {
                    System.out.println("you fed your pet");
                }
                break;
            case "play":
                pet.play();
                if(pet.isBoiii()) {
                    System.out.println("you played with your boiii");
                } else {
                    System.out.println("you played with your pet");
                }
                
                
                break;
            case "clean":
                pet.clean();
                System.out.println("\n\n\n\n\n" + loadFile("./ASCII/soap.txt") + "\n\n\n");
                if(pet.isBoiii()) {
                    System.out.println("you cleaned your boiii");
                } else {
                    System.out.println("you cleaned your pet");
                }
                break;
            case "quit":
                System.out.println("byee");
                System.exit(1);
                break;
            case "kill":
                pet.getHunger().set(-1);
                break;

            case "dream donut":
                System.out.println("\n\n\n\n\n" + loadFile("./ASCII/donut.txt") + "\n\n\n");
                System.out.println("Filip gets his dream donut");
                break;
            default:
                if(pet.isBoiii()) {
                    System.out.println("you're too dumb to care for your boiii");
                } else {
                    System.out.println("Please enter a valid option");
                }
                
                break;
        }
    }




    public static void printObject(String file){
        String loadedFile = loadFile(file);
        System.out.println(loadedFile);
    }

    public static void sickness(){
        medAsked = true;
        System.out.println("Oh no! Your creature is sick!");
        System.out.println("Would you like to give it medicine? (y/n)");
        String userInput = System.console().readLine();
        if(userInput.equals("y")){
            System.out.println(loadRandomFile("./ASCII/meds/", 5));
            System.out.println("You gave your creature medicine. It looks like its doing much better!");
            pet.setSick(false);
            return;
        }
        else{
            System.out.println("You decided not to give your creature medicine.");
        }
    }

    public static void catchSickness(){
        if(!pet.isSick()){
            int n = rand.nextInt(100);
            // System.out.println(n);
            if (pet.getDirtyBoi().get() < 100 && n < 5) {
                pet.setSick(true);
            }
            // if (pet.getDirtyBoi().get() > 90 && n <= 1) {
            //     pet.setSick(true);
            // } else if (pet.getDirtyBoi().get() > 75 && n <= 1) {
            //     pet.setSick(true);
            // } else if (pet.getDirtyBoi().get() > 50 && n <= 1) {
            //     pet.setSick(true);
            // } else if (pet.getDirtyBoi().get() > 25 && n <= 2) {
            //     pet.setSick(true);
            // } else if (pet.getDirtyBoi().get() > 0 && n <= 4) {
            //     pet.setSick(true);
            // }
        }
    }

}
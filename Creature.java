import java.util.Random;

public class Creature {
    private static Meter happiness = new Meter();
    private static Meter dirtyBoi = new Meter();
    private static Meter hunger = new Meter();
    private static int stage = 0;
    private static String art = OutputStuff.loadFile("./ASCII/stages/0.txt");

    private static boolean isAlive;
    private static boolean isSick;
    private static boolean boiii = false;
    private static Random rand = new Random();
    private static int n = rand.nextInt(9);
    private static String dir = "./ASCII/stages/"+ Integer.toString(n) + "/";
    
    /* Constructor
     *
     */
    public Creature(){
        this.isAlive = true;
        this.isSick = false;
    }

    /*
     * returns indication  if creature is sick
     */

    public static boolean isSick(){
        return isSick;
    }

    public static boolean isBoiii(){
        return boiii;
    }

    /*
     * returns indication  if creature is sick
     */
    public static void setSick(boolean b){
        isSick = b;
    }

    /* returns idication if creature is alive
     *
     */
     public static boolean isAlive(){
         if( happiness.get() < 0 || hunger.get() < 0) {
             isAlive = false;
         }
         return isAlive;
     }

    public static void loadNextStage() {
        if(stage < 5) {
            if (stage > 1) {
                boiii = true;
            }
            stage += 1;
            Terminotchi.clearTerminal(0);
            System.out.println( Terminotchi.loadFile("./ASCII/stageMessages/" + Integer.toString(stage) + ".txt") );
            setArt(dir  + Integer.toString(stage) + ".txt");
        } else {
            hunger.set(-1);
        }
        
    }

    /**
     * @return the art
     */
    public static String getArt() {
        return art;
    }

    /**
     * @param art the art to set
     */
    public static void setArt(String file) {
        Creature.art = Terminotchi.loadFile(file);
    }

    /**
     * @return the stage
     */
    public static int getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public static void setStage(int stage) {
        Creature.stage = stage;
        setArt("./ASCII/stages/" + Integer.toString(stage) + ".txt");
    }

    /**
     * @return the hunger
     */
    public static Meter getHunger() {
        return hunger;
    }

    /**
     * @param hunger the hunger to set
     */
    public static void setHunger(Meter hunger) {
        Creature.hunger = hunger;
    }

    /**
     * @return the dirtyBoi
     */
    public static Meter getDirtyBoi() {
        return dirtyBoi;
    }

    /**
     * @param dirtyBoi the dirtyBoi to set
     */
    public static void setDirtyBoi(Meter dirtyBoi) {
        Creature.dirtyBoi = dirtyBoi;
    }

    /**
     * @return the happiness
     */
    public static Meter getHappiness() {
        return happiness;
    }

    /**
     * @param happiness the happiness to set
     */
    public static void setHappiness(Meter happiness) {
        Creature.happiness = happiness;
    }

    public static void feed() {
        if(hunger.get() + 25 <= 100){
            hunger.increment(25);
        } else {
            isSick = true;
            hunger.set(100);
        }
    }

    public static void clean() {
        dirtyBoi.increment(100);
    }

    public static void play() {
        happiness.increment(25);
        dirtyBoi.increment(-10);
    }


}
public class Creature {
    private static Meter happiness = new Meter();
    private static Meter dirtyBoi = new Meter();
    private static Meter hunger = new Meter();
    private static int stage = 0;

    public static void loadNextStage() {
        setStage(getStage() + 1);
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
        hunger.increment(25);
    }

    public static void clean() {
        dirtyBoi.increment(100);
    }

    public static void play() {
        happiness.increment(25);
    }


}
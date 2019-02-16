public class Meter{
    int percent = 50;
    public static void increment(){
        this.percent += 1;
    }
    public static void decrement(){
        this.percent -= 1;        
    }
    public static void get(){
        return this.percent;
    }
    public static void set(int val){
        this.percent = val;
    }
}
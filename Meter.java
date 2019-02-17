public class Meter{
    int percent = 100;
    public void decrement(){
        this.percent -= 1;        
    }

    public void set(int val){
        this.percent = val;
    }

    public int get(){
        return this.percent;
    }

    public void increment(int val){
        this.percent += val;
        if (this.percent > 100) {
            this.percent = 100;
        }
    }
}
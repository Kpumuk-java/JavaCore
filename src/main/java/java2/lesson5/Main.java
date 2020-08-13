package java2.lesson5;

public class Main {


    public static void main(String[] args) {
        MyThreadsRun myThreadsRun = new MyThreadsRun();
        myThreadsRun.oneThread();
        //myThreadsRun.getValueMassiveTenNumber();
        myThreadsRun.massiveFillOne();
        myThreadsRun.twoThread();
    }
}

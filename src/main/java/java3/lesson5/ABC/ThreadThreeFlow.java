package java3.lesson5.ABC;


public class ThreadThreeFlow {

    private static int COUNT_REPEAT = 5;
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';


    public void startThread() {


            new Thread(() -> {
                    outInConsole('A');
            }).start();


            new Thread(() -> {
                    outInConsole('B');
            }).start();


            new Thread(() -> {
                    outInConsole('C');
            }).start();
        }



    private void outInConsole(char s) {
        synchronized (mon) {
            try {
                for (int i = 0; i < COUNT_REPEAT; i++) {
                    while (currentLetter != s) {
                        mon.wait();
                    }
                    System.out.print(s);
                    if (s == 'A') {
                        currentLetter = 'B';
                    } else if (s == 'B') {
                        currentLetter = 'C';
                    } else {
                        currentLetter = 'A';
                    }
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

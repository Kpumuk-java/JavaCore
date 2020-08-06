package java2.lesson1.model;

public class RunningTrack {

    private int distance;

    public RunningTrack() {
        distance = (int) (10 + Math.random() * 501);
    }

    public int getDistance() {
        return distance;
    }
}

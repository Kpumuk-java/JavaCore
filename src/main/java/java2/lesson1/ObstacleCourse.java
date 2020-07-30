package java2.lesson1;

public class ObstacleCourse {

    public int showObstacle (Object obstacle) {
        if (obstacle instanceof Wall) {
            return ((Wall) obstacle).getHeight();
        } else if (obstacle instanceof RunningTrack) {
            return ((RunningTrack) obstacle).getDistance();
        }
        return 0;
    }

}

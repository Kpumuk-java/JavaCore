package java2.lesson1;

public class Main {

    /*
    * Метод принимает на вход одного участника и полосу препятствий. участник проходит по всем препятствиям
    * с выводом на консоль успешно или провально прошел препятствие. Если не проходит какое то препятствие
    * цикл завершается и последующие препятствия не проверяются
    * */
    public static void partyMembersGoInObstacleCourse(PartyMembers partyMembers, Object[] obstacle) {
        System.out.println("\n" + "Начинается забег участника " + partyMembers.getName());

        for (int i = 0; i < obstacle.length; i++) {

            if (obstacle[i] instanceof Wall) {
                if (partyMembers.Jump(obstacle[i])) {
                    System.out.printf("%10s", "Провал: ");
                    System.out.println("на " + (i + 1) + " препятствии не смог перепрыгнуть стену высотой "
                            + ((Wall) obstacle[i]).getHeight());
                    break;
                } else {
                    System.out.printf("%10s", "Успех: ");
                    System.out.println("прошел " + (i + 1) + " препятствие перепрыгнув стену высотой " +
                            ((Wall) obstacle[i]).getHeight());
                }
            } else if (obstacle[i] instanceof RunningTrack) {
                if (partyMembers.Run(obstacle[i])) {
                    System.out.printf("%10s", "Провал: ");
                    System.out.println("на " + (i + 1) + " препятствии не смог пробежать расстояние "
                            + ((RunningTrack) obstacle[i]).getDistance());
                    break;
                } else {
                    System.out.printf("%10s", "Успех: ");
                    System.out.println("прошел " + (i + 1) + " препятствие пробежав расстояние " +
                            ((RunningTrack) obstacle[i]).getDistance());
                }
            }
            // Проверка если тип препятствия не ссответствуют Wall или RunningTrack цикл обрывается
            if (!(obstacle[i] instanceof Wall) && !(obstacle[i] instanceof RunningTrack)) {
                System.out.println("Остутсвует корректное препятствие");
                break;
            }
        }
    }

    // метод вывода на консоль всех участников
    public static void showPartyMembers(PartyMembers[] partyMembers) {
        System.out.println("\n" + "Участники: ");
        for (int i = 0; i < partyMembers.length; i++) {
            System.out.printf("%10s", partyMembers[i].getName());
            System.out.printf("%25s", "Максимальный прыжок: ");
            System.out.printf("%3s", partyMembers[i].getMaxJumpHeight());
            System.out.printf("%33s", "Максимальное расстояние бега: ");
            System.out.printf("%4s", partyMembers[i].getMaxRunLength());
            System.out.println();
        }

    }
    // метод вывода на консоль полосу препятствий
    public static void showObstacle (Object[] obstacle) {
        System.out.println("\n" + "Полоса препятствий:");
        ObstacleCourse obstacleCourse = new ObstacleCourse();

        for (int i = 0; i < obstacle.length; i++) {

            if (obstacle[i] instanceof Wall) {
                System.out.println((i + 1) + " Препятствие " + " стена          " +
                        "     Высота: " + obstacleCourse.showObstacle(obstacle[i]));
            } else if (obstacle[i] instanceof RunningTrack) {
                System.out.println((i + 1) + " Препятствие " + " беговая дорожка" +
                        "     Растояние: " + obstacleCourse.showObstacle(obstacle[i]));
            }
        }
    }

    public static void main(String[] args) {
        /*Классы Human, Cat, Robot наследуются от одного класса потому что без них полиморфизм не сделать
        * пришлось бы много делать кода с ((Object) o).метод()
         */
        PartyMembers[] partyMembers = new PartyMembers[10];
        Object[] obstacle = new Object[5];

        //заполнение массивов участников и препятствий
        for (int i = 0; i < partyMembers.length; i++) {
            int x = (int) (Math.random() * 3);
            if (0 == x) {
                partyMembers[i] = new Cat("Cat");
            } else if (1 == x) {
                partyMembers[i] = new Robot("R2D2-");
            } else if (2 == x) {
                partyMembers[i] = new Human("Human");
            }

            if (i < obstacle.length) {
                if (0 == ((int) (1 + Math.random() * 100)) % 2) {
                    obstacle[i] = new Wall();
                } else {
                    obstacle[i] = new RunningTrack();
                }
            }
        }


        showObstacle(obstacle);
        showPartyMembers(partyMembers);
        for (int i = 0; i < partyMembers.length; i++) {
            partyMembersGoInObstacleCourse(partyMembers[i], obstacle);
        }
    }
}

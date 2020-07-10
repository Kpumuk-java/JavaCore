package lesson4;

import java.util.Scanner;


public class TicTacToe {

    private static char[][] map;
    private static Scanner sc = new Scanner(System.in);

    private final static int MAP_SIZE = 7;
    private final static int DOTS_TO_WIN = 4;

    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char EMPTY_DOT = '•';
    // срабатывает всего раз, но если понадобиться можно будет использовать чтобы посчитать сколько сделал ходов комп
    private static int AI_TURN_COUNT = 0;
    private static byte CHECK_FIRST_MOVE;
    // у переменных которые сохраняют координат хода тип int, потому что размер карты тоже int
    private static int PLAYER_TURN_X;
    private static int PLAYER_TURN_Y;
    private static int AI_TURN_X;
    private static int AI_TURN_Y;
    // Переменная для того чтобы компьютер сходил только один раз
    private static boolean AI_TURN = true;


    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {

        initMap();
        printMap();

        if (CHECK_FIRST_MOVE == 1) {
            while (true) {
                playerTurn();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил игрок");
                    break;
                }
                if (!checkDraw()) {
                    System.out.println("Противостояние зашло в тупик, поэтому ничья");
                    break;
                }
                printMap();
                aITurn();
                if (checkWin(DOT_O)) {
                    System.out.println("Ты проиграл");
                    break;
                }
                if (!checkDraw()) {
                    System.out.println("Противостояние зашло в тупик, поэтому ничья");
                    break;
                }
                printMap();
            }

        } else {
            while (true) {
                aITurn();
                if (checkWin(DOT_O)) {
                    System.out.println("Ты проиграл");
                    break;
                }
                if (!checkDraw()) {
                    System.out.println("Противостояние зашло в тупик, поэтому ничья");
                    break;
                }
                printMap();
                playerTurn();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил игрок");
                    break;
                }
                if (!checkDraw()) {
                    System.out.println("Противостояние зашло в тупик, поэтому ничья");
                    break;
                }
                printMap();
            }
        }

    }

    public static void initMap() {
        map = new char[MAP_SIZE][MAP_SIZE];

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
        // Всего раз нужно делается, потому можно этот блок вставить в инициализацию карты
        CHECK_FIRST_MOVE = (byte) (Math.random() * 2);

        if (CHECK_FIRST_MOVE == 1) {
            System.out.println("Первым ходит игрок");
        } else {
            System.out.println("Первым ходит компьютер");
        }

    }

    public static void printMap() {
        System.out.println("Символ " + DOT_O + " - ход компьютера. Символ " + DOT_X + " - ход игрока");
        System.out.print("  ");

        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.print(" " + (i + 1) + " ");
        }

        System.out.println();

        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(" " + map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void aITurn() {
        // Необходимо это условие если первый ход компьютера (потому что координаты хода игрока еще нет)
        if (AI_TURN_COUNT == 0) {
            aITurnRandom();
            AI_TURN_COUNT++;
        } else {
            // необходимо обнулять каждый ход компа
            AI_TURN = true;

            if ((PLAYER_TURN_X - 1) >= 0 && map[PLAYER_TURN_X - 1][PLAYER_TURN_Y] == DOT_X) {
                if ((PLAYER_TURN_X + 1) < MAP_SIZE && map[PLAYER_TURN_X + 1][PLAYER_TURN_Y] == EMPTY_DOT) {
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X + 1;
                    AI_TURN_Y = PLAYER_TURN_Y;
                } else if ((PLAYER_TURN_X - 2) >= 0 && map[PLAYER_TURN_X - 2][PLAYER_TURN_Y] == EMPTY_DOT) {
                    map[PLAYER_TURN_X - 2][PLAYER_TURN_Y] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X - 2;
                    AI_TURN_Y = PLAYER_TURN_Y;
                }
            } else if ((PLAYER_TURN_X + 1) < MAP_SIZE && map[PLAYER_TURN_X + 1][PLAYER_TURN_Y] == DOT_X) {
                if ((PLAYER_TURN_X - 1) >= 0 && map[PLAYER_TURN_X - 1][PLAYER_TURN_Y] == EMPTY_DOT) {
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X - 1;
                    AI_TURN_Y = PLAYER_TURN_Y;
                } else if ((PLAYER_TURN_X + 2) < MAP_SIZE && map[PLAYER_TURN_X + 2][PLAYER_TURN_Y] == EMPTY_DOT) {
                    map[PLAYER_TURN_X + 2][PLAYER_TURN_Y] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X + 2;
                    AI_TURN_Y = PLAYER_TURN_Y;
                }
            }

            if (AI_TURN && (PLAYER_TURN_Y - 1) >= 0 && map[PLAYER_TURN_X][PLAYER_TURN_Y - 1] == DOT_X) {
                if ((PLAYER_TURN_Y + 1) < MAP_SIZE && map[PLAYER_TURN_X][PLAYER_TURN_Y + 1] == EMPTY_DOT) {
                    map[PLAYER_TURN_X][PLAYER_TURN_Y + 1] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X;
                    AI_TURN_Y = PLAYER_TURN_Y + 1;
                } else if (AI_TURN && (PLAYER_TURN_Y - 2) >= 0 && map[PLAYER_TURN_X][PLAYER_TURN_Y - 2] == EMPTY_DOT) {
                    map[PLAYER_TURN_X][PLAYER_TURN_Y - 2] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X;
                    AI_TURN_Y = PLAYER_TURN_Y - 2;
                }
            } else if (AI_TURN && (PLAYER_TURN_Y + 1) < MAP_SIZE && map[PLAYER_TURN_X][PLAYER_TURN_Y + 1] == DOT_X) {
                if ((PLAYER_TURN_Y - 1) >= 0 && map[PLAYER_TURN_X][PLAYER_TURN_Y - 1] == EMPTY_DOT) {
                    map[PLAYER_TURN_X][PLAYER_TURN_Y - 1] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X;
                    AI_TURN_Y = PLAYER_TURN_Y - 1;
                } else if (AI_TURN && (PLAYER_TURN_Y + 2) < MAP_SIZE && map[PLAYER_TURN_X][PLAYER_TURN_Y + 2] == EMPTY_DOT) {
                    map[PLAYER_TURN_X][PLAYER_TURN_Y + 2] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X;
                    AI_TURN_Y = PLAYER_TURN_Y + 2;
                }
            }

            if (AI_TURN && (PLAYER_TURN_X + 1) < MAP_SIZE && (PLAYER_TURN_Y + 1) < MAP_SIZE &&
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y + 1] == DOT_X) {
                if ((PLAYER_TURN_X - 1) >= 0 && (PLAYER_TURN_Y - 1) >= 0 && map[PLAYER_TURN_X - 1][PLAYER_TURN_Y - 1] == EMPTY_DOT) {
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y - 1] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X - 1;
                    AI_TURN_Y = PLAYER_TURN_Y - 1;
                } else if ((PLAYER_TURN_X + 2) < MAP_SIZE && (PLAYER_TURN_Y + 2) < MAP_SIZE && map[PLAYER_TURN_X + 2][PLAYER_TURN_Y + 2] == EMPTY_DOT) {
                    map[PLAYER_TURN_X + 2][PLAYER_TURN_Y + 2] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X + 2;
                    AI_TURN_Y = PLAYER_TURN_Y + 2;
                }
            } else if (AI_TURN && (PLAYER_TURN_X - 1) >= 0 && (PLAYER_TURN_Y - 1) >= 0 &&
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y - 1] == DOT_X) {
                if ((PLAYER_TURN_X + 1) < MAP_SIZE && (PLAYER_TURN_Y + 1) < MAP_SIZE &&
                        map[PLAYER_TURN_X + 1][PLAYER_TURN_Y + 1] == EMPTY_DOT) {
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y + 1] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X + 1;
                    AI_TURN_Y = PLAYER_TURN_Y + 1;
                } else if ((PLAYER_TURN_X - 2) >= 0 && (PLAYER_TURN_Y - 2) >= 0 && map[PLAYER_TURN_X - 2][PLAYER_TURN_Y - 2] == EMPTY_DOT) {
                    map[PLAYER_TURN_X - 2][PLAYER_TURN_Y - 2] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X - 2;
                    AI_TURN_Y = PLAYER_TURN_Y - 2;
                }
            }

            if (AI_TURN && (PLAYER_TURN_X + 1) < MAP_SIZE && (PLAYER_TURN_Y - 1) >= 0 &&
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y - 1] == DOT_X) {
                if ((PLAYER_TURN_X - 1) >= 0 && (PLAYER_TURN_Y + 1) < MAP_SIZE && map[PLAYER_TURN_X - 1][PLAYER_TURN_Y + 1] == EMPTY_DOT) {
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y + 1] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X - 1;
                    AI_TURN_Y = PLAYER_TURN_Y + 1;
                } else if ((PLAYER_TURN_X + 2) < MAP_SIZE && (PLAYER_TURN_Y - 2) >= 0 && map[PLAYER_TURN_X + 2][PLAYER_TURN_Y - 2] == EMPTY_DOT) {
                    map[PLAYER_TURN_X + 2][PLAYER_TURN_Y - 2] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X + 2;
                    AI_TURN_Y = PLAYER_TURN_Y - 2;
                }
            } else if (AI_TURN && (PLAYER_TURN_X - 1) >= 0 && (PLAYER_TURN_Y + 1) < MAP_SIZE &&
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y + 1] == DOT_X) {
                if ((PLAYER_TURN_X + 1) < MAP_SIZE && (PLAYER_TURN_Y - 1) >= 0 &&
                        map[PLAYER_TURN_X + 1][PLAYER_TURN_Y - 1] == EMPTY_DOT) {
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y - 1] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X + 1;
                    AI_TURN_Y = PLAYER_TURN_Y - 1;
                } else if ((PLAYER_TURN_X - 2) >= 0 && (PLAYER_TURN_Y + 2) < MAP_SIZE && map[PLAYER_TURN_X - 2][PLAYER_TURN_Y + 2] == EMPTY_DOT) {
                    map[PLAYER_TURN_X - 2][PLAYER_TURN_Y + 2] = DOT_O;
                    AI_TURN = false;
                    AI_TURN_X = PLAYER_TURN_X - 2;
                    AI_TURN_Y = PLAYER_TURN_Y + 2;
                }
            }

            if (AI_TURN && (PLAYER_TURN_X + 2) < MAP_SIZE && map[PLAYER_TURN_X + 2][PLAYER_TURN_Y] == DOT_X &&
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y] == EMPTY_DOT) {
                map[PLAYER_TURN_X + 1][PLAYER_TURN_Y] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X + 1;
                AI_TURN_Y = PLAYER_TURN_Y;
            }

            if (AI_TURN && (PLAYER_TURN_Y + 2) < MAP_SIZE && map[PLAYER_TURN_X][PLAYER_TURN_Y + 1] == EMPTY_DOT &&
                    map[PLAYER_TURN_X][PLAYER_TURN_Y + 2] == DOT_X ) {
                map[PLAYER_TURN_X][PLAYER_TURN_Y + 1] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X;
                AI_TURN_Y = PLAYER_TURN_Y + 1;
            }

            if (AI_TURN && (PLAYER_TURN_X - 2) >= 0 && map[PLAYER_TURN_X - 1][PLAYER_TURN_Y] == EMPTY_DOT &&
                    map[PLAYER_TURN_X - 2][PLAYER_TURN_Y] == DOT_X) {
                map[PLAYER_TURN_X - 1][PLAYER_TURN_Y] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X - 1;
                AI_TURN_Y = PLAYER_TURN_Y;
            }

            if (AI_TURN && (PLAYER_TURN_Y - 2) >= 0 && map[PLAYER_TURN_X][PLAYER_TURN_Y - 1] == EMPTY_DOT &&
                    map[PLAYER_TURN_X][PLAYER_TURN_Y - 2] == DOT_X) {
                map[PLAYER_TURN_X][PLAYER_TURN_Y - 1] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X;
                AI_TURN_Y = PLAYER_TURN_Y - 1;
            }

            if (AI_TURN && (PLAYER_TURN_X + 2) < MAP_SIZE && (PLAYER_TURN_Y + 2) < MAP_SIZE &&
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y + 1] == EMPTY_DOT && map[PLAYER_TURN_X + 2][PLAYER_TURN_Y + 2] == DOT_X) {
                map[PLAYER_TURN_X + 1][PLAYER_TURN_Y + 1] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X + 1;
                AI_TURN_Y = PLAYER_TURN_Y + 1;
            }

            if (AI_TURN && (PLAYER_TURN_X - 2) >= 0 && (PLAYER_TURN_Y + 2) < MAP_SIZE &&
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y + 1] == EMPTY_DOT && map[PLAYER_TURN_X - 2][PLAYER_TURN_Y + 2] == DOT_X) {
                map[PLAYER_TURN_X - 1][PLAYER_TURN_Y + 1] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X - 1;
                AI_TURN_Y = PLAYER_TURN_Y + 1;
            }

            if (AI_TURN && (PLAYER_TURN_X + 2) < MAP_SIZE && (PLAYER_TURN_Y - 2) >= 0 &&
                    map[PLAYER_TURN_X + 1][PLAYER_TURN_Y - 1] == EMPTY_DOT && map[PLAYER_TURN_X + 2][PLAYER_TURN_Y - 2] == DOT_X) {
                map[PLAYER_TURN_X + 1][PLAYER_TURN_Y - 1] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X + 1;
                AI_TURN_Y = PLAYER_TURN_Y - 1;
            }

            if (AI_TURN && (PLAYER_TURN_X - 2) >= 0 && (PLAYER_TURN_Y - 2) >= 0 &&
                    map[PLAYER_TURN_X - 1][PLAYER_TURN_Y - 1] == EMPTY_DOT && map[PLAYER_TURN_X - 2][PLAYER_TURN_Y - 2] == DOT_X) {
                map[PLAYER_TURN_X - 1][PLAYER_TURN_Y - 1] = DOT_O;
                AI_TURN = false;
                AI_TURN_X = PLAYER_TURN_X - 1;
                AI_TURN_Y = PLAYER_TURN_Y - 1;
            }
            //  Это условие впринципе не должно срабатывать, но если вдруг предыдущие условия где-то не сработали
            //  комп все равно должен сходить
            if (AI_TURN) {
                aITurnRandom();
            }
        }
    }

    public static void aITurnRandom() {
        int x, y;
        do {
            x = (int) (Math.random() * MAP_SIZE);
            y = (int) (Math.random() * MAP_SIZE);
        } while (!isTurnValid(x, y));
        map[x][y] = DOT_O;
        AI_TURN_X = x;
        AI_TURN_Y = y;
    }

    public static void playerTurn() {
        System.out.println("Введите две координаты x, y через пробел, диапозон чисел от 1 до " + MAP_SIZE + ":");
        int x, y;
        // здесь нельзя вводить текст, можно переделать на String, и уже его проверять на числа,
        // и 2 глобальные переменные использовать, но встрочке выше пишется правила что нужно вводить
        // если нужно могу переделать
        do {
            x = sc.nextInt();
            y = sc.nextInt();
        } while (!isTurnValid(x - 1, y - 1));

        map[x - 1][y - 1] = DOT_X;
        System.out.println();

        PLAYER_TURN_X = x - 1;
        PLAYER_TURN_Y = y - 1;

    }

    public static boolean isTurnValid(int x, int y) {
        if (x < 0 || x > MAP_SIZE - 1 || y < 0 || y > MAP_SIZE - 1) {
            return false;
        }
        if (map[x][y] == EMPTY_DOT) {
            return true;
        }

        return false;
    }

    public static boolean checkWin(char symbol) {

        if (symbol == DOT_X) {
            if ((checkLineHorizontal(PLAYER_TURN_X, PLAYER_TURN_Y) == DOTS_TO_WIN) ||
                    (checkLineVertical(PLAYER_TURN_X, PLAYER_TURN_Y) == DOTS_TO_WIN) ||
                    (checkDiagonalLeftToRight(PLAYER_TURN_X, PLAYER_TURN_Y) == DOTS_TO_WIN) ||
                    (checkDiagonalRightToLeft(PLAYER_TURN_X, PLAYER_TURN_Y) == DOTS_TO_WIN)) {

                return true;
            }
        } else {
            if ((checkLineHorizontal(AI_TURN_X, AI_TURN_Y) == DOTS_TO_WIN) ||
                    (checkLineVertical(AI_TURN_X, AI_TURN_Y) == DOTS_TO_WIN) ||
                    (checkDiagonalLeftToRight(AI_TURN_X, AI_TURN_Y) == DOTS_TO_WIN) ||
                    (checkDiagonalRightToLeft(AI_TURN_X, AI_TURN_Y) == DOTS_TO_WIN)) {

                return true;
            }
        }


        return false;
    }

    // метод возвращает количество сиволов подряд в горизонтале
    public static byte checkLineHorizontal(int x, int y) {
        char buffer = map[x][y];
        boolean countBreakTop = true;
        boolean countBreakBottom = true;
        byte count = 1;

        for (int i = 1; i < DOTS_TO_WIN; i++) {
            // условие сравнивает ячейки по горизонтале вправо до первого не совпадения
            if (((y + i) < MAP_SIZE) && countBreakBottom) {
                if (buffer == map[x][y + i]) {
                    count++;
                } else {
                    countBreakBottom = false;
                }
            }
            //это условие сравнивает ячейки по горизовнтале влево до первого не совпадения
            if (((y - i) >= 0) && countBreakTop) {
                if (buffer == map[x][y - i]) {
                    count++;
                } else {
                    countBreakTop = false;
                }
            }
        }

        return count;
    }

    // метод возвращает количество сиволов подряд по вертикале
    public static byte checkLineVertical(int x, int y) {
        //можно без буфера делать, прямо в условии сравнивать
        char buffer = map[x][y];
        boolean countBreakLeft = true;
        boolean countBreakRight = true;
        byte count = 1;

        for (int i = 1; i < DOTS_TO_WIN; i++) {
            // это условие сравнивает ячейки по вертикале вниз до первого не совпадения
            if (((x + i) < MAP_SIZE) && countBreakRight) {
                if (buffer == map[x + i][y]) {
                    count++;
                } else {
                    countBreakRight = false;
                }

            }
            // это условие сравнивает ячейки по вертикале вверх до первого не совпадения
            if (((x - i) >= 0) && countBreakLeft) {
                if (buffer == map[x - i][y]) {
                    count++;
                } else {
                    countBreakLeft = false;
                }

            }

        }
        return count;
    }

    public static byte checkDiagonalLeftToRight(int x, int y) {
        char buffer = map[x][y];
        boolean countBreakTop = true;
        boolean countBreakBottom = true;
        byte count = 1;

        for (int i = 1; i < DOTS_TO_WIN; i++) {
            // проверка  по диагонали слева направо вниз до первого не совпадения
            if ((x + i) < MAP_SIZE && (y + i) < MAP_SIZE && countBreakBottom) {
                if (buffer == map[x + i][y + i]) {
                    count++;
                } else {
                    countBreakBottom = false;
                }
            }
            // проверка  по диагонали справа налево вверх до первого не совпадения
            if ((x - i) >= 0 && (y - i) >= 0 && countBreakTop) {
                if (buffer == map[x - i][y - i]) {
                    count++;
                } else {
                    countBreakTop = false;
                }
            }
        }

        return count;
    }

    public static byte checkDiagonalRightToLeft(int x, int y) {
        char buffer = map[x][y];
        boolean countBreakTop = true;
        boolean countBreakBottom = true;
        byte count = 1;

        for (int i = 1; i < DOTS_TO_WIN; i++) {

            if ((x + i) < MAP_SIZE && (y - i) >= 0 && countBreakTop) {
                if (buffer == map[x + i][y - i]) {
                    count++;
                } else {
                    countBreakTop = false;
                }

            }

            if ((x - i) >= 0 && (y + i) < MAP_SIZE && countBreakBottom) {
                if (buffer == map[x - i][y + i]) {
                    count++;
                } else {
                    countBreakBottom = false;
                }

            }
        }

        return count;
    }

    public static boolean checkDraw() {
        // получилось без счетчика сделать условие, это уменьшает итерации, проверяет до первого нахождения совпадения
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == EMPTY_DOT) {
                    return true;
                }
            }
        }
        return false;
    }
}


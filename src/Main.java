import java.util.Scanner;

public class Main {

    static final int SIZE = 3;
    static char map[][] ;
    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'x';
    static final char DOT_0 = '0';



    public static void main(String[] args) {
        setMap();
        paintMap();
        while (true) {
            turnHuman();
            paintMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победа человека");
                break;
            }
            if (checkDraw()) {
                System.out.println("ничья");
            }
            turnAi();
            paintMap();
            if (checkWin(DOT_0)) {
                System.out.println("Победа компьютера");
                break;
            }
            if (checkDraw()) {
                System.out.println("победа компьютера");
            }
        }
    }

    static void setMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0;i < SIZE;i++) {
            for (int j= 0;j < SIZE;j++) {
                map[i][j] = DOT_EMPTY;
            }
        }

    }
    static void paintMap() {
        for (int i = 0;i <= SIZE;i++) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i =0;i < SIZE;i++) {
            System.out.print((i+1)+" ");
            for (int j = 0;j < SIZE;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    static void turnHuman() {
        System.out.println("Введите координаты X");
        int x;
        int y;
        do {


            Scanner sc = new Scanner(System.in);
            x = sc.nextInt()-1;
            y = sc.nextInt()-1;



        } while (!isCellValid(x,y));

        map[y][x] = DOT_X;

    }

    static void turnAi() {
        int x;
        int y;
        do {
            x = (int) (Math.random() * map.length);
            y = (int) (Math.random() * map.length);
        }while (!isCellValid(x,y));
        map[y][x] = DOT_0;
        System.out.println("Ход компьютера:"+y+x);
    }


    static boolean isCellValid(int x,int y) {
        if (x >= SIZE || x < 0 || y >= SIZE || y < 0) {
            return false;
        }
        if(map[y][x] == DOT_EMPTY ) {
            return true;
        }
        return false;
    }

    static boolean checkWin(char DOT) {
        if(checkLine(DOT,0,0)) {
            return true;
        }
        if (checkDiagonal(DOT)){
            return true;
        }
        else {
            return false;
        }
    }







    static boolean checkLine(char DOT,int i,int j) {
        for (int horizontal = i; horizontal < SIZE; horizontal++) {

            int checkVertical = 0;
            int checkHorizontal = 0;
            for (int vertical = i; vertical < SIZE; vertical++) {
                if (map[horizontal][vertical] == DOT) {
                    checkHorizontal++;
                } else {
                    checkHorizontal = 0;
                }
                if (map[vertical][horizontal] == DOT) {
                    checkVertical++;
                } else {
                    checkVertical = 0;
                }
            }
            if ( checkHorizontal == SIZE || checkVertical == SIZE) return true;
        }
        return false;
    }
    static boolean checkDiagonal(char DOT) {
        for(int i = 0;i < map.length;i++) {
            int firstDiagonal = 0;
            int secondDiagonal = 0;
            if (map[i][i] == DOT) {
                firstDiagonal++;
            }else {
                firstDiagonal =0;
            }
            if(map[i][map.length - 1 - i] == DOT) {
                secondDiagonal++;
            }else {
                secondDiagonal = 0;
            }
            if (firstDiagonal == SIZE || secondDiagonal == SIZE) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDraw() {

        for(int i = 0;i < map.length;i++) {
            for (int j = 0;j < map.length;j++){
                if(map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}

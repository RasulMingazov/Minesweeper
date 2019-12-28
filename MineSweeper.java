import java.util.Scanner;

public class MineSweeper {static char[][] matric;
    static int[][] flag;
    static int n, m, bomb;

    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 1, 1, -1};

    static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) {
        DoItBegin();
        DoItThen();
    }

    static void DoItBegin() {
        System.out.println("Размер");
        n = inp.nextInt();
        m = inp.nextInt();
        System.out.println("Число бомб");
        bomb = inp.nextInt();

        matric = new char[n][m];
        flag = new int[n][m];

        SetBombs();
        Print(Copy());

    }

    static void DoItThen() {
        for (; ; ) {

            System.out.println(" \n1 - Выбрать точку \n2 - Отметить флажок \n3 - Закончить игру");
            int Choose = inp.nextInt();

            System.out.println("Введите координаты точки");
            int x = inp.nextInt();
            int y = inp.nextInt();

            if (Choose == 3) {
                System.out.println("Вы закончили игру...");
                break; }

            if (Choose == 2) {
                Flag(x, y); }

            if (Choose == 1) {

                if (matric[y][x] == 'Б') {
                    System.out.println("Вы взорваны!");
                    Print(matric);
                    break; }

                Recursion(y, x);

                if (Win() == 0) {
                    System.out.println();
                    Print(matric);
                    System.out.println("Вы настоящий сапер!");
                    break;
                }
                Print(Copy());
            }
        }
    }

    static void Print(char[][] matric) {

        System.out.print("  ");
        for (int i = 0; i < m; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < m; i++) {
            System.out.print("--");
        }
        System.out.println();


        for (int i = 0; i < n; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < m; j++) {
                System.out.print(matric[i][j] + " ");
            }
            System.out.println();
        }
    }

    static char[][] SetBombs() {
        for (int k = 0; k < bomb; k++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * m);
            if (matric[a][b] != 'Б') {
                matric[a][b] = 'Б';
            } else bomb++;
        }
        return matric;
    }

    static void Recursion(int y, int x) {

        if (matric[y][x] >= '0' && matric[y][x] <= '9') {
            return;
        }
        int k = Quantity(x, y);

        matric[y][x] = (char) (k + '0');

        if (k == 0) {

            for (int l = 0; l < 8; l++) {

                if (0 <= y + dx[l] && y + dx[l] < n && 0 <= x + dy[l] && x + dy[l] < n) {

                    Recursion(y + dx[l], x + dy[l]);
                }
            }
        }
    }

    static int Quantity(int x, int y) {

        char q = 0;

        if (y != 0 && x != 0 && y != n - 1 && x != m - 1 && matric[y][x] != 'Б') {

            for (int i = y - 1; i < y + 2; i++) {
                for (int j = x - 1; j < x + 2; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }
            return q;
        }
        if (y == 0 && x == 0 && matric[y][x] != 'Б') {

            for (int i = 0; i < 2; i++) {
                for (int j = x; j < 2; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }
            return q;
        }
        if (y == n - 1 && x == m - 1 && matric[y][x] != 'Б') {

            for (int i = y - 1; i < y + 1; i++) {
                for (int j = x - 1; j < x + 1; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }

            return q;
        }
        if (y == n - 1 && x == 0 && matric[y][x] != 'Б') {
            for (int i = y - 1; i < y + 1; i++) {
                for (int j = x; j < x + 2; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }
            return q;
        }
        if (y == 0 && x == m - 1 && matric[y][x] != 'Б') {
            for (int i = y; i < y + 2; i++) {
                for (int j = x - 1; j < x + 1; j++) {
                    if (matric[i][j] == 'Б')

                        q++;
                }
            }
            return q;
        }
        if (y == 0 && x != m - 1 && x != 0 && matric[y][x] != 'Б') {

            for (int i = y; i < y + 2; i++) {
                for (int j = x - 1; j < x + 2; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }

            return q;
        }
        if (y == n - 1 && x != m - 1 && y != 0 && matric[y][x] != 'Б') {
            for (int i = y - 1; i < y + 1; i++) {
                for (int j = x - 1; j < x + 2; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }
            return q;
        }
        if (x == 0 && y != n - 1 && y != 0 && matric[y][x] != 'Б') {

            for (int i = y - 1; i < y + 2; i++) {
                for (int j = x; j < x + 2; j++) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }
            return q;
        }
        if (x == m - 1 && y != n - 1 && y != 0 && matric[y][x] != 'Б') {

            for (int i = y + 1; i > y - 2; i--) {
                for (int j = x; j > x - 2; j--) {
                    if (matric[i][j] == 'Б')
                        q++;
                }
            }
            return q;
        }

        return 99;
    }
    static char[][] Copy() {
        char[][] matriccopy = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matric[i][j] == 'Б') {
                    matriccopy[i][j] = '\u0000'; }
                else matriccopy[i][j] = matric[i][j];
            }
        }
        return matriccopy;
    }
    static int Win() {
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matric[i][j] == ' ' || matric[i][j] == '\u0000')
                    k++;
            }
        }
        return k;
    }
    static void Flag(int x, int y) {
        System.out.println("\n1 - Поставить флажок \n2 - Убрать флажок");
        int Choose2 = inp.nextInt();

        if (Choose2 == 1) {
            if (matric[y][x] == 'Б')
                flag[y][x] = 1;
            else
                flag[y][x] = 0;
            matric[y][x] = 'Ф';
        }
        if (Choose2 == 2) {
            if (flag[y][x] == 1)
                matric[y][x] = 'Б';
            if (flag[y][x] == 0) {
                matric[y][x] = '\u0000';
            }
        }
        Print(Copy());
    }
}

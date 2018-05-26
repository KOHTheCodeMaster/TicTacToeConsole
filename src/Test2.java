/*
 *  2nd Tester Class for Game Logic.
 */

import java.util.Scanner;

public class Test2 {
    public int move1 = 1, move2 = 2, localCount = 0;
    Scanner sc = new Scanner(System.in);
    int vertical[] = new int[3], horizontal[] = new int[3];
    int horizontalSum = 0;
    boolean turn1, turn2;
    private int a[][] = new int[3][3];
    private int resultMatrix[][] = new int[3][3];

    public static void main(String ar[]) {
        Test2 obj = new Test2();
        obj.initA();
        obj.gameStart();

    }

    private void initA() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                resultMatrix[i][j] = 0;
            }
        }
    }

    boolean isWinner() {
        System.out.println("Checking GAME Over");
        int localSum = 0;
        int columnSum[] = new int[3];
        int rowSum[] = new int[3];
        boolean win = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //if(i==0)
                columnSum[i] += resultMatrix[j][i];
                rowSum[i] += resultMatrix[i][j];
            }
        }

        //check cross1.
        int s1 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    s1 += resultMatrix[i][j];
            }
        }

        //check cross2.
        int s2 = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if ((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 0))
                    s2 += resultMatrix[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (columnSum[i] == 3 || rowSum[i] == 3
                    || columnSum[i] == 15 || rowSum[i] == 15
                    || s1 == 3 || s1 == 15 || s2 == 3 || s2 == 15) {
                System.out.println("Win");
                return true;
            }
        }
        return false;
    }

    void showResultMatrix() {

        System.out.println("Showing RM");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(resultMatrix[i][j]);
            }
            System.out.println();
        }
    }

    void gameStart() {

        boolean ans = false, moveAllowed = false;
        while (localCount < 5) {
            System.out.println("p1");
            turn1 = true;
            turn2 = false;
            moveAllowed = false;
            while (!moveAllowed) {
                move1 = sc.nextInt();
                moveAllowed = isValidMov(move1);
                if (moveAllowed) {
                    System.out.println("ko");
                } else {
                    System.out.println("Invalid Move.\nPlease Try Again!");
                }
            }
            a1(move1);
            updateResultMatrix(move1);
            ans = isWinner();
            if (!ans) {
                System.out.println("p2");
                turn1 = false;
                turn2 = true;
                moveAllowed = false;
                while (!moveAllowed) {
                    move2 = sc.nextInt();
                    moveAllowed = isValidMov(move2);
                    if (moveAllowed) {
                        System.out.println("koh");
                    } else {
                        System.out.println("Invalid Move.\nPlease Try Again!");
                    }
                }
                a1(move2);
                updateResultMatrix(move2);
                localCount++;
                ans = isWinner();
                if (ans) {
                    System.out.println("2 Wins!");
                    showResultMatrix();
                    System.exit(0);
                }
            } else {
                System.out.println("1 Wins!");
                showResultMatrix();
                System.exit(0);
            }
        }
    }

    private boolean isValidMov(int move1) {
        boolean ans = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int ju = 0;
                //showResultMatrix();
                // System.out.println();
                switch (move1) {
                    case 1:
                        if (resultMatrix[0][0] == 0)
                            ans = true;
                        break;
                    case 2:
                        int j2u = 0;
                        if (resultMatrix[0][1] == 0)
                            ans = true;
                        break;
                    case 3:
                        if (resultMatrix[0][2] == 0)
                            ans = true;
                        break;
                    case 4:
                        if (resultMatrix[1][0] == 0)
                            ans = true;
                        break;
                    case 5:
                        if (resultMatrix[1][1] == 0)
                            ans = true;
                        break;
                    case 6:
                        if (resultMatrix[1][2] == 0)
                            ans = true;
                        break;
                    case 7:
                        if (resultMatrix[2][0] == 0)
                            ans = true;
                        break;
                    case 8:
                        if (resultMatrix[2][1] == 0)
                            ans = true;
                        break;
                    case 9:
                        if (resultMatrix[2][2] == 0)
                            ans = true;
                        break;
                    default:
                        return false;
                }
            }
            //System.out.println();
        }
        return ans;
    }

    private void a1(int move) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (move) {
                    case 1:
                        if (i == 0 && j == 0)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 2:
                        if (i == 0 && j == 1)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 3:
                        if (i == 0 && j == 2)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 4:
                        if (i == 1 && j == 0)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 5:
                        if (i == 1 && j == 1)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 6:
                        if (i == 1 && j == 2)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 7:
                        if (i == 2 && j == 0)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 8:
                        if (i == 2 && j == 1)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                    case 9:
                        if (i == 2 && j == 2)
                            if (turn1)
                                a[i][j] = 1;
                            else
                                a[i][j] = 2;
                        break;
                }
                //gameMatrix[i][j] = 1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    void updateResultMatrix(int move) {

        System.out.println("Updating RM");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (move) {
                    case 1:
                        if (i == 0 && j == 0)
                            if (turn1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 2:
                        if (i == 0 && j == 1)
                            if (turn1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 3:
                        if (i == 0 && j == 2)
                            if (turn1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 4:
                        if (i == 1 && j == 0)
                            if (turn1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 5:
                        if (i == 1 && j == 1)
                            if (turn1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 6:
                        if (i == 1 && j == 2)
                            if (turn1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 7:
                        if (i == 2 && j == 0)
                            if (turn1) resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 8:
                        if (i == 2 && j == 1)
                            if (turn2)
                                resultMatrix[i][j] = 5;
                            else
                                resultMatrix[i][j] = 1;
                        break;
                    case 9:
                        if (j == 2 && i == 2)
                            if (turn2)
                                resultMatrix[i][j] = 5;
                            else
                                resultMatrix[i][j] = 1;
                        break;
                }
            }
        }
    }

}
/*
 *  Code Developed by,
 *  ~K.O.H..!!
 */

/*
 *  Game Controller Class.
 */

import java.util.Scanner;

public class TicTacToe {

    private Scanner sc = new Scanner(System.in);

    private String[][][] gameMatrix = new String[10][3][9];
    private String[][] X = new String[3][9];
    private String[][] O = new String[3][9];
    private int resultMatrix[][] = new int[3][3];
    private boolean turnOfPlayer1, turnOfPlayer2;
    private String player1 = "P1", player2 = "P2";

    TicTacToe() {

        initMatrices();

    }

    private void initMatrices() {

        //  Initialize Result Matrix.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                resultMatrix[i][j] = 0;
            }
        }

        //  Initialize X.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (((i == 0) || (i == 2)) && (j == 2 || j == 6))
                    X[i][j] = "o";
                else if (i == 1 && j == 4)
                    X[i][j] = "o";
                else
                    X[i][j] = "-";
            }
        }

        //  Initialize O.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 2) {
                    if (j >= 2 && j <= 6)
                        O[i][j] = "o";
                    else
                        O[i][j] = "-";
                } else {
                    if (j == 2 || j == 6)
                        O[i][j] = "o";
                    else
                        O[i][j] = "-";
                }
            }
        }

        //Initialize Game Matrix.
        //if (gameMatrix[0][0][0] == null) {
        for (int k = 0; k <= 9; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    gameMatrix[k][i][j] = "-";
                }
            }
        }
    }

    public void begin() {
        Scanner sc2 = new Scanner(System.in);
        boolean exitFlag = false, restartFlag = false;
        while (!exitFlag) {
            System.out.println("Welcome to Tic-Tac-Toe..!!");
            System.out.println("1. Play\t2. Rules\t3. Set Player Names\t?. Exit");
            int menuChoice = sc2.nextInt();
            switch (menuChoice) {
                case 1:
                    initMatrices();
                    gameStart();
                    if (!wannaRestart())
                        exitFlag = true;
                    break;
                case 2:
                    rule();
                    break;
                case 3:
                    setPlayerNames();
                    break;
                default:
                    exitFlag = true;
                    System.exit(0);
            }
        }
    }

    private void gameStart() {
        int moveCount = 0;
        int movePlayer1 = -1, movePlayer2 = -1;
        boolean ans = false, allowedMove = false;

        while (!isGameOver()) {
            allowedMove = false;
            turnOfPlayer1 = true;
            turnOfPlayer2 = false;
            while (!allowedMove) {
                System.out.println("|<========[" + player1 + "'s Turn]========>|");
                System.out.print("Enter [1-9]: ");
                movePlayer1 = sc.nextInt();
                allowedMove = isValidMove(movePlayer1);
                if (allowedMove) {

                } else {
                    System.out.println("Invalid Move.");
                    System.out.println("Please Try Again!");
                }
            }
            updateGameGrid(movePlayer1);
            renderGame();
            updateResultMatrix(movePlayer1);
            ans = isGameOver();

            if (ans) {
                System.out.println(player1 + " Wins!");
            }
            if (!ans) {
                System.out.println("|<========[" + player2 + "'s Turn]========>|");
                System.out.print("Enter [1-9]: ");
                turnOfPlayer1 = false;
                turnOfPlayer2 = true;
                allowedMove = false;
                while (!allowedMove) {
                    movePlayer2 = sc.nextInt();
                    allowedMove = isValidMove(movePlayer2);
                    if (allowedMove) ;
                    else
                        System.out.println("Invalid Move.\nPlease Try Again!");
                }
                updateGameGrid(movePlayer2);
                renderGame();
                updateResultMatrix(movePlayer2);
                ans = isGameOver();
                moveCount++;
                if (ans) {
                    System.out.println(player2 + " Wins!");
                }
            }
        }
        return;    // If True then Back to Main Menu.
    }

    private boolean wannaRestart() {
        boolean ans = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Restart? (y/n)");
        String yes = input.nextLine();
        yes = yes.toLowerCase();
        if (yes.equals("y") || yes.equals("yes"))
            ans = true;

        return ans;
    }

    private boolean isValidMove(int movePlayer1) {
        boolean ans = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //showResultMatrix();
                // System.out.println();
                switch (movePlayer1) {
                    case 1:
                        if (resultMatrix[0][0] == 0)
                            ans = true;
                        break;
                    case 2:
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

    boolean isGameOver() {

        int columnSum[] = new int[3];
        int rowSum[] = new int[3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //if(i==0)
                columnSum[i] += resultMatrix[j][i];
                rowSum[i] += resultMatrix[i][j];
            }
        }

        //check cross1.
        int diagonal1 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    diagonal1 += resultMatrix[i][j];
            }
        }

        //check cross2.
        int diagonal2 = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if ((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 0))
                    diagonal2 += resultMatrix[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (columnSum[i] == 3 || rowSum[i] == 3
                    || columnSum[i] == 15 || rowSum[i] == 15
                    || diagonal1 == 3 || diagonal1 == 15 || diagonal2 == 3 || diagonal2 == 15) {
                System.out.println("Win");
                return true;
            }
        }
        return false;
    }


    void updateGameGrid(int move) {

        //  Set K to O/X.
        for (int k = 1; k <= 9; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    if (turnOfPlayer1 && (k == move))
                        gameMatrix[k][i][j] = O[i][j];
                    else if (turnOfPlayer2 && (k == move))
                        gameMatrix[k][i][j] = X[i][j];
                        /*else {
                            System.out.println("Wrong Turn!!\nError!");
                            System.exit(0);
                        }
                        */
                }
            }
        }
        //end of fun.
    }

    void renderGame() {

        int k = 1, count = 1, rowCount = 1;
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int i = 0; i < 3; i++) {

                switch (rowCount) {

                    case 1:
                    case 2:
                    case 3:
                        count = 1;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        count = 4;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        count = 7;
                        break;
                    default:
                        count = 0;
                        System.out.println("Error.");
                }

                for (int boxColumn = 0; boxColumn < 3; boxColumn++) {

                    k = count;
                    for (int j = 0; j < 9; j++) {
                        System.out.print(gameMatrix[k][i][j]);
                    }
                    System.out.print("|");
                    count++;
                }
                System.out.println();
                rowCount++;

                if ((rowCount == 4) || (rowCount == 7) || (rowCount == 10))
                    System.out.println("______________________________");
            }
        }

    }


    private void rule() {

    }

    private void setPlayerNames() {

        System.out.println("Enter Player 1's name:");
        this.player1 = sc.next();
        System.out.println("Enter Player 2's name:");
        this.player2 = sc.next();
        System.out.println("Player Names saved successfully!");

    }

    //Update Resultant Matrix.
    private void updateResultMatrix(int currentMove) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (currentMove) {
                    case 1:
                        if (i == 0 && j == 0)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 2:
                        if (i == 0 && j == 1)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 3:
                        if (i == 0 && j == 2)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 4:
                        if (i == 1 && j == 0)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 5:
                        if (i == 1 && j == 1)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 6:
                        if (i == 1 && j == 2)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 7:
                        if (i == 2 && j == 0)
                            if (turnOfPlayer1) resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 8:
                        if (i == 2 && j == 1)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                    case 9:
                        if (i == 2 && j == 2)
                            if (turnOfPlayer1)
                                resultMatrix[i][j] = 1;
                            else
                                resultMatrix[i][j] = 5;
                        break;
                }
            }
        }
    }

    //  Show Result Matrix.
    void showResultMatrix() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(resultMatrix[i][j]);
            }
            System.out.println();
        }
    }

}   //End of Class.
/*
 *  Code Developed by,
 *  ~K.O.H..!!
 */


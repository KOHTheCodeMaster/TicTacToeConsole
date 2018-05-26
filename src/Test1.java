/*
 *  1st Tester Class for Basic Logic of Matrix & Boolean.
 */

public class Test1 {

    private static int a[][] = new int[3][3];

    public static void main(String ar[]) {
        int move = 1;
/*
        boolean as = true, a2 = false;
        while(!a2){
            if(!as)
                System.out.println(a2);
            else
                System.out.println("g");
            break;
        }
*/

        a1(move);
        a1(3);
    }

    private static void a1(int move) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (move) {
                    case 1:
                        if (i == 0 && j == 0)
                            a[i][j] = 1;
                        break;
                    case 2:
                        if (i == 0 && j == 1)
                            a[i][j] = 1;
                        break;
                    case 3:
                        if (i == 0 && j == 2)
                            a[i][j] = 1;
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

}
/*
 *  Code Developed by,
 *  ~K.O.H..!!
 */

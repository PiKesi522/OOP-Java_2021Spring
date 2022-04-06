package Lab02;
public class Lab_02_14 {
    public static void main(String[] args)
    {
        int N = 9; // The size of the Martix is N*N, given that the N is 9
        int[][] table = new int[N][N]; // Make a table that the answer reserved

        int num = 1; // We use joint number to fill the table
        int max = N * N; // The break situation
        table[0][0] = 1; // The orignal situation

        int hor = 0,ver = 0;
        while(true)
        {
            hor++;
            while(table[ver][hor] == 0) // GO RIGHT
            {
                table[ver][hor] = ++num;
                hor++;
                if(hor >= N)
                    break;
            }
            hor--;
            
            ver++;
            while(table[ver][hor] == 0) // GO DOWN
            {
                table[ver][hor] = ++num;
                ver++;
                if(ver >= N)
                    break;
            }
            ver--;

            hor--;
            while(table[ver][hor] == 0) // GO LEFT
            {
                table[ver][hor] = ++num;
                hor--;
                if(hor < 0)
                    break;
            }
            hor++;

            ver--;
            while(table[ver][hor] == 0) // GO UP
            {
                table[ver][hor] = ++num;
                ver--;
                if(ver < 0)
                    break;
            }
            ver++;
            
            if(num == max) // the number will end here
                break;
        }



        for(int i = 0; i < N; i++) // Print the table
        {
            for(int j = 0 ; j < N; j++)
            {
                if(table[i][j] < 10)
                    System.out.print('0'); // Make it more readability
                System.out.print(table[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}

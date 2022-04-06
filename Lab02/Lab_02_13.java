package Lab02;
public class Lab_02_13 {
    public static void main(String[] args)
    {
        int[] loc = {5,2,3,1,4}; // Any Array other than this given array is unpremittable

        /*for(int i = 0;i < 5; i++) // This fake Array is established to show the present situation
        {                           // in the table
            for(int j = 0; j < 5;j++)
            {
                if(j == loc[i] - 1)
                {
                    table[j][i] = 'Q';
                }
                else
                {
                    table[j][i] = '*';
                }
            }
        }*/

        // According to the data given
        // The queen only apeared in each line for only once
        // At the same time, the queen will only appeared in each colomn for once
        // So we only have to check the diagols' and anti-diagols fesibility

        // for example: 5,2,3,1,4
        // we will have a chess table like that
        // [1][5], [2][2], [3][3], [4][1], [5][4](Of all the element need to minus one)
        // The diagols' of [0][4](Or [1][5] as given) is [1][3], [2][2], [3][1], [4][0]
        // We can find that 0 + 4 == 1 + 3 == 2 + 2 == 3 + 1 == 4 + 0
        // The anti-dignols' of [1][1](Or [2][2] as given) is [0][0]. [1][1], [3][3], [4][4]
        // We can also find that 2 - 2 == 0 - 0 == 1 - 1 == 3 - 3 == 4 - 4
        // So we can test whether there are any situation happened in the array given

        boolean ans = true; // A signal the represent whether the table is "SAFE"

        for(int i = 0; i < 4; i++)
        {
            int temp = (loc[i] - 1) + i;   // (i) and (loc[i] - 1) seperately stands for the colomn
            int retemp = (loc[i - 1]) - i; // and the line of the specfic element, so we can calculate
                                           // the diagols and anti-diagols
                                           // Since we can't use other Array, so we can only use
                                           // temporary signal to repeat the exam
            for(int j = i + 1; j < 5;j++)
            {
                int pre = loc[j] - 1 + i;
                int repre = (loc[j] - 1) - i;
                if(temp == pre || retemp == repre)
                {
                    ans = false;
                    break;
                }
            }    

            if(ans)
            {
                break;
            }
        }

        if(ans)
        {
            System.out.println("皇后均处于安全位置");
        }
        else
        {
            System.out.println("皇后不处于安全位置");
        }
    }
}

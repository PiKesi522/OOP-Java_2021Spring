package Lab02;
public class Lab_02_09 {
    public static void main(String[] args)
    {
        int N = 5;

        int max = (int) Math.pow(2, N); 
        int[][] Had = new int[max][max]; 
        Had[0][0] = 1;

        for(int i = 1;i <= N;i++)
        {
            int size = (int)Math.pow(2, i);

            for(int j = size / 2; j < size ;j++)
            {
                for(int k = 0; k < size / 2 ;k++)
                {
                    Had[j][k] = Had[j - (size / 2)][k];
                    Had[k][j] = Had[k][j - (size / 2)];
                }                
            }

            for(int j = size / 2; j < size ;j++)
            {
                for(int k = size / 2; k < size ;k++)
                {
                    if(Had[k - (size / 2)][j - (size / 2)] == 1)
                        Had[k][j] = 0;
                    else
                        Had[k][j] = 1;
                }                
            }
        }

        for(int i = 0; i < max;i++)
        {   
            for(int j = 0;j < max;j++)
            {
                System.out.print(Had[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}

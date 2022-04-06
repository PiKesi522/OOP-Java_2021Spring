import java.awt.Color;
public class Lab_03_b_09 {
    static double noise()
    {
        return Math.random() * 0.5 + 0.5;
    }
    static double colorBlend(double random, double col, double precol)
    {
        return (random * col + (1.0 - random) * precol);
    }
    
    public static void main(String[] args)
    {
        Picture paimon = new Picture("genshin.jpg");
        int W = paimon.width();
        int H = paimon.height();
        Picture Board = new Picture(W,H);
        for(int i = 0; i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                int[] preColor = new int[3];
                preColor[0] = paimon.get(i, j).getRed();
                preColor[1] = paimon.get(i, j).getGreen();
                preColor[2] = paimon.get(i, j).getBlue();
                
                double[] newColor = new double[3];
                newColor[0] = colorBlend(noise(),(double)preColor[0] * 0.393 + (double)preColor[1] * 0.769 + (double)preColor[2] * 0.189,(double)preColor[0]); 
                newColor[1] = (int)colorBlend(noise(),(double)preColor[0] * 0.349 + (double)preColor[1] * 0.686 + (double)preColor[2] * 0.168,(double)preColor[1]); 
                newColor[2] = (int)colorBlend(noise(),(double)preColor[0] * 0.272 + (double)preColor[1] * 0.534 + (double)preColor[2] * 0.131,(double)preColor[2]); 
                for(int k = 0; k < 3; k++)
                {
                    if(newColor[k] > 255)
                        newColor[k] = 255;
                    else if(newColor[k] < 0)
                        newColor[k] = 0;
                }
                Color col = new Color((int)newColor[0],(int)newColor[1],(int)newColor[2]);
                //col = col.darker();
                Board.set(i, j, col);
            }
        }
        Board.show();
        Board.save("old.jpg");
    }
}

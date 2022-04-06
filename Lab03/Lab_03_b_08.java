import java.awt.Color;
public class Lab_03_b_08 {

    public static void main(String[] args)
    {
        //Picture paimon = new Picture("genshin.jpg");
        Picture paimon = new Picture(args[0]);
        int W = paimon.width();
        int H = paimon.height();
        for(int i = 0; i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                Color col = paimon.get(i, j);
                col = col.brighter();
                paimon.set(i, j, col);
            }
        }
        paimon.show();
        paimon.save("bright.jpg");
    }
}

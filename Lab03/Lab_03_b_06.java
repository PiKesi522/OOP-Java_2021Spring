import java.awt.Color;
public class Lab_03_b_06 {
    public static void main(String[] args){
        //Picture Paimon = new Picture("genshin.jpg");
        Picture Paimon = new Picture(args[0]);
        int W = Paimon.width();
        int H = Paimon.height();
        Picture Board = new Picture(W,H);
        for(int i = 0;i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                Color col = Paimon.get(i, j);
                Board.set(W-1-i, j, col); // Rotate with Y, fixed the Y, and change the X
            }
        }
        Board.show();
        Board.save("nihsneg.jpg");
    }
}

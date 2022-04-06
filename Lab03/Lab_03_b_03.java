import java.awt.Color;
public class Lab_03_b_03 {
    public static void main(String[] args)
    {
        //Picture Keli = new Picture("keli.jpg");
        Picture Keli = new Picture(args[0]);
        int H = Keli.height();
        int W = Keli.width();
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]); 
        Picture Board = new Picture(a,b);
        for(int i = 0; i < a; i++)
        {
            for(int j = 0; j < b; j++)
            {
                double prew = (double)W* ((double)i/(double)a); // 等比例分割原图，寻找需要的像素点
                double preh = (double)H* ((double)j/(double)b); // 等比例分割原图，寻找需要的像素点
                Color col = Keli.get((int)prew, (int)preh);     // Type:int is needed to create a new pixel
                Board.set(i, j, col);
            }
        }
        Board.save("FatKeli.jpg");
    }
}

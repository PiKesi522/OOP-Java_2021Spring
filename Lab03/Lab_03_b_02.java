import java.awt.Color;
public class Lab_03_b_02 {
     public static double lum(Color color) 
     {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return .299*r + .587*g + .114*b;
     }
    public static void main(String[] args)
    {
        //Picture Paimon = new Picture("genshin.jpg");
        Picture Paimon = new Picture(args[0]);
        int H = Paimon.height();
        int W = Paimon.width();
        for(int i = 0; i < H; i++)
        {
            for(int j = 0; j < W; j++)
            {
                int v = (int)lum(Paimon.get(i,j));  // Since function:lum return type:double, it need
                                                    //    to be transformed to type:int
                Color grey = new Color(v,v,v);      // Creat a correspondent grey with the same luminance
                Paimon.set(i, j, grey);             // Traversal all the pixel and change it
            }
        }
        Paimon.save("RIP.jpg");
    }
}

import java.awt.Color;
public class Lab_03_b_04 {
    public static double lum(Color color) 
    {
       int r = color.getRed();
       int g = color.getGreen();
       int b = color.getBlue();
       return .299*r + .587*g + .114*b;
    }
    public static void main(String[] args)
    {
        //Picture rainbow = new Picture("Rainbow.jpg");
        Picture rainbow = new Picture(args[0]);
        int W = rainbow.width();
        int H = rainbow.height();
        int N = Integer.parseInt(args[0]);
        //int N = 30;
        for(int i = 0; i < N; i++) // N pictures is needed
        {
            Picture Board = new Picture(W,H); // Create a picture with lower lumanince
            for(int w = 0; w < W; w++)
            {
                for(int h = 0; h < H; h++)
                {
                    int v = (int)lum(rainbow.get(w, h)); // The final lumniance should go to
                    Color pre_col = rainbow.get(w, h); // Get the orignal color
                    // N : the difference between each picture
                    // V : the final GreyDegree
                    // D : the orignal Color
                    // F(x) = kx + b; Since the changes is linear increase 
                    // F(0) = b = D; F(N) = kN + b = V;
                    //      => b = D; k = (V - D)/N;
                    // F(x) = ((V = D)/N) * x + D;
                    double nred =  ((double)(v - pre_col.getRed()) / (double)N ) * (double)i + (double)pre_col.getRed(); 
                    double nblue = ((double)(v - pre_col.getBlue()) / (double)N ) * (double)i + (double)pre_col.getBlue();
                    double ngreen = ((double)(v - pre_col.getGreen()) / (double)N ) * (double)i + (double)pre_col.getGreen();
                    Color col = new Color((int)nred,(int)nblue,(int)ngreen); 
                    Board.set(w, h, col);  
                }
            }
            Board.show();
            Board.save("rainbow" + i + ".jpg");
        }
    }
}

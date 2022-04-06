import java.awt.Color;
import java.util.Arrays;
public class Lab_03_b_07 {
    Picture former;
    int W;
    int H;
    public Lab_03_b_07(String txt) // Constructor function
    {
        this.former = new Picture(txt);
        W = former.width();
        H = former.height();
    }
    void filter(double[][] Convolution_Martix,int times)
    {
        Picture Board = new Picture(W,H);       // Create a draw board
        for(int i = 1; i < W - 1; i++)          // In order to prevent troubles, the edges aren't processed
        {                                       // To make the picture normal, we use another Traversal to
            for(int j = 1; j < H - 1; j++)      // keep the color in the edge
            {
                double newRed = 0.0;
                double newBlue = 0.0;
                double newGreen = 0.0;
                double[] Color_martix = {newRed,newGreen,newBlue}; // Create a Arrays to store Three 
                for(int w = i - 1; w <= i + 1; w++)				   // different colors
                {
                    for(int h = j - 1; h <= j + 1; h++)
                    {
                        Color temp = former.get(w, h); 
                        /*
                        **Martix multi: Not using Arrays to store data
                        **！Use number to represent the sequence of caculation !
                        **|1 2 3|   |1 4 7|
                        **|4 5 6| X |2 5 8|
                        **|7 8 9|   |3 6 9|
                        */
                        Color_martix[0] += (double)temp.getRed() * Convolution_Martix[h - j + 1][w - i + 1];
                        Color_martix[1]+= (double)temp.getGreen()* Convolution_Martix[h - j + 1][w - i + 1];
                        Color_martix[2] += (double)temp.getBlue()* Convolution_Martix[h - j + 1][w - i + 1];
                    }
                }
                for(int k = 0; k < 3; k++)
                {
                    Color_martix[k] /= times; // If the filter needs average, a Constant will be given
                                              // To save the space, I don't give a overwrite function
                                              // Instead, I give number:1 to filter which doesn't need it
                    if(Color_martix[k] > 255.0) 	// If the color outreach the scale, it will be changed
                        Color_martix[k] = 255.0;	// to 255 or 0	
                    else if(Color_martix[k] < 0.0)
                        Color_martix[k] = 0.0;
                }
                Color col = new Color((int)Color_martix[0],(int)Color_martix[1],(int)Color_martix[2]);
                Board.set(i, j, col);
            }
        }
        for(int i = 0; i < W; i++)
        {
            Board.set(0, i, former.get(0, i));          // The pixel on the top
            Board.set(H - 1, i, former.get(H - 1, i));  // The pixel at the bottom
        }
        for(int i = 0; i < H; i++)
        {
            Board.set(i, 0, former.get(i, 0));          // The pixel on the left
            Board.set(i, W - 1, former.get(i, W - 1));  // The pixel on the right
        }
        Board.show();
    }
    void Linear_filter()
    {
        double[][] Convolution_Martix = {{1,1,1},{1,1,1},{1,1,1}};      // Convolution_Martix
        filter(Convolution_Martix,9);
    }
    void Blur_filter()
    {
        double[][] Convolution_Martix = {{1,1,1},{1,5,1},{1,1,1}};      // Convolution_Martix
        filter(Convolution_Martix,13);

    }
    void Emboss_filter()
    {
        //double[][] Convolution_Martix = {{-1,0,1},{-1,1,1},{-1,0,1}}; // Convolution_Martix
        //double[][] Convolution_Martix = {{1,0,-1},{2,0,-2},{1,0,-1}};   // Convolution_Martix
        double[][] Convolution_Martix = {{-1,-1,0},{-1,1,1},{0,1,1}}; // Convolution_Martix
        filter(Convolution_Martix,1);
    }
    void Sharpen_filter()
    {
        double[][] Convolution_Martix = {{0,-1,0},{-1,5,-1},{0,-1,0}};  // Convolution_Martix
        filter(Convolution_Martix,1);
    }
    void Oil_painting_filter(int r)
    {
        Picture Board = new Picture(W,H);
        for(int i = 0; i < W; i++)
        {
            for(int j = 0; j < H; j++)
            {
                Color[] colors = new Color[(2 * r + 1) * (2 * r + 1)];// Create a array to store legal data
                int cnt = 0;
                for(int a = i - r; a < i + r; a++)// Traversal the whole range
                {                                 // To make it easy to understand, I choose a square area
                    for(int b = j - r; b < j + r; b++)
                    {
                        if((a < 0) || (b < 0) || (a > W - 1) || (b >= H - 1)) // If the location is out of 
                            continue;										  // the picture
                        else if(Math.abs(a - i) + Math.abs(b - j)>r) // If the Mahatton distance is beyond R
                            continue;
                        else
                        {
                            Color temp = former.get(a, b);
                            colors[cnt++] = temp;
                        }
                    }
                }
                Color col = find_Most(colors,cnt); // In leagl area, we will find out the color
                Board.set(i, j, col);
            }
        }
        Board.show();
    }
    Color find_Most(Color[] colors,int size)     // In this function, we will seperately choose the color
    {                                            // So, a situation that a color which hasn't appear in
        int newRed = 0,cntRed = 0;               // the Nearby Martix will be the final color may happen
        int newGreen = 0, cntGreen = 0;          // However, this picture may be more like to former one
        int newBlue = 0, cntBlue = 0;
        int[] Red_martix = new int[size];
        int[] Green_martix = new int[size];
        int[] Blue_martix = new int[size];

        for(int i = 0; i < size; i++) // First, we create three Arrays to store colors
        {
            Red_martix[i] = colors[i].getRed();
            Green_martix[i] = colors[i].getGreen();
            Blue_martix[i] = colors[i].getBlue();
        }
        Arrays.sort(Red_martix);    // For each Array, it need to be sorted in order to find the color
        Arrays.sort(Green_martix);  // In this example, I seperately choose color in three dimension
        Arrays.sort(Blue_martix);

        int tempRed = Red_martix[0];    // Same steps to find out the color which apppears
        int tempcnt = 1;                // for the most times
        for(int i = 1; i < size; i++)
        {
            if(tempRed == Red_martix[i])
                tempcnt++;
            else
            {
                if(cntRed < tempcnt)
                {
                    cntRed = tempcnt;
                    newRed = tempRed;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }

        int tempGreen = Green_martix[0];
        for(int i = 1; i < size; i++)
        {
            if(tempGreen == Green_martix[i])
                tempcnt++;
            else
            {
                if(cntGreen < tempcnt)
                {
                    cntGreen = tempcnt;
                    newGreen = tempGreen;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }

        int tempBlue = Blue_martix[0];
        for(int i = 1; i < size; i++)
        {
            if(tempBlue == Blue_martix[i])
                tempcnt++;
            else
            {
                if(cntBlue < tempcnt)
                {
                    cntBlue = tempcnt;
                    newBlue = tempBlue;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }
        Color col = new Color(newRed,newGreen,newBlue);
        return col;
    } 
    Color find_Most_RGB(Color[] colors,int size) //    In this function, we will find the color all in 
    {                                            // the color martix. So the color may not be so close
        int[] RGB_Martix = new int[size];        // to the orignal color, but the situation that a color
        for(int i = 0; i < size; i++)            // which hasn't appear before may appear in the new 
        {   									 // picture will not happen
            RGB_Martix[i] = colors[i].getRGB();
        }
        Arrays.sort(RGB_Martix);
        int tempRGB = RGB_Martix[0];
        int cntRGB = 0;
        int tempcnt = 1;
        int newRGB = 0;                         //    Somtimes, If the 'R' given is too small.It
        for(int i = 1; i < size; i++)           // may happen that in all the martix included there
        {                                       // is not color appears twice.
            if(tempRGB == RGB_Martix[i])        //    However, in such situation, this function
                tempcnt++;                      // should intialize the "newRGB" with the central
            else                                // piexel, which means another two variable need to
            {                                   // be given.    
                if(cntRGB < tempcnt)            //    So, in order to make it more readable.We give
                {                               // a larger 'R' and intialize it with white color,
                    cntRGB = tempcnt;           // not only it may not very outstanding.
                    newRGB = tempRGB;
                    tempcnt = 1;
                }
                else
                    tempcnt = 1;
            }
        }

        Color col = new Color(newRGB);
        return col;
    }
    public static void main(String[] args)
    {
        //Lab_03_b_07 Paimon = new Lab_03_b_07("genshin.jpg");
        Lab_03_b_07 Paimon = new Lab_03_b_07(args[0]);
        int w = 10;
        Paimon.Linear_filter();
        Paimon.Blur_filter();
        Paimon.Emboss_filter();
        Paimon.Sharpen_filter();
        Paimon.Oil_painting_filter(w);
    }
}
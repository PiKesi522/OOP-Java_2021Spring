import java.awt.Color;
public class Lab_03_b_05 {
    public static void main(String[] args){
        //Picture hutao = new Picture("hutao.jpg");
        Picture hutao = new Picture(args[0]);
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        int E = Integer.parseInt(args[3]);
		//int x = 30,y = 30, E = 20;
        int W = hutao.width();
        int H = hutao.height();
        int X = W * x / 100;        // Get the start point
        int Y = H * y / 100;        // Get the start point 
        int edge = E * W / 100;     // Get the size of the Board

        int left = X - edge / 2;    // Locate the left-edge
        int right = X + edge / 2;   // Locate the right-edge
        int up = Y - edge / 2;      // Locate the up-edge
        int down = Y + edge / 2;    // Locate the down-edge

        if(left < 0 || right > W)               // If the Board outreach the Picture 
            System.out.println("Too wide");
        else if(up < 0 || down > H)             // If the Board outreach the Picture 
            System.out.println("Too tall");
        else
        {
            Picture Board = new Picture(edge, edge);
            for(int i = left; i < right; i++)
            {
                for(int j = up; j < down; j++)
                {
                    Color col = hutao.get(i, j);
                    Board.set(i - left, j - up, col);
                }
            }
            Board.show();
            Board.save("Gui.jpg");
        }
    }
}

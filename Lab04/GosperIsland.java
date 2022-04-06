public class GosperIsland {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-3,7);
        StdDraw.setYscale(-4,6);
        pen.Gosper(Integer.parseInt(args[0]),2);
    }
}

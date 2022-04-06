public class MinkowskiCurve {
    public static void main(String[] args)
    {
        Turtle pen = new Turtle();
        StdDraw.setXscale(-1,2);
        StdDraw.setYscale(-1,2);
        pen.Minkowski(Integer.parseInt(args[0]),5);
    }
}

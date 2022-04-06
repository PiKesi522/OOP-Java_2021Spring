package Lab02;
public class Lab_02_04 {
    public static void main(String[] args){
        int X1, Y1, X2, Y2;
        X1 = Integer.parseInt(args[0]);
        Y1 = Integer.parseInt(args[1]);
        X2 = Integer.parseInt(args[2]);
        Y2 = Integer.parseInt(args[3]);

        double ans = Math.sqrt(Math.pow(X1 - X2, 2) + Math.pow(Y1 - Y2, 2)); 
        System.out.println(ans);
    }
}

public class Lab_04_a_02 {
    public static void main(String[] args)
    {
        BigInt N1 = new BigInt(args[0]);
        //BigInt N1 = new BigInt("14");
        BigInt one = new BigInt(1);         // Using my self-made consturctor function within Integer
        BigInt two = new BigInt(2);         // Using my self-made consturctor function within Integer

        BigInt i = new BigInt(1);
        BigInt fac_N = new BigInt(1);
        BigInt two_fac = new BigInt(1);         
        for(; i.compare(N1) != 1; i = i.add(one))  //   1 * 2 * 3 ...... * (N-1) * N   => N!       
        {                                          //   | +1  +1  ...... +1     +1 |
            fac_N = fac_N.multiply(i);             // start                       end
            two_fac = two_fac.multiply(two);       //   |                          |
        }                                          //   2 * 2 * 2 ...... * 2 * 2 * 2   => 2^N
        System.out.println(args[0] + "! = " + fac_N.tostring());
        System.out.println("-------------------------------------------");
        System.out.println("2^(" + args[0] + ") = " + two_fac.tostring());
    }
}

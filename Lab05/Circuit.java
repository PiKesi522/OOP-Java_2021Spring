public class Circuit {
    private double voltage;
    private double currency;
    private double[] circuit = new double[1000];
    private double[] const_circuit = new double[1000];  // Store it in a unchangable array11Q 
    private int cnt = 0;                // How many net in the circuit?
    private int len = 0;
    private int[] lloc = new int[100]; 
    private int[] rloc = new int[100];
    private void set_voltage(double d)
    {
        this.voltage = d;
        this.currency = this.voltage / this.resistance();  // calculate the currency
    }
    private void set_currency(double d)
    {
        this.currency = d;
        this.voltage = this.currency * this.resistance();  // calculate the voltage
    }
    private void get_circuit(String s)  // Store the String:circuit in an array
    {                                   // However, we don't consider the situation
        int loc = 0;                    // that the orignal number is not Integer
        int lenlloc = 0;
        int lenrloc = 0;
        while(loc++ < s.length())       // Change the String to Double
        {
            char ch = s.charAt(loc - 1);
            if(ch == ' ' || ch == ',')
                continue;
            else if('0' <= ch && ch <= '9')
            {
                int finishone = s.indexOf(',', loc - 1);// if the number isn't the last one
                                                        // in the Subcircuit, we can split
                                                        // it with ','
                
                int finishtwo = s.indexOf(')', loc - 1);// Otherwise, the numbe is the last
                                                        // number in the Subcircuit, we should
                                                        // split it with ')'
                int finish = 0;
                if(finishone == -1 || finishone > finishtwo)
                    finish = finishtwo;
                else
                    finish = finishone;
                String num = s.substring(loc - 1, finish);
                loc = finish;
                circuit[len++] = tonum(num);// Convert a String which represent a double
                                            // number to a real double number 
            }
            else if(ch == '(')
            {
                circuit[len++] = -1;        // Since char cannot stored in the Array:double
                lloc[lenlloc++] = len - 1;  // We use negetive number to record it 
                cnt++;
            }
            else if(ch == ')')
            {
                circuit[len++] = -2;
                rloc[lenrloc++] = len - 1;
            }
            else if(ch == '-')
                circuit[len++] = -3;
            else if(ch == '/')
                circuit[len++] = -4;
                
            const_circuit[len - 1] = circuit[len - 1];
        }
    }   
    private double tonum(String s)  // Convert a String which represent a double
    {                               // number to a real double number 
        int point = s.indexOf('.');
        if(point == -1)
        {
            double ans = 0;
            int len = 0;
            while(len++ < s.length())
            {
                ans *= 10;
                ans += s.charAt(len - 1) - '0';
            }
            return ans;
        }
        else
        {
            String zs = s.substring(0, point);
            String xs = s.substring(point + 1);
            double ans = 0;
            int len = 0;
            while(len++ < zs.length())
            {
                ans *= 10;
                ans += zs.charAt(len - 1) - '0';
            }
            double numxs = 0;
            for(int i = xs.length() - 1; i >= 0; i--)
            {
                numxs += xs.charAt(i) - '0';
                numxs /= 10;
            }
            return (ans + numxs);
        }
    }
    public double resistance()
    {
    // Find the first ')' in the circuit, and from the 
    // the location we find '(' in the left, which means 
    // they are in the minimum circuit, we calculate it 
    // first. Then, we replace this sub-circuit with a 
    // specific number, in order to simplify the whole circuit
    // (/, 6, (-, 5, 6))
    //        |       |
    //        (   11  )
    // (/, 6,     11   )
        int tempcnt = cnt;
        while(tempcnt-- > 0)
        {
            int start = lloc[tempcnt];
            int end = 0;
            for(end = start; end < len; end++)
                if(circuit[end] == -2.0)
                    break;

            if(circuit[start + 1] == -3)        // Calculate the Series connection
                series(start,end);
            else if(circuit[start + 1] == -4)   // Calculate the Parallel connection
                parallel(start,end);
        }
        return circuit[0];
    }
    private void series(int start, int end)
    {
        double res = 0;
        for(int i = start; i <= end; i++)
        {
            if(circuit[i] > 0)  // The resistence must be positive
                res += circuit[i];
            circuit[i] = 0;     // Clear the Subcircuit 
        }
        circuit[start] = res;   // Store the resistence of Subcircuit
    }
    private void parallel(int start, int end)
    {
        double res = 0;
        for(int i = start; i <= end; i++)
        {
            if(circuit[i] > 0)  // The resistence must be positive
                res += 1/circuit[i];
            circuit[i] = 0;     // Clear the Subcircuit 
        }
        circuit[start] = 1/res;   // Store the resistence of Subcircuit
    }
    private void all_circuits(String s)
    {
        /*
        Find the location that '(' appears, that is the end of the subcircuit
        Then find whether there are any ')' appears before the end of the subcircuit
        Pair the ')' to the closet '(' in front of itself
        For those ')' which are outreach the end of the subcircuit
        Store it in reversed sequence to pair the '('
        For example:
        '(' :   0   4   12  17  
        ')' :  10 | 23  24  25    --->    ')' :   25  10  24  23
              find|--reverse--
        */
        int[] corrloc  = new int[cnt];
        for(int i = 0; i < cnt; i++)
            corrloc[i] = -1;        // initialize the array with negetive number
                                    // So, we can easily judge whether we need 
                                    // to fill in specific space or not
        int last = lloc[cnt - 1];
        int templen = -1;
        while(rloc[++templen] < last)   // Pair small loction corrrespondantly
        {
            for(int i = 0; i < cnt; i++)
            {
                if(lloc[i] > rloc[templen])
                {
                    int j = i - 1;
                    while(corrloc[j] != -1)
                        j -= 1;
                    corrloc[j] = rloc[templen];
                    break;
                }
            }
        }
        for(int i = cnt - 1; i >= templen; i--) // Store in reserved sequence
        {
            for(int j = 0; j < cnt; j++)
            {
                if(corrloc[j] == -1)
                {
                    corrloc[j] = rloc[i];
                    break;
                }
            }
        }
        
        /*System.out.print("In subcircuit" + ":");
        String cir = Tostring(lloc[1],corrloc[1]);
        Circuit nc = new Circuit();         // Create a new instance to calculate
        nc.get_circuit(cir);                // the data we need

        if(const_circuit[lloc[0] + 1] == -4)  // If the net is Parallel connection,
                                            // the voltage of subcircuit is eqaul
                                            // to the Supcircuit
            nc.set_voltage(this.voltage);
        else                                // If the net is Series connection,
                                            // the currency of subcircuit is eqaul
                                            // to the Supcircuit
            nc.set_currency(this.currency);
    
        System.out.println("The voltage is: " + nc.voltage);
        System.out.println("The currency is: " + nc.currency);
        System.out.println("-----------------------------------------------------");
        if(nc.cnt > 1)
            nc.all_circuits(cir);*/

        for(int i = 1; i < cnt; i++)
        {
            System.out.print("In subcircuit" + i + ":");
            String cir = Tostring(lloc[i],corrloc[i]);
        /*    Circuit nc = new Circuit();         // Create a new instance to calculate
            nc.get_circuit(cir);                // the data we need

            if(const_circuit[lloc[i - 1] + 1] == -4)  // If the net is Parallel connection,
                                                // the voltage of subcircuit is eqaul
                                                // to the Supcircuit
                nc.set_voltage(this.voltage);
            else                                // If the net is Series connection,
                                                // the currency of subcircuit is eqaul
                                                // to the Supcircuit
                nc.set_currency(this.currency);
            
            System.out.println("The voltage is: " + nc.voltage);
            System.out.println("The currency is: " + nc.currency);
            System.out.println("-----------------------------------------------------");*/
        }
    }
    private String Tostring(int start, int end) // Return the Subcircuit in String
    {
        String cir = new String();
        for(int i = start; i <= end; i++)
        {
            if(const_circuit[i] == -1)
                cir += '(';
            else if(const_circuit[i] == -2)
                cir += ')';
            else if(const_circuit[i] == -3)
                cir += "-, ";
            else if(const_circuit[i] == -4)
                cir += "/, ";
            else 
            {
                cir += const_circuit[i];
                if(const_circuit[i + 1] != -2)  // Judge whether it's the latter
                                                // number or not
                    cir += ", ";
            }
        }
        System.out.println(cir);
        return cir;
    }
    public static void main(String[] args) {
        String s = "(/, (-, 3, 2), (-, 2, (/, 3, 6)))";
        //String s = args[0];
        Circuit c = new Circuit();
        c.get_circuit(s);       // Convert the string to specific Array
        c.set_voltage(12.0);
        c.all_circuits(s);
    }
}
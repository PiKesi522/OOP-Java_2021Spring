    public class BigInt {
        int[] num = new int[100000];

        int len = 0;
        boolean zero = false;       // whether this number is 0?
        boolean negetive = false;   // whether this number is below 0?
                                    // if negetive, we will take the signal 
                                    // and keep the number as positive

        BigInt()                    // default constructor function
        {
            this.zero = true;
        }
        BigInt(String s)            // constructor function
        {
            this.len = s.length();
            int i = 0;
            if(s.charAt(0) == '-')      // for example: -12345
            {
                i = 1;                  // we only use: 12345
                this.negetive = true;   // signal negetive is sperately processed
            }
            else if(s.charAt(0) == '0')
            {
                this.zero = true;
                return;
            }

            for(; i < len; i++)
            {
                this.num[(len - 1) - i] = s.charAt(i) - '0';
            }

            if(negetive)
                this.len--;                  // the negetive signal is useless
        }
        BigInt(int i)               // self-made constructor function
        {
            if(i == 0)
            {
                this.zero = true;
                return;
            }

            if(i < 0)
            {
                this.negetive = true;
                i = -i;             // take the signal and deal with it as positve number
            }

            while(i != 0)
            {                               
                int temp = i % 10;          // int i = 12345
                i /= 10;
                this.num[this.len++] = temp;// num[] = [5][4][3][2][1]
            }
        }
        BigInt(BigInt big)          // Shallow copy
        {
            this.len = big.len;
            this.negetive = big.negetive;
            this.zero = big.zero;
            for(int i = 0; i < this.len; i++)
            {
                this.num[i] = big.num[i];
            }
        }
        
        BigInt add(BigInt x)        // To make it easy to calculate, only two positive number is availble
        {                           // so we have to deal with the data before calculation
            BigInt ans = new BigInt();
            ans.zero = false;
            if(this.zero)
                return x;
            if(x.zero)
                return this;
            if(this.negetive && x.negetive)         // (-1) + (-2) = -(1 + 2)
            {
                ans.negetive = true;
            }
            else if(this.negetive && !x.negetive)   // (-1) + 2 = 2 - 1
            {
                BigInt tempthis = new BigInt(this);
                tempthis.negetive = false;
                ans = x.subtract(tempthis);
                return ans;
            }
            else if(!this.negetive && x.negetive)   // 1 + (-2) = 1 - 2
            {
                BigInt tempx = new BigInt(x);
                tempx.negetive = false;
                ans = this.subtract(tempx);
                return ans;
            }
            else if(!this.negetive && !x.negetive)  // 1 + 2 = 1 + 2
            {
                // default situation 
            }
            
            BigInt longer = new BigInt();
            longer.zero = false;
            BigInt shorter = new BigInt();
            shorter.zero = false;
            if(len > x.len)  // We find the longer number to determine the calculation
            {
                longer = this;
                shorter = x;
            }
            else
            {
                longer = x;
                shorter = this;
            }

            boolean jin_wei = false;
            int temp = 0;
            for(int i = 0; i < longer.len; i++) 
            {
                if(i < shorter.len)     // if both number is availble, we will add both of them
                    temp = shorter.num[i] + longer.num[i];
                else                    // else we only inherit the longer number
                    temp = longer.num[i];
                if(jin_wei)             // if jin_wei is needed, the present number + 1
                {
                    jin_wei = false;
                    temp++;
                }

                if(temp >= 10)          // if present number over 10, it should be changed
                {
                    temp -= 10;
                    jin_wei = true;     // and give 1 to next number
                }
                else
                    jin_wei = false;

                ans.num[ans.len++] = temp;
            }

            if(jin_wei)
            {
                jin_wei = false;
                ans.num[ans.len++] = 1;
            }
            return ans;
        }
        BigInt multiply(BigInt x)
        {
            BigInt ans = new BigInt();
            ans.zero = false;
            if(zero || x.zero)      // One of the number is zero 
            {
                ans.zero = true;
                return ans;
            }

            if(this.negetive == x.negetive)     // if both of the number has same signal
                ans.negetive = false;           // the ans is positive
            else        
                ans.negetive = true;            // otherwise ans is negetive

            for(int i = 0; i < x.len; i++)      // traversal x and choose all the number to multiply self-number
            {
                for(int j = 0; j < len; j++)    // traversal self-number and multiply with x to calculate the answer
                {
                    ans.num[j + i] += x.num[i] * num[j];
                    if(ans.num[j + i] >= 10)                // if the temproray answer is above 10
                    {
                        int jin_wei = ans.num[j + i] / 10;  // The part we don't need it and give it to next number
                        ans.num[j + i] %= 10;               // Now we have temproray correct answer
                        ans.num[j + i + 1] += jin_wei;      // The next number have all the jin_wei
                    }
                }
            }
            int big = ans.num[len + x.len - 1];
            if(big == 0)
            {
                ans.len = len + x.len - 1;
                return ans;
            }
            else
            {
                ans.len = len + x.len;
                return ans;
            }
        }
        BigInt subtract(BigInt x)   // to make it easy to calculate, only two positive number is availble
        {                           // so we have to deal with the data before calculation
                                    // Furthermore, we can make it bigger one subtract smaller one
            BigInt ans = new BigInt();
            ans.zero = false;
            if(this.compare(x) == 0)// a - a = 0
            {
                ans.zero = true;
                return ans;
            }
            if(this.zero)           // 0 - (-5) =  5
            {                       // 0 -  5   = -5
                x.negetive = !x.negetive;
                return x;
            }
            if(x.zero)              // (-5) - 0 = -5
                return this;        //  5   - 0 =  5
            if(this.negetive && x.negetive)         // (-1) - (-2) = -(1 - 2) =  1
            {                                       // (-2) - (-1) = -(2 - 1) = -1
                BigInt tempthis = new BigInt(this); // Compared to BigInt.add:
                BigInt tempx = new BigInt(x);       // subtract may lead to negetive number.
                tempthis.negetive = false;          // So we have to give two copy of these number
                tempx.negetive = false;             // both of them is postive 
                ans = tempthis.subtract(tempx);     // And finally, we change the signal
                ans.negetive = !ans.negetive;
                return ans;
            }
            else if(this.negetive && !x.negetive)   // (-1) - 2 = -(2 + 1)
            {
                BigInt tempthis = new BigInt(this);
                tempthis.negetive = false;
                ans = tempthis.add(x);
                ans.negetive = true;
                return ans;
            }
            else if(!this.negetive && x.negetive)   // 1 - (-2) = 1 + 2
            {
                BigInt tempx = new BigInt(x);
                tempx.negetive = false;
                ans = this.add(tempx);
                return ans;
            }
            else if(!this.negetive && !x.negetive)  // 1 - 2 = 1 - 2
            {
                // default situation
            }

            if(this.compare(x) == -1)   // To make it simple, we only make it bigger num subtract smaller one
            {                           // And give the result a negetive signal
                BigInt tempthis = new BigInt(this); // 3 - 5 = -(5 - 3) < 0
                ans = x.subtract(tempthis);
                ans.negetive = true;
                return ans;
            }

            boolean tui_wei = false;
            for(int i = 0; i < this.len; i++)
            {
                int temp = 0;
                if(i < x.len)
                    temp = this.num[i] - x.num[i];                
                else
                    temp = this.num[i] - 0;
                
                if(tui_wei)
                {
                    temp--;
                    tui_wei = false;
                }

                if(temp < 0)
                {
                    temp += 10;
                    tui_wei = true;
                }
                else   
                    tui_wei = false;

                ans.num[ans.len++] = temp;
            }
            for(int j = ans.len - 1; j >= 0; j--)
            {
                if(ans.num[j] == 0)
                    ans.len--;
                else
                    break;
            }
            return ans;
        }
        BigInt divide(BigInt x)     // To make it easy to calculate, only two positive number is availble
        {                           // so we have to deal with the data before calculation
            BigInt ans = new BigInt();
            ans.zero = false;
            if(x.zero)      // (? / 0) is undefined
            {
                System.out.println("the dividend cannot be zero!");
                return ans;
            }
            if(this.zero)   // (0 / ?) is zero
            {
                ans.zero = true;
                return ans;
            }

            if(this.negetive == x.negetive)     // judge the ans is postive or not
                ans.negetive = false;           // and there is no need to handle the signal again
            else if(this.negetive != x.negetive)
                ans.negetive = true;

            BigInt posthis = new BigInt(this);  // we create two positive number to simplify the caculation
            BigInt posx = new BigInt(x);
            posthis.negetive = false;   
            posx.negetive = false;

            if(posthis.compare(posx) == 0)          // 5 / 5 == 1
            {
                ans.len = 1;
                ans.num[0] = 1;
                return ans;
            }
            else if(posthis.compare(posx) == -1)    // 3 / 5 == 0
            {
                ans.zero = true;
                return ans;
            }

            int differ_len = this.len - x.len;
            if(differ_len > 0)
            {
                for(int i = posx.len - 1; i >= 0; i--)
                    posx.num[i + differ_len] = posx.num[i];
                for(int i = 0; i < differ_len; i++)
                    posx.num[i] = 0;
                posx.len += differ_len;
            }

            int[] ans_num = new int[100000];
            boolean start = false;
            do                                      // test: 12345 / 23
            {                                       // 1.    12345 < 23000 not start
                if(posthis.compare(posx) == 0)      // 2.    12345 / 2300  >>> 5 (start here)
                {                                   // 3.    845   / 230   >>> 3
                    ans_num[ans.len++] = 1;         // 4.    155   / 23    >>> 6
                    break;                          // 5.    posx: 23 == x: 23
                }                                   //         break;
                else if(posthis.compare(posx) == -1)//       answer: 536
                {
                    if(start)
                        ans_num[ans.len++] = 0;
                }
                else
                {
                    int times = 0;
                    while(posthis.compare(posx) != -1)
                    {
                        posthis = posthis.subtract(posx);
                        times++;
                    }
                    start = true;
                    ans_num[ans.len++] = times;
                }

                posx.len--;                         // 23000 -> 2300
                for(int i = 1; i <= posx.len; i++)  // 2 3 0 0 0 
                {                                   //  \ \ \ \
                    posx.num[i - 1] = posx.num[i];  // 2|2 3 0 0|
                }                                   //  |  used |
                    posx.num[posx.len] = 0;         // 0|2 3 0 0|

            }while(posx.len >= x.len);              // 230...0 == 23 ?
            for(int i = 0; i < ans.len; i++)
                ans.num[ans.len - 1 - i] = ans_num[i]; 
            
            return ans;
        }
        BigInt mod(BigInt x)
        {
            BigInt ans = new BigInt();
            ans.zero = false;
            if(x.zero)      // (? % 0) is undefined
            {
                System.out.println("the modend cannot be zero!");
                return ans;
            }
            ans = this.subtract(this.divide(x).multiply(x));    // a % b = r 
            return ans;                                         // r = a - (a / b) * b
        }
        int compare(BigInt x)       // To makt it easy to calculate, only two positive number is availble
        {                           // other situation will be change to two positive number or compare signal
            if(!this.negetive && x.negetive)        // 1 > (-2)
            {
                return 1;
            }
            else if(this.negetive && !x.negetive)   // (-1) < 2
            {
                return -1;
            }
            else if(this.zero)      // number is zero
            {
                if(x.zero)          // 0 == 0
                    return 0;
                else if(x.negetive) // 0 > (-2)
                    return 1;
                else if(!x.negetive)// 0 < 2
                    return -1;
                else
                {
                    System.out.println("Bug in compare 'if:this.zero'");
                    return 0;
                }
            }
            else if(x.zero)         // the other number is zero
            {
                if(this.negetive)       // (-1) < 0
                    return -1;
                else if(!this.negetive) // 1 > 0
                    return 1;
                else
                {
                    System.out.println("Bug in compare 'if:x.zero'");
                    return 0;
                }
            }
            else if(this.negetive && x.negetive)    // (-1) > (-2)
            {                                       //   2  >   1
                BigInt tempthis = new BigInt(this);
                BigInt tempx = new BigInt(x);
                tempx.negetive = false;
                tempthis.negetive = false;  
                int ans = tempx.compare(tempthis);      
                return ans;
            }
            
            if(this.len > x.len)    // general situation: two positive numbers
                return 1;           // check the bits that each of them have
            else if(this.len < x.len)
                return -1;
            else
            {
                for(int i = 0; i < this.len; i++)
                {
                    if(this.num[this.len - 1 - i] > x.num[x.len - 1 - i])
                        return 1;
                    else if(this.num[this.len - 1 - i] < x.num[x.len - 1 - i])
                        return -1;
                }
                return 0;
            }
        }
        boolean equals(BigInt x)    // Use BigInt.compare, if consequence is zero, represent that 
        {                           // they are the same
            if(this.compare(x) == 0)
                return true;
            return false;
        }
        String tostring()           // Rename the function since it is the same to another function in Java
        {
            String ans = new String();
            if(this.zero)
                return "0";
            if(this.negetive)
                ans += "-";
            for(int i = 0; i < this.len; i++)
            {
                ans += this.num[this.len - 1 - i];
            }
            return ans;
        }
        public static void main(String[] args){
            BigInt a = new BigInt(args[0]);
            BigInt b = new BigInt(args[1]);
            
            BigInt c = new BigInt();
            String ans = new String();

            c = a.add(b);
            ans = c.tostring();
            System.out.println(args[0] + " + " + args[1] + " = " + ans);

            c = a.subtract(b);
            ans = c.tostring();
            System.out.println(args[0] + " - " + args[1] + " = " + ans);

            c = a.multiply(b);
            ans = c.tostring();
            System.out.println(args[0] + " * " + args[1] + " = " + ans);

            c = a.divide(b);
            ans = c.tostring();
            System.out.println(args[0] + " / " + args[1] + " = " + ans);

            c = a.mod(b);
            ans = c.tostring();
            System.out.println(args[0] + " % " + args[1] + " = " + ans);
        }
    }

package Lab07;

import java.util.*;
class DifferException extends Exception{};
// Use a Exception to alert the problem that the two martix
// doesn't have the same size, so that they cannot be calculated
class NounMapException extends Exception{};
// Use a Exception to deal with the problem that the martix is null
class EmptyMapException extends Exception{};
// Use a Exception to deal with the problem that the martix is empty

public class SparseMartix {
    /* Use a triple-table to store the data
      <<x , y>, v
        x: the x-aries location of the non-zero value
        y: the y-aries location of the non-zero value
        Create a tuple to Store the location of the value in the Martix
        v: the value at the location
    */
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_shallowcopy
    // Use a Private function to shallow copy the Martix
    (LinkedHashMap<ArrayList<Integer>,Integer> a){
        LinkedHashMap<ArrayList<Integer>,Integer> ans = new LinkedHashMap<ArrayList<Integer>,Integer>();
        // The answer we need Triple-table
        Set<ArrayList<Integer>> keys = a.keySet();
        Iterator<ArrayList<Integer>> it = keys.iterator();
        // Use iterator to read the Set
        while (it.hasNext()) {
            ArrayList<Integer> key = (ArrayList<Integer>) it.next();
            // Get the key
            int value = a.get(key);
            // Get the corrsponding value
            ans.put(key, value);
        }
        return ans;
    }
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_getMap
    // Use a Private function to change Two-Dimension Array to a LinkedHashMap
    // To judge whether two Martix can calculate,
    //   the first element will be used to store the size of the Martix
    (int[][] data, int height, int length){
        LinkedHashMap<ArrayList<Integer>,Integer> ans = new LinkedHashMap<>();
        ArrayList<Integer> size = new ArrayList<>();
        size.add(height);size.add(length);
        ans.put(size, 0);
        // This data is to store the size of the Martix
        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                if(data[i][j] != 0){
                    ArrayList<Integer> loc = new ArrayList<Integer>();
                    loc.add(i);loc.add(j);
                    ans.put(loc, data[i][j]);
                }
            }
        }
        return ans;
    }
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_add
    (LinkedHashMap<ArrayList<Integer>,Integer> a, LinkedHashMap<ArrayList<Integer>,Integer> b){
        // To calculate the sum of two Martix, we just need to add the 
        // value in the same location in the Martix
        // So we just need to add the Element in the Map together
        // If the location has number already, we need to add the corrsponding 
        // value in the location
        ArrayList<Integer> F_a = a.keySet().iterator().next();
        ArrayList<Integer> F_b = b.keySet().iterator().next();
        try{
            if((F_a.get(0) != F_b.get(0)) || (F_a.get(1) != F_b.get(1))){
                // Wheter these two array dosen't have the same size
                throw new DifferException();
            }
        }
        catch(DifferException e){
            System.out.println("These two Martix have different size");
            return null;
        }

        Iterator<ArrayList<Integer>> it = a.keySet().iterator();
        LinkedHashMap<ArrayList<Integer>,Integer> ans = M_shallowcopy(b);
        // A shallow copy of Martix B;
        while (it.hasNext()) {
            // We don't need to handle the size information
            // Since every Map should have a size information,
            // The answer will inherit this information from the B
            ArrayList<Integer> loc = (ArrayList<Integer>) it.next();
            // Get the key
            if(ans.containsKey(loc)){
                ans.put(loc, a.get(loc) + b.get(loc));
            }
            else{
                ans.put(loc, a.get(loc));
            }
        }
        return ans;
    }
    private static LinkedHashMap<ArrayList<Integer>,Integer> M_mult
    (LinkedHashMap<ArrayList<Integer>,Integer> a, LinkedHashMap<ArrayList<Integer>,Integer> b){
        LinkedHashMap<ArrayList<Integer>,Integer> ans = new LinkedHashMap<>();
        Iterator<ArrayList<Integer>> a_it = a.keySet().iterator();
        Iterator<ArrayList<Integer>> b_it = b.keySet().iterator();
        ArrayList<Integer> size_a = a_it.next(), size_b = b_it.next();
        try{
            if(size_a.get(1) != size_b.get(0)){
                throw new DifferException();
            } 
        }
        catch(DifferException e){
            System.out.println("These two Martix have different size");
            return null;
        }
        ArrayList<Integer> size_ans = new ArrayList<>();
        size_ans.add(size_a.get(0));size_ans.add(size_b.get(1));    
        ans.put(size_ans, 0);
        // The size of ans is the height of first Martix with the length of the second Martix

        int ans_len = 0, ans_hei = 0;
        ArrayList<Integer> line = new ArrayList<>();
        // Use to store the location that the value is not zero
        ArrayList<Integer> number = new ArrayList<>();
        // Use to store the value that will be used to calculate the answer
        boolean empty = true;

        while(a_it.hasNext()){
            ArrayList<Integer> temp = a_it.next();
            if(ans_len != temp.get(0)){
                if(empty){
                    // This line is empty, the corrsponding number in the ans is also empty
                    ans_len += 1;
                }
                else{
                    for(ans_hei = 0; ans_hei < size_b.get(1); ans_hei++){
                        int temp_ans = 0;
                        Iterator<Integer> t_it = line.iterator();
                        Iterator<Integer> n_it = number.iterator();
                        while(t_it.hasNext()){
                            // Recrusion the Array to calculate 
                            ArrayList<Integer> temp_loc = new ArrayList<>();
                            temp_loc.add(t_it.next()); temp_loc.add(ans_hei);
                            // Store the tempprary loction, which is the reverse of orignal location
                            if(b.containsKey(temp_loc)){
                                temp_ans += n_it.next() * b.get(temp_loc);
                            }
                            else{
                                n_it.next();
                            }
                        }

                        if(temp_ans != 0){
                            // The answer is not zero
                            ArrayList<Integer> loc = new ArrayList<>();
                            loc.add(ans_len);loc.add(ans_hei);
                            ans.put(loc, temp_ans);
                        }
                    }

                    line = new ArrayList<>();
                    number = new ArrayList<>();
                    empty = true;
                    // Clear the buffer area
                }
            }
            else{
                empty = false;
                // Something in the buffer
                line.add(temp.get(1));
                number.add(a.get(temp));
                // Record the loction which has the value
            }
        } 
        if(!empty){
            for(ans_hei = 0; ans_hei < size_b.get(1); ans_hei++){
                int temp_ans = 0;
                Iterator<Integer> t_it = line.iterator();
                Iterator<Integer> n_it = number.iterator();
                while(t_it.hasNext()){
                    // Recrusion the Array to calculate 
                    ArrayList<Integer> temp_loc = new ArrayList<>();
                    temp_loc.add(t_it.next()); temp_loc.add(ans_hei);
                    // Store the tempprary loction, which is the reverse of orignal location
                    if(b.containsKey(temp_loc)){
                        temp_ans += n_it.next() * b.get(temp_loc);
                    }
                    else{
                        n_it.next();
                    }
                }

                if(temp_ans != 0){
                    // The answer is not zero
                    ArrayList<Integer> loc = new ArrayList<>();
                    loc.add(ans_len);loc.add(ans_hei);
                    ans.put(loc, temp_ans);
                }
            }

            line = new ArrayList<>();
            number = new ArrayList<>();
            empty = true;
            // Clear the buffer area
        }
        
        return ans;
    }
    private static void M_print(LinkedHashMap<ArrayList<Integer>,Integer> m){
        try{
            if(m == null){
                throw new NounMapException();
            }
            else if(m.size() == 0){
                throw new EmptyMapException();
            }
        }
        catch(NounMapException e){
            System.out.println("This martix is null");
            return;
        }
        catch(EmptyMapException e){
            System.out.println("This martix is empty");
            return;
        }
        Iterator<ArrayList<Integer>> it = m.keySet().iterator();
        ArrayList<Integer> size = it.next();
        int length = size.get(1), height = size.get(0);
        int[][] martix = new int[height][length];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                martix[i][j] = 0;
            }
        }
        ArrayList<Integer> loc = size;
        while(it.hasNext()){
            loc = it.next();
            martix[loc.get(0)][loc.get(1)] = m.get(loc);
        }

        for(int i = 0; i < height; i++){
            for(int j = 0; j < length; j++){
                System.out.print(martix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int height = 6, length = 8;
        int[][] martix_A = {{2,0,0,0,6,0,0,7},
                            {0,0,1,0,0,0,0,0},
                            {0,0,2,0,0,0,3,0},
                            {0,0,0,0,0,8,0,0},
                            {0,0,0,5,0,0,0,0},
                            {0,9,0,0,0,0,0,0}};

        int[][] martix_B = {{0,0,0,0,0,0,0,7},
                            {0,0,1,0,0,5,0,0},
                            {8,0,0,0,0,1,3,0},
                            {0,0,9,0,0,8,0,0},
                            {0,0,0,5,0,0,0,0},
                            {0,9,0,0,0,0,6,0}};

        int[][] martix_C = {{2,0,0,0,6,0,0,7},
                            {0,0,1,0,0,0,0,0},
                            {0,0,2,0,0,0,3,0},
                            {0,0,0,0,0,8,0,0},
                            {0,0,0,5,0,0,0,0}};

        int[][] martix_D = {{0,0,0,0,0,0},
                            {0,0,1,0,0,5},
                            {8,0,0,0,0,1},
                            {0,0,9,0,0,8},
                            {0,0,0,5,0,0},
                            {0,9,0,0,0,0},
                            {7,0,0,0,1,0},
                            {0,0,0,5,6,0}};
        LinkedHashMap<ArrayList<Integer>,Integer> A = M_getMap(martix_A, height, length);
        LinkedHashMap<ArrayList<Integer>,Integer> B = M_getMap(martix_B, height, length);
        LinkedHashMap<ArrayList<Integer>,Integer> C = M_getMap(martix_C, height - 1, length);
        LinkedHashMap<ArrayList<Integer>,Integer> D = M_getMap(martix_D, length, height);


        LinkedHashMap<ArrayList<Integer>,Integer> sumAB = M_add(A,B);
        LinkedHashMap<ArrayList<Integer>,Integer> sumBC = M_add(B,C);
        LinkedHashMap<ArrayList<Integer>,Integer> consAB = M_mult(A,B);
        LinkedHashMap<ArrayList<Integer>,Integer> consAD = M_mult(A,D);

        M_print(sumAB);
        M_print(sumBC);
        M_print(consAB);
        M_print(consAD);

        // Set<ArrayList<Integer>> keys = A.keySet();
        // Iterator<ArrayList<Integer>> it = keys.iterator();
        // // Use iterator to read the Set
        // while (it.hasNext()) {
        //     ArrayList<Integer> key = (ArrayList<Integer>) it.next();
        //     // Get the key
        //     int value = A.get(key);
        //     // Get the corrsponding value
        //     System.out.println(key + "=" + value);
        // }
        
    }
}

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Array: [a b c d]
// Some Valid Sub Arrays: a, b c, d a b, a b c d
public class Solution {
    public static int kadanesCircularSum(int a[])
    {
        int len = a.length;
        //
        int all_neg_flag = 0;
        for(int i=0;i<len;i++)
        {
            if(a[i]>-1)
            {
                all_neg_flag=1;
            }
        }
        if(all_neg_flag==0)
        {
            int min = a[0];
            for(int i=0;i<len;i++)
            {
                if(a[i]>min)
                {
                    min=a[i];
                }
            }
            return min;
        }
        else
        {
            int maximumKadanes = kadanes(a);
            int maxWraparound=0;
            // Get wraparound max
            int[] neg_a = new int[len];
            for(int i=0;i<len;i++)
            {
                maxWraparound = maxWraparound + a[i];
                neg_a[i] = -a[i] ;    
            }
            maxWraparound = maxWraparound + kadanes(neg_a);
            //
            if(maxWraparound>maximumKadanes)
            {
                return maxWraparound;
            }
            else
            {
                return maximumKadanes;
            }
        }
    }
    
    public static int kadanes(int a[])
    {
        int len = a.length;
        int max_till_now = 0, max_ending_here = 0;
        for(int i=0; i<len;i++)
        {
            max_ending_here = max_ending_here + a[i];
            if(max_ending_here < 0)
            {
                max_ending_here = 0;
            }
            if(max_till_now < max_ending_here)
            {
                max_till_now = max_ending_here;
            }
        }
        return max_till_now;
    }
    public static void main(String args[] ) throws Exception {
        //int a[] = {1, 2, 3, -43, 5, 6, -11};  // Case not wrap around
        //int a[] = {1, 2, 3, -43, 5, 6, -1};   // Case wrap around needed
        //int a[] = {-1, 2, 3, -4};             // Corner elements excluded
        //int a[] = {1, 2, 3, 43};              // All elements required
        //int a[] = {-5, -2, -3, -4};           // All negative
        //int a[] = {};                           // Empty
        if(a.length>0)
        {
            int max = kadanesCircularSum(a);
            System.out.println(max);
        }
        else
        {
            System.out.println("The array is empty");
        }
    }
}
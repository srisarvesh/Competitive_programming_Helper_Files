import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.*;

public class DEFKIN {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int q=0;q<t;q++)
        {
            int w=in.nextInt();
            int h=in.nextInt();
            int n=in.nextInt();
            int[] x=new int[n];
            int[] y=new int[n];
            for(int i=0;i<n;i++)
            {
                int xi=in.nextInt();
                int yi=in.nextInt();
                x[i]=xi;
                y[i]=yi;
            }
            Arrays.sort(x);
            Arrays.sort(y);
            ArrayList<Integer> l1=new ArrayList<>();
            ArrayList<Integer> l2=new ArrayList<>();
            l1.add(x[0]-1);
            l2.add(y[0]-1);
            for(int i=1;i<n;i++)
            {
                l1.add(x[i]-x[i-1]-1);
                l2.add(y[i]-y[i-1]-1);
            }
            l1.add(w-x[n-1]);
            l2.add(h-y[n-1]);
            Collections.sort(l1);
            Collections.sort(l2);
            
          
            System.out.println(l1.get(l1.size()-1)*l2.get(l2.size()-1));

        }
    }
}

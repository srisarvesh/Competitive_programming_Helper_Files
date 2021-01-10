import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

public class greedy_algo {

    static int coin_change(TreeSet<Integer> t,int money)
    {
        int ans=0;
        while(money>0)
        {
            int v=t.floor(money);//we can also use binary search to do it
            System.out.println("V="+v);
            money-=v;
            ans++;
        }
        return ans;

    }
    static class pair{
        int st,et;
        public pair(int st,int et)
        {
           this.st=st;
           this.et=et;
        }
    }
    static class pointscom implements Comparator<pair>
    {
      @Override
      public int compare(pair p1, pair p2) {
        // TOD
        return p1.et-p2.et;
       }
    }
    static int activity_selection_problem(ArrayList<pair> v)
    {

        Collections.sort(v,new pointscom());
        for(int i=0;i<v.size();i++)
        {
            System.out.println(v.get(i).st+ " "+ v.get(i).et);
        }   
        int res=1;
        int fin=v.get(0).et;
        for(int i=1;i<v.size();i++)
        {
            if(v.get(i).st>=fin)
            {
                fin=v.get(i).et;
                res++;
            }
        }
        return res;
    }
    
    
    public static void main(String[] args)
    {
        



    

    } 
}

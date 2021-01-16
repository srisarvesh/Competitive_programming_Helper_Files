import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingDeque;

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
    static class p_c_expidition implements Comparator<pair>
    {
      @Override
      public int compare(pair p1, pair p2) {
        // TOD
        return p1.st-p2.st;
       }
    }
    static class high_priority implements Comparator<Integer>
    {
      @Override
      public int compare(Integer p1,Integer p2) {
        // TOD
        return p2-p1;
       }
    }
    static void Expedition_spoj()//In this question we need to find the minimum number of fuel stops that we can stop so we can reach the final destination L ..We initially will have p units of fuel..when ever we travel 1 unit the fuel drops 1 value.
    {
        //so we will be starting from 0th point ..after certain distances there will be n no of fuel stops with fuel .. so we need to reach the final destination by stopping at "very few" fuel points .
        //So the simple logic is we need to stop and fill the fuel from the fuel stops that has high fuel value .. so we can reach upto the destination.
       //--->Greedy Algorithm technique here is (we need to choose the fuel stops that has max capacity .so we need to sort the list of stops with high fuel and those stops must be reachable from the position we are currently in.)
        //so we need to a note all the fuel stations along with its value in a priorty queue(which holds values in descending order) so,when ever there is shortage of fuel to reach next point we pop out the first element from the priority queue and add our fuel value and move further
        //By maintaing the list of fuel stations with values in a priority queue ..we can retrieve it when there is shortage of fuel.
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int d,f,L,P; //d=this holds all the 
        ArrayList<pair> pa=new ArrayList<pair>();
        for(int i=0;i<n;i++)
        {
            d=in.nextInt();   //The values of d are the distances of the fuel stations from the Last town destination so we need to subtract each of its value with the L (the last location ) to get the distance that we need to travel from the begining point.
            f=in.nextInt();
            pa.add(new pair(d,f));
        }
        L=in.nextInt();
        P=in.nextInt();
        for(int i=0;i<n;i++)
        {
            pa.set(i,new pair(L-pa.get(i).st,pa.get(i).et ));
        }
        Collections.sort(pa,new p_c_expidition());
        int ans=0;
        int prev=0;
        int x=0;
        int flag=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>(new high_priority());//This is the queue that holds all the fuels from all the stops in descending order
        while(x<n)
        {
            if(P>=(pa.get(x).st-prev))
            {
               P-=pa.get(x).st-prev;
               pq.add(pa.get(x).et);
               prev=pa.get(x).st;

            }
            else{
                  if(pq.isEmpty())
                  {
                      flag=1;
                      break;

                  }
                  int kk=pq.poll();
                  P+=kk;
                  ans++;
                  continue;

            }
            x++;
           //System.out.println("P (FUel)="+P);

        }
        if(flag==1)
        {
            System.out.println(-1);
            return;
        }
        if(P>=L-prev)
        {
            System.out.println(ans);
            return;
        }
        //if we cant reach the final town then we need to use other fuel that we have noted and kept in priority queue 
        else{
            while(P<L)
        {
            if(pq.isEmpty())//if there all the fuels  that are noted is used then the queue will be empty and at that time we return -1 (means we cant reach the final destination)
            {
                flag=1;
                break;
            }
            P+=pq.poll();
            ans++;
            continue;
        }
        if(flag==1) // checking whether we can reach or not
        {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);//returning the final no of ffuel stops
       /* for (int iny : pq) {
            System.out.print(iny+" ");
        }
        System.out.println();
        */
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
    static int connecting_wires(int[] bd,int[] wd)
    {
        Arrays.sort(bd);
        Arrays.sort(wd);
        int l=0;
        for(int i=0;i<bd.length;i++)
        {
            int k;
             if(bd[i]-wd[i]<0)
             {
                 k=bd[i]-wd[i]*-1;
             }
             k=bd[i]-wd[i];

             l+=k;
        }
        if(l<0)
        {
            l=l*-1;
        }
        return l;


    }
    static int load_balance(int[] arr,int n)
    {
        int load=0;
        for(int i=0;i<arr.length;i++)
        {
          load+=arr[i];
        } 
        load=load/n;
        int diff=0,maxload=0;
        
        //greedy step
        for(int i=0;i<n;i++) // we will be partioning 
        {
            diff+=arr[i]-load;   
            int ans=diff;
            if(diff<0)
            {
                ans*=-1;
            }
            ans=Integer.max(ans, diff);
            maxload=Integer.max(ans, maxload);
            

        }
        return maxload;
        
    }

    static int defking(int w,int h,int n,int[] x,int[] y)
    {
        //we need return the max area that is undefended ..The area that are undefended are the areas not covered by the towers .. we need to find the area=dx * dy  
        //for each consecutive towers in both the x and y axis we need to find the distances between each consecutive towers and do -1 of their length so that we get the actual distance of length that is undefended 
        //dx=max(of all dx values) and dy=max(of all dy values)
        Arrays.sort(x);
        Arrays.sort(y);
        int dx=x[0]-0-1;
        int dy=y[0]-0-1;
        System.out.println("dx="+dx+"dy="+dy);
        for(int i=1;i<n;i++)
        { 
            int ansx=x[i]-x[i-1]-1;
            dx=Integer.max(ansx,dx);
            int ansy=y[i]-y[i-1]-1;
            dy=Integer.max(ansy, dy);
            System.out.println("dx="+dx+"dy="+dy);
        }
        int ansx=w-x[n-1]-1;
        dx=Integer.max(ansx, dx);
        int ansy=h-y[n-1]-1;
        dy=Integer.max(ansy,dy);
        System.out.println("dx="+dx+"dy="+dy);

        return dx*dy;
    }
    static int Chopsticks()
    {
            Scanner in=new Scanner(System.in);
            int N=in.nextInt();
            int D=in.nextInt();
            int[] L=new int[N];
            for(int i=0;i<N;i++)
            {
                L[i]=in.nextInt();

            }
            Arrays.sort(L);
            int counter=0;
            int i=0;
            while(true)
            {
                if(i==N-1)
                {
                    break;
                }
                if(L[i+1]-L[i]<=D)
                {
                    
                    counter++;
                    i=i+2;
                }
                else{
                 if(L[i+1]-L[i]>D)
                 {
                 i=i+1;
                 }
                }
            }
            return counter;
    }
    static int N_Meetings_in_one_room(int start[], int end[], int n)
    {
        ArrayList<pair> p=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            p.add(new pair(start[i], end[i]));
        }
        Collections.sort(p,new pointscom());
        int count=0;
        int cur=p.get(0).et;
        for(int i=1;i<n;i++)
        {
            if(cur<p.get(i).st)
            {
                count++;
                cur=p.get(i).et;
            }
        }


           return count;
    }
    

    public static void main(String[] args)
    {
      ArrayList<Integer> gg=new ArrayList<>();
      for(int i=0;i<gg.size();i++)
      {
          
      }
    } 
}


import java.util.*;



public class expedition
{
    public static class pair 
    {
        int dist;
        int fuel;
        pair(int dist,int fuel)
        {
            this.dist=dist;
            this.fuel=fuel;
        }
    }
    public static class c1 implements Comparator<pair>
    {
      @Override
      public int compare(pair p1,pair p2)
      {
          if(p1.dist<p2.dist)
          {
            return -1;
          }      
          return 1;
      }
    }
    public static class c2 implements Comparator<Integer>
    {
        @Override
        public int compare(Integer i1,Integer i2)
        {
           if(i1<i2)
           {
               return 1;
           }
           return -1;
        }
    }
    public static void main(String[] args)
    {
       Scanner in=new Scanner(System.in);
       int t=in.nextInt();
       for(int e=0;e<t;e++)
       {
           int n=in.nextInt();
           ArrayList<pair> arr=new ArrayList<>();
           for(int i=0;i<n;i++)
           {
               int dist=in.nextInt();
               int fuel=in.nextInt();
               arr.add(new pair(dist, fuel));
           }
           int L=in.nextInt();
           int p=in.nextInt();
           for(int i=0;i<n;i++)
           {
               arr.set(i, new pair(L-arr.get(i).dist,arr.get(i).fuel));
           }
           Collections.sort(arr,new c1());
          
           PriorityQueue<Integer> pq=new PriorityQueue<>(new c2());
           int i=0;
           boolean flag=true;
           int prev=0;
           int ans=0;
           while(i<n)
           {
               if(p>=arr.get(i).dist-prev)
               {
                   
                   
                   pq.add(arr.get(i).fuel);
                   p-=arr.get(i).dist-prev;
                   
                   prev=arr.get(i).dist;
                   i++;
                 
               }
               else{
                   if(pq.isEmpty()==false)
                   {
                     p+=pq.poll();
                    
                     ans++;
                   }
                   else{
                       if(pq.isEmpty()==true)
                       {
                           flag=false;
                           break;
                       }
                   }
               }
           }
           int d1=L-prev;
           int d2=L-arr.get(n-1).dist;
           System.out.println("1="+d1);
           System.out.println("2="+d2);
           if(flag==false)
           {
               System.out.println(-1);
               continue;
           }
           else{
               
                if(p>=L-arr.get(n-1).dist)
                {
                    prev=L;
                    p-=L-arr.get(n-1).dist;
                    System.out.println(ans);
                    continue;
                }
                else{
                    while(p<L-arr.get(n-1).dist)
                    {
                        if(pq.isEmpty())
                        {
                            flag=false;
                            break;
                        }
                        p+=pq.poll();
                        ans+=1;
                    }
                    if(flag==false)
                    {
                        System.out.println(-1);
                        continue;
                    }
                    System.out.println(ans);
                    

                }
           }
       }

    }
}
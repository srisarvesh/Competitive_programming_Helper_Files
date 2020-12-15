import java.util.Arrays;

import jdk.nashorn.internal.runtime.regexp.joni.Syntax;



public class dp{
  

    static int fib(int n,int[] dp) //Here we will pass the n(We want to find the nth fibonoci number) and the dp array with -1 for all of its indexes and the dp array size should be n+1
    {
       int ans1;//Holds the value of fib(n-1)
       int ans2;//Holds the value of fib(n-2)
       int myans;//This will hold ans1+ans2 which has the final value of fib(n) and will be returned to the called line
       
      if(n==0||n==1)
      {
          return n;
      }
      else{
       
          if(dp[n-1]==-1)   //Here we alredy check whether the value of function call is not stored and still its -1
          {
              ans1=fib(n-1, dp); //We make the function call
              dp[n-1]=ans1; //Then we store the function call at the proper index of the dp array
          }
          else{
              ans1=dp[n-1];//If the value is already stored we take the value from here 
          }
          if(dp[n-2]==-1) //Here we alredy check whether the value of function call is not stored and still its -1
          {
              ans2=fib(n-2, dp);//We make the function call
              dp[n-2]=ans2;//Then we store the function call at the proper index of the dp array
          }
          else{
              ans2=dp[n-2];//If the value is already stored we take the value from here 
          }
       
      }
      myans=ans1+ans2; //ans1 + ans2 give the value of fib(n)
      return myans;//And we return that value
    }
    static int fibIter(int n,int[] dp)
    {
        Arrays.fill(dp,0);
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++)
        {
            dp[i]=dp[i-1]+dp[i-2]; //Creating an array that holds the call values and it find the call values by a recurance relation like if we want to  know fib(n)=fib(n-1)+fib(n-2)

        }
        return dp[n];//Thn  we return that value of n from the array
    }

    static int minstepsto1(int n,int[] dp)
    {
       if(n==1)
       {
           return 0;
       }
       int ans1=Integer.MAX_VALUE;
       int ans2=Integer.MAX_VALUE;
       int ans3=Integer.MAX_VALUE;
       if(n%3==0){
           if(dp[(int)n/3]==-1)
           { 
             ans1=minstepsto1((int)n/3, dp);
             dp[(int)n/3]=ans1;
           }
           else{
               ans1= dp[(int)n/3];
           }
        }
        if(n%2==0)
        {
        if(dp[(int)n/2]==-1)
        { 
          ans2=minstepsto1((int)n/2, dp);
          dp[(int)n/2]=ans2;
        }
        else{
            ans2= dp[(int)n/2];
          }
        }
        if(dp[n-1]==-1)
        { 
          ans3=minstepsto1(n-1, dp);
          dp[n-1]=ans3;
        }
        else{
            ans3= dp[n-1];
        }

      
     int myans=Integer.min(ans1, ans2);
     myans=Integer.min(myans,ans3);
     return myans+1;
    }
    static int minstepsto1Iter(int n)
    {
       int[] dp=new int[n+1];
       dp[1]=0;
       for(int i=2;i<=n;i++){
         int min=dp[i-1];
         if(i%3==0)
         {
             if(min>dp[(int)i/3])
             {
                 min=dp[(int)i/3];
             }
         }
         if(i%2==0)
         {
             if(min>dp[(int)i/2])
             {
                 min=dp[(int)i/2];
             }
         }
        dp[i]=1+min;

       }
       return dp[n];
      

    }
    static int minsquares(int n,int[] dp)
    {
        if(n==0)
        {
            return 0;
        }
        int ans=Integer.MAX_VALUE;
        int sroot=(int)Math.sqrt(n);
        for(int i=1;i<=sroot;i++)
        {
            int myans;

            if(dp[n-((int)Math.pow(i, 2))]==-1)
            {
                myans=minsquares(n-((int)Math.pow(i, 2)),dp);
                dp[n-((int)Math.pow(i, 2))]=myans;  

            }
            else{
                myans=dp[n-((int)Math.pow(i, 2))];
            }
           
           ans=Math.min(myans, ans);
          
        }
        return ans+1;
    }
    
   static int minsquaresIter(int n)
   {
        int[] dp=new int[n+1];
        dp[0]=0;
        for(int i=1;i<=n;i++)
        {
            int ans=Integer.MAX_VALUE;
            for(int j=1;j<=(int)Math.sqrt(i); j++)
            {
               int myans=dp[i-(j*j)];
               ans=Math.min(ans, myans);
            }
            dp[i]=ans+1;
        }
        return dp[n];
   }


   static int mincostpathinmatrix(int[][] cost,int i,int j,int n,int m,int[][] dp)
   {
       if((i==n-1) && (j==m-1))
       {
           return cost[i][j];
       }
       if(i>=n || j>=m)
       {
        return Integer.MAX_VALUE;
       }
       int ans1;
       if(dp[i+1][j]==Integer.MAX_VALUE)
       {
        ans1=mincostpathinmatrix(cost, i+1, j, n, m, dp);
        dp[i+1][j]=ans1;
       }
       else{
           ans1=dp[i+1][j];
       }
       int ans2;
       if(dp[i][j+1]==Integer.MAX_VALUE)
       {
        ans2=mincostpathinmatrix(cost, i, j+1, n, m, dp);
        dp[i][j+1]=ans2;
       }
       else{
           ans2=dp[i][j+1];
       }
       int ans3;
       if(dp[i+1][j+1]==Integer.MAX_VALUE)
       {
        ans3=mincostpathinmatrix(cost, i+1, j+1, n, m, dp);
        dp[i+1][j+1]=ans3;
       }
       else{
           ans3=dp[i+1][j+1];
       }
       int ans;
       int min;
       min=ans1;
       if(min>ans2)
       {
           min=ans2;
       }
       if(min>ans3)
       {
           min=ans3;
       }
       return min+cost[i][j];

   }


   static int mincostpathinmatrixIterBU(int[][] cost,int n,int m)
   {
       
       int[][] dp=new int[n+1][m+1];
       for(int i=0;i<n+1;i++)
       {
       for(int j=0;j<m+1;j++)
       {
           dp[i][j]=Integer.MAX_VALUE;
       }
      }
       for(int i=n-1;i>=0;i--)
       {
           for(int j=m-1;j>=0;j--)
           {
               if(i==n-1 && j==m-1)
               {
                   dp[i][j]=cost[i][j];
               }
               else{
                   int ans1=dp[i+1][j];
                   int ans2=dp[i][j+1];
                   int ans3=dp[i+1][j+1];
                   int min=ans1;
                   if(min>ans2)
                   {
                       min=ans2;
                   }
                   
                      if(min>ans3)
                      {
                          min=ans3;
                      }
                   
                  
                   dp[i][j]=cost[i][j]+min;
               }
               
           }
       }  
       return dp[0][0];

   }

   
   static int mincostpathinmatrixIterTD(int[][] cost,int n,int m)
   {
       
       int[][] dp=new int[n+1][m+1];
       for(int i=0;i<n+1;i++)
       {
       for(int j=0;j<m+1;j++)
       {
           dp[i][j]=Integer.MAX_VALUE;
       }
      }
       for(int i=1;i<n+1;i++)
       {
           for(int j=1;j<m+1;j++)
           {
               if(i==1 && j==1)
               {
                   dp[i][j]=cost[i-1][j-1];
               }
               else{
                   int ans1=dp[i-1][j];
                   int ans2=dp[i][j-1];
                   int ans3=dp[i-1][j-1];
                   int min=ans1;
                   if(min>ans2)
                   {
                       min=ans2;
                   }
                   
                      if(min>ans3)
                      {
                          min=ans3;
                      }
                   
                  
                   dp[i][j]=cost[i-1][j-1]+min;
               }
               
           }
       }  
       return dp[n][m];

   }


    public static void main(String[] args)
    {
               int n=4;
               int m=3;
               int[][] cost={{1,5,11},{8,13,12},{2,3,7},{15,16,18}};

               System.out.println(mincostpathinmatrixIterTD(cost,4,3));

           
            
    }
}
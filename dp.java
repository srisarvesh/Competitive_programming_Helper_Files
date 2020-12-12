import java.util.Arrays;

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
    public static void main(String[] args)
    {
            int n=3;
            int[] dp=new int[n+1];
            Arrays.fill(dp, -1);
            System.out.println("Value of fib of "+n+" ="+fib(n, dp));
    }
}
import java.util.Arrays;
import java.util.Scanner;

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

   static int lcs2rec(String  str1,String str2,int i,int j,int[][] dp) //here in the dp array holds the longest comman subsequence from i(str1 string ) and j(str2 string) initaaly it has all its value to be -1
   {
       if(i>=str1.length() || j>=str2.length()) //If we exceed the length of any of the string ..we cant find any lcs with the remaing  characters in any one of the string so we return 0 as the only character that is common is  null
       {
            return 0;
       }
       int ans;
       if(str1.charAt(i)==str2.charAt(j))
       {
           if(dp[i+1][j+1]==-1) //When two characters are matching in both str1 and str2 then they both contribute a length 1 common subsequnce .. so 1+lcs(i+1,j+1) //we need to traverse after that one matching character and find the rest of subsequnce
           {
               int value=lcs2rec(str1, str2, i+1, j+1, dp);
               dp[i+1][j+1]=value;
               ans=1+value;
           }
           else{
               ans=1+dp[i+1][j+1];  //if that particular value of  lcs from an index i and j is already there then we use it
           }

       }
       else{
           int ans1,ans2; //If the characters at i(str1) and j(str2) are not matching we we need to look searching for two options ans(1)=lcs2rec(i+1,j) ans(2)=lcs2rec(i,j+1) and return max of ans1,ans2
           if(dp[i+1][j]==-1)  
           {
              ans1=lcs2rec(str1, str2, i+1, j, dp);
              dp[i+1][j]=ans1;
           }
           else{
               ans1=dp[i+1][j];
           }
           if(dp[i][j+1]==-1)  
           {
              ans2=lcs2rec(str1, str2, i, j+1, dp);
              dp[i][j+1]=ans2;
           }
           else{
               ans2=dp[i][j+1];
           }
           ans=Integer.max(ans1, ans2);

       }
       return ans;
   }
   
   

   static int lcs2iterBU(String str1,String str2)
   {
       int n=str1.length();
       int m=str2.length();
       int[][] dp=new int[n+1][m+1];
       for(int i=0;i<n+1;i++)
       {
           for(int j=0;j<m+1;j++)
           {
               dp[i][j]=0;
           }
       }
       for(int i=n-1;i>=0;i--)
       {
           for(int j=m-1;j>=0;j--)
           {
               if(str1.charAt(i)==str2.charAt(j))
               {
                    dp[i][j]=1+dp[i+1][j+1];
               }
               else{
                   dp[i][j]=Integer.max(dp[i+1][j], dp[i][j+1]);
               }
           }
       }
       return dp[0][0];
   }


   static int knapsack01BU(int w,int[] val,int[] wt,int n)
   {
      
       int[][] dp=new int[n+1][w+1];
       for(int o1=0;o1<n+1;o1++)
       {
           for(int o2=0;o2<w+1;o2++)
           {
               dp[o1][o2]=0;
           }
       }
       int ans;
      for(int i=1;i<n+1;i++)
      {
          for(int j=1;j<w+1;j++)
          {

              if(wt[i-1]<=j)
              {
                int ans1=val[i-1]+dp[i-1][j-wt[i-1]];
                int ans2=dp[i-1][j];
                ans=Integer.max(ans1,ans2);
              }
              else{
                  ans=dp[i-1][j];;
              }
              dp[i][j]=ans;
          }
      }
    
      return dp[n][w];
      


   }
   static boolean reccheckif_list_is_sort(int i,int j,int[] l)
   {
       if(i >= l.length || j >= l.length)
       {
           return true;
       }
       if(l[i]>l[j])
       {
           return false;
       }
       boolean vvv=reccheckif_list_is_sort(++i,++j,l);
       if(vvv)
       {
           return true;
       }
       else{
           return false;
       }

   }
   static int firstIndexofaNumberIN_THE_LIst(int[] l,int i,int x)
   {
      if(i>=l.length)
      {
          return -1;
      }
      if(l[i]==x)
      {
          return i;
      }
      return firstIndexofaNumberIN_THE_LIst(l, ++i, x);
   }
   static int lastIndexofaNumberIN_THE_LISt(int[] l,int i,int x)
   {

    if(i>=l.length)
      {
          return -1;
      }
      int rv=lastIndexofaNumberIN_THE_LISt(l, i+1, x);
      if(rv==-1)
      {
        if(l[i]==x)
        {
            return i;
        }
      }
      
          return rv;
      
   }
   
   
   static String replacecharin_string(String s,int i,char wr,char r)
    {
        if(i>=s.length())
        {
            return s;
        }
        String check=replacecharin_string(s, i+1, wr, r);
        if(s.charAt(i)==wr)
        {
            return r+check;
        }
        else{
            return s.charAt(i)+check;
        }

    }
    static String replacePIinstring(String s)
    {
        if(s.length()==0 || s.length()==1)
        {
            return s;
        }
        if(s.charAt(0)=='p' && s.charAt(1)=='i'){
            return "3.14"+replacePIinstring(s.substring(2));

        }
        else{
            return s.charAt(0)+replacePIinstring(s.substring(1));
        }
    }
    static int bsearch(int start,int last,int x,int[] a){
    if(start<=last)
    {
        int mid=(int)(start+last)/2;
        if(a[mid]==x)
        {
            return mid;
        }
        if(a[mid]>x)
        {
            return bsearch(start, mid-1, x, a);
        }
        if(a[mid]<x){
            return bsearch(mid+1, last, x, a);
        }


    }
    return -1;
    
    }
    static int powe(int a,int n)
    {
        if(n==0)
        {
            return 1;
        }
        
        return a*powe(a, n-1);
    }
    static int multiplywithout_mulitiply_operator(int a,int n)
    {

        if(n==0)
        {
            return 0;
        }
        return a+multiplywithout_mulitiply_operator(a, n-1);
    }

    static int linearsearchusingrecursion(int[] a,int si,int x)
    {
        System.out.println("length="+a.length);
        if(a.length==0)
        {
            return -1;
        }
        if(a[0]==x)
        {
            return 0;
        }
        int i=linearsearchusingrecursion(Arrays.copyOfRange(a, si+1,a.length) ,si, x);
        if(i==-1)
        {
            return -1;
        }
         else{
            return i+1;
        }
    }
    static int linearsearchusingrecursionFrom_Last_Index(int[] a,int si,int x)
    {
        
        if(a.length==0)
        {
            return -1;
        }
       
        int i=linearsearchusingrecursionFrom_Last_Index(Arrays.copyOfRange(a, si+1,a.length) ,si, x);
        if(i==-1)
        {
            if(a[0]==x)
            {
                return 0;
            }
            else{
                return -1;
            }
        }
        
            return i+1;
        
    }  

    static int storeAllOccurances_of_an_element_into_an_array(int[] a,int i,int[] out,int j,int n,int x)
    {
        if(i==n)
        {
            return j;
        }
        if(a[i]==x)
        {
            out[j]=i;
          return  storeAllOccurances_of_an_element_into_an_array(a, i+1, out, j+1, n, x);
        }
        return storeAllOccurances_of_an_element_into_an_array(a, i+1, out, j, n, x);
    }       
    
    static void printwordfromthenumber(int n)
    {
        if(n==0)
        {
            return;
        }
        int pp=n%10;
        printwordfromthenumber(n/10);
        switch(pp)
        {
            case 0:System.out.print("Zero");
            break;
            case 1:System.out.print("One");
            break;
            case 2:System.out.print("Two"+" ");
            break;
            case 3:System.out.print("Three");
            break;
            case 4:System.out.print("Four");
            break;
            case 5:System.out.print("Five");
            break;
            case 6:System.out.print("Six");
            break;
            case 7:System.out.print("Seven");
            break;
            case 8:System.out.print("Eight");
            break;
            case 9:System.out.print("Nine");
            break;
        } 

    }

    static void replace_pi_3_14_in_charArray(char[] a,int i)
    {
       if(a[i]=='\u0000'|| a[i+1]=='\u0000' )
       {
           return ;
       }
       if(a[i]=='p' && a[i+1]=='i')
       {
           int j=i+2;
           while(a[j]!='\u0000')
           {
            j++;
           }
           while(j>=i+2)
           {
               a[j+2]=a[j];
               j--;
           }
           a[i]='3';
           a[i+1]='.';
           a[i+2]='1';
           a[i+3]='4';
           replace_pi_3_14_in_charArray(a,i+4);

       }
       else{
           replace_pi_3_14_in_charArray(a, i+1);
       }
  

    }
    static int count_no_ways_we_can_fill_N_columns_Wall_Has_4_rows(int N)
    {
        if(N<=3)
        {
            return 1;
        }
        return count_no_ways_we_can_fill_N_columns_Wall_Has_4_rows(N-1)+count_no_ways_we_can_fill_N_columns_Wall_Has_4_rows(N-4);
    }
    static int count_No_Ways_we_can_reach_the_destination_point(int n)
    {
        if(n==0)
        {
            return 1;
        }
        if(n==1)
        {
            return 1;

        }
        if(n==2)
         {
            return 2;
        }
        if(n==3)
        {
            return 4;

        }
        return count_No_Ways_we_can_reach_the_destination_point(n-1)+count_No_Ways_we_can_reach_the_destination_point(n-2)+count_No_Ways_we_can_reach_the_destination_point(n-3);
    }
    static int fact(int n) 
    { 
      if(n==0 || n==1)
      {
          return 1;
      } 
      return n*fact(n-1);
    } 
     static int nCr(int n, int r) 
    { 
    return fact(n) / (fact(r) * 
                  fact(n - r)); 
    } 
    static int friendsproblem_coming(int n)
    {
        if(n==0){
            return 1;
        }
        if(n==1)
        {
            return 1;
        }
        if(n==2)
        {
            return 2;   
        }
       

        int ans1=friendsproblem_coming(n-1);
        int ans2=nCr(n-1, 1) * friendsproblem_coming(n-2);
        return ans1+ans2;
    }
    static int count_no_Binary_strings(int n)
    {
           if(n==0)
           {
               return 0;
           }
           if(n==1)
           {
               return 2;
           }
           if(n==2)
           {
               return 3;
           }
           return count_no_Binary_strings(n-1)+count_no_Binary_strings(n-2);
        }


    static void generate_sub_sequence(char[] in,char[] out,int i,int j)
    {
        if(in[i]=='\u0000')
        {
            out[j]='\u0000';
            String e=new String(out);
            System.out.println(e);
            return;
        }

        out[j]=in[i];
        generate_sub_sequence(in, out, i+1, j+1);
        generate_sub_sequence(in, out, i+1, j);

    }

    static void generate_brakets(char[] out,int i,int n,int open,int close)
    {
        if(i==2*n)
        {
            out[i]='\u0000';
            String e=new String(out);
            System.out.println(e);
            return;    
        }
        if(open<n){
            out[i]='(';
            generate_brakets(out, i+1, n, open+1, close);
        }
        if(close<open)
        {
            out[i]=')';
            generate_brakets(out, i+1, n, open, close+1);
        }
        return;

    }
    
    static void generate_names_from_keypad_sequence(char[] in,char[] out,int i,int j,String[] keypad){
        if(in[i]=='\u0000'){
             out[j]='\u0000';
             String s=new String(out);
             System.out.println(s);
             return;
        }
        
        int digit=in[i]-'0';
        
        if(digit==0||digit==1)
        {
            generate_names_from_keypad_sequence(in, out, i+1, j,keypad);
        }

        for(int k=0;k<keypad[digit].length();k++)
        {
           out[j]=keypad[digit].charAt(k);
           generate_names_from_keypad_sequence(in, out, i+1, j+1,keypad);
        }

       return;
       

    }
    static char[] lcs={'\u0000','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    static void generate_strings(char[] in,char[] out,int i,int j)
    {
        if(in[i]=='\u0000')
        {
            out[j]='\u0000';
            String s=new String(out);
            System.out.println(s);
            return;
        }

        int digit=in[i]-'0';
        out[j]=lcs[digit];
        generate_strings(in, out, i+1, j+1);
        if(in[i+1]!='\u0000')
        {
             int digit2=in[i+1]-'0';
             int no=(digit*10)+digit2;
             if(no<=26)
             {
                 out[j]=lcs[no];
                 generate_strings(in, out, i+2, j+1);
             }
        }
        return;

    }
    static int merge(int[] a,int s,int e)
    {
        int[] temp=new int[1000000];
        int i=s;
       
        int k=s;
        int mid=(s+e)/2;
        int j=mid+1;
        int count=0;
        while(i<=mid&&j<=e)
        {
            if(a[i]<=a[j])
            {
                temp[k]=a[i];
                k++;
                i++;
            }
            else{
                temp[k++]=a[j++];
                count+=mid-i+1;
                
            }
        }
        while(i<=mid)
        {
            temp[k++]=a[i++];
        }
        while(j<=e)
        {
            temp[k++]=a[j++];
        }
        for(int q=s;q<=e;q++)
        {
            a[q]=temp[q];
        }
        return count;
    }
     
    static int inversion_count_of_array_i_is_greater_than_array_j(int[] a,int s,int e)
    {
          if(s>=e)
          {
              return 0;

          }
          int mid=(s+e)/2;
          int x=inversion_count_of_array_i_is_greater_than_array_j(a, s, mid);
          int y=inversion_count_of_array_i_is_greater_than_array_j(a, mid+1, e);
          int z=merge(a,s,e); //holds the value of cross inversions count
          return x+y+z;
    }
    static int string_to_int(String s,int n)
    {
        if(n==0)
        {
            return 0;
        }
        if(n==1)
        {
            return s.charAt(n-1)-'0';
        }
        
        return string_to_int(s,n-1)*10+s.charAt(n-1)-'0';
    }

    static int fastpow(int a,int n)
    {
        if(n==0)
        {
            return 1;
        }
        int anss=fastpow(a,n/2);
        anss*=anss;
        if(n%2==0)
        {
            return anss;
        }
        else{
            return a*anss;
        }

    }


    public static void main(String[] args)  
    {
       
      System.out.println(fastpow(2, 4)); 
        
    }
}   
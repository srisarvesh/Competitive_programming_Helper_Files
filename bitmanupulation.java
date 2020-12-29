import java.util.ArrayList;
import java.util.Arrays;


public class bitmanupulation
{
    static int uniqueNumber1intheSequence(int[] arr,int N)
    {
        int ans=0;
        for(int i=0;i<N;i++)
        {
            ans^=arr[i];
        }
        return ans;
    }

   static Boolean isOdd(int N)
    {
        if((N&1)==1){
            return true;
        }
        return false;  //Since for every odd number the last bit is 1 so N&1 is 1 so it will return True
    }
   
   static int getithBit(int N,int i)
    {
        int mask=1<<i;   //we created a mask in a way that there is 1 in the bits of a number of ith bit ex : 000..10000  
        return (N&mask)>0?1:0; //if the & gives a number greater than 0 then the ith bit is 1 or it is 0
    }

  static  int setithBit(int N,int i)
    {
        int mask=1<<i; //we need to create a mask that has set bit only at the ith position ex 001000
        int ans;
        ans=N|mask;
        return ans;

    }

    static int clearithBit(int N,int i)
    {
        int mask=~(1<<i);    //here we do (1<<i) which gives value ex: 0001000 i=3  after ~ mask will be like 1110111 
        int ans=N&mask;      //Then if we do N & mask it will clear out the ith bit of the N
        return ans; 
    }

  static int updateTheithBit(int N,int i,int V)
    {
        int mask=~(1<<i);
        int clearBit=mask&N; //we need to clear the ith bit so we can easily do or operation
        int ans=clearBit|(V<<i);  //we are intially pushing the V value to the ith position and doing the or operation withe cleared bit value of N
        return ans;
    }

  static  int clearLastiBits(int N,int i)
    {
        int mask=(~0)<<(i+1);// we will be having (~0) =111111...111111 and we are doing (~0)<<(i+1) to get a mask that clears the last i bits ex : mask=1111...1110000  if we want to clear the last i bits where i=3
        int ans=N&mask;
        return ans;
    }

  static  int clearRangeOfBits(int N,int i,int j)
    {
        int allones=(~0);//TO get a value like all 1's 111111...111111
        int a=allones<<(j+1); //we do this to get 0's after and from j bit like 111111.110000
        int b=(1<<i)-1; //to get a value like 000.000111 like one should come upto a particular bit we need to do 2^i -1
        int mask=a|b; //which will have 0 bits in a particular range like ex: 1111.110000111
        int ans=N&mask;  //we do this to clear a range of bits from j to i
        return ans;
    } 

 static   int replaceaRangeOfBitswithavalueM(int N,int m,int i,int j)
    {
        int clearrange=clearRangeOfBits(N, i, j);
        int ans=clearrange|(m<<i); //This will push the bits of m to till ith bit so when we do or operation the values get settled properly
        return ans;
    }

  static  int countNoSetBits(int N)
    {
        int count=0;
        while(N>0)
        {
           count++;
           N=N&(N-1);
        }
        return count;
    }
    
   static int decimalToBinary(int n)
    {
        int ans=0;
        int p=1;
        while(n>0)
        {
            int lastBit=n&1;
            lastBit*=p;
            ans+=lastBit;
            p*=10;
            n=n>>1;
        }
        return ans;


    }
    
    static int[] uniqueNumber2_2Unique_No_will_be_intheSequence(int[] a,int n)
    {
        int xorr=0;
        for(int i=0;i<n;i++)
        {
            xorr^=a[i];
        }
        int temp=xorr;
        int pos=0; //we are storing the first set bit occurance in this
        while((temp&1)!=1)
        {
          pos++;
          temp=temp>>1;
        }
        int mask=(1<<pos);
        int x=0,y=0;
        for(int i=0;i<n;i++)
        {
            if((a[i]&mask)==1)
            {
              x^=a[i];
            }

        }
        y=x^xorr;
        int[] arrayoftwouniquenumbers={x,y};
        return arrayoftwouniquenumbers;
        
    }
    
    static int uniqueNumberfromthesequenceof3Timesappearing(int[] arr,int nofelements)
    {
        int[] countsum=new int[64];
        Arrays.fill(countsum, 0);
        int n;
        for(int i=0;i<nofelements;i++)
        {
            n=arr[i];
            int j=0;
            while(n>0)
            {
                int lastBitt=n&1;
                countsum[j]+=lastBitt;  
                j++;
                n=n>>1;
            }
        }
        int p=1;
        int ans=0;
        for(int i=0;i<64;i++)
        {
             countsum[i]%=3;
             ans+=countsum[i]*p;
             p=p<<1;

        }
        return ans;
    }
    
   static int fastExponentiationUsingBitmasking(int a,int n)
    {
      int ans=1;
      while(n>0)
      {
          int lb=n&1;
          if((lb&1)==1)
          {
              ans*=a;
          }
          a=a*a;
          n=n>>1;
      }
      return ans;
    }

    static ArrayList<String> powerset(char[] a)
    {
     
        int n=a.length;
        ArrayList<String> l=new ArrayList<>();
        for(int i=0;i<(1<<n);i++)
        {
         
            int Num=i;
            int j=0;
            String ss="";
            while(Num>0)
            {
              
              int lb=Num&1;
              if(lb==1)
              {
              
               ss+=a[j];
              }
              j++;
             Num=Num>>1;
            }
           
           l.add(ss);
                
         
        }
        return l;
    }

   static int inclusion_exclusion_func_where_we_will_pass_a_list_of_primenumbers_to_find_NO_Nos_thar_are_divisible_by_the_primes(int[] primes,int n)
    {
         int noof_subsets=(1<<primes.length)-1;
         int ans=0;
         for(int i=1;i<=noof_subsets;i++)
         {
             int denom=1;
             int no_of_setBits=countNoSetBits(i);
             int j=0;
             int xxx=i;
             while(xxx>0)
             {
                   int lastBit=(xxx&1);
                   if((lastBit&1)==1)
                   {
                       denom=denom*primes[j];
                   }
                   j++;
                   xxx=xxx>>1;
             }
            if(no_of_setBits%2==0){
                ans-=(n/denom);
            }
            else{
                ans+=(n/denom);
            }
         }
         return ans-1;

    }
    




    public static void main(String[] args) {
      int[] p={2,3,5};
      System.out.println(inclusion_exclusion_func_where_we_will_pass_a_list_of_primenumbers_to_find_NO_Nos_thar_are_divisible_by_the_primes(p,1000));
          
          
    }
}
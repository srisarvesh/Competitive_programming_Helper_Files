import java.util.Arrays;
import java.util.Scanner;

public class pickchick {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int q=0;q<t;q++)
        {
            int N=in.nextInt();
            int K=in.nextInt();
            int B=in.nextInt();
            int T=in.nextInt();
            int tempk=0;
            int[] x=new int[N];
            for(int i=0;i<N;i++)
            {
                x[i]=in.nextInt();
            }
            int[] v=new int[N];
            for(int i=0;i<N;i++)
            {
               v[i]=in.nextInt();
            }

            int nos=0;
            int notpos=0;
            for(int i=N-1;i>=0;i--)
            {
               if((x[i]+(v[i]*T))>=B)
               {
                 tempk++;
                 if(notpos>0)
                 {
                   nos+=notpos;
                 }
                 
               }
               else{
                 notpos++;
               }
               if(tempk>=K)
               {
                 break;
               }
          
            }
            if(tempk>=K)
            {
              System.out.println(nos);
               
            }
            else{
              System.out.println("IMPOSSIBLE"); 
            }
        }
    }
}

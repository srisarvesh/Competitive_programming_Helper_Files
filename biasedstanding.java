import java.util.Arrays;
import java.util.Scanner;

public class biasedstanding {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int[] count=new int[10000];
        int t=in.nextInt();
        for(int u=0;u<t;u++)
        {
            int n=in.nextInt();
            
            Arrays.fill(count, 0);
            for(int i=0;i<n;i++)
            {
                String name=in.next();
                int rankl=in.nextInt();
                count[rankl]++;
            } 
            int value=0;
            int actual=1;
            for(int i=1;i<=n;i++)
            {
                while(count[i]!=0)
                {
                    value+=Math.abs(actual-i);
                    count[i]--;
                    actual++;
                }
                
            }
            System.out.println(value);
        }
    }
}

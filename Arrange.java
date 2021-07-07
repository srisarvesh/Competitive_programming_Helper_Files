import java.util.Arrays;
import java.util.Scanner;

public class Arrange {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int q=0;q<t;q++)
        {
            int n=in.nextInt();
            int[] a=new int[n];
            for(int i=0;i<n;i++)
            {
                a[i]=in.nextInt();
            }
            Arrays.sort(a);
            int i=0;
            for(i=0;i<n&&a[i]==1;i++)
            {
                System.out.print(a[i]+" ");
            }
            if(i==n-2&&a[i]==2&&a[i+1]==3)
            {
                System.out.print(a[i]+" "+a[i+1]+" ");
                System.out.println();
            }
            else{
                for(int k=n-1;k>=0&&a[k]!=1;k--)
                {
                  System.out.print(a[k]+" ");
                  System.out.println();
                }
            }
            
        }
    }
}

import java.nio.file.NotLinkException;
import java.util.Scanner;

public class loadbalancing {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        if(n==-1)
        {
            System.exit(0);
        }
        
        int[] arr=new int[n];
        int load=0;
        for(int i=0;i<n;i++)
        {
            arr[i]=in.nextInt();
            load+=arr[i];
        }
        if((load%2)!=0)
        {
            System.out.println(-1);
           
        }
        else{
        load=load/n;

       
        int diff=0;
        
        int max=0;
        for(int i=0;i<n;i++)
        {
        	diff+=(arr[i]-load);
        	int ans=Math.abs(diff);
        	max=Math.max(max,ans);
        }
        
        System.out.println(max);
        
        }
    }
}

import java.util.*;
public class GERGOVIA
{
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
            
            int i=0;
            int j=0;
            while(a[i]<=0)
            {
                i++;
            }
            while(a[j]>=0)
            {
                j++;
            }
  
            int value=0;
            while(i<n&&j<n)
            {
                if(Math.abs(a[i])>=Math.abs(a[j]))
                {
                    int temp=a[i];
                    a[i]+=a[j];
                    a[j]=0;
                    value+=Math.abs((j+1)-(i+1))*(Math.abs(temp-a[i]));
                    
                    while(j<n&&a[j]>=0)
                    {
                        j++;
                    }
                    
                      while(i<n&&a[i]<=0)
                      {
                          i++;
                      }
                      
                }
                else{
                  int temp=a[j];
                  a[j]+=a[i];
                  a[i]=0;
                  value+=Math.abs((j+1)-(i+1))*(Math.abs(temp-a[j]));
                    
                  while(a[j]>=0)
                    {
                        j++;
                    }
                     while(a[i]<=0)
                      {
                          i++;
                      }
                   
                }
            }
          System.out.println(value);
  
           
        }
    }
}
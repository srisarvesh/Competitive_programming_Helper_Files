import java.util.Scanner;

public class smallest{
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int S=in.nextInt();
        int D=in.nextInt();
        if(9*D<S)
        {
            System.out.println("-1");
        }
        
        char[] s=new char[D];
        for(int i=0;i<D;i++)
        {
            s[i]='0';
        }
        s[0]='1'; 
        int sum=1;
        for(int i=s.length-1;i>=0;i--)
        {
            if(((sum+(s[i]-48)+9))>=S)
            {
                s[i]=(char)((S-sum+(s[i]-48))+'0');
                sum+=(int)s[i];
            }
            else
            {
                s[i]='9';
                sum+=9;
            }
        }
        
        return String.valueOf(s);
    }
}
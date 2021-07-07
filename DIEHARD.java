import java.util.Scanner;

import LEMUSIC.c;

public class DIEHARD {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int q=0;q<t;q++)
        {
            int H=in.nextInt();
            int A=in.nextInt();
            boolean flag1=true;
            boolean flag2=false;
            boolean flag3=false;
            int count=0;
            while(H>0&&A>0)
            {
               if(flag1==true)
               {
                   H=H+3;
                   A=A+2;
                   flag1=false;
                   flag2=true;
                   flag3=true;
                   count++;
               }
               else{
                   if((flag2==true)&&(H>5&&A>10))
                   {
                       H=H-5;
                       A=A-10;
                       flag1=true;
                       flag2=false;
                       flag3=true;
                       count++;
                   }
                   else{
                       if((flag3==true)&&(H>20))
                       {
                           H=H-20;
                           A=A+5;
                           flag1=true;
                           flag2=true;
                           flag3=false;
                           count++;

                       }
                       else{
                           break;
                       }
                   }
               }
            }
            System.out.println(count);

        }
    }
}

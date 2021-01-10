import java.lang.Thread.State;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;
import jdk.vm.ci.meta.TriState;

public class backtracking {

    static void rat_in_the_maze_p2(int x,int y,int[][] maze,int n,int[][] solution)
    {
        if(x==n-1 && y==n-1)
        {
            solution[x][y]=1;
            
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    System.out.print(solution[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        if(x<0||y<0||x>=n||y>=n||(maze[x][y]==0)||(solution[x][y]==1))
                                                 //If we visited the particular cell already in the same path we should not go to that path again because that would cause infinte loop
        {
          return;
        }
        solution[x][y]=1;
        rat_in_the_maze_p2(x+1, y, maze, n, solution);
        rat_in_the_maze_p2(x, y+1, maze, n, solution);
        rat_in_the_maze_p2(x-1, y, maze, n, solution);
        rat_in_the_maze_p2(x, y-1, maze, n, solution);
        solution[x][y]=0;//we need to make the x,y cell to 0 after visiting it in a particular path
        return;
    }

    static void rat_in_the_maze_p1(int[][] maze)
    {
        int n=maze.length;
        int[][] solution=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                solution[i][j]=0;
            }
        }
       rat_in_the_maze_p2(0,0,maze,n,solution);

    }

    static boolean isSafe_n_queen_problem_sub_function(int row,int col,int[][] board,int n)
    {
        //vertical exploaration
        int i=row-1;
        while(i>=0)
        {
            if(board[i][col]==1)
            {
                return false;
            }
            i--;
        }
        //left diagonal exploration
         i=row-1;
         int  j=col-1;
         while(i>=0 && j>=0)
         {
                 if(board[i][j]==1)
                 {
                     return false;
                 }
                 i--;
                 j--;
         }
         //right diagonal exploration
         i=row-1;
         j=col+1;
         while(i>=0&&j<n)
         {
             if(board[i][j]==1)
             {
                 return false;
             }
             i--;
             j++;
         }
        return true;

    }
    static void N_queen_problem_part_1(int row,int n,int[][] board)
    {
        if(row==n)//if we cross the end row
        {
              System.out.println();
              for(int i=0;i<n;i++)
              {
                  for(int j=0;j<n;j++)
                  {
                       System.out.print(board[i][j]+" ");
                  }
                  System.out.println();
              }
        }
        
        for(int col=0;col<n;col++)// For each row we need to explore all the four columns
        {
            if(isSafe_n_queen_problem_sub_function(row,col,board,n)){
                board[row][col]=1;
                N_queen_problem_part_1(row+1, n, board);//we will go to next row after fixing the queen for the above row
                board[row][col]=0;//we make this 0 so we can visit this row again and at that time we explore the other column where we can place this queen
            }

        }
        return;
    }
    static void N_queen_problem_part_2(int n)
    {
        int[][] board=new int[n][   n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                board[i][j]=0;
            }
        }
        N_queen_problem_part_1(0, n, board);
    }

    static BitSet c=new BitSet(4);
    static BitSet d1=new BitSet(4);
    static BitSet d2=new BitSet(4);
    
    static void N_queen_problem_setBit_sol(int r,int n,int[][] board)
    {
        if(r==n)
        {
            ans++;//we are going to print the  no of different answers can be possible
            System.out.println();
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                     System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
            return;
        }
        for(int col=0;col<n;col++)
        {
            if( !c.get(col) && !d1.get(r-col+n-1) && !d2.get(r+col))
            {
                c.set(col);
                d1.set(r-col+n-1);
                d2.set(r+col);
                board[r][col]=1;
               
                N_queen_problem_setBit_sol(r+1, n, board);
               
                //Backtracking step
                c.clear(col);
                d1.clear(r-col+n-1);
                d2.clear(r+col); 
                board[r][col]=0;
            }
        }

    }



    
    static boolean isSafe_sudoko_row(int[][] grid,int row,int num)
    {
        for(int j=0;j<9;j++)
        {
            if(grid[row][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    static boolean isSafe_sudoko_column(int[][] grid,int col,int num)
    {
        for(int i=0;i<9;i++)
        {
            if(grid[i][col]==num)
            {
                return false;
            }
        }
        return true;
    }
    static boolean isSafe_sudoko_grid(int[][] grid,int row,int col,int num)
    {
        int rowfactor=row-(row%3);
        int colfactor=col-(col%3);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(grid[i+rowfactor][j+colfactor]==num)
                {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isSafe_sudoko_solver_part3(int[][] grid,int row,int col,int num)
    {
       if( isSafe_sudoko_row(grid, row, num) && isSafe_sudoko_column(grid, col, num) && isSafe_sudoko_grid(grid, row, col, num)){
           return true;
       }
       return false;
    }

    static int rr;
    static int cc;
    static boolean findEmptyLocation_sudoko_solver_part2(int[][] grid)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(grid[i][j]==0)
                {
                    rr=i; // When we fix rr as i 
                    cc=j;  //And when we fix cc as j which means these both rr,cc values can be used by the values as row and column where there is empty place
                    return true;                                                                                                                             
                }
            }
        }
        return false;
    } 
    static boolean sudokosolver_part3(int[][] grid)
    {   int row,col;
        if(!findEmptyLocation_sudoko_solver_part2(grid))
        {
            return true;
        }
        row=rr;
        col=cc;
        for(int i=1;i<=9;i++)
        {
            if(isSafe_sudoko_solver_part3(grid,row,col,i))
            {
                grid[row][col]=i;
                if(sudokosolver_part3(grid))
                {
                    return true;
                }
                grid[row][col]=0;//Backtracking step of making the value of the grid at that row and column as 0 so we can again start exploring new values from that point
            }
        }
        return false;

    }
    static int log2(int N) 
    { 
  
        // calculate log2 N indirectly 
        // using log() method 
        int result = (int)(Math.log(N) / Math.log(2)); 
  
        return result; 
    } 
    static int ans=0,DONE;
    static void N_Queen_problem_using_bitmasking(int rowmask,int ld,int rd,int[][] board,int r,int n)
    {
           if(rowmask==DONE)
        {
            ans++;
            System.out.println();
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                     System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
            
            return;
        }
        int safe=DONE&(~(rowmask|ld|rd));
        while(safe!=0)//we will iterate until safe value becomes 0
        {
              int p=safe&(-safe);//which retrives the last set bit ..Each time p value says which column we are gonna fill the q ...ex:p=0100 which means 1st column we need to fill the q
              safe=safe-p;//after retriving the right most set bit which is the safest column from rightest we need to make that column as reserved/unsafe.
              board[r][n-log2(p)-1]=1;//To find at which position set bit occurs from left side we do log2(p) but to get from right side we need to do [n-log2(p) -1 ] which will give the correct position of the column
              N_Queen_problem_using_bitmasking(rowmask|p, (ld|p)<<1, (rd|p)>>1,board,r+1, n);//thn we go to next row by making the new changes of after filling the pth column
              board[r][n-log2(p)-1]=0;//backtracking step
        }


    }
    
    static void Finding_all_the_increasing_sequences_from_1_toN_that_sums_upto_N(int i,int n,int sum,int j,int[] arr)
    {
        if(sum==0)
        {
                for(int qq=0;qq<n;qq++)
                {
                    if(arr[qq]!=0)
                    {
                 System.out.print(arr[qq]);
                    }
                }
                System.out.println();
                return;
        }
        if(i>sum)
        {
            return;
        }
        for(int v=i;v<=sum;v++)
        {
          arr[j]=v;
          Finding_all_the_increasing_sequences_from_1_toN_that_sums_upto_N(v, n, sum-v, j+1, arr);   
          arr[j]=0;
        }
        return;
    }
    static void Finding_all_the_sequences_from_1_toN_that_sums_upto_N(int n,int sum,int j,int[] arr)
    {
        if(sum==0)
        {
                for(int qq=0;qq<n;qq++)
                {
                    if(arr[qq]!=0)
                    {
                 System.out.print(arr[qq]);
                    }
                }
                System.out.println();
                return;
        }
        if(sum<0)
        {
            return;
        }
        for(int v=1;v<=sum;v++)
        {
          arr[j]=v;
          Finding_all_the_sequences_from_1_toN_that_sums_upto_N(n, sum-v, j+1, arr);   
          arr[j]=0;
        }
        return;
    }
    static Set<String> s=Collections.emptySet();
    static void Finding_all_sequences_from_an_array_that_leads_to_sum_upto_N_and_we_can_use_the_same_array_element_as_many_times_we_want_the_answers_should_be_unique(int n,int sum,int[] arr,int j,int[] a,int idx)
    {
        if(sum==0)
        {
            String w=arr.toString();
            s.add(w);
            
            return;
        }
        if(sum<0)
        {
            return;
        }
        for(int i=idx;i<n;i++)
        {
            arr[j]=a[i];
            Finding_all_sequences_from_an_array_that_leads_to_sum_upto_N_and_we_can_use_the_same_array_element_as_many_times_we_want_the_answers_should_be_unique(n, sum-a[i], arr,j+1, a,i);
            arr[j]=0;
        }
        return;
    }
    static void Finding_all_sequences_from_the_array_whose_sum_modulo_value_gives_zero(int idx,int sum,int j,int k,int[] arr,int n,int[] ans)
    {

        if(sum%k==0)
        {
            for(int i=0;i<ans.length;i++)
            {
                if(ans[i]!='\u0000')
                {
                System.out.print(ans[i]);
                }
            }
            System.out.println();
    
        }
        for(int i=idx;i<n;i++)
        {
            ans[j]=arr[i];
            Finding_all_sequences_from_the_array_whose_sum_modulo_value_gives_zero(i+1, sum+arr[i], j+1, k, arr, n, ans);
            ans[j]=0;
        }
        return;
    }
    static void subsetsII(int n,int[] nums,int i,int[] out,int j){
        if(i==n){
            out[j]='\u0000';
            for(int qq=0;out[qq]!='\u0000';qq++)
            {
               System.out.print(out[qq]);
            }
            System.out.println();
            return;
        }
        out[j]=nums[i];
        subsetsII(n, nums, i+1, out, j+1);
        subsetsII(n, nums, i+1, out, j);

    }
    static boolean isPalindrome(String str) 
    { 
  
        // Pointers pointing to the beginning 
        // and the end of the string 
        int i = 0, j = str.length() - 1; 
  
        // While there are characters toc compare 
        while (i < j) { 
  
            // If there is a mismatch 
            if (str.charAt(i) != str.charAt(j)) 
                return false; 
  
            // Increment first pointer and 
            // decrement the other 
            i++; 
            j--; 
        } 
  
        // Given string is a palindrome 
        return true; 
    } 

    static void palindrome_partitioning(String str,String[] ans,int j)
    {
        
        if(str.length()==0)
        {
            
            for(int qq=0;ans[qq]!=null;qq++)
            {
                System.out.print(ans[qq]+" ");
            }
            System.out.println();
            return;
        }
        

        for(int i=0;i<str.length();i++)
        {
           
            if(isPalindrome(str.substring(0,i+1))){
                  ans[j]=str.substring(0,i+1);
                 
                  palindrome_partitioning(str.substring(i+1),  ans, j+1);
                  ans[j]=null;
            }
           
          
        }
        return;
    }
    
    static HashSet<String> anss=new HashSet<>();
    static void word_break_problem(String str,int i,char[] out,int j,int n,int checke,HashSet<String> h) //Still not completed this problem
    {
        if(i>=n)
        {
            String ok=new String(out);
            anss.add(ok);
            return;
        }
       
        String tu="";
        for(int uq=0;out[uq]!='\u0000';uq++)
        {
            tu+=out[uq];
        }
        if(h.contains(tu.substring(checke))==true)
        {
            /*System.out.println("gopi");
            System.out.println("tu substring="+tu.substring(checke));
            System.out.println("checke="+checke);*/
            if(h.contains(tu.substring(checke)+str.charAt(i)))
            {
                
                out[j]=' ';
                word_break_problem(str, i, out, j+1, n,j+1,h);
                
                out[j]=str.charAt(i);
                out[j+1]=' ';
               
                word_break_problem(str, i+1, out, j+2, n,j+2,h);

            }
            else{
                out[j]=' ';
                word_break_problem(str, i, out, j+1, n,j+1,h);
    
            }
           
        }
       
      

        out[j]=str.charAt(i);
        word_break_problem(str, i+1, out, j+1, n,checke,h);
    
    }
    static boolean areBracketsBalanced(char[] expr) 
    {
        Deque<Character> stack  = new ArrayDeque<Character>();
        if(expr[0]=='\u0000')
        {
            return false;
        }
        
        for (int i = 0; expr[i]!='\u0000'; i++)  
        { 
            char x = expr[i]; 
            if(x==' ')
            {
                continue;
            }
            if (x == '(' )  
            { 
                // Push the element in the stack 
                stack.push(x); 
                continue; 
            } 
            
            if(stack.isEmpty())//if the current character is not opening bracket thn we will not be entering any value into the stack and we need to return false
            {
                return false;
            }
            char check;
            switch(x)
            {
                case ')':
                check = stack.pop(); 
                break;

            }
         


        }
        return (stack.isEmpty());

    }
    static int count_no_brackets(char[] out)
    {
        int count=0;
         for(int i=0;i<out.length;i++)
         {
             if(out[i]==')' || out[i]=='(')
             {
                 count++;
             }
         }
         return count;
    }
    static void remove_invalid_parenthesis(String brack,int i,char[] out,int j,int n)//not completed fully
    {
  
        if(i>=n)
        {
            if(areBracketsBalanced(out)==true)
            {
            
                String ok="";
                for(int qq=0;qq<out.length;qq++)
                {
                    if(out[qq]==')'||out[qq]=='(')
                    {
                    ok+=out[qq];
                    }

                }
                if(ok.length()==n-1)
                {
                System.out.println(ok);
                }
                
            }
         
         return;
        }
        
        
        out[j]=brack.charAt(i);
        
        remove_invalid_parenthesis(brack, i+1, out, j+1, n);
        out[j]=' ';
        remove_invalid_parenthesis(brack, i+1, out, j+1, n);
       
       
    }
    static int count=0;
    static void Largest_number_in_k_swaps(int j,int max,int idx,int k,int[] arr,int n) // Still have to verify for the time complexity
    {
        if(j>=n)
        {
           int temp=arr[max];
           arr[max]=arr[idx];
           arr[idx]=temp;
           ++count;
           
           return;
        }
        if(count==k-1)
        {
          return;
        }
        for(int i=idx;i<n;i++)
        {
           
            if(arr[i]<arr[j])
            {
                max=j;
            }
            Largest_number_in_k_swaps(j+1, max, i, k, arr, n);
            j=idx+1;
            
        }
       return;
       
    }
    static boolean w;
    static int v=0;
    static void partition_array_into_k_subsets(int[] arr,int n,int i,HashSet<Integer> h,ArrayList<Integer> out,int k,int lg)
    {
       if(i>=n)
       {
          return;
       }
       int sum=0;
       for(int r1=0;r1<out.size();r1++)
       {
           sum+=out.get(r1);
       }
      
       if(sum>lg)
       {
           return;
       }
       if(sum==lg)
       {
           boolean flag=true;
           for(int r1=0;r1<out.size();r1++)
           {
               if(h.contains(out.get(r1)))
               {
                   flag=false;
                   return;

               }
           }
           if(flag==true)
           {
             v++;
             for(int r1=0;r1<out.size();r1++)
             {
              h.add(out.get(r1));
             }    
           }
           else{
              return;
           }
           if(v==k-1)
           {
            return; 
           }
           
       }
      
       int temp=arr[i];
       out.add(arr[i]);
       partition_array_into_k_subsets(arr, n, i+1, h, out, k, lg);
       out.remove(out.size()-1);
       partition_array_into_k_subsets(arr, n, i+1, h, out, k, lg);
       


    }




     public static void main(String[] args) {
        
  
        int[] arr={2,1,5,5,6};
        int n=arr.length;
        ArrayList<Integer> out=new ArrayList<Integer>();
        HashSet<Integer> h=new HashSet<Integer>();
        int k=3;
        partition_array_into_k_subsets(arr, n, 0, h, out,  k, 6);

        if(v==k-1)
        {
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
      
   
      
    
      
        
    }

    
}

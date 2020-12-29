import java.util.Scanner;

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
        int[][] board=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                board[i][j]=0;
            }
        }
        N_queen_problem_part_1(0, n, board);
    }


    public static void main(String[] args) {


        int n=8;
        N_queen_problem_part_2(n);
    }

    
}

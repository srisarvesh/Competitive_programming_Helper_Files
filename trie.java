import java.util.HashMap;
import java.lang.System;
import java.lang.System.Logger;
public class trie {
    static class node{
        char data;//Each node will consist of data
        HashMap<Character,node> children;//map that holds pairs each of the character will point to a node .
        boolean terminal;//This value says whether a particular node is terminal or not
        node(char data)
        {
            this.data=data;
            this.terminal=false;
        }


    }
        node root;//This will hold  the root node value of the TREE which will be \0
        int cnt;// Holds the number of words that is been entered into the trie
        public trie()
        {
           this. root=new node('\0');
            this.cnt=0;
        }
        void insert(String w)
       {
           node temp=root;//from the top most node we are going to traverse and move to next nodes and insert the words we want
           for(int i=0;w.charAt(i)!='\0';i++)//we are going to traverse through the characters of the word w that we are going to insert into the trie
           {
                char ch=w.charAt(i);
                if(temp.children.get(ch)!=null)//here we are checking whether the hashmap that we have in our node has a key (that is a character) that points to a node 
                {
                      temp=temp.children.get(ch); //if the ith character is already there in the trie we are making the temp to the node that has that particular character
                }
                else{//when the particular character is not there inside the trie we create a new node that will hold the ch and we will make our current temp's map (ch,(points to the new node))
                    node nnode=new node(ch);//creating a new node
                    temp.children.put(ch, nnode);//we are making the ch key in the  hashmap points to the new node
                    temp=nnode;//then we change our temp 

                }
           }
           temp.terminal=true;//After traversing over all the characters in the above word we are in the last character of the word as the terminal node
           cnt++;
            
        }
       boolean search(String w)
       {
         node temp=root;
         boolean flag=false;
         for(int i=0;w.charAt(i)!='\0';i++)//we are going to each character present in the word
         {
             char ch=w.charAt(i);//we are going to save each character into the ch
            if(temp.children.get(ch)==null)//if the particular character in the hash map points to null then the word is not present in the trie
            {
              return false;
            }
            temp=temp.children.get(ch);//if it is present we keep on moving to next node until the last character in the word
            if(temp.terminal==false)//if the last character(of the node )  is not terminal node we return fale 
            {
                flag=false;
                
            }
            else{// else true
                flag=true;
            }
         }
         return flag;

       }
       

    
     public static void main(String[] args) {
    
     trie t=new trie();
     String[] words={"no","not","go"};
     for(int i=0;i<words.length;i++)
     {
         t.insert(words[i]);
     }    
     
     if(t.search("no"))
     {
        System.out.println("pr");
     }
     else{
         System.out.println("no");
     }
 
    }
    
}

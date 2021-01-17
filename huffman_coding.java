import java.util.HashMap;
import java.util.Map;



import java.lang.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class huffman_coding {
    
  static  Map<Character,Integer> make_frequency_dict(String text)
   {
       Map<Character,Integer> freq_dict=new HashMap<>();
       
       for(int i=0;i<text.length();i++)
       {
           
           if(freq_dict.containsKey(text.charAt(i))==false)
           {
               freq_dict.put(text.charAt(i),0);
           }
           
           freq_dict.put(text.charAt(i) ,freq_dict.get(text.charAt(i))+1);

       }
       return freq_dict;
   }
   static public class Binary_tree_node{      // Each node has value,frequency,left and right as its properties
        char value;
        int frequency;                             
        Binary_tree_node left;
        Binary_tree_node right;
        
       /* public Binary_tree_node(char value,int frequency) 
        {
           this.value=value;
           this.frequency=frequency;
           this.left=null;
           this.right=null;
          

        }
        */
   }
   static PriorityQueue<Binary_tree_node> minHeap = new PriorityQueue<>(new Comparator<Binary_tree_node>(){

       @Override
       public int compare(Binary_tree_node o1,Binary_tree_node o2) {
        
        if(o1.frequency<o2.frequency)
        {
            return -1;
        }
        if(o1.frequency>o2.frequency)
        {
            return 1;
        }
        else{
            return 0;
        }
          
       }
       
   });
   

   static void build_heap(Map<Character, Integer> freq_dict) {
       for (Map.Entry<Character, Integer> e : freq_dict.entrySet()) {
          Binary_tree_node hn=new Binary_tree_node();
          hn.value=e.getKey();
          hn.frequency=e.getValue();
          hn.left=null;
          hn.right=null;
         minHeap.add(hn);//we are newly creating a node with character-Value ,No_of_occurances-frequency
    }
   }
   static Binary_tree_node root=null; // we maintain a root node to keep track of the top most root node from where we are going to start our traversing.
   static void build_tree(){
   
       while(minHeap.size()>1)
       {
           Binary_tree_node one=minHeap.peek();
           minHeap.poll();
          
           Binary_tree_node two=minHeap.peek();
           minHeap.poll();
          
           int frq_sum=one.frequency+two.frequency;
           Binary_tree_node new_node=new Binary_tree_node();
        //  System.out.println("newnode_value"+new_node.value+" "+"newnode_freq"+new_node.frequency);
           new_node.value='-';
           new_node.frequency=frq_sum;
           new_node.left=one;
         //  System.out.println("left="+one.value);

           new_node.right=two;
        //  System.out.println("right="+two.value);
          
        root=new_node;
           minHeap.add(new_node);
        
           
       }
       return; 
   }
   
  static Map<Character,String> ch_co=new HashMap<>();
  public static void printCode(Binary_tree_node root,String s) {
    if (root.left == null && root.right == null && Character.isLetter(root.value)) {

      ch_co.put(root.value, s);

      return;
    }
    printCode(root.left, s + "0");
    printCode(root.right, s + "1");
  }
  static String encoded_text(String text)
  {
      String encoded_text="";
      for(int i=0;i<text.length();i++)
      {
          encoded_text+=ch_co.get(text.charAt(i));
      }
      return encoded_text;
  }
  static String padding_of_encoded_text(String encoded_t)
  {
      int paddedamount=8-(encoded_t.length()%8);
      //System.out.println("paddedamount="+paddedamount);
      for(int i=0;i<paddedamount;i++)
      {
          encoded_t+="0";
      }
      String padded_info=Integer.toBinaryString(paddedamount);
      //System.out.println("padded_info="+padded_info);
      int fac=8-padded_info.length();
      String fac_str="";
      for(int i=0;i<fac;i++)
      {
          fac_str+="0";
      }
     // System.out.println("fac_str="+fac_str);
      fac_str+=padded_info;
      fac_str+=encoded_t;   
      return fac_str;
  }
  public static byte[] convertIntegersToBytes (int[] integers) {
    if (integers != null) {
        byte[] outputBytes = new byte[integers.length * 4];

        for(int i = 0, k = 0; i < integers.length; i++) {
            int integerTemp = integers[i];
            for(int j = 0; j < 4; j++, k++) {
                outputBytes[k] = (byte)((integerTemp >> (8 * j)) & 0xFF);
            }
        }
        return outputBytes;
    } else {
        return null;
    }
  }
  public static byte[] int2byte(int[]src) {
    int srcLength = src.length;
    byte[]dst = new byte[srcLength << 2];
    
    for (int i=0; i<srcLength; i++) {
        int x = src[i];
        int j = i << 2;
        dst[j++] = (byte) ((x >>> 0) & 0xff);           
        dst[j++] = (byte) ((x >>> 8) & 0xff);
        dst[j++] = (byte) ((x >>> 16) & 0xff);
        dst[j++] = (byte) ((x >>> 24) & 0xff);
    }
    return dst;
}
  static byte[] t_bytes_array(String padded_encoded_t)//After converting the encoded text into padded encoded text ..we need to convert each 8 bits of the string as a byte by putting each 8 bit part of the string into an array
  {

      int[] bytes_arr=new int[padded_encoded_t.length()/8];
      int j=0;
      for(int i=0;i<padded_encoded_t.length();i+=8)
      {
         bytes_arr[j]=Integer.parseInt(padded_encoded_t.substring(i, i+8),2);
         //System.out.println("bytes_arr[j]="+bytes_arr[j]);
         j++;
      }
      byte[] b= convertIntegersToBytes(bytes_arr);
      return b;
  }
   static void compress(String text)
   {
     Map<Character,Integer> freq_dict=make_frequency_dict(text);
     build_heap(freq_dict);
     build_tree();
     printCode(root,"");
     String encoded_t=encoded_text(text);
     //System.out.println("encoded_t="+encoded_t);
    
     String padaded_encoded_t=padding_of_encoded_text(encoded_t);
     //System.out.println("padaded_encoded_t="+padaded_encoded_t);
    // byte[] b=t_bytes_array(padaded_encoded_t);
     //System.out.println(kk);
    // for(int i=0;i<b.length;i++)
     //{
      //   System.out.println(b[i]);
    // }
     
   }
  
   public static void main(String[] args)
   {
       String text="aabbbcccc";
       compress(text);
       
       

      
      

       
   }
}

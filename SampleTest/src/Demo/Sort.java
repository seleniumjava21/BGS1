package Demo;
import java.io.*;
public class Sort{

public static void main(String [] args)
{
       System.out.println ("Enter a string");

       //read in user input
       String userString = IO.readString();

       //store length of string
       int length = userString.length();

       System.out.println(length);

       int count;
       String result = "";
count = 0;
       String results = "";

       for(int i=0;i<userString.length();){
           char begin = userString.charAt(i);
           //System.out.println("begin is: "+begin);

           for(int j=i+1; j<userString.length();j++){
               char next = userString.charAt(j);
               //System.out.println("next is: "+next);

               if(begin == next){
                   count++;
               }
               else{
                   System.out.println("abbccaa");
                   break;
               }
           }
           i+= count+1;
           if(count>0){
               String add = begin + "";
               int tempcount = count +1;

               results+= tempcount + add;

           }
           else{
        	     results+= begin;
           }

           count=0;

       }

   System.out.println(results);



   IO.outputStringAnswer(result);
}
}


           }
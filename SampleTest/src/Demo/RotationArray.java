import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
 
class DateSort
{
    public static void main(String as[]) throws ParseException
    {
        Scanner sc =new Scanner (System.in);
        Date date[];
        String str[];
        System.out.println("Enter no. of dates");               
        int i,n = sc.nextInt();                                 //integer input    
        str= new String[n];                                     //dynamic memory allocation for string of size of n
        date= new Date[n];                                      //dynamic memory allocation for date of size n
        sc.nextLine();                              
         
        for(i=0;i<n;i++)
        {                                           
            str[i]=sc.nextLine();                               //getting date as string from user
        }
        /*create object for SimpleDate format and
        spcify format for day-month-year as dd-MM-yyyy
        */
        SimpleDateFormat sobj = new SimpleDateFormat("dd-MM-yyyy");// format specified in double quotes
         
        for(i=0;i<n;i++)
        {
            date[i]=sobj.parse(str[i]);                         //parse the date string to date obj
        }
        /*import java.util.Arrays and sort the date[] array 
        using inbuilt sort method
        */
        Arrays.sort(date);                                                                            
         
        for(Date date1 : date)                                  //Enchanced for loop, it loops through all element in date array
        {                                                       //each time date[i] is copied to date1 like traditional for loop
            System.out.println(sobj.format(date1));             //format the date1 to dd-MM-yyyy using sobj 
        }
         
    }
}
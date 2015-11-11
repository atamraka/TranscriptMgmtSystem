package com.tms.scripts;
import java.util.Scanner;


public class ScriptforSphinx {

    public static void main(String[] args) {
    	Scanner sc= new Scanner(System.in);
    	System.out.println("Enter the World length");
    	int wordlength =sc.nextInt();
    	
        double [] j=new double[wordlength];
        double [] k=new double[wordlength];
        String [] l=new String [wordlength];
        Scanner o=new Scanner(System.in);
        System.out.println("enter the start values");
        sc.close();
        o.close();
        
        for(int i=0;i<j.length;i++){
            j[i]=o.nextDouble();
           
           // System.out.println(j[i]);
        }
        
        Scanner p=new Scanner(System.in);
        System.out.println("enter the duration values");
        for(int i=0;i<k.length;i++){
            
           k[i]=p.nextDouble();
           // System.out.println("<span data-dur="+'"'+j[i]+'"'+" data-begin="+'"'+k[i]+'"'+">In</span>");
        }
        p.close();
        
        Scanner m=new Scanner(System.in);
        System.out.println("enter the words");
        for(int i=0;i<l.length;i++){
            
           l[i]=m.nextLine();
           System.out.println("<span data-dur="+'"'+k[i]+'"'+" data-begin="+'"'+j[i]+'"'+">"+l[i]+"</span>");
        }
        m.close();
    }
}

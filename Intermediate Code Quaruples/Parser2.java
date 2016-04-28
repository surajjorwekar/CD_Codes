/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser2;

import java.util.Stack;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser2 {
    String[] keywords = {"auto","break","case","char","const","continue","default","do","double","else","enum","extern","float","for","goto","if","int","include","long","main","register","return","short","signed","sizeof","static","struct","switch","typedef","union","unsigned","void","volatile","while" };
    String[] relop1 = {"<=",">=",">","<","=="};
    int flg=0;
    int count=0;
    Stack stack = new Stack();
    Stack symstack = new Stack();
    Stack valstack = new Stack();
    String[][] table= new String[37][22];
    
    public void CreateParseTable(){
        BufferedReader br = null;
    try
    {
            br = new BufferedReader(new FileReader("C:\\Users\\Swapnil\\Desktop\\Mail\\Parse.csv"));

            for(int row =0;row<=36; row++)
            {
                String line = br.readLine();
                if(line == null)
                {
                    break;
                }
                String[] val =  line.split(",");
                for(int col =0; col<22; col++)
                {
                    table[row][col] = val[col];
                    
                    //System.out.println("row="+row+"col="+col);
                    //System.out.println(""+table[row][col]);   
                }
            }
            
    }
    catch(Exception e)
    {
        System.out.println(""+e);
    }
    }
    
    public void Run_lex(){
        stack.push("$");
        stack.push("0");
        try
        {
            BufferedReader br=  new BufferedReader(new FileReader("C:\\Users\\Swapnil\\Desktop\\Mail\\parsefile.c"));
            String line;
            while((line = br.readLine())!=null)
            {   
             line = line.replaceAll("if","if ");
             line = line.replaceAll("[(]","( ");
             line = line.replaceAll("[)]"," ) ");
             line = line.replaceAll("[{]","{ ");
             line = line.replaceAll("[}]","} ");
             line = line.replaceAll("[;]"," ; ");
             line = line.replaceAll("[+]", " + ");
             line = line.replaceAll("[-]", " - ");
             line = line.replaceAll("[*]", " * ");
             line = line.replaceAll("[/]", " / ");
             line = line.replaceAll("\n","");
             String[] word = line.split(" ");
             String lexeme;
             for(int i=0;i<word.length;i++)
             {
                 if(checkIdentifier(word[i]) && !(word[i].equals("if")))
                 {
                    lexeme = word[i]; 
                    word[i] = "id";
                    Run_Parser(word[i].trim(),lexeme);
                   // System.out.println("token passed: "+word[i]);
                    continue;
                 }
                 if(checkReloperator(word[i]))
                 {
                     lexeme = word[i];
                     word[i] = "relop";
               //      System.out.println("token passed: "+word[i]);
                     Run_Parser(word[i].trim(),lexeme);
                     continue;
                 }
              //   System.out.println("token passed: "+word[i]);
                 Run_Parser(word[i].trim(),word[i].trim());
             }
            }
           // System.out.println("token passed: $");
            Run_Parser("$","$");
            
        }catch(Exception e)
        {
            System.out.println(""+e);
        }
    }
    public boolean checkIdentifier(String str)
    {
        boolean res = false;
        if((str.charAt(0)>='a' && str.charAt(0)<='z')||(str.charAt(0)>='A' && str.charAt(0)<='Z')||(str.charAt(0)=='_'))
            {
                res=true;
                for(int k=0;k<str.length();k++)
                {
            
                    if((str.charAt(k)>='0' && str.charAt(k)<='9')||(str.charAt(k)>='a' && str.charAt(k)<='z')||(str.charAt(k)>='A' && str.charAt(k)<='Z')||(str.charAt(k)=='_'))
                    {
                        res = true;
                        continue;
                    }
                    else{
                        res =false;
                       break; 
                    }
                    
                }
        }
        return res;
    }
    public boolean checkReloperator(String str)
    {
        boolean res = false;
        for(int k=0;k<relop1.length;k++)
        {
            if(str.equals(relop1[k]))
            {
                res = true;
                break;
            }
        }
        return res;
    }
  
    
    public void Run_Parser(String input, String lexeme){

    int cnt=0;
    
    String[] production= {"S->AS","S->IS","S->A","S->I","A->id=E;","I->if(C){S}","C->id relop id","E->E+T","E->E-T","E->T","T->T*F","T->T/F","T->F","F->(E)","F->id"};
    int[] prodcnt = {2,2,1,1,4,7,3,3,3,1,3,3,1,3,1};
    String[] sem_rule = {"n","n","n","n","=","n","r","+","-","n","*","/","n","n","n"};

    while(true)
    {
       String top = ""+stack.peek();
       //String inp = input[cnt];
       String inp = input;
        //System.out.print("stack: "+stack.subList(0, stack.size()));
        //System.out.print(" symbol stack: "+symstack.subList(0, symstack.size()));
       // System.out.print(" value stack: "+valstack.subList(0, valstack.size()));
       //System.out.println(" input: "+inp);
       
       int rowno=0,colno=0;
       for(int row =0;row<=36; row++)
       {
           if(table[row][0].equals(top))
           {
               rowno=row;
               break;
           }
       }
       for(int col =0;col<22; col++)
       {
           if(table[0][col].equals(inp))
           {
               colno=col;
               break;
           }
       }
       String val=table[rowno][colno];

       if(val.equals("0"))
        {
                   //System.out.println("error");
                   break;
        }
       if(val.equals("ACCEPT"))
       {
           System.out.println("Input Accepted");
           break;
       }
       if(val.charAt(0)=='S')
       {
           //System.out.println("***************************");
           if(input.equals("id")||input.equals("relop"))
           { 
               //System.out.println("****** lexeme entered");
               valstack.push(lexeme);
               
           }
           String v = val.substring(1, val.length());
           stack.push(v);
           symstack.push(input);
           
           return;
       }
       
       if(val.charAt(0)=='R')
        {
            
            String v = val.substring(1, val.length());
            int t = Integer.parseInt(v)-1;
            String prod = production[t];
            prod = prod.substring(0, prod.indexOf("-"));
            int pc = prodcnt[t];
            
            String sem = sem_rule[t];
            String var1 = null, var2 = null,var3=null;
            switch(sem){
                case "n":break;
                case "+":   var2 = ""+valstack.pop();
                            var1 = ""+valstack.pop();
                            //System.out.println("\n***Code Gen\n");
                           // System.out.println("t" + (count++) + ":= " + var1 + " + " + var2 + "\n");
                            System.out.println("+   " + var1 +"\t  "+ var2 + "\t"+"t"+(count++));//new
                            // System.out.println(var1 + sem + var2);
                            //System.out.println("\nCode Gen***\n");
                            valstack.push("t"+(count-1));
                            break;
                case "-":   var2 = ""+valstack.pop();
                            var1 = ""+valstack.pop();
                            //System.out.println("\n***Code Gen\n");
                            //System.out.println("t" + (count++) + ":= " + var1 + " - " + var2 + "\n");
                            System.out.println("-   " + var1 +"\t  "+ var2 + "\t"+"t"+(count++));//new
                            //  System.out.println(var1 + sem + var2);
                            //System.out.println("\nCode Gen***\n");
                            valstack.push("t"+(count-1));                    
                            break;
                case "*":   var2 = ""+valstack.pop();
                            var1 = ""+valstack.pop();
                            //System.out.println("\n***Code Gen\n");
                            //System.out.println("t" + (count++) + ":= " + var1 + " * " + var2 + "\n");
                            System.out.println("*   " + var1 +"\t  "+ var2 + "\t"+"t"+(count++));//new
                            // System.out.println(var1 + sem + var2);
                            //System.out.println("\nCode Gen***\n");
                            valstack.push("t"+(count-1));                    
                            break;
                case "/":   var2 = ""+valstack.pop();
                            var1 = ""+valstack.pop();
                            //System.out.println("\n***Code Gen\n");
                            //System.out.println("t" + (count++) + ":= " + var1 + " / " + var2 + "\n");
                            System.out.println("/   " + var1 +"\t  "+ var2 + "\t"+"t"+(count++));//new
                            //System.out.println(var1 + sem + var2);
                            //System.out.println("\nCode Gen***\n");
                            valstack.push("t"+(count-1));                    
                            break;
                case "r":   var3 = ""+valstack.pop();
                            var2 = ""+valstack.pop();
                            var1 = ""+valstack.pop();
                            //System.out.println("\n***Code Gen\n");
                            //System.out.println("t" + (count++) + ":= " + var1 + var2 + var3 + "\n");
                            System.out.println("r   " + var1 +"\t  "+ var2 + "\t"+"t"+(count++));//new
                            // System.out.println(var1 + sem + var2);
                            //System.out.println("\nCode Gen***\n");
                            valstack.push("t"+(count));                    
                            break;
                case "=":   var2 = ""+valstack.pop();
                            var1 = ""+valstack.pop();
                            //System.out.println("\n***Code Gen\n");
                            //System.out.println("t" + (count++) + ":= " + var1 + " = " + var2 + "\n");
                            System.out.println("=   " + var1 +"\t  "+ var2 + "\t"+"t"+(count++));//new
                            //  System.out.println(var1 + sem + var2);
                            //System.out.println("\nCode Gen***\n");
                            valstack.push("t"+(count-1));                    
                            break;                                        
                                                            
                    
            }
            
            int d = 0;
            while(d< pc)
            {                   
//                valstack.push(symstack.peek());
                symstack.pop();
                stack.pop();
                d++;
            }
            symstack.push(prod);

                   for(int row =1;row<36; row++)
                    {
                        if(table[row][0].equals(""+stack.peek()))
                        {
                            rowno=row;
                            break;
                        }
                    }
                    for(int col =0;col<22; col++)
                    {
                        if(table[0][col].equals(symstack.peek()))
                        {
                            colno=col;
                            break;
                        }
                    }
                     val=table[rowno][colno];
                     stack.push(val);
                     
        }
    }
}

    public static void main(String[] args){
    	System.out.println("OP " + "ARG1" +"  "+ " ARG2" + "  "+"Result");//new
        Parser2 p = new Parser2();
        p.CreateParseTable();
        p.Run_lex();
    } 
}


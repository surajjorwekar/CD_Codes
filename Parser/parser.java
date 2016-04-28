package compiler;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import jxl.read.biff.BiffException;

public class parser {
	static Stack st=null;
	static String word=null;
	Stack input=null;
	static boolean flag=false;
	 public parser() throws BiffException, IOException {
		 	st=new Stack();
		 	input=new Stack();
		 	push(st,"0");
		 	//this.word=part;
		 	//operation(st);
		 	
		 	
	}
	 public void operation(String word1) throws BiffException, IOException
	 {
		 this.word=word1;
		 System.out.print("\nInput .........\n");
		System.out.print(word);
		 System.out.print("\nStack..........\n");
		 print(st);
		 
		 int row=Datareading.getRow(st.peek().toString());
		 System.out.println("\nrow:"+row);
		 int column=Datareading.getColumn(word);
		 System.out.println("\ncolumn:"+column);
		 	String data=Datareading.Datareading(row, column);
		 System.out.println("\ndata............\n"+data);
		 	if(data.length() > 4)
			{
				System.out.println("Done!!");
				System.exit(0);
			}
		 		flag=false;
		 	process(data,st);
		 
	 }
	 
	 public void process(String data,Stack st) throws NumberFormatException, BiffException, IOException
	 {
		 if(data.startsWith("s") || data.startsWith("S"))
		 {
			 String temp=data.substring(1,data.length());
			 //System.out.println(temp);
			 int num=Integer.parseInt(temp);
			 
			 push(st,word);
			 push(st,Integer.toString(num));
		 }
		 else if(data.startsWith("r") || data.startsWith("R") )
		 {
			 String temp1 = data.substring(1, data.length());
				int stage = Integer.parseInt(temp1);
			System.out.println("stage for r:"+stage);
				int len= getlengthtoRemove(stage);
				for (int i = 0; i < len*2; i++) {
					System.out.println("pop for r:"+st.pop());
					
				}
				
				String put = get_String(stage);
				//System.out.println("put :"+word);
				String temp2 = st.peek().toString();
				int column = Datareading.getColumn(put);
				System.out.println("\ncolumn for r:"+column);
				//System.out.println("st.peek"+st.peek().toString());
				int row = Datareading.getRow(st.peek().toString()) ;
				System.out.println("\nrow for r:"+row);
				int output = Integer.parseInt(Datareading.Datareading(row, column));
				push(st,put);
				push(st, Integer.toString(output));
				flag=true;
				

		 }
		/// System.out.println("Stack items");
		 print(st);
			 
	 }
	 public  int getlengthtoRemove(int len)
		{
			switch(len)
			{
				case 11: return 1;
				case 9: return 1;
				case 14: return 1;
				case 15: return 1;
				case 16: return 1;
				case 17: return 1;
				case 6: return 3;
				case 5: return 4;
				case 13: return 3;
				case 2: return 2;
				case 3: return 1;
				case 4: return 2;
				case 10: return 3;
				case 8:return 3;
				case 7:return 1;
				case 1:return 1;
				case 12:return 7;
						
			}
			return 0;
		}

	 public String get_String(int stage)
		{
			switch(stage)
			{
				case 11: return "F";
				case 9: return "T";
				case 14: return "R";
				case 15: return "R";
				case 16: return "R";
				case 17: return "R";
				case 6: return "E";
				case 5: return "A";
				case 13: return "C";
				case 8: return "T";
				case 12: return "W";
				case 7: return "E";
				case 10: return "F";
				case 1 : return "S";
				case 2: return "S";
				
			}
			return null;
		}

	public void push(Stack st, String a)
	{
		st.push(a);
		//print(st);
	}
	 public void pop(Stack s)
	 {
		 for (int i = 0; i < st.size(); i++) {
				System.out.println(s.pop());
			}

	 }
	public void print(Stack s)
	{
		
		for(int i=0;i<s.size();i++)
		{
			System.out.print(""+s.get(i));
		}
	}

}

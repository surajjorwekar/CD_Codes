package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jxl.read.biff.BiffException;


public class lexical {
	
	String line=null;
	String [] keyword={"while","float"};
	int i=0;
	
		public lexical() throws IOException, BiffException {
			
			BufferedReader br=new BufferedReader(new FileReader("D:\\program\\lex\\compiler\\input.txt"));
			 	//System.out.println("lexical");
			parser p=new parser();
			while((line=br.readLine())!=null)
			{
				

				String []part=line.split("[ \t]");
				
				
				//parser.st.push("0");
				
				for(i=0;i<part.length;i++)
				{
					//System.out.println(""+part[i]);
					p.operation(part[i]);
					if(p.flag)
					{
						i--;
						
					}
				}
				
				
				
			}
		
	}
	
		
	
}



package compiler;

import java.io.*;

import jxl.*;
import jxl.read.biff.BiffException;

public class Datareading {
	
	
	public static String Datareading(int row,int column) throws BiffException, IOException {
		  File input = new File("sample.xls");
		  
		 Workbook w = Workbook.getWorkbook(input);
		 Sheet s=w.getSheet(0); 
		 Cell c=s.getCell(column,row);
		 return c.getContents();
		
	}
	
	
	
	public void read() throws BiffException, IOException
	{
		  File input = new File("sample.xls");
		  
			 Workbook w = Workbook.getWorkbook(input);
			 Sheet s=w.getSheet(0);
			 
			 for(int i=0;i<s.getColumns();i++)
			 {
				 for(int j=0;j<s.getRows();j++)
				 {
					 Cell c=s.getCell(i,j);
					 CellType t=c.getType();
					 if(t==CellType.LABEL)
					 {
						 System.out.println("Label"+c.getContents());
					 }
					 
					 if(t==CellType.NUMBER)
					 {
						 System.out.println("Number"+c.getContents());
					 }
				 }
				 
			 }
		
		
	}
	  public static int getRow(String r)
	  {
		  try
		   {
		      int x = Integer.parseInt(r);
		      return x+1;
		   }
		   catch( Exception e)
		   {
		      return 0;
		   }
	  }
	  
	  public static int getColumn(String r)
	  {
		  switch(r)
		  {
		  	case "$": return 1;
		  	case "id": return 2;
		  	case "+": return 3;
		  	case "*": return 4;
		  	case "<": return 5;
		  	case ">": return 6;
		  	case "!=": return 7;
		  	case "==": return 8;
		  	case "=": return 9;
		  	case "(": return 10;
		  	case ")": return 11;
		  	case "{": return 12;
		  	case "}": return 13;
		  	case "while": return 14;
		  	case ";": return 15;
		  	case "S": return 16;
		  	case "A": return 17;
		  	case "W": return 18;
		  	case "E": return 19;
		  	case "T": return 20;
		  	case "F": return 21;
		  	case "R": return 22;
		  	case "C": return 23;
		  }
		  return 0;
	  }
	  
	  
	  


}

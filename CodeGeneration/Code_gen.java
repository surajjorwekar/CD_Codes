import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Code_Gen {

	public static String nextUse[][] = new String[][] {{"a","y","2"},{"b","y","1"},{"c","y","2"},{"d","y","4"},{"t","y","3"},{"u","y","5"},{"v","y","5"}};
	public static String AD[][] = new String[][] {{"a","-1"},{"b","-1"},{"c","-1"},{"d","-1"},{"t","-1"},{"u","-1"},{"v","-1"}};
	public static String RD[] = new String[] {"-1","-1","-1"};
	public static int nextUseCount=7,ADcount=7,RDcount=3;
    public static int flag=0;//flag 1=already present, flag 2= empty assigned, flag 3=not next use var, flag =4 spill
	
	public static void main(String[] args) throws FileNotFoundException {
	int count=1,reg1=0,reg2=0,regRslt=0;
	String instruction = null;
	String opr1=null,opr2=null,rslt=null;
	File fp = new File("input.txt");
	Scanner scr = new Scanner(fp);
	System.out.println("Input code:");
	while(scr.hasNextLine())
	{
		System.out.println(scr.nextLine());
	}
	
	//code generation
	scr = new Scanner(fp);
        
	System.out.println("\nOutput code:");
	while(scr.hasNextLine())
	{
		int reg;
		instruction = scr.nextLine();
//		System.out.println("\n"+instruction);
		if(instruction.length()==3 && instruction.contains("="))
		{
			opr1 = instruction.substring(2,3);
			rslt = instruction.substring(0,1);
			reg1 =getReg(opr1,count,instruction);
			change(reg1,opr1);
			for(int i=0;i<ADcount;i++)
			{
				if(AD[i][0].equals(rslt))
				{
					AD[i][1]=Integer.toString(reg1);
				}
			}
			store(rslt, "R"+reg1);
		}
		else
		{
			if(instruction.contains("+"))
			{
				opr1 = instruction.substring(2,3);
				opr2 = instruction.substring(4,5);
				rslt = instruction.substring(0,1);
				reg1 =getReg(opr1,count,instruction);
				change(reg1,opr1);
				reg2 =getReg(opr2,count,instruction);
				change(reg2,opr2);
				regRslt = getReg(rslt, count, instruction);
				add("R"+reg1,"R"+reg2,"R"+regRslt);
				for(int i=0;i<ADcount;i++)
				{
					if(AD[i][0].equals(rslt))
					{
						AD[i][1]=Integer.toString(regRslt);
					}
				}
			}
			else if(instruction.contains("-"))
			{
				opr1 = instruction.substring(2,3);
				opr2 = instruction.substring(4,5);
				rslt = instruction.substring(0,1);
				reg1 =getReg(opr1,count,instruction);
				change(reg1,opr1);
				reg2 =getReg(opr2,count,instruction);
				change(reg2,opr2);
				regRslt = getReg(rslt, count, instruction);
				sub("R"+reg1,"R"+reg2,"R"+regRslt);
				for(int i=0;i<ADcount;i++)
				{
					if(AD[i][0].equals(rslt))
					{
						AD[i][1]=Integer.toString(regRslt);
					}
				}
			}
			
		}
		
		count++;
	}
	
}

// register allocation
public static int getReg(String oprand, int currCount, String inst)
{
	int i=-1;
	for(i=0;i<RDcount;i++)
	{
		if((RD[i].toString()).equals(oprand.toString()))
			{
			flag =1;
			return i;
			}
	}
	for(i=0;i<RDcount;i++)
	{
		if((RD[i].toString()).equals("-1"))
			{
				RD[i]=oprand;
			    for(int j=0;j<ADcount;j++)
			    {
			    	if(AD[j][0].equals(oprand))
			    		{AD[j][1]=Integer.toString(i);}
			    }
			    flag =2;
				return i;
			}
	}
	for(i=0;i<RDcount;i++)
	{
		for(int j=0;j<nextUseCount;j++)
		{
			if(RD[i].equals(nextUse[j][0]) && nextUse[j][1].equals("y") && Integer.parseInt(nextUse[j][2])<currCount)
				{
				RD[i]=oprand;
				for(int p=0;p<ADcount;p++)
			    {
			    	if(AD[p][0].equals(RD[i]))
			    		{
			    		AD[p][1]=Integer.toString(i);
			    		}
			    	if(AD[p][0].equals(nextUse[j][0]))
		    		{
		    		AD[p][1]="-1";
		    		}
			    }
				flag =3;
				return i;
				}
		}
	}
	
	for(i=0;i<RDcount;i++)
	{
		if(!inst.contains(RD[i]))
		{	for(int j=0;j<ADcount;j++)
		    {
		    	if(AD[j][0].equals(RD[i]))
		    		{
		    		AD[j][1]="-1";
		    		return i;
		    		}
		    }
		    flag =4;
		}
	}
	return i;
	
	
}

public static void load(String one, String two)
{
	System.out.println("\nLD "+one+" , "+two);
//	print();
}
public static void sub(String one, String two, String three)
{
	System.out.println("\nSUB "+one+" , "+two+" , "+three);
//	print();
}
public static void add(String one, String two, String three)
{
	System.out.println("\nADD "+one+" , "+two+" , "+three);
//	print();
}
public static void store(String one, String two)
{
	System.out.println("\nST "+one+" , "+two);
//	print();
}
public static void print()
{
	System.out.println("\nRD");
	for(int i=0;i<RDcount;i++)
	{
		System.out.print(RD[i]+"  ");
	}
	System.out.println("\n AD");
	for(int i=0;i<ADcount;i++)
	{
		System.out.print(AD[i][0]+"  "+AD[i][1]+" ; ");
	}
}
public static void change(int num,String opr)
{
	if(flag == 2)
	{
		for(int i=0;i<ADcount;i++)
		{
			if(AD[i][0].equals(opr))
				AD[i][1]=Integer.toString(num);
		}
		load("R"+num,opr);
	}
	else if(flag ==3)
	{
		for(int i=0;i<ADcount;i++)
		{
				if(AD[i][0].equals(RD[num]))
					{
					AD[i][1]="-1";
					RD[num]=opr;
					}
		}
		for(int i=0;i<ADcount;i++)
		{
				if(AD[i][0].equals(RD[num]))
					{
					AD[i][1]=Integer.toString(num);
					}
		}
		load("R"+num,opr);
	}
	else if(flag ==4)
	{
		for(int i=0;i<ADcount;i++)
		{
				if(AD[i][0].equals(RD[num]))
					{
					store(AD[i][0], "R"+num);
					AD[i][1]="-1";
					RD[num]=opr;
					}
		}
		for(int i=0;i<ADcount;i++)
		{
				if(AD[i][0].equals(RD[num]))
					{
					AD[i][1]=Integer.toString(num);
					}
		}
		load("R"+num,opr);
	}
}
}


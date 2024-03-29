package senior_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class senior_project 

{
	static String college;
	static String university;
	
	static Scanner scan = new Scanner(System.in);
	
	//This block takes college input from user:
	public static void collegeinput()
	{
		System.out.println("\nWhat college you want to attend?");
		university = scan.nextLine();
	}
	
	//This block display the final output:
	public static void output(String university, double GPA, int SAT, int x) throws BiffException, IOException
	{
		//Establish connection between JAVA and Excel file using JXL API:
		File senior_project_database = new File("/Users/pealhasan/Documents/Excel/Senior_Project_database.xls");
		Workbook mydatabase = Workbook.getWorkbook(senior_project_database);
		Sheet mysheet = mydatabase.getSheet("Sheet1");
		
		for(int i=0; i<x; i++)
		{
			Cell college_name = mysheet.getCell(0, i);
			college = college_name.getContents();
			
			if(college.equals(university))
			{
				Cell CGPA = mysheet.getCell(3, i);
				
				String GPA1 = CGPA.getContents();
				double doublegpa = Double.parseDouble(GPA1);	
				
				Cell SAT_score = mysheet.getCell(4, i);
				String SAT1 = SAT_score.getContents();
				double doublesat = Double.parseDouble(SAT1);
				
				if(GPA >= doublegpa && SAT >= doublesat)
				{
					System.out.println("\nCongratulaiton! You have good approval odds for "+ college);
					break;
				}
				
				if(GPA < doublegpa && SAT >= doublesat)
				{
					System.out.println("\nYou have poor approval odds!\nYou have to improve your GPA");
					break;
				}
				
				if(GPA >= doublegpa && SAT < doublesat)
				{
					System.out.println("\nYou have poor approval odds!\nYou have to improve your SAT score");
					break;
				}
				
				if(GPA < doublegpa && SAT < doublesat)
				{
					System.out.println("\nYou have poor approval odds!\nYou have to improve both GPA and SAT score");
					break;
				}
			}

		}
	}
	
	//Main activity block:
	public static void main(String[] args) throws BiffException, IOException 
	
	{
		        //Establish connection between JAVA and Excel file using JXL API:
				File senior_project_database = new File("/Users/pealhasan/Documents/Excel/Senior_Project_database.xls");
				Workbook mydatabase = Workbook.getWorkbook(senior_project_database);
				Sheet mysheet = mydatabase.getSheet("Sheet1");
				
				//Initial Variables:
				double GPA = 0;
				int SAT;
				int option;
				double tempgpa;
				
				//Initial List:
				ArrayList<String> collegelist = new ArrayList<String>();
				
				//Add college name to list:
				int x = mysheet.getRows();
				for(int i=0;i < x; i++)
				{
					Cell CGPA = mysheet.getCell(0, i);
					String collegename = CGPA.getContents();
					collegelist.add(collegename);
				}
				System.out.println("These are the college list:");
				
				//This will display the list in a row
				collegelist.forEach(System.out::println); 
				
				//User input:
				System.out.println("\nWhat's your GPA input type?\nPress 1 for 100.0 scale\nPress 2 for 4.0 scale");
				option = scan.nextInt();
				
				if(option == 1)
				{
					for(;;)
					{
						System.out.println("\nWhat's your GPA (100.0 scale)");
						GPA = scan.nextDouble();
						if(GPA >= 0)
						{
							break;
						}
						System.out.println("Error! Please type in the correct GPA");
					}
					
				}
				
				if(option == 2)
					{
						for(;;)
						{
							System.out.println("\nWhat's your GPA (4.0 scale)");
							tempgpa = scan.nextDouble();
							GPA = (tempgpa/4)*100;
							if(GPA >= 0)
							{
								break;
							}
							System.out.println("Error! Please type in the correct GPA");
						}		
					}
				
				for(;;)
				{
					System.out.println("\nWhat's your SAT score:");
					SAT = scan.nextInt();
					if(SAT <= 1600 && SAT >= 400)
					{
						break;
					}
					System.out.println("Error! Please type in the correct SAT score");
				}
				
				scan.nextLine();
				
				//Calling the collegeinput() block:
				collegeinput();
				
				//Calling the output() block:
				output(university, GPA, SAT, x);
				
				while(!college.equals(university))
		        {
					System.out.println("Error college name! Check your spelling");
					//Calling the collegeinput() block:
					collegeinput();
					//Calling the output() block:
					output(university, GPA, SAT, x);
			    }
				
				//Disconnect the database after completeing tasks:
				mydatabase.close();	
		
	}

}

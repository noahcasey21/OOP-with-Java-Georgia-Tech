/*
 * Concepts learned: basic syntax, operators, declarations, class instantiation, 
 *                  strings, input/output
 * 
 * Comprehensive Problem: pay report fomatter. Take in pay infomation and print a 
 *                        report-like document to output stream.
 */

import java.util.Scanner;
import java.text.NumberFormat;

class module_2 {
    public static void main(String[] args)
    {
        //some initializations
        int weeks = 52;
        Scanner input = new Scanner(System.in);

        //scanning in values
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your salary: ");
        int salary = input.nextInt();
        System.out.print("Enter your expenditures: ");
        int expenditure = input.nextInt();
        
        //more initializations
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int nameLength = name.length();
        int space = 10 - nameLength;
        String sp1, sp2;

        //spacing math
        sp1 = " ".repeat(space / 2);
        sp2 = " ".repeat(space - sp1.length());

        //print report
        System.out.printf("----------%s%s%s----------\n---------Salary Report--------\n", sp1, name, sp2);
        System.out.println("Salary: " + formatter.format(salary));
        System.out.println("Expenditures: " + formatter.format(expenditure) + "\n");
        System.out.println("Weekly Salary: " + formatter.format((double)salary / weeks));
        System.out.println("Weekly Expenditures: " + formatter.format((double) expenditure / weeks));
        System.out.print("-----------------------------");
    }
}
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //EXAMPLE 1: No editing for entering 0
        // Prompt the user to enter two integers
      	System.out.print("Enter two integers: ");
        int number1 = input.nextInt();
	    int number2 = input.nextInt();
	    System.out.println(number1 + " / " + number2 + " is " + (number1 / number2));
    
        // Add a simple test to verify denominator is not 0
        // Prompt the user to enter two integers
      	System.out.print("Enter two integers: ");
        number1 = input.nextInt();
	    number2 = input.nextInt();
        if (number2 != 0)
	        System.out.println(number1 + " / " + number2 + " is " + (number1 / number2));
        else
            System.out.println("Cannot divide by 0");

        //EXAMPLE 2: Out of Bounds 
        int[] myNumbers = {1, 2, 3};
        //System.out.println(myNumbers[10]);

        // use try/catch/finally to test
        try{
            System.out.println(myNumbers[10]);
        }
        catch (Exception e){
            System.out.println("oops - something went wrong");
        }
        finally {
            System.out.println("The 'try catch' is finished.");
        }
        System.out.println("statement after catch");


        //EXAMPLE 3: programmer throw from function
        //           add try/catch
        try{
            checkAge(18);
            checkAge(15);
        }
        catch (Exception e){
            System.out.println("oops - something went wrong: \n" + e);
        }

        input.close();

        //EXAMPLE 4: File & Programmer Defined Exceptions

        ArrayList<Person> people = new ArrayList<>();

        //writing this outside a try block will produce a compiler error
        //Scanner scan2 = new Scanner(new File("peo.txt"));
 
        try{
            //writing this way int the try block 'handles' the FileNotFoundException
            //File inFile = new File("peoe.txt");
            Scanner scanner = new Scanner(new File("people.txt"));

            String inputLine;
            while (scanner.hasNextLine()){
                inputLine = scanner.nextLine();
                String[] tokens = inputLine.split(",");
                try{
                    int tempAge = Integer.parseInt(tokens[3]);
                    char tempType = tokens[0].toUpperCase().charAt(0);
                    if (tempType != 'P' && tempType != 'T' && tempType != 'E' && tempType != 'S')
                        throw new TypeException(tempType);
                    //add test & throw exception for age (age > 0 and < 110)


                    Person tempP = new Person(tokens[0].charAt(0), tokens[1], tokens[2], tempAge);
                    people.add(tempP);
                }
                //add catch for TypeException

                catch(NumberFormatException e){
                    System.out.println("Number format exception: " + e.getMessage());
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Not enough tokens in input file: " + e.getMessage());
                }
                catch(Exception e){
                    System.out.println("Oops...: " + e.getMessage());
                }
            }
            System.out.println("closing scanner");
            scanner.close();
        }
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }


        System.out.println("printing people");
        for (Person p : people){
            System.out.printf("%-4c %-15s %-15s %5d \n",p.type, p.fname, p.lname, p.age);
        }

         //EXAMPLE 5: Nested method calls
        try{
            method1("0");
            method1("-1");
            method1("abc");
        }
        catch(Exception e){
            System.out.println("Main: error discovered in main: " + e);
        }

    }

    static void checkAge(int age) {
        if (age < 18) 
            throw new ArithmeticException("Access denied - You must be at least 18 years old.");
        else 
            System.out.println("Access granted - You are old enough!");
    }

    static void method1(String str) {
        try{
            int value = Integer.parseInt(str);
            if (value < 0) 
                throw new Exception("Method 1: Value entered is less than 0.");
            method2(value);
            System.out.println("Method 1: after method 2 call. " );
        }
        catch (Exception e){
            System.out.println("Method 1: issue found: " + e);
            //e.printStackTrace();
        }

    }
    static void method2(int value) {
        try{
            if (value == 0) 
                throw new ArithmeticException("Method 2: Value entered is 0");
            System.out.println("5 / " + value + " = " + (5/value));
        }
        catch(ArithmeticException e){
            System.out.println("Method 2: issue found: " + e);            
        }
    }
}


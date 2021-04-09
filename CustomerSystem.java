/*
 * Date: April 1, 2021
 * Name: Saad Naeem & Johnny He
 * Teacher: Mr.Ho
 * Description: Luhn Algorithm Assignment 
 * */
// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray

import java.util.Scanner;
// More packages may be imported in the space below
// Import packages
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.*; 


class CustomerTest{
    public static void main(String[] args) throws FileNotFoundException{
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
        // Initialize variables
        String infoCombine = "";
        int customerId = 1;
        String fileName = "";
        String customerFileLocation = "";
        String customerFile = "";

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                infoCombine = enterCustomerInfo(customerId);
                customerId++;
            }
            // If user choose Generate Customer data file
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return

                // If it was user's first time generating a CSV file
                if(customerId==2) {
                    // Program prompt user for File name and File location
                    System.out.print("Enter File Name: ");
                    fileName = reader.nextLine();

                    System.out.println("Enter File Location: ");
                    System.out.println("Ex: C:\\Users\\he123\\");
                    customerFileLocation = reader.nextLine();

                    // Combine name and location to create a functional file pathway
                    customerFile = customerFileLocation + fileName + ".csv";
                }
                // Call generateCustomerDataFile(infoCombine, customerFile) method
                generateCustomerDataFile(infoCombine, customerFile);
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));    // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("\nCustomer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }


    /*
     * Gets the user to input information regarding he/she - Name, City, Postal Code and Credit Card
     * 
     * @param blankString - An empty string 
     * @return stringCombine - All the user input's combined (name, city, postal code, credit card)
    */
    public static String enterCustomerInfo(int customerId) {
        // Add Scanner To The Method
        Scanner reader = new Scanner(System.in);

        // Ask user for required information (name, city, postal code, credit card #)
        System.out.print("First Name: ");
        String firstName = reader.nextLine();
        System.out.print("Last Name: ");
        String lastName = reader.nextLine();
        String fullName = firstName + " " + lastName;
        System.out.print("City: ");
        String userCity = reader.nextLine();
        System.out.print("Postal Code (Avoid Spaces): ");
        String postalCode = reader.nextLine();
        System.out.print("Credit Card Number (Avoid Spaces): ");
        String creditNumber = reader.nextLine();

        // Give them a chance to review the information they inputted and make changes if needed
        System.out.println("\nThanks for filling out the information. Before we begin to validate we would like to confirm if");
        System.out.println("the correct information was inputted. Please take a chance to review");
        System.out.println("Name: " + fullName);
        System.out.println("City: " + userCity);
        System.out.println("Postal Code: " + postalCode);
        System.out.println("creditNumber: " + creditNumber);
        System.out.println("Type 1 if the correct information was inputted. Type 2 if you would like to retype");
        int userChoice = reader.nextInt();
        reader.nextLine();
        
        // If user chooses to reinput required information
        while (userChoice == 2) {
            System.out.print("First Name: ");
            firstName = reader.nextLine();
            System.out.print("Last Name: ");
            lastName = reader.nextLine();
            System.out.print("City: ");
            userCity = reader.nextLine();
            System.out.print("Postal Code (Avoid Spaces): ");
            postalCode = reader.nextLine();
            System.out.print("Credit Card Number (Avoid Spaces): ");
            creditNumber = reader.nextLine();

            System.out.println("\nThanks for filling out the information. Before we begin to validate we would like to confirm if");
            System.out.println("the correct information was inputted. Please take a chance to review");
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("City: " + userCity);
            System.out.println("Postal Code: " + postalCode);
            System.out.println("Credit Card Number: " + creditNumber);
            System.out.println("Type 1 if the correct information was inputted. Type 2 if you would like to retype");
            userChoice = reader.nextInt();
            reader.nextLine();
        }

        // If postal code is invalid, prompt user to reinput postal code
        while (validatePostalCode(postalCode) == false) {
            System.out.println("\nThe postal code you inputted is invalid. Please try again!");
            System.out.print("Postal Code (Avoid Spaces): ");
            postalCode = reader.nextLine();
        }

        // If credit card is invalid, make them reinput valid credit card #
        while (validateCreditCard(creditNumber) == false) {
            System.out.println("\nThe credit card # you inputted is invalid. Please try again!");
            System.out.print("Credit Card Number (Avoid Spaces): ");
            creditNumber = reader.nextLine();
        }
        
        // Combine the user information into one string and return it
        String stringCombine = customerId + "," + firstName + "," + lastName + "," + userCity + "," + postalCode + "," + creditNumber;

        // Can't close scanner as it closes all scanners and error will arise

        // Return string
        return stringCombine;
    }


    /*
     * Validate the postal code inputted through the use of an algorithm which determines if it's valid or invalid and sends it back to enterCustomerInfo() method
     * 
     * @param postalCode - String of user's postal code
     * @exception  e - Invalid file input
     * @return - True or false if postal code is valid 
    */
    public static boolean validatePostalCode(String postalCode){

        // Declare variables that will be used throughout validation
        String fileLocation = "";
        String validPostal = "";

        // Add Scanner To The Method
        Scanner input = new Scanner(System.in);
        
        // Gets the first 3 characters from user's postal code 
        String userPostal = postalCode.substring(0, 3);

        // Prompt user to input his/her postal_codes.csv file location
        System.out.println("\nEnter your postal_codes.csv file location: ");
        System.out.println("Ex: C:\\Users\\he123\\Java\\-Luhn-Algorithm-Assignment\\postal_codes.csv");
        fileLocation = input.nextLine();
   
        try {
            // Initailize user's postal_codes.csv file     
            File file = new File(fileLocation);
            // Intialize scanner to scan user's file
            Scanner scan = new Scanner(file);

            // Checks the next line of the file
            while(scan.hasNextLine()){
                // Takes the first 3 character (valid postal code) from the file
                String line = scan.nextLine();
                validPostal = line.substring(0, 3);
                // If user postal code is equals to valid postal code
                if(userPostal.equals(validPostal)){
                    // Return true
                    scan.close();
                    return true;
                }
            }
            // After scan through the entire file, if there is no match valid postal code, then return false
            scan.close();
            return false;
        }
        // Return false if invalid postal_codes.csv file input
        catch (Exception e) {
            return false;
        }
        // Can't close input scanner as it cause error
    }


    /*
     * Validate the credit card number inputted through the use of an algorithm which determines if it's valid or invalid and sends it back to enterCustomerInfo() method
     * 
     * @param creditNumber - String of user's credit card number
     * @exception  e - if it disrupts flow of code and characters arent a number, it returns boolean false
     * @return - True or false if card is valid 
    */
    public static boolean validateCreditCard(String creditNumber){
        
        // Declare variables that will be used throughout validation
        int evenSum = 0;
        int totalEvenSum = 0;
        int oddSum = 0;
        int totalSum = 0;

        // Determine number of digits in credit card
        int creditLen = creditNumber.length();

        // Credit length must be greater than 9 for it to be valid
        if (creditLen >= 9) {

            // Reverse the credit card number
            String creditCardReversed = "";
            for (int i = 0; i < creditLen; i++) {
                creditCardReversed += creditNumber.charAt((creditLen-1) - i);
            }
            
            // Complete odd sum of reversed credit card number
            for (int i = 0; i < creditLen; i += 2) {

                // Try to add the odd numbers within reversed credit card number
                try {
                    oddSum += Integer.parseInt(creditCardReversed.substring(i, i + 1));
                }

                // Return false if any character isn't a number
                catch (Exception e) {
                    return false;
                }
            }

            // Complete even sum of reversed credit card number
            for (int i = 1; i < creditLen; i += 2) {

                // Try to add the even numbers within reversed credit card number
                try {
                    evenSum += Integer.parseInt(creditCardReversed.substring(i, i + 1));
                }

                // Return false if any character isn't a number
                catch (Exception e) {
                    return false;
                }

                // Multiply the integer by two
                evenSum *= 2;

                // When intenger is greater than 9, add each individual together
                if (evenSum > 9) {
                    evenSum = (evenSum % 10) + 1;
                }

                totalEvenSum += evenSum;
                evenSum = 0;
            }

            // Total both the even and odd sum
            totalSum = totalEvenSum + oddSum;

            // Sum must end with zero to be valid. If not return false
            if (totalSum % 10 == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        // When credit lenght is less than 9, return false
        else if (creditLen < 9) {
            return false;
        }

        // If algorithm doesn't come out to be true, return false
        return false;   
    }


    /*
     * Generate CSV file and store all customer information
     * 
     * @param stringCombine and customerFile - String of customer information and String of user's file name and location
     * @return void
    */
    public static void generateCustomerDataFile(String stringCombine, String customerFile) {
        try {
            // Intitalize file writer
            FileWriter fw = new FileWriter(customerFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw);

            // Append new customer information
            writer.println(stringCombine);
            writer.flush();
            writer.close();

            // Indicate the program had successfully saved changes and provide the CSV file location
            System.out.println("\nChanges Saved");
            System.out.println("File Location: " + customerFile);
        }
        // Output false if invalid customer file input 
        catch (Exception e) {
            System.out.println("Error");
        }
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}

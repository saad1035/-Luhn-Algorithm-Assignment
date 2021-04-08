/*
 * Date: April 1, 2021
 * Name: Saad Naeem & Johnny He
 * Teacher: Mr.Ho
 * Description: Luhn Algorithm Assignment 
 * */
// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
        String infoCombine;
        int customerId = 1;

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                infoCombine = enterCustomerInfo(customerId);
                customerId++;
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
     * Gets the user to input information regarding him - Name, City, Postal Code and Credit Card //#endregion
     * 
     * @param blankString - An empty string 
     * @return stringCombine - All the user input's combined (name, city, postal code, credit card)
    */
    public static String enterCustomerInfo(int customerId) {
        // Add Scanner To The Method
        Scanner reader = new Scanner(System.in);

        // Ask user for required information (name, city, postal code, credit card #)
        System.out.println("First Name: ");
        String firstName = reader.nextLine();
        System.out.println("Last Name: ");
        String lastName = reader.nextLine();
        String fullName = firstName + " " + lastName;
        System.out.println("City: ");
        String userCity = reader.nextLine();
        System.out.println("Postal Code (Avoid Spaces): ");
        String postalCode = reader.nextLine();
        System.out.println("Credit Card Number (Avoid Spaces): ");
        String creditNumber = reader.nextLine();

        // Give them a chance to review the information they inputted and make changes if needed
        System.out.println("Thanks for filling out the information. Before we begin to validate we would like to confirm if");
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
            System.out.println("First Name: ");
            firstName = reader.nextLine();
            System.out.println("Last Name: ");
            lastName = reader.nextLine();
            System.out.println("City: ");
            userCity = reader.nextLine();
            System.out.println("Postal Code (Avoid Spaces): ");
            postalCode = reader.nextLine();
            System.out.println("Credit Card Number (Avoid Spaces): ");
            creditNumber = reader.nextLine();

            System.out.println("Thanks for filling out the information. Before we begin to validate we would like to confirm if");
            System.out.println("the correct information was inputted. Please take a chance to review");
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("City: " + userCity);
            System.out.println("Postal Code: " + postalCode);
            System.out.println("Credit Card Number: " + creditNumber);
            System.out.println("Type 1 if the correct information was inputted. Type 2 if you would like to retype");
            userChoice = reader.nextInt();
            reader.nextLine();
        }

        // If credit card is invalid, make them reinput valid credit card #
        while (validateCreditCard(creditNumber) == false) {
            System.out.println("The credit card # you inputted is invalid. Please try again!");
            creditNumber = reader.nextLine();
        }
        
        // Combine the user information into one string and return it
        String stringCombine = firstName + " " + lastName + " " + userCity + " " + postalCode + " " + creditNumber;

        // Close Scanner
        reader.close();

        // Return string
        return stringCombine;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validatePostalCode(){
    }
    /*
     * Validate the credit card number inputted through the use of an algorithm which determines if it's valid or invalid and sends it back to enterCustomerInfo() method
     * 
     * @param creditNumber - String of user's credit card number
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
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}
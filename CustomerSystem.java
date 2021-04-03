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
        String blankString = "";

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                String infoCombine = enterCustomerInfo(blankString);
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
    public static String enterCustomerInfo(String blankString) {
        Scanner reader = new Scanner(System.in);

        System.out.println("First Name: ");
        String firstName = reader.nextLine();
        System.out.println("Last Name: ");
        String lastName = reader.nextLine();
        System.out.println("City: ");
        String userCity = reader.nextLine();
        System.out.println("Postal Code: ");
        String postalCode = reader.nextLine();
        System.out.println("Credit Card Number: ");
        String creditNumber = reader.nextLine();

        System.out.println("Thanks for filling out the information. Before we begin to validate we would like to confirm if");
        System.out.println("the correct information was inputted. Please take a chance to review");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("City: " + userCity);
        System.out.println("Postal Code: " + postalCode);
        System.out.println("creditNumber: " + creditNumber);
        System.out.println("Type 1 if the correct information was inputted. Type 2 if you would like to retype");
        int userChoice = reader.nextInt();
        reader.nextLine();
        

        while (userChoice == 2) {
            System.out.println("First Name: ");
            firstName = reader.nextLine();
            System.out.println("Last Name: ");
            lastName = reader.nextLine();
            System.out.println("City: ");
            userCity = reader.nextLine();
            System.out.println("Postal Code: ");
            postalCode = reader.nextLine();
            System.out.println("Credit Card Number: ");
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

        String stringCombine = firstName + lastName + userCity + postalCode + creditNumber;
        
        reader.close();

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
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validateCreditCard(){
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
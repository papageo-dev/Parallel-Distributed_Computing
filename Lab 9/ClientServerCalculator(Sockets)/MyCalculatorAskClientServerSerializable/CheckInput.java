package MyCalculatorAskClientServerSerializable;

import java.util.StringTokenizer;

public class CheckInput {
	
	    // Function to check input and build the final answer SMS to sent
	 	public boolean isValid(String theInput) {
	 			
	 		boolean valid = false;
	 		String wrongInputMsg = "????? ???????. ???????? ??????????? ????. ??????: ";
	 			
	 		// Check if input have the necessary elements or exit symbol(!)
	 		if (countWords(theInput) != 3 || theInput.equals("!")) {
	 			System.out.println(wrongInputMsg + "001. ?? ?????? ?????.");
	 			return valid;
	 		}
	 			
	 		//Input split - Split message content
	 		String[] splited = theInput.trim().split("\\s+");
	 		// 1. Number 1
	 		String num1 = splited[0];
	 		// 2. Operation
	 		String operation = splited[1];
	 		// 3. Number 2
	 		String num2 = splited[2];
	 			
	 		// Check if input is valid(2 numbers && 1 operation)
	 		if ((isNumeric(num1) && isNumeric(num2)) && 
	 				(operation.equals("+") || operation.equals("-") || 
	 						operation.equals("*") || operation.equals("/"))) { 	
	 			valid = true;
	 		} else {
	 			valid = false;
	 			// Wrong input message
	 			System.out.println(wrongInputMsg + "002. ?? ?????? ?? ???????? 2 ????????? ???????? ??? ???? ???????(+,-,*,/) ??? ?? ????? ? ?????.");
	 		}
	 					
	 		return valid;	
	 	}
	 		
	 		
	 	// Function to count total number of words in the string
	 	private int countWords(String str) {
	 				
	 		// Check if the string is null or empty then return zero
	 		if (str == null || str.isEmpty()) {
	 			return 0;
	 		}
	 				
	 		// Create a StringTokenizer with the given string passed as a parameter
	 		StringTokenizer tokens = new StringTokenizer(str);
	 				
	 		// Return the number of words in the given string using countTokens() method
	 		return tokens.countTokens();
	 	}
	 		
	 		
	 	// Function to check if input is number
	 	private boolean isNumeric(String string) {
	 		
	 		int intValue;
	 				
	 		if (string == null || string.equals("")) {
	 		    return false;
	 		}
	 		    
	 		try {
	 		    intValue = Integer.parseInt(string);
	 		    return true;
	 		} catch (NumberFormatException e) {
	 		}
	 		return false;
	 	}

}
